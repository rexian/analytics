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

package org.phoenix.data.transform.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 09.25.2015
 *
 */
public class OnlineJsonWriter {

	/**
	 * 
	 * @param obj
	 * @param outputFile
	 */
	public void writeUserObjectToJason(Object obj, File outputFile){

		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter bw = null;
		
		try {	    	  
			String usersJson = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(usersJson);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray("users");
			StringBuffer usersData = new StringBuffer();
			Iterator<JsonElement> usersItr = jsonArr.iterator();
			while(usersItr.hasNext()){
				usersData.append(usersItr.next() + "\n");
			}			
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(usersData.toString());
			bw.flush();			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param obj
	 * @param outputFile
	 */
	public void writeSpeakerObjectToJason(Object obj, File outputFile){

		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter bw = null;
		
		try {	    	  
			String usersJson = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(usersJson);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray("speakers");
			StringBuffer usersData = new StringBuffer();
			Iterator<JsonElement> usersItr = jsonArr.iterator();
			while(usersItr.hasNext()){
				usersData.append(usersItr.next() + "\n");
			}
			//System.out.println(usersData.toString());
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(usersData.toString());
			bw.flush();			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param obj
	 * @param outputFile
	 */
	public void writeExhibitorObjectToJason(Object obj, File outputFile){

		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter bw = null;
		
		try {	    	  
			String usersJson = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(usersJson);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray("exhibitors");
			StringBuffer usersData = new StringBuffer();
			Iterator<JsonElement> usersItr = jsonArr.iterator();
			while(usersItr.hasNext()){
				usersData.append(usersItr.next() + "\n");
			}
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(usersData.toString());
			bw.flush();			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param obj
	 * @param outputFile
	 */
	public void writeSessionCapacityObjectToJason(Object obj, File outputFile){

		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter bw = null;

		try {	    	  
			String usersJson = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(usersJson);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray("sessionTimes");
			StringBuffer usersData = new StringBuffer();
			Iterator<JsonElement> usersItr = jsonArr.iterator();
			while(usersItr.hasNext()){
				usersData.append(usersItr.next() + "\n");
			}
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(usersData.toString());
			bw.flush();			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param obj
	 * @param outputFile
	 */
	public void writeSessionDataObjectToJason(Object obj, File outputFile){

		ObjectMapper mapper = new ObjectMapper();
		BufferedWriter bw = null;
		
		try {	    	  
			String usersJson = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(usersJson);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray("sessions");
			StringBuffer usersData = new StringBuffer();
			Iterator<JsonElement> usersItr = jsonArr.iterator();
			while(usersItr.hasNext()){
				usersData.append(usersItr.next() + "\n");
			}		
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(usersData.toString());
			bw.flush();			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * The method takes in Java object and returns JSON object as byte stream
	 * @param obj
	 * @param dataset
	 * @return byte[]
	 */
	public byte[] writeObjectToJsonStream(Object obj, String dataset){

		ObjectMapper mapper = new ObjectMapper();
		byte[] output = null;
		
		try {
			String jsonObj = mapper.writer().writeValueAsString(obj);			
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(jsonObj);
			JsonObject jo = je.getAsJsonObject();
			JsonArray jsonArr = jo.getAsJsonArray(dataset);
			StringBuffer jsonObjData = new StringBuffer();
			Iterator<JsonElement> jsonObjItr = jsonArr.iterator();
			while(jsonObjItr.hasNext()){
				jsonObjData.append(jsonObjItr.next() + "\n");
			}
			output = jsonObjData.toString().getBytes();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return output;
	}
}
