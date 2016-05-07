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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import org.phoenix.adp.auonline.dto.exhibitor.Exhibitor;
import org.phoenix.adp.auonline.dto.exhibitor.ExhibitorFile;
import org.phoenix.adp.auonline.dto.exhibitor.Participant;

public class OnlineCSVWriter {

	File outputFile = new File("xxxxxxxxxxxx.csv");

	/**
	 * 
	 * @param exhibitors
	 * @throws IOException
	 */
	public void writeCSVFile(List<Exhibitor> exhibitors) throws IOException {
		Writer writer = new FileWriter(outputFile);
		CSVWriter csvWriter = new CSVWriter(writer,',', '"');
		List<String[]> data  = toStringArray(exhibitors);
		csvWriter.writeAll(data);
		csvWriter.close();
	}
	
	/**
	 * 
	 * @param exhibitors
	 * @return
	 */
	public List<String[]> toStringArray(List<Exhibitor> exhibitors) {
		List<String[]> records = new ArrayList<String[]>();        
		Iterator<Exhibitor> it = exhibitors.iterator();
		while(it.hasNext()){
			Exhibitor exhibitor = it.next();
			ExhibitorFile exFile = exhibitor.getExhibitorFile();
			List<String> exFileData = new ArrayList<String>(4);
			if(exFile != null){
				exFileData.add(exFile.getType());
				exFileData.add(exFile.getDisplayName());
				exFileData.add(exFile.getDescription());
				exFileData.add(exFile.getUrl());        		
			}
			List<Participant> participants = exhibitor.getParticipants();        	
			if(participants !=null && participants.size() != 0){
				for(Participant participant: participants){
					records.add(new String[]{exhibitor.getExhibitorID(), exhibitor.getName()
							, exhibitor.getStatus(), exhibitor.getType(), exhibitor.getDescription()
							, exhibitor.getUrl(), exhibitor.getEmail(), exhibitor.getBooth(),  exFileData.toArray().toString(), participant.getRole()
							, participant.getUserRef(), participant.getFirstName(), participant.getLastName()
							, participant.getParticipantEmail()});
				}       
			}        	     
		}
		return records;
	}
}
