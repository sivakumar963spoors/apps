package com.spoors.beans.workSpecs;

public class WorkSpecFeedBackFormSpecMap {

	private long id;
	private long workSpecId;
	private boolean deleted;
	private String formSpecUniqueId;
	private String phoneNumbersOpp;
	private String phoneNumbers;
	private String emailAddressOpp;
	private String emailAddress;
	
	private WorkFeedbackNotificationSpecs workFeedbackNotificationSpecs;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public WorkFeedbackNotificationSpecs getWorkFeedbackNotificationSpecs() {
		return workFeedbackNotificationSpecs;
	}
	public void setWorkFeedbackNotificationSpecs(WorkFeedbackNotificationSpecs workFeedbackNotificationSpecs) {
		this.workFeedbackNotificationSpecs = workFeedbackNotificationSpecs;
	}
	public String getPhoneNumbersOpp() {
		return phoneNumbersOpp;
	}
	public void setPhoneNumbersOpp(String phoneNumbersOpp) {
		this.phoneNumbersOpp = phoneNumbersOpp;
	}
	public String getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getEmailAddressOpp() {
		return emailAddressOpp;
	}
	public void setEmailAddressOpp(String emailAddressOpp) {
		this.emailAddressOpp = emailAddressOpp;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
