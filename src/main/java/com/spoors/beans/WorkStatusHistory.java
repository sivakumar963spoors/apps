package com.spoors.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.spoors.beans.http.request.Location;
import com.spoors.util.Api;

public class WorkStatusHistory implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	private long workStatusHistoryId;
//	private long workStatusId;
	private long workSpecId;
	private long workId;
	private long workActionSpecId;
	private Long formId;
	private long modifiedBy;
	private String workStatusCreatedTime;
	private String workStatuHistoryCreatedTime;
	private String workStatusModifiedTime;
	private String clientWorkStatusHistoryId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<FormDisplay> formDisplays;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String workActionName;
	private boolean completed;
	private String completedTime;
	private String clientWorkStatusId;
	private String clientWorkId;
	private String clientFormId;
	private String tzo;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String locationAddrr;
	private Location  location;
	private  Long locationId;
	private String  serverWorkStatuHistoryCreatedTime;
	private Long checkInId;
	
	private Long actionTat;
	
	private boolean forcePerformWork = false;
	
	private boolean canView;
	private String empName;
	
	private Long workCheckInId;
	
	private boolean canEditCompletedAction = false;
	private Long companyId;
	
	private String clientCode;
	
	//Deva,2018-08-04 , following 3 are for ibgroup repush option, for web use only.So using @JsonIgnore
	private boolean triggerPoint;
	private String actionButtonName;// possible values are INQUEUE,PUSHED,RETRY
	private String currentPushStatus;
	private String slab;
	private int triggerEvent;
	private String exportText;
	private String formDisplayMapJson;
	private int validity=1;
	
	private boolean invalidateHistory;
	private boolean isDraft;
	private Boolean draftAction;
	private String workStatuHistoryCreatedTimeTZ;
	private String workStatuHistoryCreatedTimeForViewScreen;
	private Long processingTime;
	private boolean enableApiLogs;
	private boolean isSystemActionSkipped;
	private boolean skippedSystemAction;
	private String 	sourceOfAction;
	private int bulkActionPerform;
	private String workName;
	private String lastActionDays;
	
	private long actionCount;
	
	public static final int VALIDITY_FOR_ACTIVE = 1;
	public static final int VALIDITY_FOR_INVALID_AND_DONT_REUSE = 2;
	public static final int VALIDITY_FOR_INVALID_AND_REUSE_PAST_DATA = 3;
	@JsonIgnore
	private List<FormPageSpec> formPageSpec;
	
	public String getLocationAddrr() {
		return locationAddrr;
	}
	public void setLocationAddrr(String locationAddrr) {
		this.locationAddrr = locationAddrr;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public long getWorkId() {
		return workId;
	}
	public void setWorkId(long workId) {
		this.workId = workId;
	}
	public long getWorkStatusHistoryId() {
		return workStatusHistoryId;
	}
	public void setWorkStatusHistoryId(long workStatusHistoryId) {
		this.workStatusHistoryId = workStatusHistoryId;
	}
//	public long getWorkStatusId() {
//		return workStatusId;
//	}
//	public void setWorkStatusId(long workStatusId) {
//		this.workStatusId = workStatusId;
//	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getWorkStatusCreatedTime() {
		return workStatusCreatedTime;
	}
	public void setWorkStatusCreatedTime(String workStatusCreatedTime) {
		this.workStatusCreatedTime = workStatusCreatedTime;
	}
	public String getWorkStatuHistoryCreatedTime() {
		return workStatuHistoryCreatedTime;
	}
	public void setWorkStatuHistoryCreatedTime(String workStatuHistoryCreatedTime) {
		this.workStatuHistoryCreatedTime = workStatuHistoryCreatedTime;
	}
	public String getWorkStatusModifiedTime() {
		return workStatusModifiedTime;
	}
	public void setWorkStatusModifiedTime(String workStatusModifiedTime) {
		this.workStatusModifiedTime = workStatusModifiedTime;
	}
	public String getClientWorkStatusHistoryId() {
		return clientWorkStatusHistoryId;
	}
	public void setClientWorkStatusHistoryId(String clientWorkStatusHistoryId) {
		this.clientWorkStatusHistoryId = clientWorkStatusHistoryId;
	}
	
	public List<FormDisplay> getFormDisplays() {
		return formDisplays;
	}
	public void setFormDisplays(List<FormDisplay> formDisplays) {
		this.formDisplays = formDisplays;
	}
	public String getWorkActionName() {
		return workActionName;
	}
	public void setWorkActionName(String workActionName) {
		this.workActionName = workActionName;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String getCompletedTime() {
		return completedTime;
	}
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}
	public String getClientWorkStatusId() {
		return clientWorkStatusId;
	}
	public void setClientWorkStatusId(String clientWorkStatusId) {
		this.clientWorkStatusId = clientWorkStatusId;
	}
	public String getClientWorkId() {
		return clientWorkId;
	}
	public void setClientWorkId(String clientWorkId) {
		this.clientWorkId = clientWorkId;
	}
	public String getClientFormId() {
		return clientFormId;
	}
	public void setClientFormId(String clientFormId) {
		this.clientFormId = clientFormId;
	}
	
	
	public String getTzo() {
		return tzo;
	}
	public void setTzo(String tzo) {
		this.tzo = tzo;
	}
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getWorkStatusHistoryCreatedTimeLTZ() {
		return Api.getTimeZoneDatesInLTZ(workStatuHistoryCreatedTime, tzo, null);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		WorkStatusHistory workStatusHistory=(WorkStatusHistory)obj;
	    if(workStatusHistory.getWorkStatusHistoryId()==this.getWorkStatusHistoryId()){
	    	return true;
	    }
		return false;
	}
	public String getServerWorkStatuHistoryCreatedTime() {
		return serverWorkStatuHistoryCreatedTime;
	}
	public void setServerWorkStatuHistoryCreatedTime(
			String serverWorkStatuHistoryCreatedTime) {
		this.serverWorkStatuHistoryCreatedTime = serverWorkStatuHistoryCreatedTime;
	}
	public Long getCheckInId() {
		return checkInId;
	}
	public void setCheckInId(Long checkInId) {
		this.checkInId = checkInId;
	}
		
	public boolean isForcePerformWork() {
		return forcePerformWork;
	}
	public void setForcePerformWork(boolean forcePerformWork) {
		this.forcePerformWork = forcePerformWork;
	}
	
	public boolean isCanView() {
		return canView;
	}
	public void setCanView(boolean canView) {
		this.canView = canView;
	}
	public List<FormPageSpec> getFormPageSpec() {
		return formPageSpec;
	}
	public void setFormPageSpec(List<FormPageSpec> formPageSpec) {
		this.formPageSpec = formPageSpec;
	}
	@Override
	public String toString() {
		return "WorkStatusHistory [workStatusHistoryId=" + workStatusHistoryId
				+ ", workSpecId=" + workSpecId + ", workId=" + workId
				+ ", workActionSpecId=" + workActionSpecId + ", formId="
				+ formId + ", modifiedBy=" + modifiedBy
				+ ", workStatusCreatedTime=" + workStatusCreatedTime
				+ ", workStatuHistoryCreatedTime="
				+ workStatuHistoryCreatedTime + ", workStatusModifiedTime="
				+ workStatusModifiedTime + ", clientWorkStatusHistoryId="
				+ clientWorkStatusHistoryId + ", formDisplays=" + formDisplays
				+ ", workActionName=" + workActionName + ", completed="
				+ completed + ", completedTime=" + completedTime
				+ ", clientWorkStatusId=" + clientWorkStatusId
				+ ", clientWorkId=" + clientWorkId + ", clientFormId="
				+ clientFormId + ", tzo=" + tzo + ", locationAddrr="
				+ locationAddrr + ", location=" + location + ", locationId="
				+ locationId + ", serverWorkStatuHistoryCreatedTime="
				+ serverWorkStatuHistoryCreatedTime + ", checkInId="
				+ checkInId + ", forcePerformWork="
				+ forcePerformWork + ", workCheckInId="
				+ workCheckInId + ", formPageSpec=" + formPageSpec+" ]";
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Long getWorkCheckInId() {
		return workCheckInId;
	}
	public void setWorkCheckInId(Long workCheckInId) {
		this.workCheckInId = workCheckInId;
	}
	public boolean isCanEditCompletedAction() {
		return canEditCompletedAction;
	}
	public void setCanEditCompletedAction(boolean canEditCompletedAction) {
		this.canEditCompletedAction = canEditCompletedAction;
	}
	@JsonIgnore
	public Long getCompanyId() {
		return companyId;
	}
	@JsonIgnore
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	@JsonIgnore
	public boolean isTriggerPoint() {
		return triggerPoint;
	}
	@JsonIgnore
	public void setTriggerPoint(boolean triggerPoint) {
		this.triggerPoint = triggerPoint;
	}
	@JsonIgnore
	public String getActionButtonName() {
		return actionButtonName;
	}
	@JsonIgnore
	public void setActionButtonName(String actionButtonName) {
		this.actionButtonName = actionButtonName;
	}
	@JsonIgnore
	public String getCurrentPushStatus() {
		return currentPushStatus;
	}
	@JsonIgnore
	public void setCurrentPushStatus(String currentPushStatus) {
		this.currentPushStatus = currentPushStatus;
	}
	@JsonIgnore
	public String getSlab() {
		return slab;
	}
	@JsonIgnore
	public void setSlab(String slab) {
		this.slab = slab;
	}
	@JsonIgnore
	public int getTriggerEvent() {
		return triggerEvent;
	}
	@JsonIgnore
	public void setTriggerEvent(int triggerEvent) {
		this.triggerEvent = triggerEvent;
	}
	public String getExportText() {
		return exportText;
	}
	public void setExportText(String exportText) {
		this.exportText = exportText;
	}
	public String getFormDisplayMapJson() {
		return formDisplayMapJson;
	}
	public void setFormDisplayMapJson(String formDisplayMapJson) {
		this.formDisplayMapJson = formDisplayMapJson;
	}
	
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public boolean isInvalidateHistory() {
		return invalidateHistory;
	}
	public void setInvalidateHistory(boolean invalidateHistory) {
		this.invalidateHistory = invalidateHistory;
	}
	public Long getActionTat() {
		return actionTat;
	}
	public void setActionTat(Long actionTat) {
		this.actionTat = actionTat;
	}
	public boolean isDraft() {
		return isDraft;
	}
	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}
	public Boolean getDraftAction() {
		return draftAction;
	}
	public void setDraftAction(Boolean draftAction) {
		this.draftAction = draftAction;
	}
	public String getWorkStatuHistoryCreatedTimeTZ() {
		return workStatuHistoryCreatedTimeTZ;
	}
	public void setWorkStatuHistoryCreatedTimeTZ(String workStatuHistoryCreatedTimeTZ) {
		this.workStatuHistoryCreatedTimeTZ = workStatuHistoryCreatedTimeTZ;
	}
	public Long getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(Long processingTime) {
		this.processingTime = processingTime;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public Long getProcessingTimeInSec() {
		if (processingTime > 0) {
			return processingTime / 1000;
		}
		return 0l;
	}
	public boolean isEnableApiLogs() {
		return enableApiLogs;
	}
	public void setEnableApiLogs(boolean enableApiLogs) {
		this.enableApiLogs = enableApiLogs;
	}
	public boolean isSkippedSystemAction() {
		return skippedSystemAction;
	}
	public void setSkippedSystemAction(boolean skippedSystemAction) {
		this.skippedSystemAction = skippedSystemAction;
	}
	public boolean isSystemActionSkipped() {
		return isSystemActionSkipped;
	}
	public void setSystemActionSkipped(boolean isSystemActionSkipped) {
		this.isSystemActionSkipped = isSystemActionSkipped;
	}
	public String getSourceOfAction() {
		return sourceOfAction;
	}
	public void setSourceOfAction(String sourceOfAction) {
		this.sourceOfAction = sourceOfAction;
	}
	public int getBulkActionPerform() {
		return bulkActionPerform;
	}
	public void setBulkActionPerform(int bulkActionPerform) {
		this.bulkActionPerform = bulkActionPerform;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	public long getActionCount() {
		return actionCount;
	}
	public void setActionCount(long actionCount) {
		this.actionCount = actionCount;
	}
	
	public String getLastActionDays() {
		return lastActionDays;
	}
	public void setLastActionDays(String lastActionDays) {
		this.lastActionDays = lastActionDays;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String getWorkStatuHistoryCreatedTimeForViewScreen() {
		return workStatuHistoryCreatedTimeForViewScreen;
	}
	public void setWorkStatuHistoryCreatedTimeForViewScreen(String workStatuHistoryCreatedTimeForViewScreen) {
		this.workStatuHistoryCreatedTimeForViewScreen = workStatuHistoryCreatedTimeForViewScreen;
	}
}
