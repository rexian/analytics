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

package org.phoenix.data.transform.unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.phoenix.adp.auonline.dto.sessionData.Sessions;
import org.phoenix.adp.auonline.writer.LanyonJsonWriter;

public class SessionDataUnmarshaller {

	public static void main(String[] args) {

		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();
		
		File outputFile =  new File("sessionData.json");
				
		try {
			
			jaxbContext = JAXBContext.newInstance(Sessions.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File file =  new File("SESSION_DATA.xml");
			if(file.exists()){
				System.out.println("File exists ...." + file.getName());
			}
			Sessions sessions = (Sessions) jaxbUnmarshaller.unmarshal( new FileInputStream( file ));
			jsonWriter.writeSessionDataObjectToJason(sessions, outputFile);
		} catch (JAXBException e) {			
			System.err.println("Error: " + e);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
}
