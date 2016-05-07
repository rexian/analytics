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
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 09.25.2015
 *
 */
public class ABCHttpClient extends OnlineConstants{

	ABCJsonWriter jsonWriter = new ABCJsonWriter();
	private static int API_CALL_COUNTER = 0;

	/**
	 * default constructor
	 */
	public ABCHttpClient() {
		super();		
	}

	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * MAIN()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {		
		ABCHttpClient http = new ABCHttpClient();		
		http.getJsonObject();		
	}
	
	/**
	 * Retry attempt after every 10 sec. has been implemented in case of Illegal State Exception during remote API call.
	 */
	private void getJsonObject(){
		API_CALL_COUNTER++;
		OnlineConstants auConst = new OnlineConstants();
		System.out.println("\n Send Http POST request");
		Set<String> keys = auConst.getAemApiUrl().keySet();
		Iterator<String> urlKeys = keys.iterator();
		while(urlKeys.hasNext()){
			String key = urlKeys.next();
			try {
				this.sendGet(key, auConst.getAemApiUrl().get(key));
			} catch (java.lang.IllegalStateException e) {				
				System.out.println("\n=======================================================================================");				
				System.out.println("AU Online AEM API is not accessible at the moment.");				
				e.printStackTrace();
				System.out.println("\n=======================================================================================");
				try {
					System.out.println("\n Sleeeping for - 10 sec.");
					Thread.sleep(10000);
					System.out.println("\nResuming operation after - 10 sec.");
					System.out.println("Number of API call attempt: " + API_CALL_COUNTER);
					if(API_CALL_COUNTER < 10){
						this.getJsonObject();
					}					
				} catch (InterruptedException e1) {					
					e1.printStackTrace();
				}
				finally{
					System.out.println("=======================================================================================");
					System.out.println("AEM API call for data source: " + key + " has completed.");
					System.out.println("=======================================================================================");
				}
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}		
	}

	/**
	 * 
	 * @param key
	 * @param url
	 * @throws Exception
	 */
	public void sendGet(String key, String url) throws Exception {

		ABCJsonWriter jWriter = new ABCJsonWriter();

		System.out.println("AEM URL: " + url);

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);		
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
				response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
			result.append("\n");
		}
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Speakers.class, new SpeakersAdapter());
		gsonBuilder.registerTypeAdapter(Materials.class, new MaterialsAdapter());
		gsonBuilder.disableHtmlEscaping();
		
		Gson gson = gsonBuilder.create();

		JsonArray jsonArray = jWriter.writeAemToJsonArray(result.toString());
		StringBuffer jsonObj = new StringBuffer();

		Iterator<JsonElement> aemItr = jsonArray.iterator();

		while(aemItr.hasNext()){
			System.out.println("JSON::::::::::::::::::: " + aemItr.next());
			ABCSource aemSource = gson.fromJson(aemItr.next(), ABCSource.class);
			System.out.println("JSON output: " + aemSource.toString());
			String aemSourceJson = gson.toJson(aemSource);
			jsonObj.append(aemSourceJson);
			jsonObj.append("\n");
		}
		//System.out.println(jsonObj.toString());
		byte[] jsonOutput = jsonObj.toString().getBytes();
		writeDataStreamToS3Bucket(key, jsonOutput);
	}


	/**
	 * 
	 * @param key
	 * @param dataStream in byte[]
	 */
	public void writeDataStreamToS3Bucket(String key, byte[] dataStream){		
		String prefix = PREFIX + AEM_KEY + AU_PREFIX + key + "/" + AU_PREFIX + key + ".json";
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
	 * @param url
	 * @throws Exception
	 */
	public String getJsonContent(String key, String url) throws Exception {

		System.out.println("AEM URL: " + url);

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);		
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
				response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
			result.append("\n");
		}
		return result.toString();
	}
}
