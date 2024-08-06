package com.spoors.beans.workSpecs;

import java.util.List;

public class WorkDataPostingConfiguration {
	
	private long workDataPostingConfigurationId;
	private long workSpecId;
	private long companyId;
	private long createdBy;
	private long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private String submissionUrl;
	private String modificationUrl;
	private boolean enable;
	private boolean onWorkFieldsModification;
	private boolean onStatusChange;
	private List<WorkDataPostingCondition> workDataPostingConditions;
	private List<WorkDataPostResponseCaptureConfigurations> WorkDataPostResponseCaptureConfigurations;
	private List<WorkDataPostCriteriaCondition> WorkDataPostCriteriaCondition;
	private boolean onWorkCreation;
	private Integer authenticationType;
	private String jwtSubject;
	private Long jwtExpiryTimeInMillis;
	private String userName;
	private String password;
	private String jwtHeaderName;
	private String jwtIssuer;
	
	private String authContentSourceUrl;
	private String escalationMailIds;
	private String escalationMailSubjectTemplate;
	private String escalationMailBodyTemplate;
	
	private String dataPostTime;
	private String responseReceivedTime;
	
	private int actionHistoryFormat;
	
	private boolean enabledDataPushFailedAlert;
	private int alertStoppingDurationInHours;
	private String alertDisabledTime;
	
	private int retryCount;
	private int retryMode;
	private int retryInterval;
	private String successPattern;
	private String emailIds;
	
	private int includeBase64EncodedStringForMediaFields;
	
	private boolean enableWorkPdfAttachment;
	private boolean includeMimeType;
	private boolean includeEmptyFieldsInJson;
	private boolean onWorkInvitationAccept;
	private boolean includeAdditionalParameter;
	private String workTrackingLinkLabel;
	private String employeeTrackingLinkLabel;
	private String sendEtaLabel;
    private Integer conjunction;  
    
    private boolean sendDataPushThroughJMSInSync;
    private String  workDataPostingConfigurationName ;

	
	public static final int PUSH_EVENT_TYPE_REGULAR = 1;//REGULAR(FIRST TIME)
	public static final int PUSH_EVENT_TYPE_RETRY = 2;//RETRY(FROM SECOND TIME ONWARDS)
	
	public static final int SIMPLIFIED_ACTION_HISTORY_FORMAT = 1;
	public static final int OLD_REGULAR_ACTION_HISTORY_FORMAT = 2;
	public static final int SIMPLIFIED_FLAT_DATA_PUSH = 3;
	
	public static final int ENABLED = 1;

	
	public String getDataPostTime() {
		return dataPostTime;
	}
	public void setDataPostTime(String dataPostTime) {
		this.dataPostTime = dataPostTime;
	}
	public String getResponseReceivedTime() {
		return responseReceivedTime;
	}
	public void setResponseReceivedTime(String responseReceivedTime) {
		this.responseReceivedTime = responseReceivedTime;
	}
	public String getAuthContentSourceUrl() {
		return authContentSourceUrl;
	}
	public void setAuthContentSourceUrl(String authContentSourceUrl) {
		this.authContentSourceUrl = authContentSourceUrl;
	}
	public String getJwtIssuer() {
		return jwtIssuer;
	}
	public void setJwtIssuer(String jwtIssuer) {
		this.jwtIssuer = jwtIssuer;
	}
	public Integer getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(Integer authenticationType) {
		this.authenticationType = authenticationType;
	}
	public String getJwtSubject() {
		return jwtSubject;
	}
	public void setJwtSubject(String jwtSubject) {
		this.jwtSubject = jwtSubject;
	}
	public Long getJwtExpiryTimeInMillis() {
		return jwtExpiryTimeInMillis;
	}
	public void setJwtExpiryTimeInMillis(Long jwtExpiryTimeInMillis) {
		this.jwtExpiryTimeInMillis = jwtExpiryTimeInMillis;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJwtHeaderName() {
		return jwtHeaderName;
	}
	public void setJwtHeaderName(String jwtHeaderName) {
		this.jwtHeaderName = jwtHeaderName;
	}
	
	public long getWorkDataPostingConfigurationId() {
		return workDataPostingConfigurationId;
	}
	public void setWorkDataPostingConfigurationId(
			long workDataPostingConfigurationId) {
		this.workDataPostingConfigurationId = workDataPostingConfigurationId;
	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getSubmissionUrl() {
		return submissionUrl;
	}
	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getModificationUrl() {
		return modificationUrl;
	}
	public void setModificationUrl(String modificationUrl) {
		this.modificationUrl = modificationUrl;
	}
	public boolean isOnWorkFieldsModification() {
		return onWorkFieldsModification;
	}
	public void setOnWorkFieldsModification(boolean onWorkFieldsModification) {
		this.onWorkFieldsModification = onWorkFieldsModification;
	}
	public boolean isOnStatusChange() {
		return onStatusChange;
	}
	public void setOnStatusChange(boolean onStatusChange) {
		this.onStatusChange = onStatusChange;
	}
	public List<WorkDataPostingCondition> getWorkDataPostingConditions() {
		return workDataPostingConditions;
	}
	public void setWorkDataPostingConditions(
			List<WorkDataPostingCondition> workDataPostingConditions) {
		this.workDataPostingConditions = workDataPostingConditions;
	}
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public boolean isOnWorkCreation() {
		return onWorkCreation;
	}
	public void setOnWorkCreation(boolean onWorkCreation) {
		this.onWorkCreation = onWorkCreation;
	}
	public String getEscalationMailSubjectTemplate() {
		return escalationMailSubjectTemplate;
	}

	public void setEscalationMailSubjectTemplate(String escalationMailSubjectTemplate) {
		this.escalationMailSubjectTemplate = escalationMailSubjectTemplate;
	}

	public String getEscalationMailBodyTemplate() {
		return escalationMailBodyTemplate;
	}

	public void setEscalationMailBodyTemplate(String escalationMailBodyTemplate) {
		this.escalationMailBodyTemplate = escalationMailBodyTemplate;
	}
	public String getEscalationMailIds() {
		return escalationMailIds;
	}
	public void setEscalationMailIds(String escalationMailIds) {
		this.escalationMailIds = escalationMailIds;
	}
	public int getActionHistoryFormat() {
		return actionHistoryFormat;
	}
	public void setActionHistoryFormat(int actionHistoryFormat) {
		this.actionHistoryFormat = actionHistoryFormat;
	}
	public boolean isEnabledDataPushFailedAlert() {
		return enabledDataPushFailedAlert;
	}
	public void setEnabledDataPushFailedAlert(boolean enabledDataPushFailedAlert) {
		this.enabledDataPushFailedAlert = enabledDataPushFailedAlert;
	}
	public int getAlertStoppingDurationInHours() {
		return alertStoppingDurationInHours;
	}
	public void setAlertStoppingDurationInHours(int alertStoppingDurationInHours) {
		this.alertStoppingDurationInHours = alertStoppingDurationInHours;
	}
	public String getAlertDisabledTime() {
		return alertDisabledTime;
	}
	public void setAlertDisabledTime(String alertDisabledTime) {
		this.alertDisabledTime = alertDisabledTime;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public int getRetryMode() {
		return retryMode;
	}
	public void setRetryMode(int retryMode) {
		this.retryMode = retryMode;
	}
	public int getRetryInterval() {
		return retryInterval;
	}
	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}
	public String getSuccessPattern() {
		return successPattern;
	}
	public void setSuccessPattern(String successPattern) {
		this.successPattern = successPattern;
	}
	public String getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(String emailIds) {
		this.emailIds = emailIds;
	}
	public int getIncludeBase64EncodedStringForMediaFields() {
		return includeBase64EncodedStringForMediaFields;
	}
	public void setIncludeBase64EncodedStringForMediaFields(
			int includeBase64EncodedStringForMediaFields) {
		this.includeBase64EncodedStringForMediaFields = includeBase64EncodedStringForMediaFields;
	}
	public boolean isEnableWorkPdfAttachment() {
		return enableWorkPdfAttachment;
	}
	public void setEnableWorkPdfAttachment(boolean enableWorkPdfAttachment) {
		this.enableWorkPdfAttachment = enableWorkPdfAttachment;
	}
	public boolean isIncludeMimeType() {
		return includeMimeType;
	}
	public void setIncludeMimeType(boolean includeMimeType) {
		this.includeMimeType = includeMimeType;
	}
	

	public boolean isIncludeEmptyFieldsInJson() {
		return includeEmptyFieldsInJson;
	}
	public void setIncludeEmptyFieldsInJson(boolean includeEmptyFieldsInJson) {
		this.includeEmptyFieldsInJson = includeEmptyFieldsInJson;
	}
	public boolean isOnWorkInvitationAccept() {
		return onWorkInvitationAccept;
	}
	public void setOnWorkInvitationAccept(boolean onWorkInvitationAccept) {
		this.onWorkInvitationAccept = onWorkInvitationAccept;
	}
	public boolean isIncludeAdditionalParameter() {
		return includeAdditionalParameter;
	}
	public void setIncludeAdditionalParameter(boolean includeAdditionalParameter) {
		this.includeAdditionalParameter = includeAdditionalParameter;
	}
	public String getWorkTrackingLinkLabel() {
		return workTrackingLinkLabel;
	}
	public void setWorkTrackingLinkLabel(String workTrackingLinkLabel) {
		this.workTrackingLinkLabel = workTrackingLinkLabel;
	}
	public String getEmployeeTrackingLinkLabel() {
		return employeeTrackingLinkLabel;
	}
	public void setEmployeeTrackingLinkLabel(String employeeTrackingLinkLabel) {
		this.employeeTrackingLinkLabel = employeeTrackingLinkLabel;
	}
	public String getSendEtaLabel() {
		return sendEtaLabel;
	}
	public void setSendEtaLabel(String sendEtaLabel) {
		this.sendEtaLabel = sendEtaLabel;
	}
	public List<WorkDataPostResponseCaptureConfigurations> getWorkDataPostResponseCaptureConfigurations() {
		return WorkDataPostResponseCaptureConfigurations;
	}
	public void setWorkDataPostResponseCaptureConfigurations(
			List<WorkDataPostResponseCaptureConfigurations> workDataPostResponseCaptureConfigurations) {
		WorkDataPostResponseCaptureConfigurations = workDataPostResponseCaptureConfigurations;
	}
	public List<WorkDataPostCriteriaCondition> getWorkDataPostCriteriaCondition() {
		return WorkDataPostCriteriaCondition;
	}
	public void setWorkDataPostCriteriaCondition(List<WorkDataPostCriteriaCondition> workDataPostCriteriaCondition) {
		WorkDataPostCriteriaCondition = workDataPostCriteriaCondition;
	}
	public Integer getConjunction() {
		return conjunction;
	}
	public void setConjunction(Integer conjunction) {
		this.conjunction = conjunction;
	}
	public boolean isSendDataPushThroughJMSInSync() {
		return sendDataPushThroughJMSInSync;
	}
	public void setSendDataPushThroughJMSInSync(boolean sendDataPushThroughJMSInSync) {
		this.sendDataPushThroughJMSInSync = sendDataPushThroughJMSInSync;
	}
	public String getWorkDataPostingConfigurationName() {
		return workDataPostingConfigurationName;
	}
	public void setWorkDataPostingConfigurationName(String workDataPostingConfigurationName) {
		this.workDataPostingConfigurationName = workDataPostingConfigurationName;
	}
	
	
}
