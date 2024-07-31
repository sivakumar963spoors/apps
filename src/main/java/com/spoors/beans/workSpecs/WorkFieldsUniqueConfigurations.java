package com.spoors.beans.workSpecs;

import java.util.List;

public class WorkFieldsUniqueConfigurations 
{
	private Long id;
	private Long workSpecId;
	private String formFieldUniqueId;
	private Integer uniqueCheck;
	private Long companyId;
	private Long modifiedBy;
	private String modifiedTime;
	
	private boolean canModifyWork = true;
	
	private boolean enableOnlineSearchForUniqueness = false;
	
	private String errorMessageForUniqueness;
	private String successMessageForUniqueness;
	private int workUniquenessType;
	
	private List<WorkFieldsUniqueConfigurations> fieldsUniqueConfigurations;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFormFieldUniqueId() {
		return formFieldUniqueId;
	}
	public void setFormFieldUniqueId(String formFieldUniqueId) {
		this.formFieldUniqueId = formFieldUniqueId;
	}
	
	public Integer getUniqueCheck() {
		return uniqueCheck;
	}
	public void setUniqueCheck(Integer uniqueCheck) {
		this.uniqueCheck = uniqueCheck;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public List<WorkFieldsUniqueConfigurations> getFieldsUniqueConfigurations() {
		return fieldsUniqueConfigurations;
	}
	public void setFieldsUniqueConfigurations(
			List<WorkFieldsUniqueConfigurations> fieldsUniqueConfigurations) {
		this.fieldsUniqueConfigurations = fieldsUniqueConfigurations;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public boolean isCanModifyWork() {
		return canModifyWork;
	}
	public void setCanModifyWork(boolean canModifyWork) {
		this.canModifyWork = canModifyWork;
	}
	public boolean isEnableOnlineSearchForUniqueness() {
		return enableOnlineSearchForUniqueness;
	}
	public void setEnableOnlineSearchForUniqueness(boolean enableOnlineSearchForUniqueness) {
		this.enableOnlineSearchForUniqueness = enableOnlineSearchForUniqueness;
	}
	public String getErrorMessageForUniqueness() {
		return errorMessageForUniqueness;
	}
	public void setErrorMessageForUniqueness(String errorMessageForUniqueness) {
		this.errorMessageForUniqueness = errorMessageForUniqueness;
	}
	public String getSuccessMessageForUniqueness() {
		return successMessageForUniqueness;
	}
	public void setSuccessMessageForUniqueness(String successMessageForUniqueness) {
		this.successMessageForUniqueness = successMessageForUniqueness;
	}
	public int getWorkUniquenessType() {
		return workUniquenessType;
	}
	public void setWorkUniquenessType(int workUniquenessType) {
		this.workUniquenessType = workUniquenessType;
	}
	
	
	
	
	
}
