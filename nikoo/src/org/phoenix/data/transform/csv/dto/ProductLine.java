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

public class ProductLine {
	
	@CsvBind
	private String productLineCode;
	@CsvBind
	private String productLineName;
	@CsvBind
	private String solutionType;
	@CsvBind
	private String productDivision;
	@CsvBind
	private String aaaCode;
	@CsvBind
	private String productLineSapName;
	@CsvBind
	private String determinationProductLineCode;
	@CsvBind
	private String profitCenter;
	@CsvBind
	private String solutionDivision;
	@CsvBind
	private String productReleaseDate;
	@CsvBind
	private String productLineMarketingName;
	@CsvBind
	private String publisher;
	@CsvBind
	private String currentState;
	@CsvBind
	private String relatedItem;
	@CsvBind
	private String releaseMasterid;
	
	public String getProductLineCode() {
		return productLineCode;
	}
	public void setProductLineCode(String productLineCode) {
		this.productLineCode = productLineCode;
	}
	public String getProductLineName() {
		return productLineName;
	}
	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}
	public String getSolutionType() {
		return solutionType;
	}
	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
	public String getProductDivision() {
		return productDivision;
	}
	public void setProductDivision(String productDivision) {
		this.productDivision = productDivision;
	}
	public String getAaaCode() {
		return aaaCode;
	}
	public void setAaaCode(String aaaCode) {
		this.aaaCode = aaaCode;
	}
	public String getProductLineSapName() {
		return productLineSapName;
	}
	public void setProductLineSapName(String productLineSapName) {
		this.productLineSapName = productLineSapName;
	}
	public String getDeterminationProductLineCode() {
		return determinationProductLineCode;
	}
	public void setDeterminationProductLineCode(String determinationProductLineCode) {
		this.determinationProductLineCode = determinationProductLineCode;
	}
	public String getProfitCenter() {
		return profitCenter;
	}
	public void setProfitCenter(String profitCenter) {
		this.profitCenter = profitCenter;
	}
	public String getSolutionDivision() {
		return solutionDivision;
	}
	public void setSolutionDivision(String solutionDivision) {
		this.solutionDivision = solutionDivision;
	}
	public String getProductReleaseDate() {
		return productReleaseDate;
	}
	public void setProductReleaseDate(String productReleaseDate) {
		this.productReleaseDate = productReleaseDate;
	}
	public String getProductLineMarketingName() {
		return productLineMarketingName;
	}
	public void setProductLineMarketingName(String productLineMarketingName) {
		this.productLineMarketingName = productLineMarketingName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getRelatedItem() {
		return relatedItem;
	}
	public void setRelatedItem(String relatedItem) {
		this.relatedItem = relatedItem;
	}
	public String getReleaseMasterid() {
		return releaseMasterid;
	}
	public void setReleaseMasterid(String releaseMasterid) {
		this.releaseMasterid = releaseMasterid;
	}
	
	public String toString() {
		return "ProductLine [productLineCode=" + productLineCode
				+ ", productLineName=" + productLineName + ", solutionType="
				+ solutionType + ", productDivision=" + productDivision
				+ ", aaaCode=" + aaaCode + ", productLineSapName="
				+ productLineSapName + ", determinationProductLineCode="
				+ determinationProductLineCode + ", profitCenter="
				+ profitCenter + ", solutionDivision=" + solutionDivision
				+ ", productReleaseDate=" + productReleaseDate
				+ ", productLineMarketingName=" + productLineMarketingName
				+ ", publisher=" + publisher + ", currentState=" + currentState
				+ ", relatedItem=" + relatedItem + ", releaseMasterid="
				+ releaseMasterid + "]";
	}
}
