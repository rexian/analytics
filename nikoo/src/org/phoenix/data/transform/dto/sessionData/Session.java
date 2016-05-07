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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

@XmlRootElement (name="session")
@XmlAccessorType(XmlAccessType.FIELD)

public class Session{
	
	
	public String sessionID;
	public String abbreviation;
	public String title;
	@XmlElement(name="abstract")
	public String sessionAbstract;
	public String timesOffered;
	public String capacity;
	public String type;
	public String track;
	public String status;
	public String length;
	public String created;
	public String modified;
	public String published;
	public String publicViewPrivateSchedule;
	public String privateViewPrivateSchedule;
	public String cannotViewOrSchedule;
	@XmlElement(name="sessionTime")
	@JsonUnwrapped
	public SessionTime sessionTime;
	@XmlElement(name="Primary-Track")
	public String primaryTrack;
	@XmlElement(name="Thumbnail-Image")
	public String thumbnailImage;
	@XmlElement(name="speaker")
	@JsonUnwrapped
	public List<Speaker> speakers;
	@XmlElement(name="file")
	@JsonUnwrapped
	public List<File> files;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSessionAbstract() {
		return sessionAbstract;
	}
	public void setSessionAbstract(String sessionAbstract) {
		this.sessionAbstract = sessionAbstract;
	}
	public String getTimesOffered() {
		return timesOffered;
	}
	public void setTimesOffered(String timesOffered) {
		this.timesOffered = timesOffered;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getPublicViewPrivateSchedule() {
		return publicViewPrivateSchedule;
	}
	public void setPublicViewPrivateSchedule(String publicViewPrivateSchedule) {
		this.publicViewPrivateSchedule = publicViewPrivateSchedule;
	}
	public String getPrivateViewPrivateSchedule() {
		return privateViewPrivateSchedule;
	}
	public void setPrivateViewPrivateSchedule(String privateViewPrivateSchedule) {
		this.privateViewPrivateSchedule = privateViewPrivateSchedule;
	}
	public String getCannotViewOrSchedule() {
		return cannotViewOrSchedule;
	}
	public void setCannotViewOrSchedule(String cannotViewOrSchedule) {
		this.cannotViewOrSchedule = cannotViewOrSchedule;
	}
	public SessionTime getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(SessionTime sessionTime) {
		this.sessionTime = sessionTime;
	}
	public String getPrimaryTrack() {
		return primaryTrack;
	}
	public void setPrimaryTrack(String primaryTrack) {
		this.primaryTrack = primaryTrack;
	}
	public String getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	public List<Speaker> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "Session [sessionID=" + sessionID + ", abbreviation="
				+ abbreviation + ", title=" + title + ", sessionAbstract="
				+ sessionAbstract + ", timesOffered=" + timesOffered
				+ ", capacity=" + capacity + ", type=" + type + ", track="
				+ track + ", status=" + status + ", length=" + length
				+ ", created=" + created + ", modified=" + modified
				+ ", published=" + published + ", publicViewPrivateSchedule="
				+ publicViewPrivateSchedule + ", privateViewPrivateSchedule="
				+ privateViewPrivateSchedule + ", cannotViewOrSchedule="
				+ cannotViewOrSchedule + ", sessionTime=" + sessionTime
				+ ", primaryTrack=" + primaryTrack + ", thumbnailImage="
				+ thumbnailImage + ", speakers=" + speakers + ", files="
				+ files + "]";
	}		
}
