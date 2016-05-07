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

package org.phoenix.data.transform.csv.dto;

import com.opencsv.bean.CsvBind;

public class ProductAssembly {
	
	@CsvBind
	private String refId;
	@CsvBind
	private String uuid;
	@CsvBind
	private String assemblyMasterId;
	@CsvBind
	private String assemblyMaster;
	@CsvBind
	private String unitType;
	@CsvBind
	private String supportEmail;
	@CsvBind
	private String assemblyName;
	@CsvBind
	private String buildNumber;
	@CsvBind
	private String buildDate;
	@CsvBind
	private String currentState;
	@CsvBind
	private String productLineCode;
	@CsvBind
	private String productVersionNumber;
	@CsvBind
	private String upiMasterid;
	@CsvBind
	private String upiString;
	@CsvBind
	private String milestoneTag;
	@CsvBind
	private String milestoneNumber;
	@CsvBind
	private String updateReleaseLink;
	@CsvBind
	private String extensionReleaseLink;
	@CsvBind
	private String productReleaseLink;
	@CsvBind
	private String releaseImageBr;
	@CsvBind
	private String releaseImageDb;
	@CsvBind
	private String releaseImageIn;
	@CsvBind
	private String parentAssembly;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAssemblyMasterId() {
		return assemblyMasterId;
	}
	public void setAssemblyMasterId(String assemblyMasterId) {
		this.assemblyMasterId = assemblyMasterId;
	}
	public String getAssemblyMaster() {
		return assemblyMaster;
	}
	public void setAssemblyMaster(String assemblyMaster) {
		this.assemblyMaster = assemblyMaster;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getProductLineCode() {
		return productLineCode;
	}
	public void setProductLineCode(String productLineCode) {
		this.productLineCode = productLineCode;
	}
	public String getProductVersionNumber() {
		return productVersionNumber;
	}
	public void setProductVersionNumber(String productVersionNumber) {
		this.productVersionNumber = productVersionNumber;
	}
	public String getUpiMasterid() {
		return upiMasterid;
	}
	public void setUpiMasterid(String upiMasterid) {
		this.upiMasterid = upiMasterid;
	}
	public String getUpiString() {
		return upiString;
	}
	public void setUpiString(String upiString) {
		this.upiString = upiString;
	}
	public String getMilestoneTag() {
		return milestoneTag;
	}
	public void setMilestoneTag(String milestoneTag) {
		this.milestoneTag = milestoneTag;
	}
	public String getMilestoneNumber() {
		return milestoneNumber;
	}
	public void setMilestoneNumber(String milestoneNumber) {
		this.milestoneNumber = milestoneNumber;
	}
	public String getUpdateReleaseLink() {
		return updateReleaseLink;
	}
	public void setUpdateReleaseLink(String updateReleaseLink) {
		this.updateReleaseLink = updateReleaseLink;
	}
	public String getExtensionReleaseLink() {
		return extensionReleaseLink;
	}
	public void setExtensionReleaseLink(String extensionReleaseLink) {
		this.extensionReleaseLink = extensionReleaseLink;
	}
	public String getProductReleaseLink() {
		return productReleaseLink;
	}
	public void setProductReleaseLink(String productReleaseLink) {
		this.productReleaseLink = productReleaseLink;
	}
	public String getReleaseImageBr() {
		return releaseImageBr;
	}
	public void setReleaseImageBr(String releaseImageBr) {
		this.releaseImageBr = releaseImageBr;
	}
	public String getReleaseImageDb() {
		return releaseImageDb;
	}
	public void setReleaseImageDb(String releaseImageDb) {
		this.releaseImageDb = releaseImageDb;
	}
	public String getReleaseImageIn() {
		return releaseImageIn;
	}
	public void setReleaseImageIn(String releaseImageIn) {
		this.releaseImageIn = releaseImageIn;
	}
	public String getParentAssembly() {
		return parentAssembly;
	}
	public void setParentAssembly(String parentAssembly) {
		this.parentAssembly = parentAssembly;
	}
	
	public String toString() {
		return "ProductAssembly [refId=" + refId + ", uuid=" + uuid
				+ ", assemblyMasterId=" + assemblyMasterId
				+ ", assemblyMaster=" + assemblyMaster + ", unitType="
				+ unitType + ", supportEmail=" + supportEmail
				+ ", assemblyName=" + assemblyName + ", buildNumber="
				+ buildNumber + ", buildDate=" + buildDate + ", currentState="
				+ currentState + ", productLineCode=" + productLineCode
				+ ", productVersionNumber=" + productVersionNumber
				+ ", upiMasterid=" + upiMasterid + ", upiString=" + upiString
				+ ", milestoneTag=" + milestoneTag + ", milestoneNumber="
				+ milestoneNumber + ", updateReleaseLink=" + updateReleaseLink
				+ ", extensionReleaseLink=" + extensionReleaseLink
				+ ", productReleaseLink=" + productReleaseLink
				+ ", releaseImageBr=" + releaseImageBr + ", releaseImageDb="
				+ releaseImageDb + ", releaseImageIn=" + releaseImageIn
				+ ", parentAssembly=" + parentAssembly + "]";
	}
}
