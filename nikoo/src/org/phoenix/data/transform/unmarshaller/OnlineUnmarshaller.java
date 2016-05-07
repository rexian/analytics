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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.phoenix.adp.auonline.dto.exhibitor.Exhibitors;
import org.phoenix.adp.auonline.dto.sessionCapacity.SessionCapacities;
import org.phoenix.adp.auonline.dto.sessionCapacity.SessionTime;
import org.phoenix.adp.auonline.dto.sessionData.Sessions;
import org.phoenix.adp.auonline.dto.speaker.Speakers;
import org.phoenix.adp.auonline.dto.userData.Users;
import org.phoenix.adp.auonline.writer.LanyonJsonWriter;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *  @since 09.24.2015
 */
public class OnlineUnmarshaller {

	/**
	 * Unmarshall exhibitor dataset
	 * @param xmlInput
	 * @return
	 */
	public byte[] unmarshallExhibitorData(String xmlInput){
		
		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();		
		byte[] outputStream = null;
		
		try {
			jaxbContext = JAXBContext.newInstance(Exhibitors.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Exhibitors exhibitors = (Exhibitors) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader( xmlInput )));
			outputStream = jsonWriter.writeObjectToJsonStream(exhibitors, "exhibitors");
		} catch (JAXBException e) {			
			e.printStackTrace();
		} 
		return outputStream;
	}
	
	/**
	 * Unmarshall Session Capacities
	 * @param xmlInput
	 * @return
	 */
	public byte[] unmarshallSessionCapacities(String xmlInput){
		
		List<SessionTime> sessionTimeList = new ArrayList<SessionTime>();
		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();
		byte[] outputStream = null;
				
		try {
			jaxbContext = JAXBContext.newInstance(SessionCapacities.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();			
			SessionCapacities sessionCapacities = (SessionCapacities) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader( xmlInput )));
			outputStream = jsonWriter.writeObjectToJsonStream(sessionCapacities, "sessionTimes");			
			//System.out.println("SessionTimes: " + sessionCapacities.getSessionTimes());
			
			for(SessionTime sessionTime : sessionCapacities.getSessionTimes()) {
				sessionTimeList.add(sessionTime);
			}
		} catch (JAXBException e) {						
			System.err.println("Error: " + e.getMessage());
		}
		return outputStream;
	}
	
	/**
	 * Unmarshall Session dataset
	 * @param xmlInput
	 * @return
	 */
	public byte[] unmarshallSessionData(String xmlInput){
		
		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();
		byte[] outputStream = null;
		
		try {
			jaxbContext = JAXBContext.newInstance(Sessions.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();			
			Sessions sessions = (Sessions) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader( xmlInput )));
			outputStream = jsonWriter.writeObjectToJsonStream(sessions, "sessions");
		} catch (JAXBException e) {			
			System.err.println("Error: " + e.getMessage());
		}	
		return outputStream;
	}
	
	/**
	 * Unmarshall Speaker dataset
	 * @param xmlInput
	 * @return
	 */
	public byte[] unmarshallSpeakerData(String xmlInput){
		
		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();
		byte[] outputStream = null;
		
		try {
			jaxbContext = JAXBContext.newInstance(Speakers.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();			
			Speakers speakers = (Speakers) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader( xmlInput )));
			outputStream = jsonWriter.writeObjectToJsonStream(speakers, "speakers");						
		} catch (JAXBException e) {			
			System.err.println("Error: " + e);
		}
		return outputStream;
	}
	
	/**
	 * Unmarshall User dataset
	 * @param xmlInput
	 * @return
	 */
	public byte[] unmarshallUserData(String xmlInput){
		
		JAXBContext jaxbContext;		
		OnlineJsonWriter jsonWriter = new OnlineJsonWriter();
		byte[] outputStream = null;
		
		try {
			jaxbContext = JAXBContext.newInstance(Users.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();			
			
						
			Users users = (Users) jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader( xmlInput )));
			outputStream = jsonWriter.writeObjectToJsonStream(users, "users");			
		} catch (JAXBException e) {	
			e.printStackTrace();
			if(e.getLinkedException() != null){
				System.err.println("Error in XML input dataset: " + e.getLinkedException().getMessage());
			}			
		} 		
		return outputStream;
	}
}
