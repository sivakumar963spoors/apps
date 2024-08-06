package com.spoors.beans.workSpecs;

public class FormToWorkAutoFillField {

	private long formToWorkAutoFillFieldId;
	private Long formToWorkAutoFillId;
	private String workFieldSpecUniqueId;
	private String formFieldSpecUniqueId;
	private int fieldType;
	private boolean allowNull;
	
	
	public long getFormToWorkAutoFillFieldId() {
		return formToWorkAutoFillFieldId;
	}
	public void setFormToWorkAutoFillFieldId(long formToWorkAutoFillFieldId) {
		this.formToWorkAutoFillFieldId = formToWorkAutoFillFieldId;
	}
	public Long getFormToWorkAutoFillId() {
		return formToWorkAutoFillId;
	}
	public void setFormToWorkAutoFillId(Long formToWorkAutoFillId) {
		this.formToWorkAutoFillId = formToWorkAutoFillId;
	}
	public String getWorkFieldSpecUniqueId() {
		return workFieldSpecUniqueId;
	}
	public void setWorkFieldSpecUniqueId(String workFieldSpecUniqueId) {
		this.workFieldSpecUniqueId = workFieldSpecUniqueId;
	}
	public String getFormFieldSpecUniqueId() {
		return formFieldSpecUniqueId;
	}
	public void setFormFieldSpecUniqueId(String formFieldSpecUniqueId) {
		this.formFieldSpecUniqueId = formFieldSpecUniqueId;
	}
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	@Override
	public String toString() {
		return "FormToWorkAutoFillField [formToWorkAutoFillFieldId="
				+ formToWorkAutoFillFieldId + ", formToWorkAutoFillId="
				+ formToWorkAutoFillId + ", workFieldSpecUniqueId="
				+ workFieldSpecUniqueId + ", formFieldSpecUniqueId="
				+ formFieldSpecUniqueId + ", fieldType=" + fieldType + "]";
	}
	public boolean isAllowNull() {
		return allowNull;
	}
	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}
	
	
}
