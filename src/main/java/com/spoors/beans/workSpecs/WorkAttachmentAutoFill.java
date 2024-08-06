package com.spoors.beans.workSpecs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class WorkAttachmentAutoFill {

	private Long workAttachmentAutoFillId;
	private String formSpecUniqueId;
	private boolean enable;
	private Long workSpecId;
	private boolean attachmentSectionAutoFill;
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFields;
	private List<AttachmnetFormAutoFillSectionConfiguration> attachmnetFormAutoFillSectionConfigurations;
	private boolean deleted;
	
	
	public Long getWorkAttachmentAutoFillId() {
		return workAttachmentAutoFillId;
	}
	public void setWorkAttachmentAutoFillId(Long workAttachmentAutoFillId) {
		this.workAttachmentAutoFillId = workAttachmentAutoFillId;
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
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public boolean isAttachmentSectionAutoFill() {
		return attachmentSectionAutoFill;
	}
	public void setAttachmentSectionAutoFill(boolean attachmentSectionAutoFill) {
		this.attachmentSectionAutoFill = attachmentSectionAutoFill;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public List<WorkAttachmentFormAutoFillField> getWorkAttachmentFormAutoFillFields() {
		return workAttachmentFormAutoFillFields;
	}
	public void setWorkAttachmentFormAutoFillFields(
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFields) {
		this.workAttachmentFormAutoFillFields = workAttachmentFormAutoFillFields;
	}
	public List<AttachmnetFormAutoFillSectionConfiguration> getAttachmnetFormAutoFillSectionConfigurations() {
		return attachmnetFormAutoFillSectionConfigurations;
	}
	public void setAttachmnetFormAutoFillSectionConfigurations(
			List<AttachmnetFormAutoFillSectionConfiguration> attachmnetFormAutoFillSectionConfigurations) {
		this.attachmnetFormAutoFillSectionConfigurations = attachmnetFormAutoFillSectionConfigurations;
	}
	
}
