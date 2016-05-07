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

public class ProductFeature {
	
	@CsvBind
	private String featureId;
	@CsvBind
	private String featureNameVersionless;
	@CsvBind
	private String featureName;
	@CsvBind
	private String licenseIntegrationOwner;
	@CsvBind
	private String featureType;
	@CsvBind
	private String productKey;
	@CsvBind
	private String featureLegalName;
	@CsvBind
	private String productLineCode;
	@CsvBind
	private String typeCode;
	@CsvBind
	private String comments;
	@CsvBind
	private String offeringId;
	@CsvBind
	private String featureLookup;
	@CsvBind
	private String wfExec;
	@CsvBind
	private String buildLookupValue;
	@CsvBind
	private String itemType;
	@CsvBind
	private String path;
	
	
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	public String getFeatureNameVersionless() {
		return featureNameVersionless;
	}
	public void setFeatureNameVersionless(String featureNameVersionless) {
		this.featureNameVersionless = featureNameVersionless;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getLicenseIntegrationOwner() {
		return licenseIntegrationOwner;
	}
	public void setLicenseIntegrationOwner(String licenseIntegrationOwner) {
		this.licenseIntegrationOwner = licenseIntegrationOwner;
	}
	public String getFeatureType() {
		return featureType;
	}
	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getFeatureLegalName() {
		return featureLegalName;
	}
	public void setFeatureLegalName(String featureLegalName) {
		this.featureLegalName = featureLegalName;
	}
	public String getProductLineCode() {
		return productLineCode;
	}
	public void setProductLineCode(String productLineCode) {
		this.productLineCode = productLineCode;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOfferingId() {
		return offeringId;
	}
	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}
	public String getFeatureLookup() {
		return featureLookup;
	}
	public void setFeatureLookup(String featureLookup) {
		this.featureLookup = featureLookup;
	}
	public String getWfExec() {
		return wfExec;
	}
	public void setWfExec(String wfExec) {
		this.wfExec = wfExec;
	}
	public String getBuildLookupValue() {
		return buildLookupValue;
	}
	public void setBuildLookupValue(String buildLookupValue) {
		this.buildLookupValue = buildLookupValue;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString() {
		return "ProductFeature [featureId=" + featureId
				+ ", featureNameVersionless=" + featureNameVersionless
				+ ", featureName=" + featureName + ", licenseIntegrationOwner="
				+ licenseIntegrationOwner + ", featureType=" + featureType
				+ ", productKey=" + productKey + ", featureLegalName="
				+ featureLegalName + ", productLineCode=" + productLineCode
				+ ", typeCode=" + typeCode + ", comments=" + comments
				+ ", offeringId=" + offeringId + ", featureLookup="
				+ featureLookup + ", wfExec=" + wfExec + ", buildLookupValue="
				+ buildLookupValue + ", itemType=" + itemType + ", path="
				+ path + "]";
	}
}
