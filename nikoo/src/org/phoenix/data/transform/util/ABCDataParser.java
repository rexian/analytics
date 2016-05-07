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

package org.phoenix.data.transform.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.phoenix.adp.aem.client.AEMHttpClient;
import org.phoenix.adp.aem.client.AUOnlineConstants;
import org.phoenix.adp.aem.writer.AEMJsonWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Surajit Paul
 *
 */
public class ABCDataParser {
	
	private static StringBuffer aemData = new StringBuffer();
	
	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		ABCHttpClient http = new ABCHttpClient();		
		OnlineConstants auConst = new OnlineConstants();
		System.out.println("\n Send Http POST request");
		String jsonContent = null;
		Set<String> keys = auConst.getAemApiUrl().keySet();
		Iterator<String> urlKeys = keys.iterator();
		while(urlKeys.hasNext()){
			String key = urlKeys.next();
			jsonContent = http.getJsonContent(key, auConst.getAemApiUrl().get(key));
			System.out.println("=======================================================================================");
			System.out.println("AEM API call for data source: " + key + " has completed.");
			System.out.println("=======================================================================================");
		}
		
		ABCJsonWriter jWriter = new ABCJsonWriter();
		String jsonCleanedUp = null;
		BufferedWriter bw = null;
		File outputFile =  new File("xxxxx.txt");
		aemData.append(jsonContent);
		
		try {	    	  
			jsonCleanedUp = jWriter.writeAemDataToJsonStream(aemData.toString());
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(jsonCleanedUp);
			bw.flush();			
		}  catch (IOException e) {
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
	 * @param elem
	 */
	public static void decorateJSON(JsonElement elem){
		
		if(!elem.isJsonPrimitive() && !elem.isJsonArray()){		
			JsonObject jsonObj = elem.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> elemKeys = jsonObj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> elemKeysItr = elemKeys.iterator();
			while(elemKeysItr.hasNext()){
				Map.Entry<String, JsonElement> elemVal = (Entry<String, JsonElement>) elemKeysItr.next();
				aemData.append(elemVal.getKey());
				aemData.append(':');
				JsonElement jElem = elemVal.getValue();
				decorateJSON(jElem);				
			}
		}		
		else{
			aemData.append(elem);
			aemData.append(',');
		}		
	}
}
