package com.spoors.beans.workSpecs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class WorkFormAutoFill {

	private Long workFormAutoFillId;
	private String formSpecUniqueId;
	private boolean enable;
	private Long workActionSpecId;
	private boolean sectionAutoFill;
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<WorkFormAutoFillField> workFormAutoFillFields;
	private List<FormAutoFillSectionConfiguration> formAutoFillSectionConfigurations;
	private boolean deleted;
	
	public Long getWorkFormAutoFillId() {
		return workFormAutoFillId;
	}
	public void setWorkFormAutoFillId(Long workFormAutoFillId) {
		this.workFormAutoFillId = workFormAutoFillId;
	}
	
	
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public List<WorkFormAutoFillField> getWorkFormAutoFillFields() {
		return workFormAutoFillFields;
	}
	public void setWorkFormAutoFillFields(List<WorkFormAutoFillField> workFormAutoFillFields) {
		this.workFormAutoFillFields = workFormAutoFillFields;
	}
	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	public boolean isSectionAutoFill() {
		return sectionAutoFill;
	}
	public void setSectionAutoFill(boolean sectionAutoFill) {
		this.sectionAutoFill = sectionAutoFill;
	}
	public List<FormAutoFillSectionConfiguration> getFormAutoFillSectionConfigurations() {
		return formAutoFillSectionConfigurations;
	}
	public void setFormAutoFillSectionConfigurations(
			List<FormAutoFillSectionConfiguration> formAutoFillSectionConfigurations) {
		this.formAutoFillSectionConfigurations = formAutoFillSectionConfigurations;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
