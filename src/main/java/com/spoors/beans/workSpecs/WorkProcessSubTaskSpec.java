package com.spoors.beans.workSpecs;

public class WorkProcessSubTaskSpec 
{
	private Long workProcessSubTaskSpecId;
	private Long parentWorkSpecId;
	private String subTaskName;
	private String subTaskDesccription;
	private String subTaskWorkSpecId;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private boolean deleted;
	private String subTaskNameEmpty;
	private String subTaskWorkSpecIdEmpty;
	private boolean active;
	private boolean deletedStatus;//used for UI purpose
	
	private boolean allowParentWorksToClient;
	private boolean showSubTaskInListScreen;
	
	public Long getWorkProcessSubTaskSpecId() {
		return workProcessSubTaskSpecId;
	}
	public void setWorkProcessSubTaskSpecId(Long workProcessSubTaskSpecId) {
		this.workProcessSubTaskSpecId = workProcessSubTaskSpecId;
	}
	public Long getParentWorkSpecId() {
		return parentWorkSpecId;
	}
	public void setParentWorkSpecId(Long parentWorkSpecId) {
		this.parentWorkSpecId = parentWorkSpecId;
	}
	public String getSubTaskName() {
		return subTaskName;
	}
	public void setSubTaskName(String subTaskName) {
		this.subTaskName = subTaskName;
	}
	public String getSubTaskDesccription() {
		return subTaskDesccription;
	}
	public void setSubTaskDesccription(String subTaskDesccription) {
		this.subTaskDesccription = subTaskDesccription;
	}
	public String getSubTaskWorkSpecId() {
		return subTaskWorkSpecId;
	}
	public void setSubTaskWorkSpecId(String subTaskWorkSpecId) {
		this.subTaskWorkSpecId = subTaskWorkSpecId;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getSubTaskNameEmpty() {
		return subTaskNameEmpty;
	}
	public void setSubTaskNameEmpty(String subTaskNameEmpty) {
		this.subTaskNameEmpty = subTaskNameEmpty;
	}
	public String getSubTaskWorkSpecIdEmpty() {
		return subTaskWorkSpecIdEmpty;
	}
	public void setSubTaskWorkSpecIdEmpty(String subTaskWorkSpecIdEmpty) {
		this.subTaskWorkSpecIdEmpty = subTaskWorkSpecIdEmpty;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(boolean deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public boolean equals(Object obj) {
		if(obj instanceof WorkProcessSubTaskSpec){
			WorkProcessSubTaskSpec workProcessSubTaskSpec = (WorkProcessSubTaskSpec) obj;
		   if(workProcessSubTaskSpec!=null && workProcessSubTaskSpec.getWorkProcessSubTaskSpecId()!=null){
		        boolean result= workProcessSubTaskSpec.getWorkProcessSubTaskSpecId().longValue() == this.getWorkProcessSubTaskSpecId().longValue();
		        return result;
		   }
		   return false;
		}  else {
			return super.equals(obj);
		}
}
	public boolean isAllowParentWorksToClient() {
		return allowParentWorksToClient;
	}
	public void setAllowParentWorksToClient(boolean allowParentWorksToClient) {
		this.allowParentWorksToClient = allowParentWorksToClient;
	}
	public boolean isShowSubTaskInListScreen() {
		return showSubTaskInListScreen;
	}
	public void setShowSubTaskInListScreen(boolean showSubTaskInListScreen) {
		this.showSubTaskInListScreen = showSubTaskInListScreen;
	}
	

}
