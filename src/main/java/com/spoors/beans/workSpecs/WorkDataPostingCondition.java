package com.spoors.beans.workSpecs;

public class WorkDataPostingCondition {

	public static final int WORK_ACTION_COMPLETED = 1;
	public static final int WORK_ACTION_COMPLETED_AND_WORK_COMPLETED = 2;
	
	private long workDataPostingConditionId;
	private long workDataPostingConfigurationId;
	private long workSpecId;
	private long workActionSpecId;
	private int triggerCondition;
	private long companyId;
	private long createdBy;
	private String createdTime;
	
	
	public long getWorkDataPostingConditionId() {
		return workDataPostingConditionId;
	}
	public void setWorkDataPostingConditionId(long workDataPostingConditionId) {
		this.workDataPostingConditionId = workDataPostingConditionId;
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
	public long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
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
	public int getTriggerCondition() {
		return triggerCondition;
	}
	public void setTriggerCondition(int triggerCondition) {
		this.triggerCondition = triggerCondition;
	}
	
	
}
