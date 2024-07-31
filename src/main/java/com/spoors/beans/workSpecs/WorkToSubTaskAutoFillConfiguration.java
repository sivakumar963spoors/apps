package com.spoors.beans.workSpecs;

public class WorkToSubTaskAutoFillConfiguration {
	
	private Long id;
	private Long parentWorkSpecId;
	private Long workProcessSubTaskSpecId;
	private Long subTaskWorkSpecId;
	private String copyFromWorkFormFieldSpecUniqueId;
	private String copyToWorkFormFieldSpecUniqueId;
	private boolean pickAnother = true;
	private String createdBy;
	private String createdTime;
	
	
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
	public Long getSubTaskWorkSpecId() {
		return subTaskWorkSpecId;
	}
	public void setSubTaskWorkSpecId(Long subTaskWorkSpecId) {
		this.subTaskWorkSpecId = subTaskWorkSpecId;
	}
	public String getCopyFromWorkFormFieldSpecUniqueId() {
		return copyFromWorkFormFieldSpecUniqueId;
	}
	public void setCopyFromWorkFormFieldSpecUniqueId(
			String copyFromWorkFormFieldSpecUniqueId) {
		this.copyFromWorkFormFieldSpecUniqueId = copyFromWorkFormFieldSpecUniqueId;
	}
	public String getCopyToWorkFormFieldSpecUniqueId() {
		return copyToWorkFormFieldSpecUniqueId;
	}
	public void setCopyToWorkFormFieldSpecUniqueId(
			String copyToWorkFormFieldSpecUniqueId) {
		this.copyToWorkFormFieldSpecUniqueId = copyToWorkFormFieldSpecUniqueId;
	}
	public boolean isPickAnother() {
		return pickAnother;
	}
	public void setPickAnother(boolean pickAnother) {
		this.pickAnother = pickAnother;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
	

}
