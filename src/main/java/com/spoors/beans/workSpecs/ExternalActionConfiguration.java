package com.spoors.beans.workSpecs;

import java.util.List;

public class ExternalActionConfiguration {
	private Long externalActionConfigurationId;
	private Long workSpecId;
	private Long workActionSpecId;
	private boolean canPerformActionByCustomer;
	private Integer expiryTimeLimit;
	private boolean canRegenerateNewLink;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private Long companyId;
	private List<ExternalActionUrlSharingDetails> externalActionUrlSharingDetails;
	private String messageBody;
	
	private boolean enableAutomaticEmailTrigger;
	private boolean canOnlyPerformByExternalUser;
	private boolean sendRemainderNotification; 
	private int reminderCount;
	private int reminderFrequency;
	private String remainderNotificationMessageBody;
	private String messageSubject;
	private String remainderNotificationMessageSubject;
	private String customMessageAfterExternalActionCompletion;

	
	public Long getExternalActionConfigurationId() {
		return externalActionConfigurationId;
	}
	public void setExternalActionConfigurationId(Long externalActionConfigurationId) {
		this.externalActionConfigurationId = externalActionConfigurationId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	public boolean isCanPerformActionByCustomer() {
		return canPerformActionByCustomer;
	}
	public void setCanPerformActionByCustomer(boolean canPerformActionByCustomer) {
		this.canPerformActionByCustomer = canPerformActionByCustomer;
	}
	public Integer getExpiryTimeLimit() {
		return expiryTimeLimit;
	}
	public void setExpiryTimeLimit(Integer expiryTimeLimit) {
		this.expiryTimeLimit = expiryTimeLimit;
	}
	public boolean getCanRegenerateNewLink() {
		return canRegenerateNewLink;
	}
	public void setCanRegenerateNewLink(boolean canRegenerateNewLink) {
		this.canRegenerateNewLink = canRegenerateNewLink;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public List<ExternalActionUrlSharingDetails> getExternalActionUrlSharingDetails() {
		return externalActionUrlSharingDetails;
	}
	public void setExternalActionUrlSharingDetails(List<ExternalActionUrlSharingDetails> externalActionUrlSharingDetails) {
		this.externalActionUrlSharingDetails = externalActionUrlSharingDetails;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public boolean getEnableAutomaticEmailTrigger() {
		return enableAutomaticEmailTrigger;
	}
	public void setEnableAutomaticEmailTrigger(boolean enableAutomaticEmailTrigger) {
		this.enableAutomaticEmailTrigger = enableAutomaticEmailTrigger;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public boolean isCanOnlyPerformByExternalUser() {
		return canOnlyPerformByExternalUser;
	}
	public void setCanOnlyPerformByExternalUser(boolean canOnlyPerformByExternalUser) {
		this.canOnlyPerformByExternalUser = canOnlyPerformByExternalUser;
	}
	public boolean isSendRemainderNotification() {
		return sendRemainderNotification;
	}
	public void setSendRemainderNotification(boolean sendRemainderNotification) {
		this.sendRemainderNotification = sendRemainderNotification;
	}
	public int getReminderCount() {
		return reminderCount;
	}
	public void setReminderCount(int reminderCount) {
		this.reminderCount = reminderCount;
	}
	public int getReminderFrequency() {
		return reminderFrequency;
	}
	public void setReminderFrequency(int reminderFrequency) {
		this.reminderFrequency = reminderFrequency;
	}
	public String getRemainderNotificationMessageBody() {
		return remainderNotificationMessageBody;
	}
	public void setRemainderNotificationMessageBody(String remainderNotificationMessageBody) {
		this.remainderNotificationMessageBody = remainderNotificationMessageBody;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getRemainderNotificationMessageSubject() {
		return remainderNotificationMessageSubject;
	}
	public void setRemainderNotificationMessageSubject(String remainderNotificationMessageSubject) {
		this.remainderNotificationMessageSubject = remainderNotificationMessageSubject;
	}
	public String getCustomMessageAfterExternalActionCompletion() {
		return customMessageAfterExternalActionCompletion;
	}
	public void setCustomMessageAfterExternalActionCompletion(String customMessageAfterExternalActionCompletion) {
		this.customMessageAfterExternalActionCompletion = customMessageAfterExternalActionCompletion;
	}
}
