package com.spoors.beans.workSpecs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class NextWorkSpec {
	
	private Long id;
	private Long workSpecId;
	private Long nextWorkSpecId;
	private Long actionSpecId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String createdTime;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String modifiedTime;
	private boolean deleted;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getNextWorkSpecId() {
		return nextWorkSpecId;
	}
	public void setNextWorkSpecId(Long nextWorkSpecId) {
		this.nextWorkSpecId = nextWorkSpecId;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	

}
