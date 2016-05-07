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

public class ProductReleaseMaster {
	
	@CsvBind
	private String refId;
	@CsvBind
	private String productLine;
	@CsvBind
	private String productVersionNumber;
	@CsvBind
	private String releaseMasterName;
	@CsvBind
	private String productVersionReleaseDate;
	@CsvBind
	private String codeName;
	@CsvBind
	private String thumbnailUrl;
	@CsvBind
	private String supportedLanguages;
	@CsvBind
	private String languageImplementationType;
	@CsvBind
	private String supportedPlatforms;
	@CsvBind
	private String productVersionMarketingName;
	@CsvBind
	private String owner;
	@CsvBind
	private String createdOn;
	@CsvBind
	private String createdBy;
	@CsvBind
	private String lastModifiedBy;
	@CsvBind
	private String lastModifiedOn;
	@CsvBind
	private String lastAction;
	@CsvBind
	private String currentState;
	@CsvBind
	private String lastActionBy;
	@CsvBind
	private String lastActionDate;
	@CsvBind
	private String lastActionComments;
	@CsvBind
	private String relatedItem;
	@CsvBind
	private String assemblyMasterId;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getProductVersionNumber() {
		return productVersionNumber;
	}
	public void setProductVersionNumber(String productVersionNumber) {
		this.productVersionNumber = productVersionNumber;
	}
	public String getReleaseMasterName() {
		return releaseMasterName;
	}
	public void setReleaseMasterName(String releaseMasterName) {
		this.releaseMasterName = releaseMasterName;
	}
	public String getProductVersionReleaseDate() {
		return productVersionReleaseDate;
	}
	public void setProductVersionReleaseDate(String productVersionReleaseDate) {
		this.productVersionReleaseDate = productVersionReleaseDate;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getSupportedLanguages() {
		return supportedLanguages;
	}
	public void setSupportedLanguages(String supportedLanguages) {
		this.supportedLanguages = supportedLanguages;
	}
	public String getLanguageImplementationType() {
		return languageImplementationType;
	}
	public void setLanguageImplementationType(String languageImplementationType) {
		this.languageImplementationType = languageImplementationType;
	}
	public String getSupportedPlatforms() {
		return supportedPlatforms;
	}
	public void setSupportedPlatforms(String supportedPlatforms) {
		this.supportedPlatforms = supportedPlatforms;
	}
	public String getProductVersionMarketingName() {
		return productVersionMarketingName;
	}
	public void setProductVersionMarketingName(String productVersionMarketingName) {
		this.productVersionMarketingName = productVersionMarketingName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(String lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getLastActionBy() {
		return lastActionBy;
	}
	public void setLastActionBy(String lastActionBy) {
		this.lastActionBy = lastActionBy;
	}
	public String getLastActionDate() {
		return lastActionDate;
	}
	public void setLastActionDate(String lastActionDate) {
		this.lastActionDate = lastActionDate;
	}
	public String getLastActionComments() {
		return lastActionComments;
	}
	public void setLastActionComments(String lastActionComments) {
		this.lastActionComments = lastActionComments;
	}
	public String getRelatedItem() {
		return relatedItem;
	}
	public void setRelatedItem(String relatedItem) {
		this.relatedItem = relatedItem;
	}
	public String getAssemblyMasterId() {
		return assemblyMasterId;
	}
	public void setAssemblyMasterId(String assemblyMasterId) {
		this.assemblyMasterId = assemblyMasterId;
	}
	
	public String toString() {
		return "ProductReleaseMaster [refId=" + refId + ", productLine="
				+ productLine + ", product_versionNumber="
				+ productVersionNumber + ", releaseMasterName="
				+ releaseMasterName + ", productVersionReleaseDate="
				+ productVersionReleaseDate + ", codeName=" + codeName
				+ ", thumbnailUrl=" + thumbnailUrl + ", supportedLanguages="
				+ supportedLanguages + ", languageImplementationType="
				+ languageImplementationType + ", supportedPlatforms="
				+ supportedPlatforms + ", productVersionMarketingName="
				+ productVersionMarketingName + ", owner=" + owner
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedOn="
				+ lastModifiedOn + ", lastAction=" + lastAction
				+ ", currentState=" + currentState + ", lastActionBy="
				+ lastActionBy + ", lastActionDate=" + lastActionDate
				+ ", lastActionComments=" + lastActionComments
				+ ", relatedItem=" + relatedItem + ", assemblyMasterid="
				+ assemblyMasterId + "]";
	}
}
