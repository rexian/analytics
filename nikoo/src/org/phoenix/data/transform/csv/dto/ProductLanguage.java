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

public class ProductLanguage {
	
	@CsvBind
	private String refId;
	@CsvBind
	private String code;
	@CsvBind
	private String name;
	@CsvBind
	private String locale;	
	@CsvBind
	private String state;
	@CsvBind
	private String format;
	@CsvBind
	private String displayNameLanguage;
	
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDisplayNameLanguage() {
		return displayNameLanguage;
	}
	public void setDisplayNameLanguage(String displayNameLanguage) {
		this.displayNameLanguage = displayNameLanguage;
	}
	
	public String toString() {
		return "ProductLanguage [refId=" + refId + ", code=" + code
				+ ", name=" + name + ", locale=" + locale + ", state=" + state + ", format=" + format
				+ ", displayNameLanguage=" + displayNameLanguage + "]";
	}
}
