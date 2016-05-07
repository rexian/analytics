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
public class ABCHttpJsonClient extends OnlineConstants{

	ABCJsonWriter jsonWriter = new ABCJsonWriter();
	
	/**
	 * default constructor
	 */
	public ABCHttpJsonClient() {
		super();		
	}

	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * MAIN()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		OnlineConstants auConst = new OnlineConstants();
		ABCHttpJsonClient http = new ABCHttpJsonClient();		
		System.out.println("\n Send Http POST request");
		Set<String> keys = auConst.getAemApiUrl().keySet();
		Iterator<String> urlKeys = keys.iterator();
		while(urlKeys.hasNext()){
			String key = urlKeys.next();
			http.sendGet(key, auConst.getAemApiUrl().get(key));
			System.out.println("=======================================================================================");
			System.out.println("AEM API call for data source: " + key + " has completed.");
			System.out.println("=======================================================================================");
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
		rd.close();
		
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Speakers.class, new SpeakersAdapter());
		gsonBuilder.registerTypeAdapter(Materials.class, new MaterialsAdapter());
		gsonBuilder.disableHtmlEscaping();
		//gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();

		JsonArray jsonArray = jWriter.writeAemToJsonArray(result.toString());
		StringBuffer jsonObj = new StringBuffer();
		
		Iterator<JsonElement> aemItr = jsonArray.iterator();
		while(aemItr.hasNext()){
			JsonElement je = aemItr.next();			
			ABCSource aemSource = gson.fromJson(je, ABCSource.class);			
			String aemSourceJson = gson.toJson(aemSource);
			jsonObj.append(aemSourceJson);
			jsonObj.append("\n");
		}		
		jWriter.writeAemDataToJason(jsonObj.toString());
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
