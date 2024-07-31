package com.spoors.beans.workSpecs;

import java.util.List;

public class WorkUnassignmentCriterias 
{
	public static final int CRITERIA_TYPE_ACTION_FORM_FIELDS = 1;
	public static final int CRITERIA_TYPE_ACTION_RECETITION = 2;
	
	public static final int CUNJUCTION_FOR_OR = 0;
	public static final int CUNJUCTION_FOR_AND = 1;
	
	
	private Long workUnassignmentCriteriaId;
	private Long workSpecId;
	private Long workActionSpecId;
	private String formSpecUniqueId;
	private Long companyId;
	
	private int conjunction;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	
	List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions;
	
	public Long getWorkUnassignmentCriteriaId() {
		return workUnassignmentCriteriaId;
	}
	public void setWorkUnassignmentCriteriaId(Long workUnassignmentCriteriaId) {
		this.workUnassignmentCriteriaId = workUnassignmentCriteriaId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public int getConjunction() {
		return conjunction;
	}
	public void setConjunction(int conjunction) {
		this.conjunction = conjunction;
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
	public List<WorkAssignmentCriteriaConditions> getWorkAssignmentCriteriaConditions() {
		return workAssignmentCriteriaConditions;
	}
	public void setWorkAssignmentCriteriaConditions(
			List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions) {
		this.workAssignmentCriteriaConditions = workAssignmentCriteriaConditions;
	}
	
}
