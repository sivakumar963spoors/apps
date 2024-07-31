package com.spoors.beans.workSpecs;

public class AddingSubTaskEmployeeConfiguration {
	
	private Long id;
	private Long parentWorkSpecId;
	private Long workProcessSubTaskSpecId;
	private String workEmpFieldSpecUniqueId;
	private boolean canViewSubTask;
	private boolean canAddSubTask;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private boolean performMandateSubTask;
	
	
	private String workEmpFieldSpecLabel;
	
	private String workSpecTitle;

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

	public String getWorkEmpFieldSpecUniqueId() {
		return workEmpFieldSpecUniqueId;
	}

	public void setWorkEmpFieldSpecUniqueId(String workEmpFieldSpecUniqueId) {
		this.workEmpFieldSpecUniqueId = workEmpFieldSpecUniqueId;
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

	public String getWorkEmpFieldSpecLabel() {
		return workEmpFieldSpecLabel;
	}

	public void setWorkEmpFieldSpecLabel(String workEmpFieldSpecLabel) {
		this.workEmpFieldSpecLabel = workEmpFieldSpecLabel;
	}

	public boolean isPerformMandateSubTask() {
		return performMandateSubTask;
	}

	public void setPerformMandateSubTask(boolean performMandateSubTask) {
		this.performMandateSubTask = performMandateSubTask;
	}

	public boolean isCanViewSubTask() {
		return canViewSubTask;
	}

	public void setCanViewSubTask(boolean canViewSubTask) {
		this.canViewSubTask = canViewSubTask;
	}

	public boolean isCanAddSubTask() {
		return canAddSubTask;
	}

	public void setCanAddSubTask(boolean canAddSubTask) {
		this.canAddSubTask = canAddSubTask;
	}

	public String getWorkSpecTitle() {
		return workSpecTitle;
	}

	public void setWorkSpecTitle(String workSpecTitle) {
		this.workSpecTitle = workSpecTitle;
	}
	
	

}
