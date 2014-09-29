package org.phoenix.mr.design.structure.productreview.xml;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class UserReview {
	
	private String userId = null;
	private String profileName = null;
	private String helpfulness = null;
	private double score = 0.0;
	private long time = 0;
	private String summary = null;
	private String text = null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getHelpfulness() {
		return helpfulness;
	}
	public void setHelpfulness(String helpfulness) {
		this.helpfulness = helpfulness;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "UserReview [userId=" + userId + ", profileName=" + profileName
				+ ", helpfulness=" + helpfulness + ", score=" + score
				+ ", time=" + time + ", summary=" + summary + ", text=" + text
				+ "]";
	}	
}
