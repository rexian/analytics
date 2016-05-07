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

package org.phoenix.data.transform.csv.util;

public interface CleansingConstants {
	
		// AWS access credentials
		public static String ACCESS_KEY = "XXXXXXXXXXXXXXXXXXXXXXX";
		public static String SECRET_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		public static String BUCKET_NM = "xxxxxxxxxxxx";
		
	
		public static String INPUT_KEY = "xxxx/xxxxx/xxxxx/";
		public static String OUTPUT_KEY = "xxxx/xxxxx/xxxxx/";
		public static String TRIGGER_KEY = "xxxx/xxxxx/xxxxx/";
		
		public static String PREFIX = "users/";
		public static String S3_ENDPOINT = "https://s3.amazonaws.com";		
		
		public static final int HEADERS_LINE = 1;
		
		
		public static final String DATE_VERSION = "03152016";
		public static final String PRODUCT_LINE = "EDP_Product_Line_" + DATE_VERSION;
		public static final String RELEASE_MASTER = "EDP_Product_Release_Master_" + DATE_VERSION;
		public static final String ASSEMBLY_MASTER = "EDP_Product_Assembly_Master_" + DATE_VERSION;
		public static final String ASSEMBLY = "EDP_Product_Assembly_" + DATE_VERSION;
		public static final String PRODUCT_LANGUAGE = "EDP_Product_language_" + DATE_VERSION;
		public static final String PRODUCT_RELEASE = "EDP_Product_Release_" + DATE_VERSION;
		public static final String PRODUCT_EXTENSION = "EDP_Product_Extension_" + DATE_VERSION;
		public static final String UPDATE_RELEASE = "EDP_Update_Release_" + DATE_VERSION;
		public static final String NPI_FEATURE = "NPI_Product_Feature_" + DATE_VERSION;
}
