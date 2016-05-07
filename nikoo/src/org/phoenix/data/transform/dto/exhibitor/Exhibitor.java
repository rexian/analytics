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

package org.phoenix.data.transform.dto.exhibitor;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="exhibitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Exhibitor {

	private String exhibitorID;
	private String name;
	private String status;
	private String type;
	private String description;
	private String url;
	private String email;
	private String booth;
	
	@XmlElement(name="exhibitorFile")
	private ExhibitorFile exhibitorFile;
	
	@XmlElement(name="participant")
	private List<Participant> participants;
	
	public String getExhibitorID() {
		return exhibitorID;
	}
	public void setExhibitorID(String exhibitorID) {
		this.exhibitorID = exhibitorID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}	
	public ExhibitorFile getExhibitorFile() {
		return exhibitorFile;
	}
	public void setExhibitorFile(ExhibitorFile exhibitorFile) {
		this.exhibitorFile = exhibitorFile;
	}
	public List<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
}
