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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.log4j.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.phoenix.data.axon.aws.AwsDataLoader;
import org.phoenix.data.axon.dto.AxonProductAssembly;
import org.phoenix.data.axon.util.AxonConstants;
import org.phoenix.data.axon.util.EdpCSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class ProductAssemblyCleanser implements DataCleanser, CleansingConstants {

	Logger log = Logger.getLogger(ProductAssemblyCleanser.class.getName());

	private final String source;	
	List<ProductAssembly> list = new LinkedList<ProductAssembly>();

	public ProductAssemblyCleanser(String source) {
		super();
		this.source = source;
	}

	/**
	 * Data cleansing method
	 */
	public void cleanseData(AmazonS3 client) throws Exception {

		AwsDataLoader loader = new AwsDataLoader();		
		CSVReader reader = null;
		String prefix = loader.getS3Prefix(source);		               
		client.setEndpoint(S3_ENDPOINT);		
		S3Object object = client.getObject(new GetObjectRequest(BUCKET_NM, prefix));
		reader = new CSVReader(new BufferedReader(new InputStreamReader(object.getObjectContent())), 
				CSVParser.DEFAULT_SEPARATOR, 
				CSVParser.DEFAULT_QUOTE_CHARACTER, 
				CSVParser.DEFAULT_ESCAPE_CHARACTER, HEADERS_LINE);
		ColumnPositionMappingStrategy<ProductAssembly> strat = new ColumnPositionMappingStrategy<ProductAssembly>();
		strat.setType(ProductAssembly.class);
		String[] inputColumns = new String[] {"refId", "uuid", "assemblyMaster", "unitType", "supportEmail", "assemblyName", "buildNumber", "buildDate", "currentState", "productLineCode", "productVersionNumber", "upiMasterid", "upiString", "milestoneTag", "milestoneNumber", "updateReleaseLink", "extensionReleaseLink", "productReleaseLink", "releaseImageBr", "releaseImageDb", "releaseImageIn", "parentAssembly"}; 
		String[] outputColumns = new String[] {"refId", "uuid", "assemblyMasterId", "assemblyMaster", "unitType", "supportEmail", "assemblyName", "buildNumber", "buildDate", "currentState", "productLineCode", "productVersionNumber", "upiMasterid", "upiString", "milestoneTag", "milestoneNumber", "updateReleaseLink", "extensionReleaseLink", "productReleaseLink", "releaseImageBr", "releaseImageDb", "releaseImageIn", "parentAssembly"};
		strat.setColumnMapping(inputColumns);

		CsvToBean<ProductAssembly> csv = new CsvToBean<ProductAssembly>();
		List<ProductAssembly> list = csv.parse(strat, reader);

		System.out.println("ProductAssemblyCleanser Input size: " + list.size());
		
		this.updateDataset(list);
		
		BeanToCsv<ProductAssembly> csvWriter = new BeanToCsv<ProductAssembly>();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(os), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
		strat.setColumnMapping(outputColumns);		
		csvWriter.write(strat, writer, list);		
		writer.flush();
		String dataset = os.toString(); 
		String outPrefix = PREFIX + OUTPUT_KEY + source + ".csv";
		client.setEndpoint(S3_ENDPOINT);
		ObjectMetadata omd = new ObjectMetadata();		

		try {
			byte[] content = dataset.getBytes(StandardCharsets.UTF_8);
			ByteArrayInputStream input = new ByteArrayInputStream(content);			
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));
			buffReader.readLine();
			InputStream inputObj = new ReaderInputStream(buffReader);			
			//omd.setContentLength(IOUtils.toByteArray(input).length);
			client.putObject(BUCKET_NM, outPrefix, inputObj, omd); 	
			input.close();
		} catch (IOException e) {			
			log.error("Axon data write to s3 failed: " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param list
	 */
	private void updateDataset(List<ProductAssembly> list){

		Pattern p = Pattern.compile(".*[AM]{1}[0-9]{6}");
		for(ProductAssembly assembly: list){			
			String assemblyMaster = assembly.getAssemblyMaster();	
			//System.out.println("assemblyMaster >> " + assemblyMaster);
			if(assemblyMaster != null && assemblyMaster.trim().contains("AM")){				
				Matcher m = p.matcher(assemblyMaster);
				if(m.matches()){						
					String[] fields = assemblyMaster.split("-");
					assembly.setAssemblyMasterId(fields[fields.length - 1].trim());
				}				
			}			
			if(assemblyMaster != null && assemblyMaster.trim().contains("{")){
				int index = assemblyMaster.indexOf("{");					
				assembly.setAssemblyMasterId(assemblyMaster.substring(index));
			}		
		}		
		for(ProductAssembly assembly: list){	
			log.info("assemblyMasterId >> " + assembly.getAssemblyMasterId());
		}
		return;
	}
}
