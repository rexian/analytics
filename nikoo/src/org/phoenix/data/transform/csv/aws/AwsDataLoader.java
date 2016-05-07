/**
 * 
 * Copyright 2016 Surajit Paul
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.phoenix.data.transform.csv.aws;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.phoenix.data.axon.cleansing.AxonDataCleanser;
import org.phoenix.data.axon.cleansing.CleansingFactory;
import org.phoenix.data.axon.util.AxonConstants;

/**
 * 
 * @author surajitpaul
 *
 */
public class AwsDataLoader implements CleansingConstants {

	static Logger log = Logger.getLogger(AwsDataLoader.class.getName());

	public static void main(String[] args) throws Exception {
		MDC.put("system.ip", getSystemIP());
		MDC.put("bundle.name", "EDPProduct");
		MDC.put("bundle.version", "1.0");
		AmazonS3 client = getAmazonClient();

		List<String> inputFiles = listS3Objects(client);
		for(String filename: inputFiles){			
			DataCleanser cleanser = CleansingFactory.getCleanser(filename);
			if(cleanser != null){
				cleanser.cleanseData(client);
			}		
		}
		disableS3Trigger(client);
	}

	/**
	 * 
	 * @param client
	 * @return
	 */
	public static List<String> listS3Objects(AmazonS3 client){
		List<String> inputFiles = new ArrayList<String>();              
		client.setEndpoint(S3_ENDPOINT);
		String prefix = PREFIX + INPUT_KEY;		
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
		.withBucketName(BUCKET_NM)
		.withPrefix(prefix)
		.withDelimiter("/")
		.withMarker("/");

		ObjectListing objectListing;
		objectListing = client.listObjects(listObjectsRequest);
		List<S3ObjectSummary> summaries = objectListing.getObjectSummaries();
		for(S3ObjectSummary summary: summaries){			
			File f = new File(summary.getKey());
			System.out.println("File name - " + FilenameUtils.getBaseName(f.getName()));
			inputFiles.add(FilenameUtils.getBaseName(f.getName()));
		}		
		return inputFiles;
	}

	public void getAwsFileContent(String file) throws IOException{
		String prefix = PREFIX + INPUT_KEY + file + ".csv";		
		log.info("Prefix - " + prefix);
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig);               
		client.setEndpoint(S3_ENDPOINT);
		S3Object object = client.getObject(new GetObjectRequest(BUCKET_NM, prefix));
		BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public S3Object getAxonFileContent(String file) throws IOException{		
		String prefix = this.getS3Prefix(file); 				
		log.info("Prefix - " + prefix);				
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig);               
		client.setEndpoint(S3_ENDPOINT);		
		S3Object object = client.getObject(new GetObjectRequest(BUCKET_NM, prefix));
		return object;		
	}


	/**
	 * 
	 * @param events
	 */
	public void persistCleanData(List<String> rows, String key){
		if(rows == null || rows.size() <= 0){
			return;
		}				
		StringBuffer rowdata = new StringBuffer();
		for(String row: rows){
			rowdata.append(row);
			rowdata.append("\n");
		}

		String dataset = rowdata.toString();
		String prefix = PREFIX + OUTPUT_KEY + key + ".csv";		
		log.info("[INFO] : JSON data from CSE Events has been written on s3 bucket " + prefix);
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig);               
		client.setEndpoint(S3_ENDPOINT);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(dataset.getBytes().length);

		try {
			byte[] content = dataset.getBytes();
			ByteArrayInputStream input = new ByteArrayInputStream(content);
			client.putObject(BUCKET_NM, prefix, input, omd); 	
			input.close();
		} catch (IOException e) {			
			log.error("Axon data write to s3 failed: " + e.getMessage());
		}
	}

	/**
	 * 
	 * @return system IP address String
	 */
	public static String getSystemIP(){
		String ip = "0.0.0.0";
		try {
			ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("System IP not available" + e.getMessage());
		}
		return ip;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public String getS3Prefix(String file){
		if(file == null || file.length() == 0){
			return null;
		}
		return PREFIX + INPUT_KEY + file + ".csv";
	}

	private static AmazonS3 getAmazonClient(){
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig); 
		return client;
	}
	
	private static boolean enableS3Trigger(AmazonS3 client){
		boolean enabled = false;
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		String prefix = PREFIX + TRIGGER_KEY + "_SUCCESS";		
		PutObjectResult result = client.putObject(new PutObjectRequest(BUCKET_NM, prefix, emptyContent, metadata));
		log.info("Triggering file added - " + result.getETag());
		return enabled;
	}
	
	private static void disableS3Trigger(AmazonS3 client){		
		String prefix = PREFIX + TRIGGER_KEY + "_SUCCESS";		
		client.deleteObject(new DeleteObjectRequest(BUCKET_NM, prefix));
		log.info("Trigger file deleted.");		
	}
	
	private static boolean enableHdfsTrigger(AmazonS3 client){
		boolean enabled = false;
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		String prefix = PREFIX + TRIGGER_KEY + "_SUCCESS";		
		PutObjectResult result = client.putObject(new PutObjectRequest(BUCKET_NM, prefix, emptyContent, metadata));
		log.info("Triggering file added - " + result.getETag());
		return enabled;
	}
	
	private static void disableHdfsTrigger(AmazonS3 client){		
		String prefix = PREFIX + TRIGGER_KEY + "_SUCCESS";		
		client.deleteObject(new DeleteObjectRequest(BUCKET_NM, prefix));
		log.info("Trigger file deleted.");		
	}

}
