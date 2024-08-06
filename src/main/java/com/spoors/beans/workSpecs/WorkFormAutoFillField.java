package com.spoors.beans.workSpecs;

public class WorkFormAutoFillField {

	private long workFormAutoFillFieldId;
	private Long workFormAutoFillId;
	private String fieldSpecUniqueId;
	private String workFieldSpecUniqueId;
	private int fieldType;
	
	//added on 6-may-2015 by ameer for pickAnother value feature
	private Integer pickAnother = 1;
	private String sourceSpecUniqueId;
	private Integer sourceType;
	private Long sourceSpecId;
	
	public long getWorkFormAutoFillFieldId() {
		return workFormAutoFillFieldId;
	}
	public void setWorkFormAutoFillFieldId(long workFormAutoFillFieldId) {
		this.workFormAutoFillFieldId = workFormAutoFillFieldId;
	}
	public long getWorkFormAutoFillId() {
		return workFormAutoFillId;
	}
	public void setWorkFormAutoFillId(long workFormAutoFillId) {
		this.workFormAutoFillId = workFormAutoFillId;
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
