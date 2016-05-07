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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionCapacitiesParser {

	public static void main(String[] args) {
		File dataFile =  new File("XXXXXXXX.xml");		
		Set<String> fieldsSet = new HashSet<String>();
		String line = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(dataFile));
			while((line = br.readLine()) != null){				
				Pattern p = Pattern.compile("[<][a-zA-Z0-9]+[-]?[a-zA-Z0-9]+[-]?[a-zA-Z0-9]+?[>]");
				Matcher m = p.matcher(line);
				while(m.find()){
					fieldsSet.add(m.group());
				}
				for(String field: fieldsSet){
					System.out.println(field);
				}							
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
	}
}
