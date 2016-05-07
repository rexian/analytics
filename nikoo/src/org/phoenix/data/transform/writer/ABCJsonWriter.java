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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.phoenix.adp.aem.client.AUOnlineConstants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
public class ABCJsonWriter extends OnlineConstants{

	/**
	 * Default constructor
	 */
	public ABCJsonWriter() {
		super();		
	}

	/**
	 * 
	 * @param aemJsonObj as String
	 */
	public void writeAemDataToJason(String aemJsonObj){

		BufferedWriter bw = null;
		File outputFile =  new File("XXXXXXXXXXXX.json");

		try {	    	  			
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(aemJsonObj);
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
	 * @param aemJsonObj
	 * @return
	 */
	public String writeAemDataToJsonStream(String aemJsonObj){			
		OnlineConstants auConst = new OnlineConstants();
		List<String> jsonKeys = new ArrayList<String>();
		List<String> fieldKeys = new ArrayList<String>();
		Pattern p = Pattern.compile(COLON_CLEANUP_REGEX);
		//Pattern p = Pattern.compile("[\"]{1}[a-z]{2,}[:]{1}");
		int recordsCount = 0;
		Matcher matcher = p.matcher(aemJsonObj);
		while(matcher.find()){
			jsonKeys.add(matcher.group());			
		}
		Set<String> uniqueJsonKeys = new HashSet<String>(jsonKeys);
		Iterator<String> jsonKeyIterator = uniqueJsonKeys.iterator();
		while(jsonKeyIterator.hasNext() && aemJsonObj != null){
			String val = null;
			String key = jsonKeyIterator.next();
			Map<String, String> jsonKeyMap = auConst.getJsonKeyMap();			
			if(jsonKeyMap.containsKey(key)){				
				val = jsonKeyMap.get(key);
				aemJsonObj = aemJsonObj.replace(key, val);		
			}							
		}

		String jsonline = aemJsonObj;
		Pattern pat = Pattern.compile(HYPHEN_CLEANUP_REGEX);		
		Matcher textMatch = pat.matcher(jsonline);
		while(textMatch.find()){
			fieldKeys.add(textMatch.group());	
		}		

		Set<String> uniqueKeys = new HashSet<String>(fieldKeys);
		System.out.println(uniqueKeys.toString());
		Iterator<String> keyIterator = uniqueKeys.iterator();
		while(keyIterator.hasNext() && jsonline != null){
			String val = null;
			String key = keyIterator.next();
			Map<String, String> jsonKeyMap = auConst.getJsonKeyMap();			
			if(jsonKeyMap.containsKey(key)){				
				val = jsonKeyMap.get(key);
				jsonline = jsonline.replace(key, val);		
			}							
		}

		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonline);
		JsonObject jo = je.getAsJsonObject();
		JsonArray jsonArr = jo.getAsJsonArray("hits");
		StringBuffer aemData = new StringBuffer();
		Iterator<JsonElement> aemItr = jsonArr.iterator();
		while(aemItr.hasNext()){
			aemData.append(aemItr.next() + "\n");
			recordsCount++;
		}
		return aemData.toString();
	}	

	/**
	 * 
	 * @param aemJsonObj
	 * @return
	 */
	public JsonArray writeAemToJsonArray(String aemJsonObj){			
		OnlineConstants auConst = new OnlineConstants();
		List<String> jsonKeys = new ArrayList<String>();
		List<String> fieldKeys = new ArrayList<String>();
		Pattern p = Pattern.compile(COLON_CLEANUP_REGEX);				
		Matcher matcher = p.matcher(aemJsonObj);
		while(matcher.find()){
			jsonKeys.add(matcher.group());			
		}
		Set<String> uniqueJsonKeys = new HashSet<String>(jsonKeys);
		System.out.println(uniqueJsonKeys.toString());

		Iterator<String> jsonKeyIterator = uniqueJsonKeys.iterator();
		while(jsonKeyIterator.hasNext() && aemJsonObj != null){
			String val = null;
			String key = jsonKeyIterator.next();
			Map<String, String> jsonKeyMap = auConst.getJsonKeyMap();			
			if(jsonKeyMap.containsKey(key)){				
				val = jsonKeyMap.get(key);
				aemJsonObj = aemJsonObj.replace(key, val);		
			}							
		}

		String jsonline = aemJsonObj;
		Pattern pat = Pattern.compile(HYPHEN_CLEANUP_REGEX);		
		Matcher textMatch = pat.matcher(jsonline);
		while(textMatch.find()){
			fieldKeys.add(textMatch.group());	
		}		

		Set<String> uniqueKeys = new HashSet<String>(fieldKeys);
		System.out.println(uniqueKeys.toString());
		Iterator<String> keyIterator = uniqueKeys.iterator();
		while(keyIterator.hasNext() && jsonline != null){
			String val = null;
			String key = keyIterator.next();
			Map<String, String> jsonKeyMap = auConst.getJsonKeyMap();			
			if(jsonKeyMap.containsKey(key)){				
				val = jsonKeyMap.get(key);
				jsonline = jsonline.replace(key, val);		
			}							
		}

		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonline);
		JsonObject jo = je.getAsJsonObject();
		JsonArray jsonArr = jo.getAsJsonArray("hits");
		return jsonArr;
	}	
}
