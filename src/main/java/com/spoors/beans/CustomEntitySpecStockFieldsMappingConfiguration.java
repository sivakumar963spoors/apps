package com.spoors.beans;

public class CustomEntitySpecStockFieldsMappingConfiguration {
	
	public static final String mobileTableName = "custom_entity_spec_stock_fields_mapping_configuration";

	private Long CustomEntitySpecStockFieldsMappingConfigurationId;
	private Long customEntitySpecStockConfigurationId;
	private String customEntityFieldId;
	private String fieldSpecUniqueId;
	private Integer customEntityFieldType;
	private boolean sectionField;
	private String modifiedTime;
	
	public Long getCustomEntitySpecStockFieldsMappingConfigurationId() {
		return CustomEntitySpecStockFieldsMappingConfigurationId;
	}
	public void setCustomEntitySpecStockFieldsMappingConfigurationId(
			Long customEntitySpecStockFieldsMappingConfigurationId) {
		CustomEntitySpecStockFieldsMappingConfigurationId = customEntitySpecStockFieldsMappingConfigurationId;
	}
	public Long getCustomEntitySpecStockConfigurationId() {
		return customEntitySpecStockConfigurationId;
	}
	public void setCustomEntitySpecStockConfigurationId(Long customEntitySpecStockConfigurationId) {
		this.customEntitySpecStockConfigurationId = customEntitySpecStockConfigurationId;
	}
	public String getCustomEntityFieldId() {
		return customEntityFieldId;
	}
	public void setCustomEntityFieldId(String customEntityFieldId) {
		this.customEntityFieldId = customEntityFieldId;
	}
	public Integer getCustomEntityFieldType() {
		return customEntityFieldType;
	}
	public void setCustomEntityFieldType(Integer customEntityFieldType) {
		this.customEntityFieldType = customEntityFieldType;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public boolean isSectionField() {
		return sectionField;
	}
	public void setSectionField(boolean sectionField) {
		this.sectionField = sectionField;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	

}
