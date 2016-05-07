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

package org.phoenix.data.transform.dto.sessionCapacity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="sessionTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionTime {

	@XmlElement(name="sessionID")
	private String sessionID;
	@XmlElement(name="abbreviation")
	private String abbreviation;
	@XmlElement(name="sessionTimeID")
	private String sessionTimeID;
	@XmlElement(name="capacity")
	private String capacity;
	@XmlElement(name="seatsRemaining")
	private String seatsRemaining;
	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getSessionTimeID() {
		return sessionTimeID;
	}
	public void setSessionTimeID(String sessionTimeID) {
		this.sessionTimeID = sessionTimeID;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getSeatsRemaining() {
		return seatsRemaining;
	}
	public void setSeatsRemaining(String seatsRemaining) {
		this.seatsRemaining = seatsRemaining;
	}
	
}
