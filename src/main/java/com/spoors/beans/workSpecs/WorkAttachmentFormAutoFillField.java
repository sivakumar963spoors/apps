package com.spoors.beans.workSpecs;

public class WorkAttachmentFormAutoFillField {

	private long workAttachmentFormAutoFillFieldId;
	private Long workAttachmentAutoFillId;
	private String fieldSpecUniqueId;
	private String workFieldSpecUniqueId;
	private int fieldType;
	
	//added on 6-may-2015 by ameer for pickAnother value feature
	private Integer pickAnother = 1;
	private String sourceSpecUniqueId;
	private Integer sourceType;
	private Long sourceSpecId;
	
	
	public long getWorkAttachmentFormAutoFillFieldId() {
		return workAttachmentFormAutoFillFieldId;
	}
	public void setWorkAttachmentFormAutoFillFieldId(long workAttachmentFormAutoFillFieldId) {
		this.workAttachmentFormAutoFillFieldId = workAttachmentFormAutoFillFieldId;
	}
	public Long getWorkAttachmentAutoFillId() {
		return workAttachmentAutoFillId;
	}
	public void setWorkAttachmentAutoFillId(Long workAttachmentAutoFillId) {
		this.workAttachmentAutoFillId = workAttachmentAutoFillId;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	public String getWorkFieldSpecUniqueId() {
		return workFieldSpecUniqueId;
	}
	public void setWorkFieldSpecUniqueId(String workFieldSpecUniqueId) {
		this.workFieldSpecUniqueId = workFieldSpecUniqueId;
	}
	public Integer getPickAnother() {
		return pickAnother;
	}
	public void setPickAnother(Integer pickAnother) {
		this.pickAnother = pickAnother;
	}
	public String getSourceSpecUniqueId() {
		return sourceSpecUniqueId;
	}
	public void setSourceSpecUniqueId(String sourceSpecUniqueId) {
		this.sourceSpecUniqueId = sourceSpecUniqueId;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Long getSourceSpecId() {
		return sourceSpecId;
	}
	public void setSourceSpecId(Long sourceSpecId) {
		this.sourceSpecId = sourceSpecId;
	}
}
