package com.spoors.beans.workSpecs;

public class WorkActionExcalatedEmpIds {

	private long id;
	private long companyId;
	private long workSpecId;
	private long empId;
	private String createdTime;
	private Long workId;
	private Long actionSpecId;
	private Long stageId;
	private String modifiedTime;
	private boolean deleted;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getActionSpecId() {
		return actionSpecId;
	}
	public void setActionSpecId(Long actionSpecId) {
		this.actionSpecId = actionSpecId;
	}
	public Long getStageId() {
		return stageId;
	}
	public void setStageId(Long stageId) {
		this.stageId = stageId;
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
	@Override
	public String toString() {
		return "WorkActionExcalatedEmpIds [id=" + id + ", companyId=" + companyId + ", workSpecId=" + workSpecId
				+ ", empId=" + empId + ", createdTime=" + createdTime + ", workId=" + workId + ", actionSpecId="
				+ actionSpecId + ", stageId=" + stageId + ", modifiedTime=" + modifiedTime + ", deleted=" + deleted
				+ "]";
	}
	
	
	
	
	
	
	
}
