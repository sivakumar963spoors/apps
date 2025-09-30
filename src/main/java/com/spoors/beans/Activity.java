package com.spoors.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Activity implements Serializable{

	private Long activityId;
	private Long formSpecId;
	private String allVersionFormSpec;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long companyId;
	private String activityName;
	private String formSpecUniqueId;
	private String empId;
	private String empName;
	private long formsCount;
	private String formIdWithIdentifier;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long createdBy;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long modifiedBy;
	
	private Short isDeleted;
	
	private String creationTime;
	private String modifiedTime;
	
	private String formName;
	private String customerTypeIds;
	
	//Added customer ActivityVisibility  
	private String activityVisibility;
	private boolean visibleToEmpGroup =false;
	
	private String visibility;
	private String mappedToEmpGrp;
	private boolean activityChecked;
	
	private String mandatoryCustomerTypeIds;
	private String  SevenDays;
	private String ActivityCount;
	private boolean hideActivity;
	private Long formId;
	private Long filledBy;
	private String customerName;
	private Long customerId;

	
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
	public Long getFormSpecId() {
		return formSpecId;
	}
	public void setFormSpecId(Long formSpecId) {
		this.formSpecId = formSpecId;
	}
	
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
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
	public Short getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getAllVersionFormSpec() {
		return allVersionFormSpec;
	}
	public void setAllVersionFormSpec(String allVersionFormSpec) {
		this.allVersionFormSpec = allVersionFormSpec;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public long getFormsCount() {
		return formsCount;
	}
	public void setFormsCount(long formsCount) {
		this.formsCount = formsCount;
	}
	public String getFormIdWithIdentifier() {
		return formIdWithIdentifier;
	}
	public void setFormIdWithIdentifier(String formIdWithIdentifier) {
		this.formIdWithIdentifier = formIdWithIdentifier;
	}
	
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getCustomerTypeIds() {
		return customerTypeIds;
	}
	public void setCustomerTypeIds(String customerTypeIds) {
		this.customerTypeIds = customerTypeIds;
	}
	public String getActivityVisibility() {
		return activityVisibility;
	}
	public void setActivityVisibility(String activityVisibility) {
		this.activityVisibility = activityVisibility;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getMappedToEmpGrp() {
		return mappedToEmpGrp;
	}
	public void setMappedToEmpGrp(String mappedToEmpGrp) {
		this.mappedToEmpGrp = mappedToEmpGrp;
	}
	public boolean isVisibleToEmpGroup() {
		return visibleToEmpGroup;
	}
	public void setVisibleToEmpGroup(boolean visibleToEmpGroup) {
		this.visibleToEmpGroup = visibleToEmpGroup;
	}
	public boolean isActivityChecked() {
		return activityChecked;
	}
	public void setActivityChecked(boolean activityChecked) {
		this.activityChecked = activityChecked;
	}
	public String getMandatoryCustomerTypeIds() {
		return mandatoryCustomerTypeIds;
	}
	public void setMandatoryCustomerTypeIds(String mandatoryCustomerTypeIds) {
		this.mandatoryCustomerTypeIds = mandatoryCustomerTypeIds;
	}
	
	public String getSevenDays() {
		return SevenDays;
	}
	public void setSevenDays(String sevenDays) {
		SevenDays = sevenDays;
	}
	public String getActivityCount() {
		return ActivityCount;
	}
	public void setActivityCount(String activityCount) {
		ActivityCount = activityCount;
	}
	public boolean isHideActivity() {
		return hideActivity;
	}
	public void setHideActivity(boolean hideActivity) {
		this.hideActivity = hideActivity;
	}
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	public Long getFilledBy() {
		return filledBy;
	}
	public void setFilledBy(Long filledBy) {
		this.filledBy = filledBy;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
