package com.spoors.beans.workSpecs;

import java.util.List;

public class FormAutoFillSectionConfiguration {
	
	private Long formAutoFillSectionConfigurationId;
	private Long workFormAutoFillId;
	private String sectionSpecUniqueId;
	private Long sourceActionSpecId;
	private String sourceSectionSpecUniqueId;
	private boolean deleteSectionInstance;
	private boolean addSectionInstance;
	
	private List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration;

	public Long getFormAutoFillSectionConfigurationId() {
		return formAutoFillSectionConfigurationId;
	}

	public void setFormAutoFillSectionConfigurationId(Long formAutoFillSectionConfigurationId) {
		this.formAutoFillSectionConfigurationId = formAutoFillSectionConfigurationId;
	}

	public Long getWorkFormAutoFillId() {
		return workFormAutoFillId;
	}

	public void setWorkFormAutoFillId(Long workFormAutoFillId) {
		this.workFormAutoFillId = workFormAutoFillId;
	}

	

	public Long getSourceActionSpecId() {
		return sourceActionSpecId;
	}

	public void setSourceActionSpecId(Long sourceActionSpecId) {
		this.sourceActionSpecId = sourceActionSpecId;
	}


	public List<FormAutoFillSectionFieldsConfiguration> getFormAutoFillSectionFieldsConfiguration() {
		return formAutoFillSectionFieldsConfiguration;
	}

	public void setFormAutoFillSectionFieldsConfiguration(
			List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration) {
		this.formAutoFillSectionFieldsConfiguration = formAutoFillSectionFieldsConfiguration;
	}

	public String getSectionSpecUniqueId() {
		return sectionSpecUniqueId;
	}

	public void setSectionSpecUniqueId(String sectionSpecUniqueId) {
		this.sectionSpecUniqueId = sectionSpecUniqueId;
	}

	public String getSourceSectionSpecUniqueId() {
		return sourceSectionSpecUniqueId;
	}

	public void setSourceSectionSpecUniqueId(String sourceSectionSpecUniqueId) {
		this.sourceSectionSpecUniqueId = sourceSectionSpecUniqueId;
	}

	public boolean isDeleteSectionInstance() {
		return deleteSectionInstance;
	}

	public void setDeleteSectionInstance(boolean deleteSectionInstance) {
		this.deleteSectionInstance = deleteSectionInstance;
	}

	public boolean isAddSectionInstance() {
		return addSectionInstance;
	}

	public void setAddSectionInstance(boolean addSectionInstance) {
		this.addSectionInstance = addSectionInstance;
	}

	@Override
	public String toString() {
		return "FormAutoFillSectionConfiguration [formAutoFillSectionConfigurationId="
				+ formAutoFillSectionConfigurationId + ", workFormAutoFillId=" + workFormAutoFillId
				+ ", sectionSpecUniqueId=" + sectionSpecUniqueId + ", sourceActionSpecId=" + sourceActionSpecId
				+ ", sourceSectionSpecUniqueId=" + sourceSectionSpecUniqueId + ", deleteSectionInstance="
				+ deleteSectionInstance + ", addSectionInstance=" + addSectionInstance
				+ ", formAutoFillSectionFieldsConfiguration=" + formAutoFillSectionFieldsConfiguration + "]";
	}

		

}
