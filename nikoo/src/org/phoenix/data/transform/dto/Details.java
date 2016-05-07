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

package org.phoenix.data.transform.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 10.15.2015
 */
public class Details  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int year;
	private List<String> software;
	private List<String> audience;
	private List<String> industry;
	@SerializedName("audience_type")
	private String audienceType;
	@SerializedName("sling_resourceType")
	private String slingResourceType;	
	private String type;
	private String conferences;
	@SerializedName("jcr_primaryType")
	private String jcrPrimaryType;
	@SerializedName("industry_group")
	private List<String> industryGroup;
	private List<String> track;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<String> getSoftware() {
		return software;
	}
	public void setSoftware(List<String> software) {
		this.software = software;
	}
	public List<String> getAudience() {
		return audience;
	}
	public void setAudience(List<String> audience) {
		this.audience = audience;
	}
	public String getAudienceType() {
		return audienceType;
	}
	public void setAudienceType(String audienceType) {
		this.audienceType = audienceType;
	}	
	public List<String> getIndustry() {
		return industry;
	}
	public void setIndustry(List<String> industry) {
		this.industry = industry;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public void setSlingResourceType(String slingResourceType) {
		this.slingResourceType = slingResourceType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getConferences() {
		return conferences;
	}
	public void setConferences(String conferences) {
		this.conferences = conferences;
	}
	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(String jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	public List<String> getIndustryGroup() {
		return industryGroup;
	}
	public void setIndustryGroup(List<String> industryGroup) {
		this.industryGroup = industryGroup;
	}
	public List<String> getTrack() {
		return track;
	}
	public void setTrack(List<String> track) {
		this.track = track;
	}
	@Override
	public String toString() {
		return "Details [year=" + year + ", software=" + software
				+ ", audience=" + audience + ", industry=" + industry
				+ ", audienceType=" + audienceType + ", slingResourceType="
				+ slingResourceType + ", type=" + type + ", conferences="
				+ conferences + ", jcrPrimaryType=" + jcrPrimaryType
				+ ", industryGroup=" + industryGroup + ", track=" + track + "]";
	}	
}
