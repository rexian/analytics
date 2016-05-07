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
public class Material  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("materialName")
	private String materialName;
	@SerializedName("jcr_lastModifiedBy")
	private String jcrLastModifiedBy;
	@SerializedName("materialUrl")
	private String materialUrl;
	@SerializedName("materialType")
	private String materialType;
	@SerializedName("sling_resourceType")
	private String slingResourceType;
	@SerializedName("jcr_lastModified")
	private String jcrLastModified;
	@SerializedName("jcr_primaryType")
	private String jcrPrimaryType;
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialUrl() {
		return materialUrl;
	}
	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public void setSlingResourceType(String slingResourceType) {
		this.slingResourceType = slingResourceType;
	}
	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(String jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	public String getJcrLastModifiedBy() {
		return jcrLastModifiedBy;
	}
	public void setJcrLastModifiedBy(String jcrLastModifiedBy) {
		this.jcrLastModifiedBy = jcrLastModifiedBy;
	}
	public String getJcrLastModified() {
		return jcrLastModified;
	}
	public void setJcrLastModified(String jcrLastModified) {
		this.jcrLastModified = jcrLastModified;
	}
	@Override
	public String toString() {
		return "Material [materialName=" + materialName
				+ ", jcrLastModifiedBy=" + jcrLastModifiedBy + ", materialUrl="
				+ materialUrl + ", materialType=" + materialType
				+ ", slingResourceType=" + slingResourceType
				+ ", jcrLastModified=" + jcrLastModified + ", jcrPrimaryType="
				+ jcrPrimaryType + "]";
	}		
}
