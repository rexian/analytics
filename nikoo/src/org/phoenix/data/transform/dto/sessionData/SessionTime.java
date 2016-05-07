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

package org.phoenix.data.transform.dto.sessionData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="sessionTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionTime {

	private String sessionTimeID;
	private String room;
	private String date;
	private String time;
	private String length;
	private String capacity;
	private String registered;
	
	public String getSessionTimeID() {
		return sessionTimeID;
	}
	public void setSessionTimeID(String sessionTimeID) {
		this.sessionTimeID = sessionTimeID;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getRegistered() {
		return registered;
	}
	public void setRegistered(String registered) {
		this.registered = registered;
	}
	@Override
	public String toString() {
		return "SessionTime [sessionTimeID=" + sessionTimeID + ", room=" + room
				+ ", date=" + date + ", time=" + time + ", length=" + length
				+ ", capacity=" + capacity + ", registered=" + registered + "]";
	}	
	
}
