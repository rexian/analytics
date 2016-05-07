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

package org.phoenix.data.transform.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.phoenix.adp.auonline.unmarshaller.AUOnlineUnmarshaller;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 09.25.2015
 *
 */
public class DEFHttpClient extends OnlineConstants1{

	/**
	 * default constructor
	 */
	public DEFHttpClient() {
		super();		
	}

	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * MAIN()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		DEFHttpClient http = new DEFHttpClient();		
		System.out.println("\n Send Http POST request");
//		Set<String> keys = urlMap.keySet();
//		Iterator<String> urlKeys = keys.iterator();
//		while(urlKeys.hasNext()){
//			String key = urlKeys.next();
		String key = args[0];
			http.sendPost(key, urlMap.get(key));
			System.out.println("=================================================================================================");
			System.out.println("Lanyon API call for data source: " + key + " has completed.");
			System.out.println("=================================================================================================");
//		}
	}


	/**
	 * HTTP POST request
	 * @param key
	 * @param url
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private void sendPost(String key, String url) throws Exception {

		System.out.println("URL" + url);

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);


		// add header
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
		

		post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));
		post.setHeader("Content-Type", "text/xml; charset=UTF-8");
		post.setHeader("Accept-Encoding","UTF-8");

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
				response.getStatusLine().getStatusCode());
		System.out.println("Protocol version : " + post.getProtocolVersion());
		System.out.println("Content Type : " + response.getEntity().getContentType());
		System.out.println("Content Encoding : " + response.getEntity().getContentEncoding());		

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		String validXmlInput = "";
		int recordsCount = 0;

		while ((line = rd.readLine()) != null) {			
			result.append(line);
			recordsCount++;
		}
		rd.close();
		System.out.println("Key:::: " + key);
						
		validXmlInput = StringEscapeUtils.unescapeHtml(result.toString());		
		validXmlInput = StringEscapeUtils.unescapeXml(validXmlInput);
		validXmlInput = validXmlInput.replaceAll("&", "&amp;");
		
		
		/* Generate JSON output on s3 */
		byte[] jsonOutput = getJsonOutputStream(key, validXmlInput);		
		writeDataStreamToS3Bucket(key, jsonOutput);

		/* Generate XML output on s3 */
		//		writeToS3Bucket(key, result.toString());
		//System.out.println(result.toString());
		System.out.println("\n");
		System.out.println("Records count for the source - " + key + ":"+ recordsCount);
	}

	/**
	 * Get Json output stream from each input dataset
	 * @param key
	 * @param result
	 * @return byte[]
	 */
	private byte[] getJsonOutputStream(String key, String result){
		OnlineUnmarshaller auUnmarshaller = new OnlineUnmarshaller();
		byte[] jsonOutput = null;

		switch(key){
		case "EXHIBITOR":
			jsonOutput = auUnmarshaller.unmarshallExhibitorData(result.toString());
			break;
		case "SESSION_CAPACITY":
			jsonOutput = auUnmarshaller.unmarshallSessionCapacities(result.toString());
			break;
		case "SESSION":
			jsonOutput = auUnmarshaller.unmarshallSessionData(result.toString());
			break;
		case "SPEAKER":
			jsonOutput = auUnmarshaller.unmarshallSpeakerData(result.toString());
			break;
		case "USER":
			jsonOutput = auUnmarshaller.unmarshallUserData(result.toString());
			break;
		default:
			throw new IllegalArgumentException("Invalid KEY: " + key);
		}
		return jsonOutput;
	}

	/**
	 * 
	 * @param key
	 * @param dataStream in byte[]
	 */
	private void writeDataStreamToS3Bucket(String key, byte[] dataStream){
		if(dataStream == null){
			return;
		}
		String prefix = PREFIX + KEY + AU_PREFIX + key + "/" + AU_PREFIX + key + ".json";
		System.out.println("=================================================================================================");
		System.out.println("XML data from Lanyon API " + key + " has been copied to s3://" + BUCKET_NM + "/" + prefix);
		System.out.println("=================================================================================================");
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig);               
		client.setEndpoint("https://s3.amazonaws.com");
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(dataStream.length);

		try {			
			ByteArrayInputStream input = new ByteArrayInputStream(dataStream);
			client.putObject(BUCKET_NM, prefix, input, omd); 		
			input.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 * @param dataset
	 */
	public void writeToS3Bucket(String key, String dataset){
		String prefix = PREFIX + KEY + AU_PREFIX + key + "/" + AU_PREFIX + key + ".xml";
		System.out.println("===================================================================================");
		System.out.println("JSON data from Lanyon API " + key + "has been written on s3 bucket " + prefix);
		System.out.println("===================================================================================");
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY); 
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setConnectionTimeout(50000);
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 client = new AmazonS3Client(credentials, clientConfig);               
		client.setEndpoint("https://s3.amazonaws.com");

		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(dataset.getBytes().length);

		try {

			byte[] content = dataset.getBytes();
			ByteArrayInputStream input = new ByteArrayInputStream(content);
			client.putObject(BUCKET_NM, prefix, input, omd); 		

			input.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
