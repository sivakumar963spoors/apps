package com.spoors.beans.workSpecs;

public class WorkSpecGroupBasedAssignmentCriteria {

	//`id`, `workSpecSchedulingConfigId`, `workSpecId`, `entitySpecId`, `groupId`, `entityId`, `weightageBased`, `considerLeaves`, `considerSignIn`, `createdTime`, `modifiedTime`
	private Long id;
	private Long workSpecSchedulingConfigId;
	private Long workSpecId;
	private Long groupId;
	private String entityId;
	private boolean weightageBased;
	private boolean considerLeaves;
	private boolean considerSignIn;
	private String createdTime;
	private String modifiedTime;
	private String workFieldSpecUniqueId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkSpecSchedulingConfigId() {
		return workSpecSchedulingConfigId;
	}
	public void setWorkSpecSchedulingConfigId(Long workSpecSchedulingConfigId) {
		this.workSpecSchedulingConfigId = workSpecSchedulingConfigId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public boolean isWeightageBased() {
		return weightageBased;
	}
	public void setWeightageBased(boolean weightageBased) {
		this.weightageBased = weightageBased;
	}
	public boolean isConsiderLeaves() {
		return considerLeaves;
	}
	public void setConsiderLeaves(boolean considerLeaves) {
		this.considerLeaves = considerLeaves;
	}
	public boolean isConsiderSignIn() {
		return considerSignIn;
	}
	public void setConsiderSignIn(boolean considerSignIn) {
		this.considerSignIn = considerSignIn;
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
	public String getWorkFieldSpecUniqueId() {
		return workFieldSpecUniqueId;
	}
	public void setWorkFieldSpecUniqueId(String workFieldSpecUniqueId) {
		this.workFieldSpecUniqueId = workFieldSpecUniqueId;
	}
	
	
	
}
