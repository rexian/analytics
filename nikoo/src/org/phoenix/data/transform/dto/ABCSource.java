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
public class ABCSource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SerializedName("jcr_path")	
	private String jcrPath;
	@SerializedName("sling_resourceType")	
	private String slingResourceType;
	@SerializedName("jcr_uuid")	
	private String jcrUuid;
	@SerializedName("jcr_mixinTypes")
	private List<String> jcrMixinTypes;
	@SerializedName("jcr_title")
	private String jcrTitle;
	@SerializedName("jcr_created")
	private String jcrCreated;
	@SerializedName("jcr_baseVersion")
	private String jcrBaseVersion;
	@SerializedName("cq_lastReplicationAction")
	private String cqLastReplicationAction;
	@SerializedName("jcr_primaryType")
	private String jcrPrimaryType;
	@SerializedName("cq_lastModifiedBy")
	private String cqLastModifiedBy;
	@SerializedName("cq_template")
	private String cqTemplate;
	@SerializedName("jcr_isCheckedOut")
	private boolean jcrIsCheckedOut;
	private List<String> socialMedia;
	@SerializedName("jcr_predecessors")
	private List<String> jcrPredecessors;
	private String code;
	@SerializedName("jcr_createdBy")
	private String jcrCreatedBy;
	@SerializedName("jcr_versionHistory")
	private String jcrVersionHistory;
	private String background;
	@SerializedName("cq_lastReplicatedBy")
	private String cqLastReplicatedBy;
	@SerializedName("cq_lastModified")
	private String cqLastModified;
	@SerializedName("cq_lastReplicated")
	private String cqLastReplicated;
	@SerializedName("left_navigation")
	private LeftNavigation leftNavigation;
	@SerializedName("description")
	private Description description;
	@SerializedName("speakers")
	private Speakers speakers;
	@SerializedName("keylearning")
	private KeyLearning keyLearning;
	@SerializedName("au_video")
	private AUVideo auVideo;	
	private Details details;	
	private Materials materials;
	@SerializedName("social_share")
	private SocialShare socialShare;
	
	public String getJcrPath() {
		return jcrPath;
	}
	public void setJcrPath(String jcrPath) {
		this.jcrPath = jcrPath;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public void setSlingResourceType(String slingResourceType) {
		this.slingResourceType = slingResourceType;
	}
	public String getJcrUuid() {
		return jcrUuid;
	}
	public void setJcrUuid(String jcrUuid) {
		this.jcrUuid = jcrUuid;
	}
	public List<String> getJcrMixinTypes() {
		return jcrMixinTypes;
	}
	public void setJcrMixinTypes(List<String> jcrMixinTypes) {
		this.jcrMixinTypes = jcrMixinTypes;
	}
	public String getJcrTitle() {
		return jcrTitle;
	}
	public void setJcrTitle(String jcrTitle) {
		this.jcrTitle = jcrTitle;
	}
	public String getJcrCreated() {
		return jcrCreated;
	}
	public void setJcrCreated(String jcrCreated) {
		this.jcrCreated = jcrCreated;
	}
	public String getJcrBaseVersion() {
		return jcrBaseVersion;
	}
	public void setJcrBaseVersion(String jcrBaseVersion) {
		this.jcrBaseVersion = jcrBaseVersion;
	}
	public String getCqLastReplicationAction() {
		return cqLastReplicationAction;
	}
	public void setCqLastReplicationAction(String cqLastReplicationAction) {
		this.cqLastReplicationAction = cqLastReplicationAction;
	}
	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}
	public void setJcrPrimaryType(String jcrPrimaryType) {
		this.jcrPrimaryType = jcrPrimaryType;
	}
	public String getCqLastModifiedBy() {
		return cqLastModifiedBy;
	}
	public void setCqLastModifiedBy(String cqLastModifiedBy) {
		this.cqLastModifiedBy = cqLastModifiedBy;
	}
	public String getCqTemplate() {
		return cqTemplate;
	}
	public void setCqTemplate(String cqTemplate) {
		this.cqTemplate = cqTemplate;
	}
	public boolean getJcrIsCheckedOut() {
		return jcrIsCheckedOut;
	}
	public void setJcrIsCheckedOut(boolean jcrIsCheckedOut) {
		this.jcrIsCheckedOut = jcrIsCheckedOut;
	}
	public List<String> getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(List<String> socialMedia) {
		this.socialMedia = socialMedia;
	}
	public List<String> getJcrPredecessors() {
		return jcrPredecessors;
	}
	public void setJcrPredecessors(List<String> jcrPredecessors) {
		this.jcrPredecessors = jcrPredecessors;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getJcrCreatedBy() {
		return jcrCreatedBy;
	}
	public void setJcrCreatedBy(String jcrCreatedBy) {
		this.jcrCreatedBy = jcrCreatedBy;
	}
	public String getJcrVersionHistory() {
		return jcrVersionHistory;
	}
	public void setJcrVersionHistory(String jcrVersionHistory) {
		this.jcrVersionHistory = jcrVersionHistory;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getCqLastReplicatedBy() {
		return cqLastReplicatedBy;
	}
	public void setCqLastReplicatedBy(String cqLastReplicatedBy) {
		this.cqLastReplicatedBy = cqLastReplicatedBy;
	}
	public String getCqLastModified() {
		return cqLastModified;
	}
	public void setCqLastModified(String cqLastModified) {
		this.cqLastModified = cqLastModified;
	}
	public String getCqLastReplicated() {
		return cqLastReplicated;
	}
	public void setCqLastReplicated(String cqLastReplicated) {
		this.cqLastReplicated = cqLastReplicated;
	}
	public LeftNavigation getLeftNavigation() {
		return leftNavigation;
	}
	public void setLeftNavigation(LeftNavigation leftNavigation) {
		this.leftNavigation = leftNavigation;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public Speakers getSpeakers() {
		return speakers;
	}
	public void setSpeakers(Speakers speakers) {
		this.speakers = speakers;
	}
	public KeyLearning getKeyLearning() {
		return keyLearning;
	}
	public void setKeyLearning(KeyLearning keyLearning) {
		this.keyLearning = keyLearning;
	}
	public AUVideo getAuVideo() {
		return auVideo;
	}
	public void setAuVideo(AUVideo auVideo) {
		this.auVideo = auVideo;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public Materials getMaterials() {
		return materials;
	}
	public void setMaterials(Materials materials) {
		this.materials = materials;
	}
	public SocialShare getSocialShare() {
		return socialShare;
	}
	public void setSocialShare(SocialShare socialShare) {
		this.socialShare = socialShare;
	}
	@Override
	public String toString() {
		return "ABCSource [jcrPath=" + jcrPath + ", slingResourceType="
				+ slingResourceType + ", jcrUuid=" + jcrUuid
				+ ", jcrMixinTypes=" + jcrMixinTypes + ", jcrTitle=" + jcrTitle
				+ ", jcrCreated=" + jcrCreated + ", jcrBaseVersion="
				+ jcrBaseVersion + ", cqLastReplicationAction="
				+ cqLastReplicationAction + ", jcrPrimaryType="
				+ jcrPrimaryType + ", cqLastModifiedBy=" + cqLastModifiedBy
				+ ", cqTemplate=" + cqTemplate + ", jcrIsCheckedOut="
				+ jcrIsCheckedOut + ", socialMedia=" + socialMedia
				+ ", jcrPredecessors=" + jcrPredecessors + ", code=" + code
				+ ", jcrCreatedBy=" + jcrCreatedBy + ", jcrVersionHistory="
				+ jcrVersionHistory + ", background=" + background
				+ ", cqLastReplicatedBy=" + cqLastReplicatedBy
				+ ", cqLastModified=" + cqLastModified + ", cqLastReplicated="
				+ cqLastReplicated + ", leftNavigation=" + leftNavigation
				+ ", description=" + description + ", speakers=" + speakers
				+ ", keyLearning=" + keyLearning + ", auVideo=" + auVideo
				+ ", details=" + details + ", materials=" + materials
				+ ", socialShare=" + socialShare + "]";
	}	
}
