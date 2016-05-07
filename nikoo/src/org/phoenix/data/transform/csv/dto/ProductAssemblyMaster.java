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

public class ProductAssemblyMaster {
	
	@CsvBind
	private String refId;
	@CsvBind
	private String releaseMaster;
	@CsvBind
	private String unitType;
	@CsvBind
	private String alert;
	@CsvBind
	private String assemblyMasterName;
	@CsvBind
	private String productLanguage;
	@CsvBind
	private String productPlatform;
	@CsvBind
	private String lockedType;
	@CsvBind
	private String uniqueQualifier;
	@CsvBind
	private String upi1MasterId;
	@CsvBind
	private String parentAssembly;
	@CsvBind
	private String quantity;
	@CsvBind
	private String relatedItem;
	@CsvBind
	private String relationshipType;
	@CsvBind
	private String description;
	@CsvBind
	private String productLanguageOriginal;	
	@CsvBind
	private String baseLanguageCode;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getReleaseMaster() {
		return releaseMaster;
	}
	public void setReleaseMaster(String releaseMaster) {
		this.releaseMaster = releaseMaster;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getAssemblyMasterName() {
		return assemblyMasterName;
	}
	public void setAssemblyMasterName(String assemblyMasterName) {
		this.assemblyMasterName = assemblyMasterName;
	}
	public String getProductLanguage() {
		return productLanguage;
	}
	public void setProductLanguage(String productLanguage) {
		this.productLanguage = productLanguage;
	}
	public String getProductPlatform() {
		return productPlatform;
	}
	public void setProductPlatform(String productPlatform) {
		this.productPlatform = productPlatform;
	}
	public String getLockedType() {
		return lockedType;
	}
	public void setLockedType(String lockedType) {
		this.lockedType = lockedType;
	}
	public String getUniqueQualifier() {
		return uniqueQualifier;
	}
	public void setUniqueQualifier(String uniqueQualifier) {
		this.uniqueQualifier = uniqueQualifier;
	}
	public String getUpi1MasterId() {
		return upi1MasterId;
	}
	public void setUpi1MasterId(String upi1MasterId) {
		this.upi1MasterId = upi1MasterId;
	}
	public String getParentAssembly() {
		return parentAssembly;
	}
	public void setParentAssembly(String parentAssembly) {
		this.parentAssembly = parentAssembly;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRelatedItem() {
		return relatedItem;
	}
	public void setRelatedItem(String relatedItem) {
		this.relatedItem = relatedItem;
	}
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductLanguageOriginal() {
		return productLanguageOriginal;
	}
	public void setProductLanguageOriginal(String productLanguageOriginal) {
		this.productLanguageOriginal = productLanguageOriginal;
	}	
	
	public String getBaseLanguageCode() {
		return baseLanguageCode;
	}
	public void setBaseLanguageCode(String baseLanguageCode) {
		this.baseLanguageCode = baseLanguageCode;
	}
	@Override
	public String toString() {
		return "ProductAssemblyMaster [refId=" + refId + ", releaseMaster="
				+ releaseMaster + ", unitType=" + unitType + ", alert=" + alert
				+ ", assemblyMasterName=" + assemblyMasterName
				+ ", productLanguage=" + productLanguage + ", productPlatform="
				+ productPlatform + ", lockedType=" + lockedType
				+ ", uniqueQualifier=" + uniqueQualifier + ", upi1MasterId="
				+ upi1MasterId + ", parentAssembly=" + parentAssembly
				+ ", quantity=" + quantity + ", relatedItem=" + relatedItem
				+ ", relationshipType=" + relationshipType + ", description="
				+ description + ", productLanguageOriginal="
				+ productLanguageOriginal + ", baseLanguageCode=" + baseLanguageCode
				+ "]";
	}	
}
