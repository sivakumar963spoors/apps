package com.spoors.beans.workSpecs;

import java.util.List;

public class AttachmnetFormAutoFillSectionConfiguration {

	private Long attachmnetFormAutoFillSectionConfigurationId;
	private Long workAttachmentAutoFillId;
	private String sectionSpecUniqueId;
	private Long sourceActionSpecId;
	private String sourceSectionSpecUniqueId;
	private boolean deleteSectionInstance;
	private boolean addSectionInstance;
	
	private List<WorkAttachmentAutoFillSectionFieldsConfiguration> workAttachmentAutoFillSectionFieldsConfiguration;

	
	
	public Long getAttachmnetFormAutoFillSectionConfigurationId() {
		return attachmnetFormAutoFillSectionConfigurationId;
	}

	public void setAttachmnetFormAutoFillSectionConfigurationId(Long attachmnetFormAutoFillSectionConfigurationId) {
		this.attachmnetFormAutoFillSectionConfigurationId = attachmnetFormAutoFillSectionConfigurationId;
	}

	public Long getWorkAttachmentAutoFillId() {
		return workAttachmentAutoFillId;
	}

	public void setWorkAttachmentAutoFillId(Long workAttachmentAutoFillId) {
		this.workAttachmentAutoFillId = workAttachmentAutoFillId;
	}

	public Long getSourceActionSpecId() {
		return sourceActionSpecId;
	}

	public void setSourceActionSpecId(Long sourceActionSpecId) {
		this.sourceActionSpecId = sourceActionSpecId;
	}


	public List<WorkAttachmentAutoFillSectionFieldsConfiguration> getWorkAttachmentAutoFillSectionFieldsConfiguration() {
		return workAttachmentAutoFillSectionFieldsConfiguration;
	}

	public void setWorkAttachmentAutoFillSectionFieldsConfiguration(
			List<WorkAttachmentAutoFillSectionFieldsConfiguration> workAttachmentAutoFillSectionFieldsConfiguration) {
		this.workAttachmentAutoFillSectionFieldsConfiguration = workAttachmentAutoFillSectionFieldsConfiguration;
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
}
