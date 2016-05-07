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

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 10.15.2015
 */
public class Speaker  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("biography")
	private String biography;
	@SerializedName("jcr_lastModifiedBy")
	private String jcrLastModifiedBy;
	@SerializedName("sling_resourceType")
	private String slingResourceType;
	@SerializedName("image")
	private String image;
	@SerializedName("type")
	private String type;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("jcr_lastModified")
	private String jcrLastModified;
	@SerializedName("jcr_primaryType")
	private String jcrPrimaryType;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public String getJcrLastModifiedBy() {
		return jcrLastModifiedBy;
	}
	public void setJcrLastModifiedBy(String jcrLastModifiedBy) {
		this.jcrLastModifiedBy = jcrLastModifiedBy;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public void setSlingResourceType(String slingResourceType) {
		this.slingResourceType = slingResourceType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJcrLastModified() {
		return jcrLastModified;
	}
	public void setJcrLastModified(String jcrLastModified) {
		this.jcrLastModified = jcrLastModified;
	}
	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(String jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "Speaker [firstName=" + firstName + ", biography=" + biography
				+ ", jcrLastModifiedBy=" + jcrLastModifiedBy
				+ ", slingResourceType=" + slingResourceType + ", image="
				+ image + ", type=" + type + ", lastName=" + lastName
				+ ", jcrLastModified=" + jcrLastModified + ", jcrPrimaryType="
				+ jcrPrimaryType + "]";
	}
}
