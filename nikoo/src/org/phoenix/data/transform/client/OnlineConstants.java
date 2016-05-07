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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Surajit Paul
 * 
 *
 */
public class OnlineConstants {
	
	public Map<String, String> aemApiUrl = new HashMap<String, String>();
	public Map<String, String> jsonKeyMap = new HashMap<String, String>();

	public OnlineConstants() {
		super();		
	}
	
	public static String ACCESS_KEY = "XXXXXXXXXXXXXXXXXXXXXXX";
	public static String SECRET_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	public static String BUCKET_NM = "xxxxxxxxxxxxxxx";

	public static String PREFIX = "data/";	
	public static String AU_PREFIX = "XXXXXXXXXXXX";
	public static String AEM_KEY = "models/xxxxxx/xxxxxxx/";
	public static String COLON_CLEANUP_REGEX = "[^:]{1}[\"]{1}[a-z&&[^hp]]+[^\\p{Punct}][:]{1}";
	public static String HYPHEN_CLEANUP_REGEX = "[\"]{1}[a-z]+[-]{1}[a-z]+[\"]{1}";

	public Map<String, String> getJsonKeyMap() {
		return jsonKeyMap;
	}
	public void setJsonKeyMap(Map<String, String> jsonKeyMap) {
		this.jsonKeyMap = jsonKeyMap;
	}
	public Map<String, String> getAemApiUrl() {
		return aemApiUrl;
	}
	public void setAemApiUrl(Map<String, String> aemApiUrl) {
		this.aemApiUrl = aemApiUrl;
	}	
}
