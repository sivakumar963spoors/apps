package com.spoors.beans.workSpecs;

public class WorkActionFormVisibility {
	
	
	private Long WorkActionFormVisibilityId;
	private Long workSpecId;
	private Long companyId;
	private Long actionSpecId;
    private String createdTime;
	private String modifiedTime;
	
	public Long getWorkActionFormVisibilityId() {
		return WorkActionFormVisibilityId;
	}
	public void setWorkActionFormVisibilityId(Long workActionFormVisibilityId) {
		WorkActionFormVisibilityId = workActionFormVisibilityId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getActionSpecId() {
		return actionSpecId;
	}
	public void setActionSpecId(Long actionSpecId) {
		this.actionSpecId = actionSpecId;
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
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	

}

