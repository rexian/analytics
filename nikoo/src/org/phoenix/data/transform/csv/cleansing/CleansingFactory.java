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

package org.phoenix.data.transform.csv.cleansing;

import com.xxxxx.data.axon.util.AxonConstants;

public class CleansingFactory implements CleansingConstants {
	
	public static DataCleanser getCleanser(String source){
		
		DataCleanser axonDataCleanser = null;
				
		switch(source){
		case PRODUCT_LINE: axonDataCleanser = new ProductLineCleanser(source);
			break;
		case RELEASE_MASTER:axonDataCleanser = new ProductReleaseMasterCleanser(source);
			break;
		case ASSEMBLY_MASTER:axonDataCleanser = new ProductAssemblyMasterCleanser(source);
			break;
		case ASSEMBLY:axonDataCleanser = new ProductAssemblyCleanser(source);
			break;
		case PRODUCT_LANGUAGE:axonDataCleanser = new ProductLanguageCleanser(source);
			break;
		case PRODUCT_RELEASE:axonDataCleanser = new ProductReleaseCleanser(source);
			break;
		case PRODUCT_EXTENSION:axonDataCleanser = new ProductExtensionCleanser(source);
			break;
		case UPDATE_RELEASE:axonDataCleanser = new UpdateReleaseCleanser(source);
			break;
		case NPI_FEATURE:axonDataCleanser = new ProductFeatureCleanser(source);
			break;
		default: axonDataCleanser = null; 
			break;		
		}	
		return axonDataCleanser;
	}
}
