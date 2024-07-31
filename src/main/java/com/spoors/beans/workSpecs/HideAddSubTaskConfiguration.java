package com.spoors.beans.workSpecs;

public class HideAddSubTaskConfiguration {
	
	private Long id;
	private Long parentWorkSpecId;
	private Long workProcessSubTaskSpecId;
	private Long actionSpecId;
	private String createdTime;
	private String modifiedTime;
	private Long createdBy;
	private boolean enabled;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentWorkSpecId() {
		return parentWorkSpecId;
	}
	public void setParentWorkSpecId(Long parentWorkSpecId) {
		this.parentWorkSpecId = parentWorkSpecId;
	}
	public Long getWorkProcessSubTaskSpecId() {
		return workProcessSubTaskSpecId;
	}
	public void setWorkProcessSubTaskSpecId(Long workProcessSubTaskSpecId) {
		this.workProcessSubTaskSpecId = workProcessSubTaskSpecId;
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
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
