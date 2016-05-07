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
public class SocialShare  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("displayShareButtonLarge")
	private boolean displayShareButtonLarge;
	private List<String> socialMedia;
	@SerializedName("sling_resourceType")
	private String slingResourceType;
	@SerializedName("displayShareButtonMedium")
	private boolean displayShareButtonMedium;
	@SerializedName("displayShareButtonSmall")
	private boolean displayShareButtonSmall;
	@SerializedName("jcr_primaryType")
	private boolean jcrPrimaryType;
	
	public boolean isDisplayShareButtonLarge() {
		return displayShareButtonLarge;
	}
	public void setDisplayShareButtonLarge(boolean displayShareButtonLarge) {
		this.displayShareButtonLarge = displayShareButtonLarge;
	}
	public List<String> getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(List<String> socialMedia) {
		this.socialMedia = socialMedia;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public void setSlingResourceType(String slingResourceType) {
		this.slingResourceType = slingResourceType;
	}
	public boolean isDisplayShareButtonMedium() {
		return displayShareButtonMedium;
	}
	public void setDisplayShareButtonMedium(boolean displayShareButtonMedium) {
		this.displayShareButtonMedium = displayShareButtonMedium;
	}
	public boolean isDisplayShareButtonSmall() {
		return displayShareButtonSmall;
	}
	public void setDisplayShareButtonSmall(boolean displayShareButtonSmall) {
		this.displayShareButtonSmall = displayShareButtonSmall;
	}
	public boolean isJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(boolean jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	@Override
	public String toString() {
		return "SocialShare [displayShareButtonLarge="
				+ displayShareButtonLarge + ", socialMedia=" + socialMedia
				+ ", slingResourceType=" + slingResourceType
				+ ", displayShareButtonMedium=" + displayShareButtonMedium
				+ ", displayShareButtonSmall=" + displayShareButtonSmall
				+ ", jcrPrimaryType=" + jcrPrimaryType + "]";
	}	
}
