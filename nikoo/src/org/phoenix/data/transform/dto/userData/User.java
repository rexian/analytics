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

package org.phoenix.data.transform.dto.userData;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	private String userRef;
	private String firstName;
	private String lastName;
	private String email;
	private String companyName;
	private String jobTitle;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String stateID;
	private String foreignState;
	private String zip;
	private String countryID;
	private String screenName;
	private String phoneNumber;
	private String phone2Number;
	private String middleInitial;
	private String preferredName;
	private String email2;
	private String faxNumber;
	private String publicBio;
	@XmlElement (name="report-classification")
	private String reportClassification;
	private String registered;
	private String paid;
	@XmlElement (name="create-date")
	private String createDate;
	@XmlElement (name="last-modified-date")
	private String lastModifiedDate;
	private String badgeID;
	private String publicViewPrivateSchedule;
	private String privateViewPrivateSchedule;
	private String cannotViewOrSchedule;
	@XmlElement(name="About-Me")
	private String aboutMe;
	@XmlElement(name="Attendee-Type")
	private List<String> attendeeType;
	@XmlElement(name="Blog-Address")
	private String blogAddress;
	@XmlElement (name="Facebook-Profile-Id")
	private String facebookProfileId;
	@XmlElement (name="Industry-Group")
	private String industryGroup;
	@XmlElement (name="Industry-Segment")
	private String industrySegment;
	@XmlElement (name="LinkedIn-Profile-Page")
	private String linkedinProfilePage;
	@XmlElement (name="Occupation-Profession")
	private String occupationProfession;
	@XmlElement (name="Registration-Package-Names")
	private List<String> registrationPackageNames;
	@XmlElement (name="Twitter-Handle")
	private String twitterHandle;
	@XmlElement(name="regcodes")
	private Regcodes regcodes;
	@XmlElement(name="schedule")
	private Schedule schedule;
	
	public String getUserRef() {
		return userRef;
	}
	public void setUserRef(String userRef) {
		this.userRef = userRef;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateID() {
		return stateID;
	}
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}
	public String getForeignState() {
		return foreignState;
	}
	public void setForeignState(String foreignState) {
		this.foreignState = foreignState;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhone2Number() {
		return phone2Number;
	}
	public void setPhone2Number(String phone2Number) {
		this.phone2Number = phone2Number;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getPreferredName() {
		return preferredName;
	}
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getPublicBio() {
		return publicBio;
	}
	public void setPublicBio(String publicBio) {
		this.publicBio = publicBio;
	}
	public String getReportClassification() {
		return reportClassification;
	}
	public void setReportClassification(String reportClassification) {
		this.reportClassification = reportClassification;
	}
	public String getRegistered() {
		return registered;
	}
	public void setRegistered(String registered) {
		this.registered = registered;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getBadgeID() {
		return badgeID;
	}
	public void setBadgeID(String badgeID) {
		this.badgeID = badgeID;
	}
	public String getPublicViewPrivateSchedule() {
		return publicViewPrivateSchedule;
	}
	public void setPublicViewPrivateSchedule(String publicViewPrivateSchedule) {
		this.publicViewPrivateSchedule = publicViewPrivateSchedule;
	}
	public String getPrivateViewPrivateSchedule() {
		return privateViewPrivateSchedule;
	}
	public void setPrivateViewPrivateSchedule(String privateViewPrivateSchedule) {
		this.privateViewPrivateSchedule = privateViewPrivateSchedule;
	}
	public String getCannotViewOrSchedule() {
		return cannotViewOrSchedule;
	}
	public void setCannotViewOrSchedule(String cannotViewOrSchedule) {
		this.cannotViewOrSchedule = cannotViewOrSchedule;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public List<String> getAttendeeType() {
		return attendeeType;
	}
	public void setAttendeeType(List<String> attendeeType) {
		this.attendeeType = attendeeType;
	}
	public String getBlogAddress() {
		return blogAddress;
	}
	public void setBlogAddress(String blogAddress) {
		this.blogAddress = blogAddress;
	}
	public String getFacebookProfileId() {
		return facebookProfileId;
	}
	public void setFacebookProfileId(String facebookProfileId) {
		this.facebookProfileId = facebookProfileId;
	}
	public String getIndustryGroup() {
		return industryGroup;
	}
	public void setIndustryGroup(String industryGroup) {
		this.industryGroup = industryGroup;
	}
	public String getIndustrySegment() {
		return industrySegment;
	}
	public void setIndustrySegment(String industrySegment) {
		this.industrySegment = industrySegment;
	}
	public String getLinkedinProfilePage() {
		return linkedinProfilePage;
	}
	public void setLinkedinProfilePage(String linkedinProfilePage) {
		this.linkedinProfilePage = linkedinProfilePage;
	}
	public String getOccupationProfession() {
		return occupationProfession;
	}
	public void setOccupationProfession(String occupationProfession) {
		this.occupationProfession = occupationProfession;
	}
	public List<String> getRegistrationPackageNames() {
		return registrationPackageNames;
	}
	public void setRegistrationPackageNames(List<String> registrationPackageNames) {
		this.registrationPackageNames = registrationPackageNames;
	}
	public String getTwitterHandle() {
		return twitterHandle;
	}
	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}
	public Regcodes getRegcodes() {
		return regcodes;
	}
	public void setRegcodes(Regcodes regcodes) {
		this.regcodes = regcodes;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}	
	
}
