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
import java.util.Iterator;
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
import org.phoenix.data.axon.dto.AxonProductReleaseMaster;
import org.phoenix.data.axon.util.AxonConstants;
import org.phoenix.data.axon.util.EdpCSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class ProductReleaseMasterCleanser implements DataCleanser, CleansingConstants {
	
	Logger log = Logger.getLogger(ProductReleaseMasterCleanser.class.getName());

	private final String source;	
	List<ProductReleaseMaster> list = new LinkedList<ProductReleaseMaster>();

	public ProductReleaseMasterCleanser(String source) {
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
		ColumnPositionMappingStrategy<ProductReleaseMaster> strat = new ColumnPositionMappingStrategy<ProductReleaseMaster>();
		strat.setType(ProductReleaseMaster.class);
		String[] columns = new String[] {"refId","productLine","productVersionNumber","releaseMasterName","productVersionReleaseDate","codeName","thumbnailUrl","supportedLanguages","languageImplementationType","supportedPlatforms","productVersionMarketingName","owner","createdOn","createdBy","lastModifiedBy","lastModifiedOn","lastAction","currentState","lastActionBy","lastActionDate","lastActionComments","relatedItem","assemblyMasterId"}; 
		strat.setColumnMapping(columns);

		CsvToBean<ProductReleaseMaster> csv = new CsvToBean<ProductReleaseMaster>();
		List<ProductReleaseMaster> list = csv.parse(strat, reader);

		System.out.println("ProductReleaseMasterCleanser input size: " + list.size());
		this.updateDataset(list);	
		
		BeanToCsv<ProductReleaseMaster> csvWriter = new BeanToCsv<ProductReleaseMaster>();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(os), ',', '"');
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
	
	private void updateDataset(List<ProductReleaseMaster> list){
		
		Pattern p = Pattern.compile(".*[AM]{1}[0-9]{6}");
			
		Iterator<ProductReleaseMaster> rowdata = list.listIterator();
		while(rowdata.hasNext()){
			ProductReleaseMaster productReleaseMaster = rowdata.next();
			String relatedItem = productReleaseMaster.getRelatedItem();
			if(relatedItem != null && relatedItem.trim().contains("AM")){				
				Matcher m = p.matcher(relatedItem);
				if(m.matches()){
					//System.out.println(relatedItem);
					String[] fields = relatedItem.split("-");
					//System.out.println(fields[fields.length - 1]);					
					productReleaseMaster.setAssemblyMasterId(fields[fields.length - 1].trim());
				}				
			}			
			if(relatedItem != null && relatedItem.trim().contains("{")){
				int index = relatedItem.indexOf("{");											
				productReleaseMaster.setRelatedItem(relatedItem.substring(0,  (index - 3)));
				productReleaseMaster.setAssemblyMasterId(relatedItem.substring(index));
			}
		}		
		rowdata = list.listIterator();
		while(rowdata.hasNext()){
			ProductReleaseMaster data = rowdata.next();
			
			if(data.getSupportedLanguages() != null && data.getSupportedLanguages().contains("\n")){				
				data.setSupportedLanguages(data.getSupportedLanguages().replace("\n", ",").trim());				
			}
			
			if(data.getSupportedPlatforms() != null && data.getSupportedPlatforms().contains("\n")){				
				data.setSupportedPlatforms(data.getSupportedPlatforms().replace("\n", ",").trim());				
			}
			log.info(data.getSupportedLanguages() + " <> " + data.getSupportedPlatforms() + " <> " + data.getAssemblyMasterId());
		}
		return;
	}
}
