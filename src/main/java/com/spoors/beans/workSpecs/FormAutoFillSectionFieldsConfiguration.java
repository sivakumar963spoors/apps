package com.spoors.beans.workSpecs;

public class FormAutoFillSectionFieldsConfiguration {
	
	private Long formAutoFillSectionFieldsConfigurationId;
	private Long formAutoFillSectionConfigurationId;
	private Long workFormAutoFillId;
	private String fieldSpecUniqueId;
	private String sourceFieldSpecUniqueId;
	private Integer pickAnother ;
	
	public Long getFormAutoFillSectionFieldsConfigurationId() {
		return formAutoFillSectionFieldsConfigurationId;
	}
	public void setFormAutoFillSectionFieldsConfigurationId(Long formAutoFillSectionFieldsConfigurationId) {
		this.formAutoFillSectionFieldsConfigurationId = formAutoFillSectionFieldsConfigurationId;
	}
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
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public String getSourceFieldSpecUniqueId() {
		return sourceFieldSpecUniqueId;
	}
	public void setSourceFieldSpecUniqueId(String sourceFieldSpecUniqueId) {
		this.sourceFieldSpecUniqueId = sourceFieldSpecUniqueId;
	}
	public Integer getPickAnother() {
		return pickAnother;
	}
	public void setPickAnother(Integer pickAnother) {
		this.pickAnother = pickAnother;
	}
	@Override
	public String toString() {
		return "FormAutoFillSectionFieldsConfiguration [formAutoFillSectionFieldsConfigurationId="
				+ formAutoFillSectionFieldsConfigurationId + ", formAutoFillSectionConfigurationId="
				+ formAutoFillSectionConfigurationId + ", workFormAutoFillId=" + workFormAutoFillId
				+ ", fieldSpecUniqueId=" + fieldSpecUniqueId + ", sourceFieldSpecUniqueId=" + sourceFieldSpecUniqueId
				+ ", pickAnother=" + pickAnother + "]";
	}
	
	

}
