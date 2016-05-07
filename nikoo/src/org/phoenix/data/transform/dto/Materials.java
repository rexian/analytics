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
public class Materials  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("jcr_primaryType")
	private String jcrPrimaryType;
	@SerializedName("material")
	private List<Material> material;
	
	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(String jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	public List<Material> getMaterial() {
		return material;
	}
	public void setMaterial(List<Material> material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "Materials [jcrPrimaryType=" + jcrPrimaryType + ", material="
				+ material + "]";
	}	
}