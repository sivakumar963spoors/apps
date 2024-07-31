package com.spoors.beans.workSpecs;

import java.util.List;

public class FormToWorkAutoFillSectionConfiguration {

	
	private Long formToWorkAutoFillSectionConfigurationId;
	private Long formToWorkAutoFillId;
	private String sectionSpecUniqueId;
	private Long sourceActionSpecId;
	private String sourceSectionSpecUniqueId;
	
	private List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfiguration;
	
	public Long getFormToWorkAutoFillSectionConfigurationId() {
		return formToWorkAutoFillSectionConfigurationId;
	}

	public void setFormToWorkAutoFillSectionConfigurationId(Long formToWorkAutoFillSectionConfigurationId) {
		this.formToWorkAutoFillSectionConfigurationId = formToWorkAutoFillSectionConfigurationId;
	}

	public Long getFormToWorkAutoFillId() {
		return formToWorkAutoFillId;
	}

	public void setFormToWorkAutoFillId(Long formToWorkAutoFillId) {
		this.formToWorkAutoFillId = formToWorkAutoFillId;
	}

	public Long getSourceActionSpecId() {
		return sourceActionSpecId;
	}

	public void setSourceActionSpecId(Long sourceActionSpecId) {
		this.sourceActionSpecId = sourceActionSpecId;
	}

	public List<FormToWorkAutoFillSectionFieldsConfiguration> getFormToWorkAutoFillSectionFieldsConfiguration() {
		return formToWorkAutoFillSectionFieldsConfiguration;
	}

	public void setFormToWorkAutoFillSectionFieldsConfiguration(
			List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfiguration) {
		this.formToWorkAutoFillSectionFieldsConfiguration = formToWorkAutoFillSectionFieldsConfiguration;
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

}
