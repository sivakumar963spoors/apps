package com.spoors.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormSpecContainer {
	private Map<Long, Boolean> formSpecsVisibilityMap = new HashMap<Long, Boolean>();
	
	private List<FormSpec> formSpecs;
	private List<FormFieldSpec> fields;
	private List<FormFieldSpecValidValue> fieldValidValues;
	
	private List<FormSectionSpec> sections;
	private List<FormSectionFieldSpec> sectionFields;
	private List<FormSectionFieldSpecValidValue> sectionFieldValidValues;
	private List<FormPageSpec> pageSpecs;
	private List<ListFilteringCritiria>listFilteringCriterias;
	private List<VisibilityDependencyCriteria>visibilityDependencyCriterias;
	@JsonProperty("formFieldBackgroundColorDependency")
	private List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriterias;
	@JsonProperty("formCleanUpRules")
	private List<FormCleanUpRule> formCleanUpRule;
	private List<FormFieldGroupSpec> formFieldGroupSpecs;
	
	private List<FormSpecDataSource>formSpecDataSource;
	private List<DataSourceRequestHeader> dataSourceRequestHeaders;
	private List<DataSourceRequestParam> dataSourceRequestParams;
	private List<DataSourceResponseMapping> dataSourceResponseMappings;
	
	private List<JobFormMapBean> jobFormMapBeans;
	private List<CustomerFilteringCritiria> customerFilteringCriterias;
	private List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUp;
	private List<WorkFormFieldMap> workFormFieldMap;
	private List<PaymentMapping> paymentMappings;
	
	private List<FieldValidationCritiria> fieldValidationCritirias;
	
	private List<StockFormConfiguration> stockFormConfigurations;
	
	private List<FieldSpecFilter> formFieldSpecFilters;
	private List<FieldSpecFilter> formSectionFieldSpecFilters;
	private List<FormSpecPermission> formSpecPermissions;
	private List<FormSpecConfigSaveOnOtpVerify> saveFormOnOtpVerify;
	
	private List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations;
	
	String printTemplate;
	String emailTemplate;
	String mobilePrintTemplate;
	
	private List<FormFilteringCritiria> formFilteringCriterias;
	private List<EmployeeFilteringCritiria> employeeFilteringCriterias;
	
	private List<OfflineListUpdateConfiguration> offlineListUpdateConfigurations;
	
	private List<FormSpecPromotionsAndDiscountsConfiguration> formSpecPromotionsAndDiscountsConfiguration;
	
	private List<CustomEntitySpecStockConfiguration> customEntitySpecStockConfiguration;
	
	private List<CustomEntitySpecStockFieldsMappingConfiguration> customEntitySpecStockFieldsMappingConfiguration ;
	
	private List<PromotionAndDisCountFormSpecDetails> promotionAndDisCountFormSpecDetails;

	private List<PromotionAndDisCountFormSpecMappingDetails> promotionAndDisCountFormSpecMappingDetails;
	
	private List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfigurations;

	private List<FormFieldSpecsExtra> fieldsExtra;
	private List<FormSectionFieldSpecsExtra> sectionFieldsExtra;
	private List<CustomEntityFilteringCritiria> customEntityFilteringCritirias;
	
	private List<CustomerAutoFilteringCritiria> customerAutoFilteringCritirias;
	
	private List<RemainderFieldsMap> remainderFieldsMap;
	
	List<FieldValidation> fieldValidations;
	
	
//	private List<FormFieldType> fieldTypes;
	
	public FormSpecContainer() {
		this.formSpecs = new ArrayList<FormSpec>();
		this.fields = new ArrayList<FormFieldSpec>();
		this.fieldValidValues = new ArrayList<FormFieldSpecValidValue>();
		
		this.sections = new ArrayList<FormSectionSpec>();
		this.sectionFields = new ArrayList<FormSectionFieldSpec>();
		this.sectionFieldValidValues = new ArrayList<FormSectionFieldSpecValidValue>();
		
		this.listFilteringCriterias=new ArrayList<ListFilteringCritiria>();
		this.visibilityDependencyCriterias=new ArrayList<VisibilityDependencyCriteria>();
		this.jobFormMapBeans=new ArrayList<JobFormMapBean>();
		this.workSpecFormSpecFollowUp=new ArrayList<WorkSpecFormSpecFollowUp>();
		this.workFormFieldMap=new ArrayList<WorkFormFieldMap>();
		this.paymentMappings=new ArrayList<PaymentMapping>();
		this.fieldValidationCritirias = new ArrayList<FieldValidationCritiria>();
		this.setStockFormConfigurations(new ArrayList<StockFormConfiguration>());
		this.formFieldSpecFilters = new ArrayList<FieldSpecFilter>();
		this.formSectionFieldSpecFilters = new ArrayList<FieldSpecFilter>();
		this.formFilteringCriterias = new ArrayList<FormFilteringCritiria>();
		this.offlineListUpdateConfigurations = new ArrayList<OfflineListUpdateConfiguration>();
		this.customEntitySpecStockConfiguration = new ArrayList<CustomEntitySpecStockConfiguration>();
		this.customEntitySpecStockFieldsMappingConfiguration = new ArrayList<CustomEntitySpecStockFieldsMappingConfiguration>();
		this.fieldsExtra = new ArrayList<FormFieldSpecsExtra>();
		this.sectionFieldsExtra = new ArrayList<FormSectionFieldSpecsExtra>();
		this.remainderFieldsMap = new ArrayList<RemainderFieldsMap>();
//		this.fieldTypes = new ArrayList<FormFieldType>();
	}

	public Map<Long, Boolean> getFormSpecsVisibilityMap() {
		return formSpecsVisibilityMap;
	}

	public void setFormSpecsVisibilityMap(Map<Long, Boolean> formSpecsVisibilityMap) {
		this.formSpecsVisibilityMap = formSpecsVisibilityMap;
	}

	public List<FormSpec> getFormSpecs() {
		return formSpecs;
	}

	public void setFormSpecs(List<FormSpec> formSpecs) {
		this.formSpecs = formSpecs;
	}

	public List<FormFieldSpec> getFields() {
		return fields;
	}

	public void setFields(List<FormFieldSpec> fields) {
		this.fields = fields;
	}

	public List<FormFieldSpecValidValue> getFieldValidValues() {
		return fieldValidValues;
	}

	public void setFieldValidValues(List<FormFieldSpecValidValue> fieldValidValues) {
		this.fieldValidValues = fieldValidValues;
	}

	public List<FormSectionSpec> getSections() {
		return sections;
	}

	public void setSections(List<FormSectionSpec> sections) {
		this.sections = sections;
	}

	public List<FormSectionFieldSpec> getSectionFields() {
		return sectionFields;
	}

	public void setSectionFields(List<FormSectionFieldSpec> sectionFields) {
		this.sectionFields = sectionFields;
	}

	public List<FormSectionFieldSpecValidValue> getSectionFieldValidValues() {
		return sectionFieldValidValues;
	}

	public void setSectionFieldValidValues(
			List<FormSectionFieldSpecValidValue> sectionFieldValidValues) {
		this.sectionFieldValidValues = sectionFieldValidValues;
	}

	public List<FormPageSpec> getPageSpecs() {
		return pageSpecs;
	}

	public void setPageSpecs(List<FormPageSpec> pageSpecs) {
		this.pageSpecs = pageSpecs;
	}

	public List<ListFilteringCritiria> getListFilteringCriterias() {
		return listFilteringCriterias;
	}

	public void setListFilteringCriterias(List<ListFilteringCritiria> listFilteringCriterias) {
		this.listFilteringCriterias = listFilteringCriterias;
	}

	public List<VisibilityDependencyCriteria> getVisibilityDependencyCriterias() {
		return visibilityDependencyCriterias;
	}

	public void setVisibilityDependencyCriterias(List<VisibilityDependencyCriteria> visibilityDependencyCriterias) {
		this.visibilityDependencyCriterias = visibilityDependencyCriterias;
	}

	@JsonProperty("JobFormFieldSpecMapping")
	public List<JobFormMapBean> getJobFormMapBeans() {
		return jobFormMapBeans;
	}

	public void setJobFormMapBeans(List<JobFormMapBean> jobFormMapBeans) {
		this.jobFormMapBeans = jobFormMapBeans;
	}

	public List<FormSpecDataSource> getFormSpecDataSource() {
		return formSpecDataSource;
	}

	public void setFormSpecDataSource(List<FormSpecDataSource> formSpecDataSource) {
		this.formSpecDataSource = formSpecDataSource;
	}

	public List<DataSourceRequestHeader> getDataSourceRequestHeaders() {
		return dataSourceRequestHeaders;
	}

	public void setDataSourceRequestHeaders(
			List<DataSourceRequestHeader> dataSourceRequestHeaders) {
		this.dataSourceRequestHeaders = dataSourceRequestHeaders;
	}

	public List<DataSourceRequestParam> getDataSourceRequestParams() {
		return dataSourceRequestParams;
	}

	public void setDataSourceRequestParams(
			List<DataSourceRequestParam> dataSourceRequestParams) {
		this.dataSourceRequestParams = dataSourceRequestParams;
	}

	public List<DataSourceResponseMapping> getDataSourceResponseMappings() {
		return dataSourceResponseMappings;
	}

	public void setDataSourceResponseMappings(
			List<DataSourceResponseMapping> dataSourceResponseMappings) {
		this.dataSourceResponseMappings = dataSourceResponseMappings;
	}

	public List<CustomerFilteringCritiria> getCustomerFilteringCriterias() {
		return customerFilteringCriterias;
	}

	public void setCustomerFilteringCriterias(
			List<CustomerFilteringCritiria> customerFilteringCriterias) {
		this.customerFilteringCriterias = customerFilteringCriterias;
	}

	public List<WorkSpecFormSpecFollowUp> getWorkSpecFormSpecFollowUp() {
		return workSpecFormSpecFollowUp;
	}

	public void setWorkSpecFormSpecFollowUp(List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUp) {
		this.workSpecFormSpecFollowUp = workSpecFormSpecFollowUp;
	}

	public List<WorkFormFieldMap> getWorkFormFieldMap() {
		return workFormFieldMap;
	}

	public void setWorkFormFieldMap(List<WorkFormFieldMap> workFormFieldMap) {
		this.workFormFieldMap = workFormFieldMap;
	}

	public List<PaymentMapping> getPaymentMappings() {
		return paymentMappings;
	}

	public void setPaymentMappings(List<PaymentMapping> paymentMappings) {
		this.paymentMappings = paymentMappings;
	}

	public List<FieldValidationCritiria> getFieldValidationCritirias() {
		return fieldValidationCritirias;
	}

	public void setFieldValidationCritirias(List<FieldValidationCritiria> fieldValidationCritirias) {
		this.fieldValidationCritirias = fieldValidationCritirias;
	}

	public List<StockFormConfiguration> getStockFormConfigurations() {
		return stockFormConfigurations;
	}

	public void setStockFormConfigurations(List<StockFormConfiguration> stockFormConfigurations) {
		this.stockFormConfigurations = stockFormConfigurations;
	}

	public List<FormFieldGroupSpec> getFormFieldGroupSpecs() {
		return formFieldGroupSpecs;
	}

	public void setFormFieldGroupSpecs(List<FormFieldGroupSpec> formFieldGroupSpecs) {
		this.formFieldGroupSpecs = formFieldGroupSpecs;
	}

	public String getPrintTemplate() {
		return printTemplate;
	}

	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getMobilePrintTemplate() {
		return mobilePrintTemplate;
	}

	public void setMobilePrintTemplate(String mobilePrintTemplate) {
		this.mobilePrintTemplate = mobilePrintTemplate;
	}

	public List<FieldSpecFilter> getFormFieldSpecFilters() {
		return formFieldSpecFilters;
	}

	public void setFormFieldSpecFilters(List<FieldSpecFilter> formFieldSpecFilters) {
		this.formFieldSpecFilters = formFieldSpecFilters;
	}

	public List<FieldSpecFilter> getFormSectionFieldSpecFilters() {
		return formSectionFieldSpecFilters;
	}

	public void setFormSectionFieldSpecFilters(
			List<FieldSpecFilter> formSectionFieldSpecFilters) {
		this.formSectionFieldSpecFilters = formSectionFieldSpecFilters;
	}
	
	public List<FormFieldsColorDependencyCriterias> getFormFieldsColorDependencyCriterias() {
		return formFieldsColorDependencyCriterias;
	}

	public void setFormFieldsColorDependencyCriterias(
			List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriterias) {
		this.formFieldsColorDependencyCriterias = formFieldsColorDependencyCriterias;
	}
	public List<FormCleanUpRule> getFormCleanUpRule() {
		return formCleanUpRule;
	}

	public void setFormCleanUpRule(List<FormCleanUpRule> formCleanUpRule) {
		this.formCleanUpRule = formCleanUpRule;
	}

	public List<FormFilteringCritiria> getFormFilteringCriterias() {
		return formFilteringCriterias;
	}

	public void setFormFilteringCriterias(
			List<FormFilteringCritiria> formFilteringCriterias) {
		this.formFilteringCriterias = formFilteringCriterias;

	}

	public List<EmployeeFilteringCritiria> getEmployeeFilteringCriterias() {
		return employeeFilteringCriterias;
	}

	public void setEmployeeFilteringCriterias(List<EmployeeFilteringCritiria> employeeFilteringCriterias) {
		this.employeeFilteringCriterias = employeeFilteringCriterias;
	}

	public List<OfflineListUpdateConfiguration> getOfflineListUpdateConfigurations() {
		return offlineListUpdateConfigurations;
	}

	public void setOfflineListUpdateConfigurations(
			List<OfflineListUpdateConfiguration> offlineListUpdateConfigurations) {
		this.offlineListUpdateConfigurations = offlineListUpdateConfigurations;
	}

	public List<FormSpecPromotionsAndDiscountsConfiguration> getFormSpecPromotionsAndDiscountsConfiguration() {
		return formSpecPromotionsAndDiscountsConfiguration;
	}

	public void setFormSpecPromotionsAndDiscountsConfiguration(
			List<FormSpecPromotionsAndDiscountsConfiguration> formSpecPromotionsAndDiscountsConfiguration) {
		this.formSpecPromotionsAndDiscountsConfiguration = formSpecPromotionsAndDiscountsConfiguration;
	}

	public List<CustomEntitySpecStockConfiguration> getCustomEntitySpecStockConfiguration() {
		return customEntitySpecStockConfiguration;
	}

	public void setCustomEntitySpecStockConfiguration(
			List<CustomEntitySpecStockConfiguration> customEntitySpecStockConfiguration) {
		this.customEntitySpecStockConfiguration = customEntitySpecStockConfiguration;
	}

	public List<CustomEntitySpecStockFieldsMappingConfiguration> getCustomEntitySpecStockFieldsMappingConfiguration() {
		return customEntitySpecStockFieldsMappingConfiguration;
	}

	public void setCustomEntitySpecStockFieldsMappingConfiguration(
			List<CustomEntitySpecStockFieldsMappingConfiguration> customEntitySpecStockFieldsMappingConfiguration) {
		this.customEntitySpecStockFieldsMappingConfiguration = customEntitySpecStockFieldsMappingConfiguration;
	}
	
	public List<FormFieldSpecsExtra> getFieldsExtra() {
		return fieldsExtra;
	}

	public void setFieldsExtra(List<FormFieldSpecsExtra> fieldsExtra) {
		this.fieldsExtra = fieldsExtra;
	}

	public List<FormSectionFieldSpecsExtra> getSectionFieldsExtra() {
		return sectionFieldsExtra;
	}

	public void setSectionFieldsExtra(
			List<FormSectionFieldSpecsExtra> sectionFieldsExtra) {
		this.sectionFieldsExtra = sectionFieldsExtra;
	}

	public List<CustomEntityFilteringCritiria> getCustomEntityFilteringCritirias() {
		return customEntityFilteringCritirias;
	}

	public void setCustomEntityFilteringCritirias(List<CustomEntityFilteringCritiria> customEntityFilteringCritirias) {
		this.customEntityFilteringCritirias = customEntityFilteringCritirias;
	}

	public List<PromotionAndDisCountFormSpecDetails> getPromotionAndDisCountFormSpecDetails() {
		return promotionAndDisCountFormSpecDetails;
	}

	public void setPromotionAndDisCountFormSpecDetails(
			List<PromotionAndDisCountFormSpecDetails> promotionAndDisCountFormSpecDetails) {
		this.promotionAndDisCountFormSpecDetails = promotionAndDisCountFormSpecDetails;
	}

	public List<PromotionAndDisCountFormSpecMappingDetails> getPromotionAndDisCountFormSpecMappingDetails() {
		return promotionAndDisCountFormSpecMappingDetails;
	}

	public void setPromotionAndDisCountFormSpecMappingDetails(
			List<PromotionAndDisCountFormSpecMappingDetails> promotionAndDisCountFormSpecMappingDetails) {
		this.promotionAndDisCountFormSpecMappingDetails = promotionAndDisCountFormSpecMappingDetails;
	}

	public List<OfflineCustomEntityUpdateConfiguration> getOfflineCustomEntityUpdateConfigurations() {
		return offlineCustomEntityUpdateConfigurations;
	}

	public void setOfflineCustomEntityUpdateConfigurations(
			List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfigurations) {
		this.offlineCustomEntityUpdateConfigurations = offlineCustomEntityUpdateConfigurations;
	}

	public List<CustomerAutoFilteringCritiria> getCustomerAutoFilteringCritirias() {
		return customerAutoFilteringCritirias;
	}

	public void setCustomerAutoFilteringCritirias(List<CustomerAutoFilteringCritiria> customerAutoFilteringCritirias) {
		this.customerAutoFilteringCritirias = customerAutoFilteringCritirias;
	}

	public List<FormSpecPermission> getFormSpecPermissions() {
		return formSpecPermissions;
	}

	public void setFormSpecPermissions(List<FormSpecPermission> formSpecPermissions) {
		this.formSpecPermissions = formSpecPermissions;
	}
	
	public List<RemainderFieldsMap> getRemainderFieldsMap() {
		return remainderFieldsMap;
	}

	public void setRemainderFieldsMap(List<RemainderFieldsMap> remainderFieldsMap) {
		this.remainderFieldsMap = remainderFieldsMap;
	}

	public List<FormSpecConfigSaveOnOtpVerify> getSaveFormOnOtpVerify() {
		return saveFormOnOtpVerify;
	}

	public void setSaveFormOnOtpVerify(List<FormSpecConfigSaveOnOtpVerify> saveFormOnOtpVerify) {
		this.saveFormOnOtpVerify = saveFormOnOtpVerify;
	}

	public List<FieldValidation> getFieldValidations() {
		return fieldValidations;
	}

	public void setFieldValidations(List<FieldValidation> fieldValidations) {
		this.fieldValidations = fieldValidations;
	}

	public List<AutoGenereteSequenceSpecConfiguaration> getAutoGenereteSequenceSpecConfiguarations() {
		return autoGenereteSequenceSpecConfiguarations;
	}

	public void setAutoGenereteSequenceSpecConfiguarations(
			List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations) {
		this.autoGenereteSequenceSpecConfiguarations = autoGenereteSequenceSpecConfiguarations;
	}
	
	
	
	

//	public List<FormFieldType> getFieldTypes() {
//		return fieldTypes;
//	}
//
//	public void setFieldTypes(List<FormFieldType> fieldTypes) {
//		this.fieldTypes = fieldTypes;
//	}
	
}
