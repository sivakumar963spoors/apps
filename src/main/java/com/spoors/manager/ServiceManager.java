package com.spoors.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spoors.beans.EntityFieldSpecValidValue;
import com.spoors.beans.EntitySectionField;
import com.spoors.beans.EntitySectionFieldSpec;
import com.spoors.beans.EntitySectionSpec;
import com.spoors.beans.Activity;
import com.spoors.beans.AutoGenereteSequenceSpecConfiguaration;
import com.spoors.beans.AutoGenereteSequenceSpecConfiguarationField;
import com.spoors.beans.FieldSpecRestrictionGroup;
import com.spoors.beans.FieldValidation;
import com.spoors.beans.CompanyFont;
import com.spoors.beans.Constants;
import com.spoors.beans.CustomEntityFilteringCritiria;
import com.spoors.beans.CustomEntitySpec;
import com.spoors.beans.CustomerAutoFilteringCritiria;
import com.spoors.beans.CustomerFilteringCritiria;
import com.spoors.beans.DataSourceRequestHeader;
import com.spoors.beans.DataSourceRequestParam;
import com.spoors.beans.DataSourceResponseMapping;
import com.spoors.beans.Employee;
import com.spoors.beans.EmployeeFilteringCritiria;
import com.spoors.beans.Entity;
import com.spoors.beans.EntityField;
import com.spoors.beans.EntityFieldSpec;
import com.spoors.beans.EntitySpec;
import com.spoors.beans.FieldRestrictionCritiria;
import com.spoors.beans.FieldSpecFilter;
import com.spoors.beans.FieldValidationCritiria;
import com.spoors.beans.FormCleanUpRule;
import com.spoors.beans.FormFieldGroupSpec;
import com.spoors.beans.FormFieldSpec;
import com.spoors.beans.FormFieldSpecValidValue;
import com.spoors.beans.FormFieldSpecsExtra;
import com.spoors.beans.FormFieldsColorDependencyCriterias;
import com.spoors.beans.FormFilteringCritiria;
import com.spoors.beans.FormPageSpec;
import com.spoors.beans.FormSectionFieldSpec;
import com.spoors.beans.FormSectionFieldSpecValidValue;
import com.spoors.beans.FormSectionFieldSpecsExtra;
import com.spoors.beans.FormSectionSpec;
import com.spoors.beans.FormSpec;
import com.spoors.beans.FormSpecConfigSaveOnOtpVerify;
import com.spoors.beans.FormSpecContainer;
import com.spoors.beans.FormSpecDataSource;
import com.spoors.beans.FormSpecPermission;
import com.spoors.beans.JobFormMapBean;
import com.spoors.beans.ListFilteringCritiria;
import com.spoors.beans.Media;
import com.spoors.beans.OfflineCustomEntityUpdateConfiguration;
import com.spoors.beans.OfflineListUpdateConfiguration;
import com.spoors.beans.PaymentMapping;
import com.spoors.beans.RemainderFieldsMap;
import com.spoors.beans.StockFormConfiguration;
import com.spoors.beans.VisibilityDependencyCriteria;
import com.spoors.beans.WebUser;
import com.spoors.beans.WorkFormFieldMap;
import com.spoors.beans.WorkSpecFormSpecFollowUp;
import com.spoors.beans.workSpecs.ActionableEmployeeGroupSpecs;
import com.spoors.beans.workSpecs.AddingSubTaskEmployeeConfiguration;
import com.spoors.beans.workSpecs.AttachmnetFormAutoFillSectionConfiguration;
import com.spoors.beans.workSpecs.ExternalActionConfiguration;
import com.spoors.beans.workSpecs.FormAutoFillSectionConfiguration;
import com.spoors.beans.workSpecs.FormAutoFillSectionFieldsConfiguration;
import com.spoors.beans.workSpecs.FormToWorkAutoFill;
import com.spoors.beans.workSpecs.FormToWorkAutoFillField;
import com.spoors.beans.workSpecs.FormToWorkAutoFillSectionConfiguration;
import com.spoors.beans.workSpecs.FormToWorkAutoFillSectionFieldsConfiguration;
import com.spoors.beans.workSpecs.HideAddSubTaskConfiguration;
import com.spoors.beans.workSpecs.NextActionSpec;
import com.spoors.beans.workSpecs.NextWorkSpec;
import com.spoors.beans.workSpecs.WorkActionExcalatedEmpIds;
import com.spoors.beans.workSpecs.WorkActionFormVisibility;
import com.spoors.beans.workSpecs.WorkActionGroup;
import com.spoors.beans.workSpecs.WorkActionNotificationEscalationMatrix;
import com.spoors.beans.workSpecs.WorkActionSpec;
import com.spoors.beans.workSpecs.WorkActionSpecConditions;
import com.spoors.beans.workSpecs.WorkActionSpecEndCondition;
import com.spoors.beans.workSpecs.WorkActionSpecVisibilityCondition;
import com.spoors.beans.workSpecs.WorkActionVisibilityConfiguration;
import com.spoors.beans.workSpecs.WorkAssignmentCriteriaConditions;
import com.spoors.beans.workSpecs.WorkAttachmentAutoFill;
import com.spoors.beans.workSpecs.WorkAttachmentAutoFillSectionFieldsConfiguration;
import com.spoors.beans.workSpecs.WorkAttachmentFormAutoFillField;
import com.spoors.beans.workSpecs.WorkFieldsUniqueConfigurations;
import com.spoors.beans.workSpecs.WorkFormAutoFill;
import com.spoors.beans.workSpecs.WorkFormAutoFillField;
import com.spoors.beans.workSpecs.WorkProcessSubTaskSpec;
import com.spoors.beans.workSpecs.WorkReassignmentRules;
import com.spoors.beans.workSpecs.WorkSpec;
import com.spoors.beans.workSpecs.WorkSpecAppLabel;
import com.spoors.beans.workSpecs.WorkSpecContainer;
import com.spoors.beans.workSpecs.WorkSpecCustomDashboardConfiguration;
import com.spoors.beans.workSpecs.WorkSpecCustomDashboardMetric;
import com.spoors.beans.workSpecs.WorkSpecCustomerCallApi;
import com.spoors.beans.workSpecs.WorkSpecFormSpecMap;
import com.spoors.beans.workSpecs.WorkSpecListLevelVisibilityConfiguration;
import com.spoors.beans.workSpecs.WorkSpecPermission;
import com.spoors.beans.workSpecs.WorkToSubTaskAutoFillConfiguration;
import com.spoors.beans.workSpecs.WorkUnassignmentCriterias;
import com.spoors.dao.EffortDao;
import com.spoors.util.Api;
import com.spoors.util.Api.CsvOptions;

import ch.qos.logback.classic.Logger;

@Service
public class ServiceManager 
{
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceManager.class);
	
	public EffortDao effortDao;
	
	public ServiceManager(EffortDao effortDao) {
		this.effortDao = effortDao;
	}

	public void getExportFormSpecData(Set<String> formSpecUniqueIds, FormSpecContainer formSpecContainer)
	{
		try {
			
			List<FormSpec> latestFormSpecs = effortDao.getLatestFormSpecsForUnquids(Api.toCSV(formSpecUniqueIds));
			
			if(latestFormSpecs == null || latestFormSpecs.size() == 0) {
				return;
			}
			List<FormSpec> formSpecs = effortDao.getFormSpecsIn(Api.toCSVFromList(latestFormSpecs, "formSpecId"));
			
			
			if(formSpecs!=null) {
				for(FormSpec formSpec : formSpecs) {
					Activity activity = effortDao.getActivitiesByUniqueId(formSpec.getUniqueId());
					if(activity !=null) {
						formSpec.setEnableCustomerActivity(true);
					}
				}
				List<String> uniqueIdsList = new ArrayList<>();
				uniqueIdsList.addAll(formSpecUniqueIds);
				
				String uniqueIdsCsv = Api.processStringValuesList(uniqueIdsList);
				
				String formSpecIds = Api.toCSV(formSpecs,"formSpecId",CsvOptions.FILTER_NULL_OR_EMPTY);
				
				List<FormPageSpec> pageSpecs = getFormPageSpecsForFormSpec(formSpecIds);
				
				List<FormFieldSpec> fieldSpecs = getFormFieldSpecForIn(formSpecs);
				
				List<FormFieldSpecsExtra> fieldSpecsExtra =	getFormFieldSpecsExtraForIn(formSpecs);//phone masking
				
				List<FormFieldSpecValidValue> fieldSpecValidValues = getFormFieldSpecValidValues(fieldSpecs);
				
				List<FormSectionSpec> sectionSpecs = effortDao.getFormSectionSpecForIn(formSpecs);
				
				List<FormSectionFieldSpec> sectionFieldSpecs = getFormSectionFieldSpecForIn(sectionSpecs);
				
				List<FormSectionFieldSpecsExtra> sectionFieldSpecsExtra = getFormSectionFieldSpecsForIn(formSpecs);//repeatbel section
				
				List<FormSectionFieldSpecValidValue> sectionFieldSpecValidValues = getFormSectionFieldSpecValidValuesIn(sectionFieldSpecs);
				
				List<FormFieldGroupSpec> formFieldGroupSpecs = getFormFieldGroupSpecForIn(formSpecIds);
				
				List<VisibilityDependencyCriteria> visibilityDependencyCriterias = getVisibilityDependencyCriteriasForFormSpecs(formSpecIds);
				
				List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriterias = getFormFieldsColorDependencyCriteriasByFormSpecId(formSpecIds);
				
				List<FormCleanUpRule> formCleanUpRule = getFormDataCleanUpRulesForFormSpecId(formSpecIds);
				
				List<ListFilteringCritiria> lisFilteringCritirias =  getListFilteringCritiriasForFormSpecs(formSpecIds);
				
				List<RemainderFieldsMap> remainderFieldsMap = getRemainderFieldsMapForFormSpecs(formSpecIds);
				
				List<CustomerAutoFilteringCritiria> customerAutoFilteringCritirias = getCustomerAutoFilteringCritiriasForFormSpecs(formSpecIds);
				
				List<CustomerFilteringCritiria> customerFilteringCritirias = getListOfCustomerFilterCriterias(formSpecIds);
				
				List<EmployeeFilteringCritiria> employeeFilteringCritirias = getListOfEmployeeFilterCriterias(formSpecIds);
				
				List<FieldValidationCritiria> fieldValidationCritirias = getFieldValidationCritirias(formSpecIds);
				List<FieldRestrictionCritiria> fieldRestrictionCritirias = getFieldRestrictionCritiria(formSpecIds);
				
				List<FormFilteringCritiria> formFilteringCritirias = getFormFilteringCritiriasForFormSpecs(formSpecIds);
				
				List<CustomEntityFilteringCritiria> customEntityFilteringCritirias = getListOfCustomEntityFilteringCritiriaForFormSpecs(formSpecIds);
				
				List<StockFormConfiguration> stockFormConfigurations = getStockFieldConfigurationsForSync(formSpecIds);
				
				List<OfflineListUpdateConfiguration> offlineListUpdateConfiguration = getOfflineListUpdateConfigurationsForSync(formSpecIds);
				
				List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfiguration = getOfflineCustomEntityUpdateConfigurationForSync(formSpecIds);
				
				List<FieldSpecFilter> formFieldSpecFilters = getFieldSpecFiltersFormFormSpecIds(formSpecIds, FieldSpecFilter.FIELD_IS_FORMFIELD);
				
				List<FieldSpecFilter> formSectionFieldSpecFilters = getFieldSpecFiltersFormFormSpecIds(formSpecIds, FieldSpecFilter.FIELD_IS_SECTIONFIELD);
				
				List<PaymentMapping> paymentMappings = getPaymentMappingByFormSpec(uniqueIdsCsv);
				
				List<FormSpecDataSource> formSpecDataSource =  getFormSpecDataSourceUsingUniqueId(uniqueIdsCsv);
				if(formSpecDataSource != null && !formSpecDataSource.isEmpty()){
					for (FormSpecDataSource formSpecDataSource2 : formSpecDataSource) {
						formSpecDataSource2.setDataSourceRequestHeaders(null);
						formSpecDataSource2.setDataSourceRequestParams(null);
						formSpecDataSource2.setDataSourceResponseMappings(null);
					}
				}
				
				
				formSpecContainer.getFormSpecs().addAll(formSpecs);
				formSpecContainer.getPageSpecs().addAll(pageSpecs);
				formSpecContainer.getFields().addAll(fieldSpecs);
				formSpecContainer.getFieldsExtra().addAll(fieldSpecsExtra);
				formSpecContainer.getFieldValidValues().addAll(fieldSpecValidValues);
				formSpecContainer.getSections().addAll(sectionSpecs);
				formSpecContainer.getSectionFields().addAll(sectionFieldSpecs);
				formSpecContainer.getSectionFieldsExtra().addAll(sectionFieldSpecsExtra);
				formSpecContainer.getSectionFieldValidValues().addAll(sectionFieldSpecValidValues);
				formSpecContainer.getFormFieldGroupSpecs().addAll(formFieldGroupSpecs);
				formSpecContainer.getVisibilityDependencyCriterias().addAll(visibilityDependencyCriterias);
				formSpecContainer.getFormFieldsColorDependencyCriterias().addAll(formFieldsColorDependencyCriterias);//
				formSpecContainer.getListFilteringCriterias().addAll(lisFilteringCritirias);
				formSpecContainer.getRemainderFieldsMap().addAll(remainderFieldsMap);
				formSpecContainer.getCustomerAutoFilteringCritirias().addAll(customerAutoFilteringCritirias);
				formSpecContainer.getCustomerFilteringCriterias().addAll(customerFilteringCritirias);
				formSpecContainer.getEmployeeFilteringCriterias().addAll(employeeFilteringCritirias);
				formSpecContainer.getFieldValidationCritirias().addAll(fieldValidationCritirias);
				formSpecContainer.getFieldRestrictionCritirias().addAll(fieldRestrictionCritirias);
				formSpecContainer.getFormFilteringCriterias().addAll(formFilteringCritirias);
				formSpecContainer.getCustomEntityFilteringCritirias().addAll(customEntityFilteringCritirias);
				formSpecContainer.getStockFormConfigurations().addAll(stockFormConfigurations);
				formSpecContainer.getOfflineListUpdateConfigurations().addAll(offlineListUpdateConfiguration);
				formSpecContainer.getOfflineCustomEntityUpdateConfigurations().addAll(offlineCustomEntityUpdateConfiguration);
				formSpecContainer.getFormFieldSpecFilters().addAll(formFieldSpecFilters);
				formSpecContainer.getFormSectionFieldSpecFilters().addAll(formSectionFieldSpecFilters);
				formSpecContainer.getPaymentMappings().addAll(paymentMappings);
				
				String formSpecDataSourceIds = "-1";
				if(formSpecDataSource != null && !formSpecDataSource.isEmpty()){
					formSpecDataSourceIds = Api.toCSV(formSpecDataSource, "formSpecDataSourceId", CsvOptions.NONE);
					List<DataSourceRequestHeader> dataSourceRequestHeaders = getDataSourceRequestHeaders(formSpecDataSourceIds);
					formSpecContainer.setDataSourceRequestHeaders(dataSourceRequestHeaders);
					
					List<DataSourceRequestParam> dataSourceRequestParams = getDataSourceRequestParam(formSpecDataSourceIds); 
					formSpecContainer.setDataSourceRequestParams(dataSourceRequestParams);
					
					List<DataSourceResponseMapping> dataSourceResponseMappings = getDataSourceResponseMapping(formSpecDataSourceIds);
					formSpecContainer.setDataSourceResponseMappings(dataSourceResponseMappings);
					
				}
				
				List<FormSpecPermission> formSpecPermissions = effortDao.geFormSpecPermissionsForSync("",formSpecIds,uniqueIdsCsv);
				formSpecContainer.setFormSpecPermissions(formSpecPermissions);
				
				List<FormSpecConfigSaveOnOtpVerify> saveFormOnOtpVerifyList = getFormSpecConfigSaveOnOtpVerifyList(uniqueIdsCsv);
				formSpecContainer.setSaveFormOnOtpVerify(saveFormOnOtpVerifyList);
				
				List<JobFormMapBean> jobFormMapBeans = getJobFormMapBeans(formSpecIds+"",null);
				formSpecContainer.setJobFormMapBeans(jobFormMapBeans);
				
				List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUpList = getWorkSpecFormSpecFollowUpForSync(formSpecIds,"1970-01-01 00:00:00");
				List<WorkFormFieldMap> workFormFieldMaps = getWorkFormFieldMappingByWorkSpecFormSpecFollowUpIds(Api.toCSV(workSpecFormSpecFollowUpList, "workSpecFormSpecFollowUpId", CsvOptions.NONE));
				
				formSpecContainer.setWorkSpecFormSpecFollowUp(workSpecFormSpecFollowUpList);
				formSpecContainer.setWorkFormFieldMap(workFormFieldMaps);

				List<FieldValidation> fieldValidations = effortDao.getFieldValidationsForFormSpecIds(formSpecIds);

				if (fieldValidations != null && !fieldValidations.isEmpty()) {
				    formSpecContainer.getFieldValidations().addAll(fieldValidations);
				}



				
			}
			
		}catch(Exception e) {
			LOGGER.info("Got Exception while getExportFormSpecData() : "+e);
			LOGGER.error("Got Exception while getExportFormSpecData() : "+e);
			e.printStackTrace();
		}
	}

	public List<FormFieldSpec> getFormFieldSpecForIn(List<FormSpec> formSpecs) {
		String ids = "";
		for (FormSpec formSpec : formSpecs) {
			if (!Api.isEmptyString(ids)) {
				ids += ",";
			}

			ids += "'" + formSpec.getFormSpecId() + "'";
		}

		return effortDao.getFormFieldSpecForIn(ids);
	}
	
	private List<WorkFormFieldMap> getWorkFormFieldMappingByWorkSpecFormSpecFollowUpIds(String workSpecFormSpecFollowUpIds) {
		// TODO Auto-generated method stub
		return effortDao.getWorkFormFieldMappingByWorkSpecFormSpecFollowUpIds(workSpecFormSpecFollowUpIds);
	}

	private List<WorkSpecFormSpecFollowUp> getWorkSpecFormSpecFollowUpForSync(String formSpecIds,
			String syncDate) {
		return effortDao.getWorkSpecFormSpecFollowUpForSync(formSpecIds,syncDate);
	}

	private List<JobFormMapBean> getJobFormMapBeans(String formSpecIds, String syncDate) {
		return effortDao.getJobFormMapBeans(formSpecIds,syncDate);
	}

	private List<FormSpecConfigSaveOnOtpVerify> getFormSpecConfigSaveOnOtpVerifyList(String uniqueIdsCsv) {
		return effortDao.getFormSpecConfigSaveOnOtpVerifyList(uniqueIdsCsv);
	}

	private List<FormSpecPermission> geFormSpecPermissions(String uniqueId, boolean check) {
		return effortDao.getFormSpecPermissions(uniqueId,check);
	}

	private List<DataSourceResponseMapping> getDataSourceResponseMapping(String formSpecDataSourceIds) {
		return effortDao.getDataSourceResponseMapping(formSpecDataSourceIds);
	}

	private List<DataSourceRequestParam> getDataSourceRequestParam(String formSpecDataSourceIds) {
		return effortDao.getDataSourceRequestParam(formSpecDataSourceIds);
	}

	private List<DataSourceRequestHeader> getDataSourceRequestHeaders(String formSpecDataSourceIds) {
		return effortDao.getDataSourceRequestHeaders(formSpecDataSourceIds);
	}

	private List<FormSpecDataSource> getFormSpecDataSourceUsingUniqueId(String uniqueIdsCsv) {
		return effortDao.getFormSpecDataSourceUsingUniqueId(uniqueIdsCsv);
	}

	private List<PaymentMapping> getPaymentMappingByFormSpec(String uniqueIds) {
		return effortDao.getPaymentMappingByFormSpec(uniqueIds);
	}

	private List<FieldSpecFilter> getFieldSpecFiltersFormFormSpecIds(String formSpecIds, int formfieldType) 
	{
		return effortDao.getFieldSpecFiltersFormFormSpecIds(formSpecIds,formfieldType);
	}

	private List<OfflineCustomEntityUpdateConfiguration> getOfflineCustomEntityUpdateConfigurationForSync(String formSpecIds) {
		return effortDao.getOfflineCustomEntityUpdateConfigurationForSync(formSpecIds);
	}

	private List<OfflineListUpdateConfiguration> getOfflineListUpdateConfigurationsForSync(String formSpecIds) {
		return effortDao.getOfflineListUpdateConfigurationsForSync(formSpecIds);
	}

	private List<StockFormConfiguration> getStockFieldConfigurationsForSync(String formSpecIds) {
		return effortDao.getStockFieldConfigurationsForSync(formSpecIds);
	}

	private List<CustomEntityFilteringCritiria> getListOfCustomEntityFilteringCritiriaForFormSpecs(String formSpecIds) {
		return effortDao.getListOfCustomEntityFilteringCritiriaForFormSpecs(formSpecIds);
	}

	private List<FormFilteringCritiria> getFormFilteringCritiriasForFormSpecs(String formSpecIds) {
		return effortDao.getFormFilteringCritiriasForFormSpecs(formSpecIds);
	}

	private List<CustomerAutoFilteringCritiria> getCustomerAutoFilteringCritiriasForFormSpecs(String formSpecIds) {
		return effortDao.getCustomerAutoFilteringCritiriasForFormSpecs(formSpecIds);
	}

	private List<RemainderFieldsMap> getRemainderFieldsMapForFormSpecs(String formSpecIds) {
		// TODO Auto-generated method stub
		return effortDao.getRemainderFieldsMapForFormSpecs(formSpecIds);
	}

	private List<FormSectionFieldSpecsExtra> getFormSectionFieldSpecsForIn(List<FormSpec> formSpecs) {
		String ids = "";
		if(formSpecs!= null && formSpecs.size()>0){
			ids = Api.toCSV(formSpecs, "formSpecId", CsvOptions.FILTER_NULL_OR_EMPTY);
		}
		return effortDao.getFormSectionFieldSpecsForIn(ids);
	}

	private List<FormFieldSpecsExtra> getFormFieldSpecsExtraForIn(List<FormSpec> formSpecs) 
	{
		String ids = "";
		if(formSpecs!= null && formSpecs.size()>0){
			ids = Api.toCSV(formSpecs, "formSpecId", CsvOptions.FILTER_NULL_OR_EMPTY);
		}
		return effortDao.getFormFieldSpecsExtraForIn(ids);
	}

	private List<FieldValidationCritiria> getFieldValidationCritirias(String formSpecIds) {
		return effortDao.getFieldValidationCritirias(formSpecIds);
	}
	private List<FieldRestrictionCritiria> getFieldRestrictionCritiria(String formSpecIds) {
		return effortDao.getFieldRestrictionCritirias(formSpecIds);
	}
	private List<EmployeeFilteringCritiria> getListOfEmployeeFilterCriterias(String formSpecIds) {
		return effortDao.getListOfEmployeeFilterCriterias(formSpecIds);
	}

	private List<CustomerFilteringCritiria> getListOfCustomerFilterCriterias(String formSpecIds) {
		return effortDao.getListOfCustomerFilterCriterias(formSpecIds);
	}

	private List<ListFilteringCritiria> getListFilteringCritiriasForFormSpecs(String formSpecIds) {
		return effortDao.getListFilteringCritiriasForFormSpecs(formSpecIds);
	}

	private List<FormCleanUpRule> getFormDataCleanUpRulesForFormSpecId(String formSpecIds) {
		return effortDao.getFormDataCleanUpRulesForFormSpecId(formSpecIds);
	}

	private List<FormFieldsColorDependencyCriterias> getFormFieldsColorDependencyCriteriasByFormSpecId(
			String formSpecIds) {
		return effortDao.getFormFieldsColorDependencyCriteriasByFormSpecId(formSpecIds);
	}

	private List<VisibilityDependencyCriteria> getVisibilityDependencyCriteriasForFormSpecs(String formSpecIds) {
		return effortDao.getVisibilityDependencyCriteriasForFormSpecs(formSpecIds);
	}

	private List<FormFieldGroupSpec> getFormFieldGroupSpecForIn(String formSpecId) {
		return effortDao.getFormFieldGroupSpecForIn(formSpecId);
	}

	private List<FormSectionFieldSpecValidValue> getFormSectionFieldSpecValidValuesIn(
			List<FormSectionFieldSpec> sectionFieldSpecs) {
		return effortDao.getFormSectionFieldSpecValidValuesIn(sectionFieldSpecs);
	}

	private List<FormSectionFieldSpec> getFormSectionFieldSpecForIn(List<FormSectionSpec> sectionSpecs) {
		return effortDao.getFormSectionFieldSpecForIn(sectionSpecs);
	}

	private List<FormSectionSpec> getFormSectionSpecs(long formSpecId) {
		List<FormSectionSpec> formSectionSpecs = effortDao
				.getFormSectionSpecs(formSpecId);
		Map<Long, List<FormSectionFieldSpec>> sectionFieldSpecMap = effortDao
				.getFormSectionFieldSpecMap(formSectionSpecs);

		for (FormSectionSpec formSectionSpec : formSectionSpecs) {
			List<FormSectionFieldSpec> sectionFieldSpecs = sectionFieldSpecMap
					.get(formSectionSpec.getSectionSpecId());
			resolveFormSectionFieldStyleProperties(sectionFieldSpecs);
			formSectionSpec.setFormSectionFieldSpecs(sectionFieldSpecs);
		}

		return formSectionSpecs;
	}
	
	public void resolveFormSectionFieldStyleProperties(List<FormSectionFieldSpec> formSectionFieldSpecsList) {
		
		if(formSectionFieldSpecsList!=null) {
			for(FormSectionFieldSpec fsf : formSectionFieldSpecsList){
				if(fsf.getFieldLabelFontId() != null) {
					CompanyFont companyFont = getCompanyFont(fsf.getFieldLabelFontId());
					if(companyFont != null) {
						if(companyFont.getFontColor() != null) {
							fsf.setFontColor(companyFont.getFontColor());
						}
						if(companyFont.getFontSize() != null) {
							fsf.setFontSize(companyFont.getFontSize());
						}
						fsf.setBold(companyFont.isBold());
						fsf.setItalic(companyFont.isItalic());
						fsf.setUnderLine(companyFont.isUnderLine());
					}
				}
			}
		}
					
	}

	private CompanyFont getCompanyFont(Long fieldLabelFontId) {
		return effortDao.getCompanyFont(fieldLabelFontId);
	}

	private List<FormFieldSpecValidValue> getFormFieldSpecValidValues(List<FormFieldSpec> fieldSpecs) {
		return effortDao.getFormFieldSpecValidValues(fieldSpecs);
	}

	private List<FormFieldSpec> getFormFieldSpecs(long formSpecId) {
		return effortDao.getFormFieldSpecs(formSpecId);
	}

	private List<FormPageSpec> getFormPageSpecsForFormSpec(String formSpecIds) {
		List<FormPageSpec> formPageSpecs = effortDao.getFormPageSpecsForFormSpec(formSpecIds);
		return formPageSpecs;
	}

	public void createFormSpecs(List<FormSpecContainer> formSpecContainerList, Integer companyId) {
		
		Map<Long, Long> formSpecIdsMap = new HashMap<Long, Long>();
		Map<String, String> formSpecUniqueIdsMap = new HashMap<String, String>();
		Map<Long, Long> visitStatesMap = new HashMap<Long, Long>();
		Map<String, String> formFieldSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> formSectionFieldSpecUniqueIdsMap = new HashMap<String, String>();
		Map<Long, Long> formFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> formSectionFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> entityFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> entitySpecsIdMap = new HashMap<Long, Long>();
		Map<Long, EntitySpec> entitySpecMap = new HashMap<Long, EntitySpec>();
		Map<Long, Long> formSectionSpecsIdMap = new HashMap<Long, Long>();
		Map<String, String> formSectionSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> innerformSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> customEntitySpecIdsMap = new HashMap<String, String>();

		
		Map<Long, CustomEntitySpec> customEntitySpecsMap = new HashMap<Long, CustomEntitySpec>();
		List<EntitySpec> entitySpecs = getAllEntitySpecsForCompany(companyId);
		entitySpecMap = (Map)Api.getMapFromList(entitySpecs, "entitySpecId");
		List<CustomEntitySpec> customEntitySpecs = getCustomEntitySpecs(companyId);
		customEntitySpecsMap = (Map)Api.getMapFromList(customEntitySpecs, "customEntitySpecId");
		
		WebUser webUser = new WebUser();
		long rootEmpId = effortDao.getRootEmployeeId(companyId);
		Employee employee =effortDao.getEmployeeBasicDetailsAndTimeZoneByEmpId(rootEmpId+"");
		webUser.setEmpId(employee.getEmpId());
		webUser.setTzo(employee.getTimezoneOffset());
		webUser.setCompanyId(employee.getCompanyId());
		
		for(FormSpecContainer formSpecContainer : formSpecContainerList) {
			
			copyTemplatesFromSkeleton(webUser,formSpecContainer,entitySpecsIdMap, entitySpecMap, formSpecIdsMap,
					formSpecUniqueIdsMap, formFieldSpecUniqueIdsMap, formSectionFieldSpecUniqueIdsMap,
					formFieldSpecsIdMap, formSectionFieldSpecsIdMap, entityFieldSpecsIdMap, true, true,
					formSectionSpecUniqueIdsMap, formSectionSpecsIdMap,customEntitySpecIdsMap,customEntitySpecsMap,innerformSpecUniqueIdsMap,true);
			
		}
		
		
		
	}
	public void copyTemplatesFromSkeleton(WebUser webUser, FormSpecContainer formSpecContainer,
			Map<Long, Long> entitySpecsIdMap,
			Map<Long, EntitySpec> entitySpecMap, Map<Long, Long> formSpecIdsMap,
			Map<String, String> formSpecUniqueIdsMap, Map<String, String> formFieldSpecUniqueIdsMap,
			Map<String, String> formSectionFieldSpecUniqueIdsMap, Map<Long, Long> formFieldSpecsIdMap,
			Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> entityFieldSpecsIdMap, Boolean isSkeleton,
			boolean isWorkSpec, Map<String, String> formSectionSpecUniqueIdsMap, Map<Long, Long> formSectionSpecsIdMap,
			Map<String, String> customEntitySpecIdsMap, Map<Long, CustomEntitySpec> customEntitySpecsMap,
			Map<String, String> innerformSpecUniqueIdsMap,boolean cloneInnerFormSpecs) {
		
		
		List<FormPageSpec> formPageSpecs = formSpecContainer.getPageSpecs();
		List<FormSpec> formSpecList = formSpecContainer.getFormSpecs();
		FormSpec formSpec = formSpecList.get(0);
		
		FormSpec formSpecBySkeletonSpecId = effortDao.getFormSpecBySkeletonSpecId(formSpec.getFormSpecId());
		if(formSpecBySkeletonSpecId != null) {
			LOGGER.info(" FormSpec already Exported  : "+formSpecBySkeletonSpecId.getFormSpecId());
			return;
		}
		
		String printTemplate = Api
				.makeEmptyIfNull(getFormSpecPrintTemplate(formSpec
						.getFormSpecId() + ""));
		String emailTemplate = Api
				.makeEmptyIfNull(getFormSpecEmailTemplate(formSpec
						.getFormSpecId() + ""));
		
		String printTemplatePdfSaveNameFieldUniqueId = null;
		String mobilePrintTemplate = Api
				.makeEmptyIfNull(getFormSpecMobilePrintTemplate(formSpec
						.getFormSpecId() + ""));

		List<FormFieldSpec> formFieldSpecs = formSpecContainer.getFields();
		List<FormSectionSpec> formSectionSpecs = formSpecContainer.getSections();
		
		List<FormFieldGroupSpec> formFieldGroupSpecs = formSpecContainer.getFormFieldGroupSpecs();
		// List<EntitySpec> extraEntities=new ArrayList<EntitySpec>();
		List<FormSectionFieldSpec> formSectionFieldSpecs = formSpecContainer.getSectionFields();
		
		List<FormFieldSpecValidValue> formFieldSpecValidValues = formSpecContainer.getFieldValidValues();

		List<FormSectionFieldSpecValidValue> formSectionFieldSpecValidValues = formSpecContainer
				.getSectionFieldValidValues();
		
		List<EntitySpec> companyEntitySpecs = getEntitySpecsForCompany(webUser
				.getCompanyId());
		Map<Long, EntitySpec> companyEntitySpecMap = new HashMap<Long, EntitySpec>();
		for (EntitySpec entitySpec : companyEntitySpecs) {
			companyEntitySpecMap.put(entitySpec.getParentId(), entitySpec);
		}
		
		List<EntityFieldSpec> companyEntityFieldSpecs = effortDao.getEntityFieldSpecs(Api.toCSV(companyEntitySpecs, "entitySpecId", CsvOptions.FILTER_NULL_OR_EMPTY));
		
		Map<Long,List<EntityFieldSpec>> companyEntityFieldSpecMap = new HashMap<Long, List<EntityFieldSpec>>();
		if(companyEntityFieldSpecs!=null && companyEntityFieldSpecs.size()>0)
		{
			companyEntityFieldSpecMap = (Map) Api.getResolvedMapFromList(companyEntityFieldSpecs, "entitySpecId");
		}
				 
		Map<Long, CustomEntitySpec> companyCustomEntitySpecsMap = new HashMap<Long, CustomEntitySpec>();
		List<CustomEntitySpec> customEntitySpecsCompany = getCustomEntitySpecs(webUser.getCompanyId());
		companyCustomEntitySpecsMap = (Map)Api.getMapFromList(customEntitySpecsCompany, "skeletonCustomEntitySpecId");

		List<EntitySpec> entitySpecs = new ArrayList<EntitySpec>();
		
		List<CustomEntitySpec> customEntitySpecs = new ArrayList<CustomEntitySpec>();
		if (formFieldSpecs != null) {
			for (FormFieldSpec formFieldSpec : formFieldSpecs) {
				if ((formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST)
						&& !Api.isEmptyString(formFieldSpec.getFieldTypeExtra())) {
					if (!entitySpecsIdMap.containsKey(Long
							.parseLong(formFieldSpec.getFieldTypeExtra()))) {
						EntitySpec entitySpecTemp = entitySpecMap.get(Long
								.parseLong(formFieldSpec.getFieldTypeExtra()));
						if (entitySpecTemp != null) {
							EntitySpec entitySpecCompany = companyEntitySpecMap
									.get(entitySpecTemp.getEntitySpecId());
							if (entitySpecCompany == null) {
								entitySpecs.add(entitySpecTemp);
								companyEntitySpecMap.put(
										entitySpecTemp.getEntitySpecId(),
										entitySpecTemp);
							} else {
								List<EntityFieldSpec> entityFieldSpecs = companyEntityFieldSpecMap
										.get(entitySpecCompany.getEntitySpecId());
								if (entityFieldSpecs != null && entityFieldSpecs.size() > 0) {
									for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
										if (entityFieldSpec.getSkeletonEntityFieldSpecId() != null) {
											entityFieldSpecsIdMap.put(entityFieldSpec.getSkeletonEntityFieldSpecId(),
													entityFieldSpec.getEntityFieldSpecId());
										}

									}
								}
								formFieldSpec.setFieldTypeExtra(entitySpecCompany.getEntitySpecId() + "");
							}
						}
					}
				}
				else if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_FORM
			            && !Api.isEmptyString(formFieldSpec.getFieldTypeExtraForm())) {
			        Set<String> innerFormSpecUniqueIds = new HashSet<>();
			        innerFormSpecUniqueIds.add(formFieldSpec.getFieldTypeExtraForm());
			        getExportFormSpecData(innerFormSpecUniqueIds, formSpecContainer);
			    }
				else if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY && !Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {
					if(!customEntitySpecIdsMap.containsKey(formFieldSpec.getFieldTypeExtraCustomEntity()))
					{
						CustomEntitySpec customEntitySpecTemp = customEntitySpecsMap.get(Long
								.parseLong(formFieldSpec.getFieldTypeExtraCustomEntity()));
						if (customEntitySpecTemp != null) {
							CustomEntitySpec customEntitySpecCompany = companyCustomEntitySpecsMap
									.get(customEntitySpecTemp.getCustomEntitySpecId());
							/*if (customEntitySpecCompany == null) {
								customEntitySpecs.add(customEntitySpecTemp);
								companyCustomEntitySpecsMap.put(
										customEntitySpecTemp.getCustomEntitySpecId(),
										customEntitySpecTemp);
							} else {
								formFieldSpec.setFieldTypeExtraCustomEntity(
										customEntitySpecCompany.getCustomEntitySpecId() + "");
							}*/
							
							customEntitySpecs.add(customEntitySpecTemp);
							companyCustomEntitySpecsMap.put(
									customEntitySpecTemp.getCustomEntitySpecId(),
									customEntitySpecTemp);
						}
					}
					
				} else if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_FORM
						&& formFieldSpec.getFieldTypeExtraForm() != null && (!isWorkSpec || cloneInnerFormSpecs)) {
					innerformSpecUniqueIdsMap.put(formFieldSpec.getFieldTypeExtraForm(),
							formFieldSpec.getFieldTypeExtraForm());
				}
			}
		}
		if (formSectionFieldSpecs != null) {
			for (FormSectionFieldSpec formSectionFieldSpec : formSectionFieldSpecs) {
				if ((formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST)
						&& !Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtra())) {
					if (!entitySpecsIdMap
							.containsKey(Long.parseLong(formSectionFieldSpec
									.getFieldTypeExtra()))) {
						EntitySpec entitySpecTemp = entitySpecMap.get(Long
								.parseLong(formSectionFieldSpec
										.getFieldTypeExtra()));
						if (entitySpecTemp != null) {
							EntitySpec entitySpecCompany = companyEntitySpecMap
									.get(entitySpecTemp.getEntitySpecId());
							if (entitySpecCompany == null) {
								entitySpecs.add(entitySpecTemp);
								companyEntitySpecMap.put(
										entitySpecTemp.getEntitySpecId(),
										entitySpecTemp);
							}else {
								List<EntityFieldSpec> entityFieldSpecs = companyEntityFieldSpecMap
										.get(entitySpecCompany.getEntitySpecId());
								if (entityFieldSpecs != null && entityFieldSpecs.size() > 0) {
									for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
										if (entityFieldSpec.getSkeletonEntityFieldSpecId() != null) {
											entityFieldSpecsIdMap.put(entityFieldSpec.getSkeletonEntityFieldSpecId(),
													entityFieldSpec.getEntityFieldSpecId());
										}

									}
								}
								formSectionFieldSpec.setFieldTypeExtra(entitySpecCompany.getEntitySpecId() + "");
							}
						}
					}
				} else if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY && !Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtraCustomEntity())) {
					if (!customEntitySpecIdsMap.containsKey(formSectionFieldSpec.getFieldTypeExtraCustomEntity())) {
						CustomEntitySpec customEntitySpecTemp = customEntitySpecsMap
								.get(Long.parseLong(formSectionFieldSpec.getFieldTypeExtraCustomEntity()));
						if (customEntitySpecTemp != null) {
							CustomEntitySpec customEntitySpecCompany = companyCustomEntitySpecsMap
									.get(customEntitySpecTemp.getCustomEntitySpecId());
							/*if (customEntitySpecCompany == null) {
								customEntitySpecs.add(customEntitySpecTemp);
								companyCustomEntitySpecsMap.put(customEntitySpecTemp.getCustomEntitySpecId(),
										customEntitySpecTemp);
							} else {
								formSectionFieldSpec.setFieldTypeExtraCustomEntity(
										customEntitySpecCompany.getCustomEntitySpecId() + "");
							}*/
							customEntitySpecs.add(customEntitySpecTemp);
							companyCustomEntitySpecsMap.put(customEntitySpecTemp.getCustomEntitySpecId(),
									customEntitySpecTemp);
						}
					}
				}else if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_FORM
						&& formSectionFieldSpec.getFieldTypeExtraForm() != null && (!isWorkSpec || cloneInnerFormSpecs)) {
					innerformSpecUniqueIdsMap.put(formSectionFieldSpec.getFieldTypeExtraForm(),
							formSectionFieldSpec.getFieldTypeExtraForm());
				}
		
			}
		}
		for (FormSectionSpec formSectionSpec : formSectionSpecs) {
			if(!Api.isEmptyString(formSectionSpec.getAutoCreateFieldTypeExtra())) {

				if (!entitySpecsIdMap
						.containsKey(Long.parseLong(formSectionSpec.getAutoCreateFieldTypeExtra()))) {
					EntitySpec entitySpecTemp = entitySpecMap.get(Long
							.parseLong(formSectionSpec.getAutoCreateFieldTypeExtra()));
					if (entitySpecTemp != null) {
						EntitySpec entitySpecCompany = companyEntitySpecMap
								.get(entitySpecTemp.getEntitySpecId());
						if (entitySpecCompany == null) {
							entitySpecs.add(entitySpecTemp);
							companyEntitySpecMap.put(
									entitySpecTemp.getEntitySpecId(),
									entitySpecTemp);
						}else {
							List<EntityFieldSpec> entityFieldSpecs = companyEntityFieldSpecMap
									.get(entitySpecCompany.getEntitySpecId());
							if (entityFieldSpecs != null && entityFieldSpecs.size() > 0) {
								for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
									if (entityFieldSpec.getSkeletonEntityFieldSpecId() != null) {
										entityFieldSpecsIdMap.put(entityFieldSpec.getSkeletonEntityFieldSpecId(),
												entityFieldSpec.getEntityFieldSpecId());
									}

								}
							}
							formSectionSpec.setAutoCreateFieldTypeExtra(entitySpecCompany.getEntitySpecId() + "");
						}
					}
				}
			
				
			}
		}
		List<EntityFieldSpec> entityFieldSpecs = getEntityFieldSpecsIn(entitySpecs);
		// Map<Long, Long> entityFieldSpecsIdMap = new HashMap<Long, Long>();

		for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
			if (entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST) {
				if (!entitySpecsIdMap.containsKey(Long
						.parseLong(entityFieldSpec.getFieldTypeExtra()))) {
					resolveAndInsertListInListForFields(
							entityFieldSpec.getFieldTypeExtra(),
							entitySpecsIdMap, entityFieldSpecsIdMap, webUser,true);
				}
				entityFieldSpec.setFieldTypeExtra(""
						+ entitySpecsIdMap.get(Long.parseLong(entityFieldSpec
								.getFieldTypeExtra())));
			}
		}

		Iterator<EntityFieldSpec> entitySpecIterator = entityFieldSpecs
				.iterator();
		while (entitySpecIterator.hasNext()) {
			EntityFieldSpec entityFieldSpec = (EntityFieldSpec) entitySpecIterator
					.next();
			if (entitySpecsIdMap.containsKey(entityFieldSpec.getEntitySpecId())) {
				entitySpecIterator.remove();
			}

		}
		
		List<FormSpec> formSpecs = getLatestFormSpecsForUnquids(
				Api.toCSV(customEntitySpecs, "formSpecUniqueId", CsvOptions.FILTER_NULL_OR_EMPTY));
		Map<String, FormSpec> uniqueIdAndFormSpecMap = (Map) Api.getMapFromList(formSpecs, "uniqueId");
		

		for (CustomEntitySpec customEntitySpec : customEntitySpecs) {
			customEntitySpec.setCompanyId(webUser.getCompanyId());
			customEntitySpec.setCreatedBy(webUser.getEmpId());
			customEntitySpec.setModifiedBy(webUser.getEmpId());
			Long oldCustomEntitySpecId = customEntitySpec.getCustomEntitySpecId();
			customEntitySpec.setSkeletonCustomEntitySpecId(customEntitySpec.getCustomEntitySpecId());
			customEntitySpecIdsMap.put(oldCustomEntitySpecId+"", customEntitySpec.getCustomEntitySpecId()+"");
			if (uniqueIdAndFormSpecMap.get(customEntitySpec.getFormSpecUniqueId()) != null) {
		 
				// have to work on them
				/*
				 * String uniqueId = addCustomEntityFormSpec(webUser,
				 * uniqueIdAndFormSpecMap.get(customEntitySpec.getFormSpecUniqueId()),
				 * false,customEntitySpecIdsMap,formSpecIdsMap,formSpecUniqueIdsMap,
				 * formFieldSpecUniqueIdsMap,formSectionFieldSpecUniqueIdsMap,
				 * formFieldSpecsIdMap,formSectionFieldSpecsIdMap,entityFieldSpecsIdMap,
				 * entitySpecsIdMap,
				 * entitySpecMap,formSectionSpecsIdMap,formSectionSpecUniqueIdsMap,
				 * innerformSpecUniqueIdsMap); customEntitySpec.setFormSpecUniqueId(uniqueId);
				 * extraSupportDao.insertCustomEntitySpecs(customEntitySpec);
				 */
				customEntitySpecIdsMap.put(oldCustomEntitySpecId+"", customEntitySpec.getCustomEntitySpecId()+"");
			}
		}
		
		List<EntityFieldSpecValidValue> entityFieldSpecValidValues = getEntityFieldSpecValidValuesIn(entityFieldSpecs);


		// Map<Long, Long> entitySpecsIdMap = new HashMap<Long, Long>();
		for (EntitySpec entitySpec : entitySpecs) {
			if (!entitySpecsIdMap.containsKey(entitySpec.getEntitySpecId())) {
				long oldEntitySpecId = entitySpec.getEntitySpecId();

				entitySpec.setParentId(oldEntitySpecId);
				entitySpec.setSkeletonEntitySpecId(oldEntitySpecId);
				entitySpec.setIsSystemDefined(0);
				entitySpec.setSyncScope(-1);
				effortDao.insertEntitySpec(entitySpec, webUser.getCompanyId(),
						webUser.getEmpId());
				entitySpecsIdMap.put(oldEntitySpecId,
						entitySpec.getEntitySpecId());
			}
		}
		// for (EntitySpec entitySpec : companyEntitySpecs) {
		// if (entitySpec.getParentId() != null) {
		// entitySpecsIdMap.put(entitySpec.getParentId(),
		// entitySpec.getEntitySpecId());
		// }
		// }

		if (entityFieldSpecs != null) {
			for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
				long oldEntityFieldSpecId = entityFieldSpec
						.getEntityFieldSpecId();
				long entitySpecId = entitySpecsIdMap.get(entityFieldSpec
						.getEntitySpecId());
				if (isSkeleton)
					entityFieldSpec
							.setSkeletonEntityFieldSpecId(oldEntityFieldSpecId);
				else
					entityFieldSpec
							.setSkeletonEntityFieldSpecId(entityFieldSpec
									.getSkeletonEntityFieldSpecId());

				effortDao.insertEntityFieldSpec(entityFieldSpec, entitySpecId);
				entityFieldSpecsIdMap.put(oldEntityFieldSpecId,
						entityFieldSpec.getEntityFieldSpecId());
			}

			for (EntityFieldSpecValidValue entityFieldSpecValidValue : entityFieldSpecValidValues) {
				long entityFieldSpecId = entityFieldSpecsIdMap
						.get(entityFieldSpecValidValue.getEntityFieldSpecId());
				effortDao.insertEntityFieldSpecValidValue(entityFieldSpecId,
						entityFieldSpecValidValue.getValue());
			}

		}

		Map<Long, Long> entityIdMap = new HashMap<Long, Long>();
		
		Long oldFormSpecId = formSpec.getFormSpecId();
		String oldFormSpecUniqueId = formSpec.getUniqueId();
		formSpec.setParentId(formSpec.getFormSpecId());
		
		formSpec.setUniqueId(null);
		

		if (formSpec.getSkeletonFormSpecId() == null) {
			formSpec.setSkeletonFormSpecId(formSpec.getFormSpecId());
		} else {
			formSpec.setSkeletonFormSpecId(formSpec.getSkeletonFormSpecId());
		}

		if(formSpec.getPurpose()==100 || formSpec.getPurpose() == FormSpec.PURPOUSE_SIGN_IN_SIGN_OUT_UPDATE_FORM) {
		     formSpec.setIsSystemDefined(0);
		     formSpec.setPurpose(-1);
		}else if(formSpec.getPurpose() == 12) {
			formSpec.setIsSystemDefined(1);
		}
		else if(formSpec.getPurpose() == 7) {
			formSpec.setIsSystemDefined(1);
		}else{
			if (isWorkSpec) {
				formSpec.setIsSystemDefined(0);
			} else {
				formSpec.setIsSystemDefined(1);
			}
			
		}
		formSpec.setIsPublic(true);
		formSpec.setAllAccess(true);
		//formSpec.setEnableCustomerActivity(true);
		effortDao.insertFormSpec(formSpec, webUser.getCompanyId(),
				webUser.getEmpId());
		
		if(formSpec.isEnableCustomerActivity()) {
			Activity activity = new Activity();
			activity.setFormSpecId(formSpec.getFormSpecId());
			activity.setActivityName(formSpec.getFormTitle());
			activity.setActivityVisibility("false");
			createActivity(webUser, activity);
		}
		
		
		FormSpec formSpecForUniqueId = getFormSpec(formSpec.getFormSpecId()
				+ "");
		formSpecIdsMap.put(oldFormSpecId, formSpec.getFormSpecId());
		formSpecUniqueIdsMap.put(oldFormSpecUniqueId,
				formSpecForUniqueId.getUniqueId());
		effortDao.updateFormSpecForInitialFormSpecId(formSpec);
		for (FormPageSpec formPageSpec : formPageSpecs) {
			effortDao.insertFormPageSpec(formPageSpec, formSpec.getFormSpecId());
		}
		
		for(FormFieldGroupSpec formFieldGroupSpec : formFieldGroupSpecs) {
			effortDao.insertFormFieldGroupSpec(formFieldGroupSpec,formSpec.getFormSpecId() ,webUser.getCompanyId());
		}
		List<CustomEntitySpec> customEntitySpecsLists = getCustomEntitySpecs(webUser.getCompanyId());
		Map<Long, CustomEntitySpec> customEntitySpecMap = (Map) Api.getMapFromList(customEntitySpecsLists,
				"skeletonCustomEntitySpecId");
		
		if (formFieldSpecs != null) {
			for (FormFieldSpec formFieldSpec : formFieldSpecs) {
				long oldFieldSpecId = formFieldSpec.getFieldSpecId();
				boolean mandatory = formFieldSpec.isMandatory();
				boolean visible = formFieldSpec.getIsVisible();
				String oldFieldSpecUniqueId = formFieldSpec.getUniqueId();
				if ((formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST)
						&& !Api.isEmptyString(formFieldSpec.getFieldTypeExtra())) {
					if (entitySpecsIdMap.size() > 0) {
						Long entitySpecId = entitySpecsIdMap.get(Long
								.parseLong(formFieldSpec.getFieldTypeExtra()));
						formFieldSpec.setFieldTypeExtra(entitySpecId + "");
					}
				}
				if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY && !Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {
					if (formFieldSpec.getFieldTypeExtraCustomEntity() != null) {
						CustomEntitySpec customEntitySpec = companyCustomEntitySpecsMap
								.get(Long.parseLong(formFieldSpec.getFieldTypeExtraCustomEntity()));
						if (customEntitySpec != null) {
							formFieldSpec.setFieldTypeExtraCustomEntity(customEntitySpec.getCustomEntitySpecId() + "");
						}
					}

				}
				if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_DATE_TIME) {
					if(!Api.isEmptyString(formFieldSpec.getRemainderRemarksFields())) {
						List<String> remainderFields = Api.csvToList(formFieldSpec.getRemainderRemarksFields());
						formFieldSpec.setRemainderRemarksFieldsCsv(formFieldSpec.getRemainderRemarksFields());
						if(remainderFields != null && remainderFields.size() > 0) {
							formFieldSpec.setRemainderRemarksFields(remainderFields.get(0));
						}
					}
				}
				formFieldSpec.setUniqueId(null);
				// if (isSkeleton)
				// formFieldSpec.setSkeletonFormFieldSpecId(oldFieldSpecId);
				// else
				// formFieldSpec.setSkeletonFormFieldSpecId(formFieldSpec.getSkeletonFormFieldSpecId());

				/*if (formFieldSpec.getSkeletonFormFieldSpecId() == null) {
					formFieldSpec.setSkeletonFormFieldSpecId(oldFieldSpecId);
				} else {
					formFieldSpec.setSkeletonFormFieldSpecId(formFieldSpec
							.getSkeletonFormFieldSpecId());
				}*/
				if (formFieldSpec.getSkeletonFormFieldSpecId() != null) {
					formFieldSpec.setSkeletonFormFieldSpecId(formFieldSpec
							.getSkeletonFormFieldSpecId());
				} 

				formFieldSpec.setMandatory(mandatory);
				formFieldSpec.setIsVisible(visible);
				effortDao.insertFormFieldSpec(formFieldSpec,
						formSpec.getFormSpecId());
				effortDao.updateInitialFormFieldSpecId(formFieldSpec);
				FormFieldSpec formFieldSpec2 = getFormFieldSpec(formFieldSpec
						.getFieldSpecId() + "");
				
				printTemplate = printTemplate.replace("id=\"F" + oldFieldSpecId
						+ "\"", "id=\"F" + formFieldSpec.getFieldSpecId()
						+ "\"");
				emailTemplate = emailTemplate.replaceAll("F" + oldFieldSpecId,
						"F" + formFieldSpec.getFieldSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<F"
						+ oldFieldSpecId + ">",
						"<F" + formFieldSpec.getFieldSpecId() + ">");

				formFieldSpecsIdMap.put(oldFieldSpecId,
						formFieldSpec.getFieldSpecId());
				formFieldSpecUniqueIdsMap.put(oldFieldSpecUniqueId,
						formFieldSpec2.getUniqueId());
			}
		}

		/*if (formSectionSpecs != null) {
			for (FormSectionSpec formSectionSpec : formSectionSpecs) {
				long oldSectionSpecId = formSectionSpec.getSectionSpecId();
				String oldSectionSpecUniqueId = formSectionSpec.getSectionSpecUniqueId();

				if (isSkeleton)
					formSectionSpec
							.setSkeletonFormSectionSpecId(oldSectionSpecId);
				else
					formSectionSpec
							.setSkeletonFormSectionSpecId(formSectionSpec
									.getSkeletonFormSectionSpecId());

				formSectionSpec.setSectionSpecUniqueId(null);
				effortDao.insertFormSectionSpec(formSectionSpec,
						formSpec.getFormSpecId(), webUser.getCompanyId());
				effortDao.updateInitialFormSectionSpecId(formSectionSpec);
				
				printTemplate = printTemplate.replace("id=\"S"
						+ oldSectionSpecId + "\"",
						"id=\"S" + formSectionSpec.getSectionSpecId() + "\"");
				emailTemplate = emailTemplate.replaceAll(
						"S" + oldSectionSpecId,
						"S" + formSectionSpec.getSectionSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<S"
						+ oldSectionSpecId + ">",
						"<S" + formSectionSpec.getSectionSpecId() + ">");

				formSectionSpecsIdMap.put(oldSectionSpecId,
						formSectionSpec.getSectionSpecId());
				
				if(formSectionSpecUniqueIdsMap != null)
				{
					formSectionSpecUniqueIdsMap.put(oldSectionSpecUniqueId,
							formSectionSpec.getSectionSpecUniqueId());
				}
			}
		}*/
		if (formSectionSpecs != null) {
		    for (FormSectionSpec formSectionSpec : formSectionSpecs) {
		        long oldSectionSpecId = formSectionSpec.getSectionSpecId();
		        String oldSectionSpecUniqueId = formSectionSpec.getSectionSpecUniqueId();

		        // --- Skeleton linkage ---
		        if (isSkeleton)
		            formSectionSpec.setSkeletonFormSectionSpecId(oldSectionSpecId);
		        else
		            formSectionSpec.setSkeletonFormSectionSpecId(formSectionSpec.getSkeletonFormSectionSpecId());

		        // --- Resolve autoCreateFieldTypeExtra ---
		     // --- Resolve autoCreateFieldTypeExtra ---
		        if (!Api.isEmptyString(formSectionSpec.getAutoCreateFieldTypeExtra())) {
		            long oldEntitySpecId = Long.parseLong(formSectionSpec.getAutoCreateFieldTypeExtra());

		            // ✅ Try to resolve to new EntitySpec ID if cloned
		            Long newEntitySpecId = entitySpecsIdMap.get(oldEntitySpecId);

		            if (newEntitySpecId != null) {
		                // Found cloned entity, map to new child
		                formSectionSpec.setAutoCreateFieldTypeExtra(String.valueOf(newEntitySpecId));
		            } else {
		                // No mapping — preserve the existing reference (like fieldTypeExtra does)
		                formSectionSpec.setAutoCreateFieldTypeExtra(String.valueOf(oldEntitySpecId));
		            }
		        }

		        // --- Insert cloned FormSectionSpec ---
		        formSectionSpec.setSectionSpecUniqueId(null);
		        effortDao.insertFormSectionSpec(formSectionSpec, formSpec.getFormSpecId(), webUser.getCompanyId());
		        effortDao.updateInitialFormSectionSpecId(formSectionSpec);

		        // --- Update templates ---
		        printTemplate = printTemplate.replace(
		            "id=\"S" + oldSectionSpecId + "\"",
		            "id=\"S" + formSectionSpec.getSectionSpecId() + "\""
		        );

		        emailTemplate = emailTemplate.replaceAll(
		            "S" + oldSectionSpecId,
		            "S" + formSectionSpec.getSectionSpecId()
		        );

		        mobilePrintTemplate = mobilePrintTemplate.replace(
		            "<S" + oldSectionSpecId + ">",
		            "<S" + formSectionSpec.getSectionSpecId() + ">"
		        );

		        // --- Track mappings ---
		        formSectionSpecsIdMap.put(oldSectionSpecId, formSectionSpec.getSectionSpecId());

		        if (formSectionSpecUniqueIdsMap != null) {
		            formSectionSpecUniqueIdsMap.put(oldSectionSpecUniqueId, formSectionSpec.getSectionSpecUniqueId());
		        }
		    }
		}

		if (formSectionFieldSpecs != null) {
			for (FormSectionFieldSpec formSectionFieldSpec : formSectionFieldSpecs) {
				long oldSectionSpecId = formSectionFieldSpec.getSectionSpecId();
				long oldSectionFieldSpecId = formSectionFieldSpec
						.getSectionFieldSpecId();
				String oldSectionFieldSpecUniqueId = formSectionFieldSpec
						.getUniqueId();

				long sectionSpecId = formSectionSpecsIdMap
						.get(formSectionFieldSpec.getSectionSpecId());
				if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST) {
					if(!Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtra()))
					{
						Long entitySpecId = entitySpecsIdMap
								.get(Long.parseLong(formSectionFieldSpec
										.getFieldTypeExtra()));
						formSectionFieldSpec.setFieldTypeExtra(entitySpecId + "");
					}
					
				}
				if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY||
				formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOM_ENTITY){
					if (!Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtraCustomEntity())) {
						CustomEntitySpec customEntitySpec = companyCustomEntitySpecsMap
								.get(Long.parseLong(formSectionFieldSpec.getFieldTypeExtraCustomEntity()));
						if (customEntitySpec != null) {
							formSectionFieldSpec.setFieldTypeExtraCustomEntity(customEntitySpec.getCustomEntitySpecId() + "");
						}
					}

				}
				formSectionFieldSpec.setUniqueId(null);
				/*
				 * if (isSkeleton) formSectionFieldSpec
				 * .setSkeletonFormSectionFieldSpecId
				 * (oldSectionFieldSpecUniqueId); else formSectionFieldSpec
				 * .setSkeletonFormSectionFieldSpecId(formSectionFieldSpec
				 * .getSkeletonFormSectionFieldSpecId());
				 */

				if (formSectionFieldSpec.getSkeletonFormSectionFieldSpecId() == null) {
					formSectionFieldSpec
							.setSkeletonFormSectionFieldSpecId(oldSectionFieldSpecId);
				} else {
					formSectionFieldSpec
							.setSkeletonFormSectionFieldSpecId(formSectionFieldSpec
									.getSkeletonFormSectionFieldSpecId());
				}

				effortDao.insertFormSectionFieldSpec(formSectionFieldSpec,
						formSpec.getFormSpecId(), sectionSpecId);
				effortDao.updateFormSectionFieldSpecId(formSectionFieldSpec);
				FormSectionFieldSpec formSectionFieldSpec2 = getFormSectionFieldSpec(formSectionFieldSpec
						.getSectionFieldSpecId() + "");
				
				printTemplate = printTemplate.replace("id=\"T"
						+ oldSectionFieldSpecId + "\"", "id=\"T"
						+ formSectionFieldSpec.getSectionFieldSpecId() + "\"");
				emailTemplate = emailTemplate.replaceAll("T"
						+ oldSectionFieldSpecId,
						"T" + formSectionFieldSpec.getSectionFieldSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<S"
						+ oldSectionSpecId + "",
						"<S" + formSectionFieldSpec.getSectionSpecId() + "");
				mobilePrintTemplate = mobilePrintTemplate.replace("F"
						+ oldSectionFieldSpecId + ">", "F"
						+ formSectionFieldSpec.getSectionFieldSpecId() + ">");

				formSectionFieldSpecsIdMap.put(oldSectionFieldSpecId,
						formSectionFieldSpec.getSectionFieldSpecId());
				formSectionFieldSpecUniqueIdsMap.put(
						oldSectionFieldSpecUniqueId,
						formSectionFieldSpec2.getUniqueId());
			}
		}

		for (FormFieldSpecValidValue fieldSpecValidValue : formFieldSpecValidValues) {
			long fieldSpecId = formFieldSpecsIdMap.get(fieldSpecValidValue
					.getFieldSpecId());
			FormFieldSpec formFieldSpec = effortDao.getFormFieldSpec(fieldSpecId+"");
			effortDao.insertFormFieldSpecValidValue(fieldSpecId,
					fieldSpecValidValue.getValue());
		}

		for (FormSectionFieldSpecValidValue sectionFieldSpecValidValue : formSectionFieldSpecValidValues) {
			long sectionFieldSpecId = formSectionFieldSpecsIdMap
					.get(sectionFieldSpecValidValue.getSectionFieldSpecId());

			FormSectionFieldSpec formSectionFieldSpec = effortDao.getFormSectionFieldSpec(sectionFieldSpecValidValue.getSectionFieldSpecId()+"");
			
			effortDao.insertFormSectionFieldSpecValidValue(sectionFieldSpecId,
					sectionFieldSpecValidValue.getValue());
		}


		effortDao.updateFormSpecPrintTemplate(formSpec.getFormSpecId(),
				Api.makeNullIfEmpty(printTemplate),
				printTemplatePdfSaveNameFieldUniqueId);
		effortDao.updateFormSpecEmailTemplate(formSpec.getFormSpecId(),
				Api.makeNullIfEmpty(emailTemplate),
				printTemplatePdfSaveNameFieldUniqueId);
		effortDao.updateFormSpecMobilePrintTemplate(formSpec.getFormSpecId(),
				Api.makeNullIfEmpty(mobilePrintTemplate));
		
		
		
		List<FieldValidation> fieldValidations = formSpecContainer.getFieldValidations();
		List<VisibilityDependencyCriteria> visibilityDependencyCriterias = formSpecContainer.getVisibilityDependencyCriterias();
		List<ListFilteringCritiria> listFilteringCritirias = formSpecContainer.getListFilteringCriterias();
		List<CustomerFilteringCritiria> customerFilteringCritirias = formSpecContainer.getCustomerFilteringCriterias();
		List<FieldValidationCritiria> fieldValidationCritirias = formSpecContainer.getFieldValidationCritirias();
		List<FieldRestrictionCritiria> fieldRestrictionCritirias = formSpecContainer.getFieldRestrictionCritirias();
		List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations = formSpecContainer.getAutoGenereteSequenceSpecConfiguarations();
		List<FormFieldsColorDependencyCriterias> formFieldColorDependencyCriterias = formSpecContainer.getFormFieldsColorDependencyCriterias();
		List<FieldSpecFilter> fieldSpecFilters = formSpecContainer.getFormFieldSpecFilters();
		List<FieldSpecFilter> sectionFieldSpecFilters = formSpecContainer.getFormSectionFieldSpecFilters();
		
		List<AutoGenereteSequenceSpecConfiguarationField> autoGenereteSequenceSpecConfiguarationFields = new ArrayList<AutoGenereteSequenceSpecConfiguarationField>();
		List<RemainderFieldsMap> remainderFieldsMaps = formSpecContainer.getRemainderFieldsMap();
		
		resolveRemainderFieldsMaps(remainderFieldsMaps,formFieldSpecsIdMap,formSpec);
		
	    effortDao.insertRemainderFieldsMap(remainderFieldsMaps);
		
	    resolveFieldValidations(fieldValidations,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertFieldValidations(fieldValidations); //  have to set values in bean
		
		resolveListFilterCritrias(listFilteringCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec,entityFieldSpecsIdMap);
		effortDao.insertListFilterCritria(listFilteringCritirias);
		
		resolveVisibilityDependencyCritirias(visibilityDependencyCriterias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertVisibilityDependencyCritirias(visibilityDependencyCriterias);
		
		resolveCustomerFilterCritria(customerFilteringCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertCustomerFilterCritria(customerFilteringCritirias);
		
		resolveFieldValidationCritria(fieldValidationCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertFieldValidationCritria(fieldValidationCritirias);
		
		resolveFieldRestrictionCritiria(fieldRestrictionCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFieldRestrictionCritria(fieldRestrictionCritirias);
		
		
		resolveFormFieldsColorDependencyCriterias(formFieldColorDependencyCriterias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertFormFieldsColorDependencyCriterias(formFieldColorDependencyCriterias);
		if(autoGenereteSequenceSpecConfiguarations!=null && !autoGenereteSequenceSpecConfiguarations.isEmpty())
		{
			resolveAutoGeneratedSequenceSpecConfigurations(autoGenereteSequenceSpecConfiguarations,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
			effortDao.insertAutoGeneratedSequenceSpecConfigurations(autoGenereteSequenceSpecConfiguarations);
		}
		if(autoGenereteSequenceSpecConfiguarationFields!=null && !autoGenereteSequenceSpecConfiguarationFields.isEmpty())
		{
			resolveAutoGenereteSequenceSpecConfiguarationFields(autoGenereteSequenceSpecConfiguarationFields,formFieldSpecUniqueIdsMap,formSpec);
			effortDao.insertAutoGenereteSequenceSpecConfiguarationFields(autoGenereteSequenceSpecConfiguarationFields);
		}
		
		resolveFormFieldSpecFilters(fieldSpecFilters,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertFormFieldSpecFilters(fieldSpecFilters);
		resolveFormSectionFieldSpecFilters(fieldSpecFilters,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpec);
		effortDao.insertFormSectionFieldSpecFilters(sectionFieldSpecFilters);
		
	}
	
	private void resolveFormSectionFieldSpecFilters(List<FieldSpecFilter> fieldSpecFilters,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<FieldSpecFilter> fieldValidationsIterator = fieldSpecFilters.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldSpecFilter remainderFieldsMap = (FieldSpecFilter) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());


					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormSectionFieldSpecId()) != null) {
						remainderFieldsMap
								.setFormSectionFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormSectionFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				
			}
		} catch (Exception e) {
			fieldSpecFilters = new ArrayList<>();
		}
		
	}
	private void resolveFormSectionFieldSpecFilters(List<FieldSpecFilter> fieldSpecFilters,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<FieldSpecFilter> fieldValidationsIterator = fieldSpecFilters.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldSpecFilter remainderFieldsMap = (FieldSpecFilter) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}


					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormSectionFieldSpecId()) != null) {
						remainderFieldsMap
								.setFormSectionFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormSectionFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				
			}
		} catch (Exception e) {
			fieldSpecFilters = new ArrayList<>();
		}
		
	}

	private void resolveFormFieldSpecFilters(List<FieldSpecFilter> fieldSpecFilters,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<FieldSpecFilter> fieldValidationsIterator = fieldSpecFilters.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldSpecFilter remainderFieldsMap = (FieldSpecFilter) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());

					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap.setFormFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}

			}
		} catch (Exception e) {
			fieldSpecFilters = new ArrayList<>();
		}
	
	}
	
	private void resolveFormFieldSpecFilters(List<FieldSpecFilter> fieldSpecFilters,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<FieldSpecFilter> fieldValidationsIterator = fieldSpecFilters.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldSpecFilter remainderFieldsMap = (FieldSpecFilter) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}

					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap.setFormFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}

			}
		} catch (Exception e) {
			fieldSpecFilters = new ArrayList<>();
		}
	
	}

	private void resolveAutoGenereteSequenceSpecConfiguarationFields(
			List<AutoGenereteSequenceSpecConfiguarationField> autoGenereteSequenceSpecConfiguarationFields,
			Map<String, String> formFieldSpecUniqueIdsMap, FormSpec formSpec) {

		try {
			Iterator<AutoGenereteSequenceSpecConfiguarationField> fieldValidationsIterator = autoGenereteSequenceSpecConfiguarationFields.iterator();
			while (fieldValidationsIterator.hasNext()) {
				AutoGenereteSequenceSpecConfiguarationField remainderFieldsMap = (AutoGenereteSequenceSpecConfiguarationField) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecUniqueId(formSpec.getUniqueId());
				
					if (formFieldSpecUniqueIdsMap.get(remainderFieldsMap.getFieldSpecUniqueId()) != null) {
						remainderFieldsMap.setFieldSpecUniqueId(formFieldSpecUniqueIdsMap.get(remainderFieldsMap.getFieldSpecUniqueId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}

			}
		} catch (Exception e) {
			autoGenereteSequenceSpecConfiguarationFields = new ArrayList<>();
		}
	
	}
	
	private void resolveAutoGenereteSequenceSpecConfiguarationFields(
			List<AutoGenereteSequenceSpecConfiguarationField> autoGenereteSequenceSpecConfiguarationFields,
			Map<String, String> formFieldSpecUniqueIdsMap, Map<String, String> formSpecUniqueIdsMap) {

		try {
			Iterator<AutoGenereteSequenceSpecConfiguarationField> fieldValidationsIterator = autoGenereteSequenceSpecConfiguarationFields.iterator();
			while (fieldValidationsIterator.hasNext()) {
				AutoGenereteSequenceSpecConfiguarationField remainderFieldsMap = (AutoGenereteSequenceSpecConfiguarationField) fieldValidationsIterator.next();
				String newUniqueId = formSpecUniqueIdsMap.get(remainderFieldsMap.getFormSpecUniqueId());
				if(!Api.isEmptyString(newUniqueId)) {
					remainderFieldsMap.setFormSpecUniqueId(newUniqueId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				
					if (formFieldSpecUniqueIdsMap.get(remainderFieldsMap.getFieldSpecUniqueId()) != null) {
						remainderFieldsMap.setFieldSpecUniqueId(formFieldSpecUniqueIdsMap.get(remainderFieldsMap.getFieldSpecUniqueId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}

			}
		} catch (Exception e) {
			autoGenereteSequenceSpecConfiguarationFields = new ArrayList<>();
		}
	
	}

	private void resolveAutoGeneratedSequenceSpecConfigurations(
			List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<AutoGenereteSequenceSpecConfiguaration> fieldValidationsIterator = autoGenereteSequenceSpecConfiguarations.iterator();
			while (fieldValidationsIterator.hasNext()) {
				AutoGenereteSequenceSpecConfiguaration remainderFieldsMap = (AutoGenereteSequenceSpecConfiguaration) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
				

				if (remainderFieldsMap.getFieldType()==1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap.setFormFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType()==2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap
								.setFormFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			autoGenereteSequenceSpecConfiguarations = new ArrayList<>();
		}
	
	}
	
	private void resolveAutoGeneratedSequenceSpecConfigurations(
			List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<AutoGenereteSequenceSpecConfiguaration> fieldValidationsIterator = autoGenereteSequenceSpecConfiguarations.iterator();
			while (fieldValidationsIterator.hasNext()) {
				AutoGenereteSequenceSpecConfiguaration remainderFieldsMap = (AutoGenereteSequenceSpecConfiguaration) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				

				if (remainderFieldsMap.getFieldType()==1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap.setFormFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType()==2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()) != null) {
						remainderFieldsMap
								.setFormFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFormFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			autoGenereteSequenceSpecConfiguarations = new ArrayList<>();
		}
	
	}

	private void resolveFormFieldsColorDependencyCriterias(
			List<FormFieldsColorDependencyCriterias> formFieldColorDependencyCriterias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<FormFieldsColorDependencyCriterias> fieldValidationsIterator = formFieldColorDependencyCriterias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FormFieldsColorDependencyCriterias remainderFieldsMap = (FormFieldsColorDependencyCriterias) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
				

				if (remainderFieldsMap.getFieldType()==1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType()==2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			formFieldColorDependencyCriterias = new ArrayList<>();
		}
	
	}
	
	private void resolveFormFieldsColorDependencyCriterias(
			List<FormFieldsColorDependencyCriterias> formFieldColorDependencyCriterias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<FormFieldsColorDependencyCriterias> fieldValidationsIterator = formFieldColorDependencyCriterias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FormFieldsColorDependencyCriterias remainderFieldsMap = (FormFieldsColorDependencyCriterias) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				

				if (remainderFieldsMap.getFieldType()==1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType()==2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			formFieldColorDependencyCriterias = new ArrayList<>();
		}
	
	}

	private void resolveFieldValidationCritria(List<FieldValidationCritiria> fieldValidationCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<FieldValidationCritiria> fieldValidationsIterator = fieldValidationCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldValidationCritiria remainderFieldsMap = (FieldValidationCritiria) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
				

				if (!remainderFieldsMap.isSectionField()) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.isSectionField()) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			fieldValidationCritirias = new ArrayList<>();
		}
	
	}
	
	private void resolveFieldValidationCritria(List<FieldValidationCritiria> fieldValidationCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<FieldValidationCritiria> fieldValidationsIterator = fieldValidationCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldValidationCritiria remainderFieldsMap = (FieldValidationCritiria) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				

				if (!remainderFieldsMap.isSectionField()) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.isSectionField()) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			fieldValidationCritirias = new ArrayList<>();
		}
	
	}

	private void resolveFieldRestrictionCritiria(List<FieldRestrictionCritiria> fieldRestrictionCritirias,
	        Map<Long, Long> formFieldSpecsIdMap, 
	        Map<Long, Long> formSectionFieldSpecsIdMap, 
	        Map<Long, Long> formSpecIdsMap) {

	    try {
	        Iterator<FieldRestrictionCritiria> fieldRestrictionIterator = fieldRestrictionCritirias.iterator();
	        while (fieldRestrictionIterator.hasNext()) {
	            FieldRestrictionCritiria restriction = fieldRestrictionIterator.next();

	            // Resolve Form Spec ID
	            Long newFormSpecId = formSpecIdsMap.get(restriction.getFormSpecId());
	            if (newFormSpecId != null) {
	                restriction.setFormSpecId(newFormSpecId);
	            } else {
	                fieldRestrictionIterator.remove();
	                continue;
	            }

	            if (!restriction.isSectionField()) {
	                Long newFieldSpecId = formFieldSpecsIdMap.get(restriction.getFieldSpecId());
	                if (newFieldSpecId != null) {
	                    restriction.setFieldSpecId(newFieldSpecId);
	                } else {
	                    fieldRestrictionIterator.remove();
	                    continue;
	                }
	            } else {
	                Long newSectionFieldSpecId = formSectionFieldSpecsIdMap.get(restriction.getFieldSpecId());
	                if (newSectionFieldSpecId != null) {
	                    restriction.setFieldSpecId(newSectionFieldSpecId);
	                } else {
	                    fieldRestrictionIterator.remove();
	                    continue;
	                }
	            }
	        }
	    } catch (Exception e) {
	        fieldRestrictionCritirias = new ArrayList<>();
	    }
	}

	private void resolveCustomerFilterCritria(List<CustomerFilteringCritiria> customerFilteringCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {

		try {
			Iterator<CustomerFilteringCritiria> fieldValidationsIterator = customerFilteringCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				CustomerFilteringCritiria remainderFieldsMap = (CustomerFilteringCritiria) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
				

				if (remainderFieldsMap.getType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			customerFilteringCritirias = new ArrayList<>();
		}
	
	}
	private void resolveCustomerFilterCritria(List<CustomerFilteringCritiria> customerFilteringCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap,Map<Long, Long> formSpecIdsMap) {

		try {
			Iterator<CustomerFilteringCritiria> fieldValidationsIterator = customerFilteringCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				CustomerFilteringCritiria remainderFieldsMap = (CustomerFilteringCritiria) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				

				if (remainderFieldsMap.getType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			customerFilteringCritirias = new ArrayList<>();
		}
	
	}

	private void resolveVisibilityDependencyCritirias(List<VisibilityDependencyCriteria> visibilityDependencyCriterias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {
		try {
			Iterator<VisibilityDependencyCriteria> fieldValidationsIterator = visibilityDependencyCriterias.iterator();
			List<Integer> supportedDataTypes = Arrays.asList(1,2,3,4,8,9,10,11,16,19,20,44);
			while (fieldValidationsIterator.hasNext()) {
				VisibilityDependencyCriteria remainderFieldsMap = (VisibilityDependencyCriteria) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
				
				if(!supportedDataTypes.contains(remainderFieldsMap.getFieldDataType())) {
					fieldValidationsIterator.remove();
				}
				

				if (remainderFieldsMap.getFieldType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			visibilityDependencyCriterias = new ArrayList<>();
		}
		
	}
	
	private void resolveVisibilityDependencyCritirias(List<VisibilityDependencyCriteria> visibilityDependencyCriterias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap,Map<Long, Long> formSpecIdsMap) {
		try {
			Iterator<VisibilityDependencyCriteria> fieldValidationsIterator = visibilityDependencyCriterias.iterator();
			List<Integer> supportedDataTypes = Arrays.asList(1,2,3,4,8,9,10,11,16,19,20,44);
			while (fieldValidationsIterator.hasNext()) {
				VisibilityDependencyCriteria remainderFieldsMap = (VisibilityDependencyCriteria) fieldValidationsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				
				if(!supportedDataTypes.contains(remainderFieldsMap.getFieldDataType())) {
					fieldValidationsIterator.remove();
				}
				

				if (remainderFieldsMap.getFieldType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
			}
		} catch (Exception e) {
			visibilityDependencyCriterias = new ArrayList<>();
		}
		
	}

	private void resolveListFilterCritrias(List<ListFilteringCritiria> listFilteringCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec,
			Map<Long, Long> entityFieldSpecsIdMap) {
		try {
			Iterator<ListFilteringCritiria> fieldValidationsIterator = listFilteringCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				ListFilteringCritiria remainderFieldsMap = (ListFilteringCritiria) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());

				if (remainderFieldsMap.getType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
				if (entityFieldSpecsIdMap.get(remainderFieldsMap.getListFieldSpecId()) != null) {
					remainderFieldsMap
							.setListFieldSpecId(entityFieldSpecsIdMap.get(remainderFieldsMap.getListFieldSpecId()));
				} else {
					fieldValidationsIterator.remove();
					continue;
				}

			}
		} catch (Exception e) {
			listFilteringCritirias = new ArrayList<>();
		}
		
	}
	
	private void resolveListFilterCritrias(List<ListFilteringCritiria> listFilteringCritirias,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap,Map<Long, Long> formSpecIdsMap,
			Map<Long, Long> entityFieldSpecsIdMap) {
		try {
			Iterator<ListFilteringCritiria> fieldValidationsIterator = listFilteringCritirias.iterator();
			while (fieldValidationsIterator.hasNext()) {
				ListFilteringCritiria remainderFieldsMap = (ListFilteringCritiria) fieldValidationsIterator.next();
				
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}
				
				if (remainderFieldsMap.getType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				
				if (entityFieldSpecsIdMap.get(remainderFieldsMap.getListFieldSpecId()) != null) {
					remainderFieldsMap
							.setListFieldSpecId(entityFieldSpecsIdMap.get(remainderFieldsMap.getListFieldSpecId()));
				} else {
					fieldValidationsIterator.remove();
					continue;
				}

			}
		} catch (Exception e) {
			listFilteringCritirias = new ArrayList<>();
		}
		
	}

	private void resolveFieldValidations(List<FieldValidation> fieldValidations,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap, FormSpec formSpec) {
		try {
			Iterator<FieldValidation> fieldValidationsIterator = fieldValidations.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldValidation remainderFieldsMap = (FieldValidation) fieldValidationsIterator.next();
				remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());

				if (remainderFieldsMap.getFieldType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

			}
		} catch (Exception e) {
			fieldValidations = new ArrayList<>();
		}

	}
	
	private void resolveFieldValidations(List<FieldValidation> fieldValidations,
			Map<Long, Long> formFieldSpecsIdMap, Map<Long, Long> formSectionFieldSpecsIdMap,Map<Long, Long> formSpecIdsMap) {
		try {
			Iterator<FieldValidation> fieldValidationsIterator = fieldValidations.iterator();
			while (fieldValidationsIterator.hasNext()) {
				FieldValidation remainderFieldsMap = (FieldValidation) fieldValidationsIterator.next();
				
				Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
				
				if(newFormSpecId != null) {
					remainderFieldsMap.setFormSpecId(newFormSpecId);
				}else {
					fieldValidationsIterator.remove();
					continue;
				}

				if (remainderFieldsMap.getFieldType() == 1) {
					if (formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}

				if (remainderFieldsMap.getFieldType() == 2) {
					if (formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
						remainderFieldsMap
								.setFieldSpecId(formSectionFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
					} else {
						fieldValidationsIterator.remove();
						continue;
					}
				}
				

			}
		} catch (Exception e) {
			fieldValidations = new ArrayList<>();
		}

	}

	private void resolveRemainderFieldsMaps(List<RemainderFieldsMap> remainderFieldsMaps,
			Map<Long, Long> formFieldSpecsIdMap,FormSpec formSpec) {
		
		Iterator<RemainderFieldsMap> remainderFieldsIerator = remainderFieldsMaps
				.iterator();
		while (remainderFieldsIerator.hasNext()) {
			RemainderFieldsMap remainderFieldsMap = (RemainderFieldsMap) remainderFieldsIerator
					.next();
			remainderFieldsMap.setFormSpecId(formSpec.getFormSpecId());
			if(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
				remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
			}else {
				remainderFieldsIerator.remove();
				continue;
			}
			
			if(formFieldSpecsIdMap.get(remainderFieldsMap.getReferenceFieldSpecId()) != null) {
				remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
			}else {
				remainderFieldsIerator.remove();
				continue;
			}

		}
		
	}
	private void resolveRemainderFieldsMaps(List<RemainderFieldsMap> remainderFieldsMaps,
			Map<Long, Long> formFieldSpecsIdMap,Map<Long, Long> formSpecIdsMap) {
		
		Iterator<RemainderFieldsMap> remainderFieldsIerator = remainderFieldsMaps
				.iterator();
		while (remainderFieldsIerator.hasNext()) {
			RemainderFieldsMap remainderFieldsMap = (RemainderFieldsMap) remainderFieldsIerator
					.next();
			Long newFormSpecId = formSpecIdsMap.get(remainderFieldsMap.getFormSpecId());
			
			if(newFormSpecId != null) {
				remainderFieldsMap.setFormSpecId(newFormSpecId);
			}else {
				remainderFieldsIerator.remove();
				continue;
			}
			
			if(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()) != null) {
				remainderFieldsMap.setFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getFieldSpecId()));
			}else {
				remainderFieldsIerator.remove();
				continue;
			}
			
			if(formFieldSpecsIdMap.get(remainderFieldsMap.getReferenceFieldSpecId()) != null) {
				remainderFieldsMap.setReferenceFieldSpecId(formFieldSpecsIdMap.get(remainderFieldsMap.getReferenceFieldSpecId()));
			}else {
				remainderFieldsIerator.remove();
				continue;
			}

		}
		
	}

	public FormSpec getFormSpec(String formSpecId) {
		FormSpec formSpec = null;

		try {
			formSpec = effortDao.getFormSpec(formSpecId);
		} catch (Exception e) {
			
		}

		return formSpec;
	}
	public List<FormSpec> getLatestFormSpecsForUnquids(String uniqueIds) {
		return effortDao.getLatestFormSpecsForUnquids(uniqueIds);
	}

	public List<EntitySpec> getAllEntitySpecsForCompany(Integer companyId) {
		return effortDao.getEntitySpecsForCompany(companyId, null);
	}
	public List<CustomEntitySpec> getCustomEntitySpecs(Integer companyId)
	{
		return effortDao.getCustomEntitySpecs(companyId);
	}
	public List<EntitySpec> getEntitySpecsForCompany(Integer companyId) {
		return effortDao.getEntitySpecsForCompany(companyId, 0);
	}
	public List<EntityFieldSpec> getEntityFieldSpecsIn(
			List<EntitySpec> entitySpecs) {
		return effortDao.getEntityFieldSpecs(entitySpecs);
	}
	public EntitySpec getEntitySpec(String entitySpecId) {
		EntitySpec entitySpec = null;

		try {
			entitySpec = effortDao.getEntitySpec(entitySpecId);
		} catch (Exception e) {
			
		}

		return entitySpec;
	}
	
	public void resolveAndInsertListInListForFields(String entitySpecId,
			Map<Long, Long> entitySpecsIdMap,
			Map<Long, Long> entityFieldSpecsIdMap, WebUser webUser,boolean isClone) {
		EntitySpec entitySpec = getEntitySpec(entitySpecId);

		long oldEntitySpecId = entitySpec.getEntitySpecId();
		entitySpec.setParentId(oldEntitySpecId);
		entitySpec.setSkeletonEntitySpecId(oldEntitySpecId);
		if (isClone) {
			entitySpec.setIsSystemDefined(0);
		} else {
			entitySpec.setIsSystemDefined(1);
		}
		
		effortDao.insertEntitySpec(entitySpec, webUser.getCompanyId(),
				webUser.getEmpId());
		entitySpecsIdMap.put(oldEntitySpecId, entitySpec.getEntitySpecId());

		List<EntityFieldSpec> entityFieldSpecs = effortDao.getEntityFieldSpecs(oldEntitySpecId);

		for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {

			if (entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST) {
				if (!entitySpecsIdMap.containsKey(Long
						.parseLong(entityFieldSpec.getFieldTypeExtra()))) {
					resolveAndInsertListInListForFields(
							entityFieldSpec.getFieldTypeExtra(),
							entitySpecsIdMap, entityFieldSpecsIdMap, webUser,isClone);
				}
				entityFieldSpec.setFieldTypeExtra(""
						+ entitySpecsIdMap.get(Long.parseLong(entityFieldSpec
								.getFieldTypeExtra())));
			}

			long oldEntityFieldSpecId = entityFieldSpec.getEntityFieldSpecId();
			long entitySpecIdForExtra = entitySpecsIdMap.get(entityFieldSpec
					.getEntitySpecId());
			entityFieldSpec.setSkeletonEntityFieldSpecId(oldEntityFieldSpecId);
			effortDao.insertEntityFieldSpec(entityFieldSpec,
					entitySpecIdForExtra);
			entityFieldSpecsIdMap.put(oldEntityFieldSpecId,
					entityFieldSpec.getEntityFieldSpecId());

		}

		List<EntityFieldSpecValidValue> entityFieldSpecValidValues = getEntityFieldSpecValidValuesIn(entityFieldSpecs);
		for (EntityFieldSpecValidValue entityFieldSpecValidValue : entityFieldSpecValidValues) {
			long entityFieldSpecId = entityFieldSpecsIdMap
					.get(entityFieldSpecValidValue.getEntityFieldSpecId());
			effortDao.insertEntityFieldSpecValidValue(entityFieldSpecId,
					entityFieldSpecValidValue.getValue());
		}

	}
	public List<EntityFieldSpecValidValue> getEntityFieldSpecValidValuesIn(
			List<EntityFieldSpec> entityFieldSpecs) {
		return effortDao.getEntityFieldSpecValidValuesIn(entityFieldSpecs);
	}
	public FormFieldSpec getFormFieldSpec(String formFieldSpecId) {
		FormFieldSpec formFieldSpec = null;

		try {
			formFieldSpec = effortDao.getFormFieldSpec(formFieldSpecId);
		} catch (Exception e) {
		}

		return formFieldSpec;
	}
	public String getFormSpecPrintTemplate(String formSpecId) {
		return effortDao.getFormSpecPrintTemplate(formSpecId);
	}

	public String getFormSpecEmailTemplate(String formSpecId) {
		return effortDao.getFormSpecEmailTemplate(formSpecId);
	}

	public String getFormSpecMobilePrintTemplate(String formSpecId) {
		return effortDao.getFormSpecMobilePrintTemplate(formSpecId);
	}
	public FormSectionFieldSpec getFormSectionFieldSpec(String sectionSpecId) {
		return effortDao.getFormSectionFieldSpec(sectionSpecId);
	}
	
	public void getExportWorkSpecData(String workSpecId, WorkSpecContainer workSpecContainer) {
		
		try {
			WorkSpec workSpec = getWorkSpec(workSpecId);
			List<WorkSpec> workSpecs = new ArrayList<>();
			if(workSpec!=null) {
				workSpecs.add(workSpec);
				
				String workSpecIdsForSendingSubTaskSpecs =  Api.toCSVFromList(workSpecs, "workSpecId");
				
				List<WorkProcessSubTaskSpec> workProcessSubTaskSpecs = getWorkPrcocessSubTaskSpecsForSync(workSpecIdsForSendingSubTaskSpecs);
				if(workProcessSubTaskSpecs!=null && workProcessSubTaskSpecs.size()>0)
				{
					
					workSpecContainer.setWorkProcessSubTaskSpecConfigurations(workProcessSubTaskSpecs);
					
					String subTaskWorkSpecIds = Api.toCSVFromList(workProcessSubTaskSpecs, "subTaskWorkSpecId");
					
					if(!Api.isEmptyString(subTaskWorkSpecIds))
					{
						List<WorkSpec> subTaskWorkSpecs = getWorkSpecsForWorkSpecIdsIn(subTaskWorkSpecIds);
						if(subTaskWorkSpecs!=null && subTaskWorkSpecs.size()>0)
						{
							if(workSpecs!=null && workSpecs.size()>0)
							{
								workSpecs.removeAll(subTaskWorkSpecs);
								workSpecs.addAll(subTaskWorkSpecs);
							}
						}
					}
					
					List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations = new ArrayList<WorkToSubTaskAutoFillConfiguration>();
					if(workSpecs!=null && workSpecs.size()>0)
					{
						workToSubTaskAutoFillConfigurations = getWorkToSubTaskAutoFillConfirationsForSync(workSpecs,workSpec.getCompanyId());
					}
					workSpecContainer.setWorkToSubTaskAutoFillConfigurations(workToSubTaskAutoFillConfigurations);
					
					String workProcessSubTaskSpecIdsCsv = Api.toCSVFromList(workProcessSubTaskSpecs, "workProcessSubTaskSpecId");
					
					if(!Api.isEmptyString(workProcessSubTaskSpecIdsCsv))
					{
						List<AddingSubTaskEmployeeConfiguration> subTaskEmployeeConf = getAddingSubTaskEmployeeConfigurationsForSync(workProcessSubTaskSpecIdsCsv);
						
						if(subTaskEmployeeConf != null && subTaskEmployeeConf.size()>0)
						{
							workSpecContainer.setWorkProcessSubTaskEmployeesConfigurations(subTaskEmployeeConf);
						}
					}
				}
				
				if(workSpecs != null && workSpecs.size() > 0)
				{
					List<Long> workSpecIdsList = Api.listToLongList(workSpecs, "workSpecId");
					List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations = getHideAddSubTaskConfigurationForSync(Api.toCSV(workSpecIdsList));
					
					if(hideAddSubTaskConfigurations != null && hideAddSubTaskConfigurations.size()>0)
					{
						workSpecContainer.setHideAddSubTaskConfigurations(hideAddSubTaskConfigurations);
					}
				}
				
				String workSpecIdsCsv = Api.toCSVFromList(workSpecs, "workSpecId");
				
				List<WorkActionSpec> workActionSpecs = getWorkActionSpecsWithAllocationConfigForSync(workSpecIdsCsv);
				
				String actionSpecIds = Api.toCSVFromList(workActionSpecs,"workActionSpecId");
				
				List<NextActionSpec> nextActionSpecs = getNextActionSpecs(actionSpecIds);
				
				List<NextWorkSpec> nextWorkSpecs = getNextWorkSpecs(actionSpecIds, workSpecIdsCsv);
				
				List<WorkSpecFormSpecMap> workSpecFormSpecMaps = getWorkSpecFormSpecMaps(workSpecIdsCsv);
				
				List<WorkActionSpecConditions> workActionSpecConditions = getWorkActionSpecConditions(actionSpecIds);
				
				List<WorkActionSpecEndCondition> workActionSpecEndConditions = getWorkActionSpecEndConditionsForWorkActionSpecIds(actionSpecIds);
				
				List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations = getWorkActionVisibilityConfigurationByCompanyIdForSync(workSpec.getCompanyId(),workSpecIdsCsv);
				
				List<ExternalActionConfiguration> workActionSpecExternalActionConfig = getExternalActionConnfigurationForSync(actionSpecIds);
				
				List<WorkFieldsUniqueConfigurations> workFieldsUniqueConfigurations = getWorkFieldsUniqueConfigurationsForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkFieldsUniqueConfigurations> openWorkFieldsUniqueConfigurations = getOpenWorkFieldsUniqueConfigurationsForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkReassignmentRules> workReassignmentRules = getWorkReassignmentRulesForSync(workSpecIdsCsv, null);
				
				List<WorkSpecAppLabel> workSpecAppLabels =  getWorkSpecAppLabelsForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkSpecPermission> workSpecPermissions =  getWorkSpecPermissionsForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkSpecListLevelVisibilityConfiguration> workSpecListLevelVisibilityConfigurations = getWorkSpecListLevelVisibilityConfigurationsForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations = getWorkSpecCustomDashboardConfiguration(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				
				List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics = getWorkSpecCustomDashboardMetrics(workSpecIdsCsv, null, workSpec.getCompanyId());

				List<WorkActionFormVisibility> workActionFormVisibility = getWorkActionFormVisibilityForSync(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs = getActionableEmployeeGroupSpecs(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkActionNotificationEscalationMatrix> workActionNotificationEscalationMatrix = getWorkActionNotificationEscalationMatrix(workSpecIdsCsv, null, workSpec.getCompanyId());
				
				List<WorkUnassignmentCriterias> workUnassignmentCriterias = getWorkUnassignmentCriteriasForWorkSpecs(workSpecIdsCsv, workSpec.getCompanyId());
				if(!workUnassignmentCriterias.isEmpty()) {
					String workUnassignmentCriteriaIdcsv = "-1";
					try {
						workUnassignmentCriteriaIdcsv = Api.toCSV(workUnassignmentCriterias, "workUnassignmentCriteriaId");
					} catch (Exception e4) {
						e4.printStackTrace();
					} 
					List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions = getWorkAssignmentCriteriaConditionsByCriteriaIds(workUnassignmentCriteriaIdcsv);
					workSpecContainer.setWorkUnassignmentCriterias(workUnassignmentCriterias);
					workSpecContainer.setWorkAssignmentCriteriaConditions(workAssignmentCriteriaConditions);

				}
				
				if(workSpecs != null && workSpecs.size() > 0) {
					try {
						List<WorkSpecCustomerCallApi> workSpecCustomerCallApis = getWorkSpecCustomerCallApis(Api.toCSV(workSpecs, "workSpecId"));
						workSpecContainer.setWorkSpecCustomerCallApis(workSpecCustomerCallApis);
					} catch (Exception e) {
						
					} 
					try {
						sendingWorkActionGroupsInSync(Api.toCSV(workSpecs, "workSpecId"), workSpecContainer);
					} catch (Exception e) {

					}
				}
				
				
				if(workSpecContainer.getWorkSpecs() != null && workSpecContainer.getWorkSpecs().size() > 0){
					workSpecContainer.getWorkSpecs().addAll(workSpecs);
				}else{
					workSpecContainer.setWorkSpecs(workSpecs);
				}
				
				if(workSpecContainer.getWorkActionSpecs() != null && workSpecContainer.getWorkActionSpecs().size() > 0){
					workSpecContainer.getWorkActionSpecs().addAll(workActionSpecs);
				}else{
					workSpecContainer.setWorkActionSpecs(workActionSpecs);
				}
				
				if(workSpecContainer.getWorkActionSpecs()!=null && workSpecContainer.getWorkActionSpecs().size()>0){
					List<WorkActionSpec> workActionSpecsAll = workSpecContainer.getWorkActionSpecs();
					for(WorkActionSpec workActionSpec : workActionSpecsAll)
					{
						if(workActionSpec.getWorkActionActionableType() == WorkActionSpec.ACTION_TYPE_API_SERVICE ||
								workActionSpec.getWorkActionActionableType() == WorkActionSpec.ACTION_TYPE_DECISION ||
								workActionSpec.getWorkActionActionableType() == WorkActionSpec.ACTION_TYPE_SYSTEM || 
								workActionSpec.getWorkActionActionableType() == WorkActionSpec.ACTION_TYPE_JAVASCRIPT)
						{
							workActionSpec.setSystemAction(true);
						}
					}
					workSpecContainer.setWorkActionSpecs(workActionSpecsAll);
				}
				

				if(workSpecContainer.getNextActionSpecs() != null && workSpecContainer.getNextActionSpecs().size() > 0){
					workSpecContainer.getNextActionSpecs().addAll(nextActionSpecs);
				}else{
					workSpecContainer.setNextActionSpecs(nextActionSpecs);
				}

				if(workSpecContainer.getNextWorkSpecs() != null && workSpecContainer.getNextWorkSpecs().size() > 0){
					workSpecContainer.getNextWorkSpecs().addAll(nextWorkSpecs);
				}else{
					workSpecContainer.setNextWorkSpecs(nextWorkSpecs);
				}
				
				
				
				if(workSpecContainer.getWorkSpecFormSpecMaps() != null && workSpecContainer.getWorkSpecFormSpecMaps().size() > 0){
					workSpecContainer.getWorkSpecFormSpecMaps().addAll(workSpecFormSpecMaps);
				}else{
					workSpecContainer.setWorkSpecFormSpecMaps(workSpecFormSpecMaps);
				}
				
				if(workSpecContainer.getWorkActionSpecConditions() != null && workSpecContainer.getWorkActionSpecConditions().size() > 0){
					workSpecContainer.getWorkActionSpecConditions().addAll(workActionSpecConditions);
				}else{
					workSpecContainer.setWorkActionSpecConditions(workActionSpecConditions);
				}	
				
				if(workSpecContainer.getWorkActionSpecEndConditions() != null && workSpecContainer.getWorkActionSpecEndConditions().size() > 0){
					workSpecContainer.getWorkActionSpecEndConditions().addAll(workActionSpecEndConditions);
				}else{
					workSpecContainer.setWorkActionSpecEndConditions(workActionSpecEndConditions);
				}	
				
				if(workSpecContainer.getWorkActionVisibilityConfigurations() != null && workSpecContainer.getWorkActionVisibilityConfigurations().size() > 0){
					workSpecContainer.getWorkActionVisibilityConfigurations().addAll(workActionVisibilityConfigurations);
				}else{
					workSpecContainer.setWorkActionVisibilityConfigurations(workActionVisibilityConfigurations);
				}	
				
				if (workSpecContainer.getWorkFieldsUniqueConfigurations() != null && workSpecContainer.getWorkFieldsUniqueConfigurations().size() > 0) {
					workSpecContainer.getWorkFieldsUniqueConfigurations().addAll(workFieldsUniqueConfigurations);
				} else {
					workSpecContainer.setWorkFieldsUniqueConfigurations(workFieldsUniqueConfigurations);
				}
				
				if (workSpecContainer.getOpenWorkFieldsUniqueConfigurations() != null && workSpecContainer.getOpenWorkFieldsUniqueConfigurations().size() > 0) {
					workSpecContainer.getOpenWorkFieldsUniqueConfigurations().addAll(openWorkFieldsUniqueConfigurations);
				} else {
					workSpecContainer.setOpenWorkFieldsUniqueConfigurations(openWorkFieldsUniqueConfigurations);
				}

				if (workSpecContainer.getWorkReassignmentRules() != null && workSpecContainer.getWorkReassignmentRules().size() > 0) {
					workSpecContainer.getWorkReassignmentRules().addAll(workReassignmentRules);
				} else {
					workSpecContainer.setWorkReassignmentRules(workReassignmentRules);
				}
				
				if (workSpecContainer.getWorkSpecAppLabels() != null && workSpecContainer.getWorkSpecAppLabels().size() > 0) {
					workSpecContainer.getWorkSpecAppLabels().addAll(workSpecAppLabels);
				} else {
					workSpecContainer.setWorkSpecAppLabels(workSpecAppLabels);
				}
				
				if (workSpecContainer.getWorkSpecPermissions() != null	&& workSpecContainer.getWorkSpecPermissions().size() > 0) {
					workSpecContainer.getWorkSpecPermissions().addAll(workSpecPermissions);
				} else {
					workSpecContainer.setWorkSpecPermissions(workSpecPermissions);
				}
				
				if (workSpecContainer.getExternalActionConfigurations() != null && workSpecContainer.getExternalActionConfigurations().size() > 0) {
					workSpecContainer.getExternalActionConfigurations().addAll(workActionSpecExternalActionConfig);
				} else {
					workSpecContainer.setExternalActionConfigurations(workActionSpecExternalActionConfig);
				}
		        
		        if (workSpecContainer.getWorkSpecListLevelVisibilityConfigurations() != null && workSpecContainer.getWorkSpecListLevelVisibilityConfigurations().size() > 0) {
		                workSpecContainer.getWorkSpecListLevelVisibilityConfigurations().addAll(workSpecListLevelVisibilityConfigurations);
		        } else {
		                workSpecContainer.setWorkSpecListLevelVisibilityConfigurations(workSpecListLevelVisibilityConfigurations);
		        }
		        
		        if (workSpecContainer.getWorkActionFormVisibility() != null && workSpecContainer.getWorkActionFormVisibility().size() > 0) {
		            workSpecContainer.getWorkActionFormVisibility().addAll(workActionFormVisibility);
			    } else {
			            workSpecContainer.setWorkActionFormVisibility(workActionFormVisibility);
			    }
		        
				if (workSpecContainer.getWorkSpecCustomDashboardConfigurations() != null
						&& workSpecContainer.getWorkSpecCustomDashboardConfigurations().size() > 0) {
					workSpecContainer.getWorkSpecCustomDashboardConfigurations()
							.addAll(workSpecCustomDashboardConfigurations);
				} else {
					workSpecContainer.setWorkSpecCustomDashboardConfigurations(workSpecCustomDashboardConfigurations);
				}

				if (workSpecContainer.getWorkSpecCustomDashboardMetrics() != null
						&& workSpecContainer.getWorkSpecCustomDashboardMetrics().size() > 0) {
					workSpecContainer.getWorkSpecCustomDashboardMetrics()
							.addAll(workSpecCustomDashboardMetrics);
				} else {
					workSpecContainer.setWorkSpecCustomDashboardMetrics(workSpecCustomDashboardMetrics);
				}
				
				if (workSpecContainer.getActionableEmployeeGroupSpecs() != null
						&& workSpecContainer.getActionableEmployeeGroupSpecs().size() > 0) {
					workSpecContainer.getActionableEmployeeGroupSpecs()
							.addAll(actionableEmployeeGroupSpecs);
				} else {
					workSpecContainer.setActionableEmployeeGroupSpecs(actionableEmployeeGroupSpecs);
				}
				
				if (workSpecContainer.getWorkActionNotificationEscalationMatrix() != null
						&& workSpecContainer.getWorkActionNotificationEscalationMatrix().size() > 0) {
					workSpecContainer.getWorkActionNotificationEscalationMatrix()
							.addAll(workActionNotificationEscalationMatrix);
				} else {
					workSpecContainer.setWorkActionNotificationEscalationMatrix(workActionNotificationEscalationMatrix);
				}
				
				
			}
			
		}catch(Exception e) {
			LOGGER.info("Got Exception getExportWorkSpecData  : "+e );
			LOGGER.error("Got Exception getExportWorkSpecData  : "+e );
			e.printStackTrace();
		}
	}

	
	private void sendingWorkActionGroupsInSync(String workSpecIdsCsv,
			WorkSpecContainer workSpecContainer) {
		
		try {
			List<WorkActionGroup> workActionGroups = effortDao.getWorkActionGroupByWorkSpecIdsForSync(workSpecIdsCsv);
			if(workActionGroups != null && workActionGroups.size() > 0) {
				workSpecContainer.setWorkActionGroups(workActionGroups);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<WorkSpecCustomerCallApi> getWorkSpecCustomerCallApis(String csv) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<WorkAssignmentCriteriaConditions> getWorkAssignmentCriteriaConditionsByCriteriaIds(
			String workUnassignmentCriteriaIdcsv) {
		return effortDao.getWorkAssignmentCriteriaConditionsByCriteriaIds(workUnassignmentCriteriaIdcsv);
	}

	private List<WorkUnassignmentCriterias> getWorkUnassignmentCriteriasForWorkSpecs(String workSpecIdsCsv,
			int companyId) {
		return effortDao.getWorkUnassignmentCriteriasForWorkSpecs(workSpecIdsCsv,companyId);
	}

	private List<WorkActionNotificationEscalationMatrix> getWorkActionNotificationEscalationMatrix(
			String workSpecIdsCsv, String syncDate, int companyId) {
		return effortDao.getWorkActionNotificationEscalationMatrix(workSpecIdsCsv,syncDate,companyId);
	}

	private List<ActionableEmployeeGroupSpecs> getActionableEmployeeGroupSpecs(String workSpecIdsCsv, String syncDate,
			int companyId) {
		return effortDao.getActionableEmployeeGroupSpecs(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkActionFormVisibility> getWorkActionFormVisibilityForSync(String workSpecIdsCsv, String syncDate,
			int companyId) {
		return effortDao.getWorkActionFormVisibilityForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkSpecCustomDashboardMetric> getWorkSpecCustomDashboardMetrics(String workSpecIdsCsv, String syncDate,
			int companyId) {
		return effortDao.getWorkSpecCustomDashboardMetrics(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkSpecCustomDashboardConfiguration> getWorkSpecCustomDashboardConfiguration(String workSpecIdsCsv,
			String syncDate, int companyId) {
		return effortDao.getWorkSpecCustomDashboardConfiguration(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkSpecListLevelVisibilityConfiguration> getWorkSpecListLevelVisibilityConfigurationsForSync(
			String workSpecIdsCsv,  String syncDate, int companyId) {
		return effortDao.getWorkSpecListLevelVisibilityConfigurationsForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkSpecPermission> getWorkSpecPermissionsForSync(String workSpecIdsCsv,  String syncDate,
			int companyId) {
		return effortDao.getWorkSpecPermissionsForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkSpecAppLabel> getWorkSpecAppLabelsForSync(String workSpecIdsCsv, String syncDate, int companyId) {
		return effortDao.getWorkSpecAppLabelsForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkReassignmentRules> getWorkReassignmentRulesForSync(String workSpecIdsCsv, String syncDate) {
		return effortDao.getWorkReassignmentRulesForSync(workSpecIdsCsv,syncDate);
	}

	private List<WorkFieldsUniqueConfigurations> getOpenWorkFieldsUniqueConfigurationsForSync(String workSpecIdsCsv,
			String syncDate, int companyId) {
		return effortDao.getOpenWorkFieldsUniqueConfigurationsForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<WorkFieldsUniqueConfigurations> getWorkFieldsUniqueConfigurationsForSync(String workSpecIdsCsv,
			String syncDate, int companyId)
	{
		return effortDao.getWorkFieldsUniqueConfigurationsForSync(workSpecIdsCsv,syncDate,companyId);
	}

	private List<ExternalActionConfiguration> getExternalActionConnfigurationForSync(String actionSpecIds) {
		return effortDao.getExternalActionConnfigurationForSync(actionSpecIds);
	}

	private List<WorkActionVisibilityConfiguration> getWorkActionVisibilityConfigurationByCompanyIdForSync(
			int companyId, String workSpecIdsCsv) {
		return effortDao.getWorkActionVisibilityConfigurationByCompanyIdForSync(companyId,workSpecIdsCsv);
	}

	public List<WorkActionSpecEndCondition> getWorkActionSpecEndConditionsForWorkActionSpecIds(
			String workActionSpecIds) 
	{
		List<WorkActionSpecEndCondition> workActionSpecEndCondtions = new ArrayList<WorkActionSpecEndCondition>();
		if(!Api.isEmptyString(workActionSpecIds))
		{
			workActionSpecEndCondtions = effortDao.getWorkActionSpecEndConditionsForWorkActionSpecIds(workActionSpecIds);
		}
		return workActionSpecEndCondtions;
	}

	private List<WorkActionSpecConditions> getWorkActionSpecConditions(String actionSpecIds) {
		return effortDao.getWorkActionSpecConditions(actionSpecIds);
	}

	private List<WorkSpecFormSpecMap> getWorkSpecFormSpecMaps(String workSpecIdsCsv) {
		return effortDao.getWorkSpecFormSpecMaps(workSpecIdsCsv);
	}

	private List<NextWorkSpec> getNextWorkSpecs(String actionSpecIds, String workSpecIdsCsv) {
		return effortDao.getNextWorkSpecs(actionSpecIds,workSpecIdsCsv);
	}

	private List<NextActionSpec> getNextActionSpecs(String actionSpecIds) {
		return effortDao.getNextActionSpecs(actionSpecIds);
	}

	private List<WorkActionSpec> getWorkActionSpecsWithAllocationConfigForSync(String workSpecIdsCsv) {
		return effortDao.getWorkActionSpecsWithAllocationConfigForSync(workSpecIdsCsv);
	}

	private List<HideAddSubTaskConfiguration> getHideAddSubTaskConfigurationForSync(String workSpecIds) {
		return effortDao.getHideAddSubTaskConfigurationForSync(workSpecIds);
	}

	private List<AddingSubTaskEmployeeConfiguration> getAddingSubTaskEmployeeConfigurationsForSync(
			String workProcessSubTaskSpecIdsCsv) {
		return effortDao.getAddingSubTaskEmployeeConfigurationsForSync(workProcessSubTaskSpecIdsCsv);
	}

	private List<WorkToSubTaskAutoFillConfiguration> getWorkToSubTaskAutoFillConfirationsForSync(
			List<WorkSpec> workSpecs, int companyId) 
	{
		return effortDao.getWorkToSubTaskAutoFillConfirationsForSync(workSpecs,companyId);
	}

	private List<WorkSpec> getWorkSpecsForWorkSpecIdsIn(String subTaskWorkSpecIds) {
		return effortDao.getWorkSpecsForWorkSpecIdsIn(subTaskWorkSpecIds);
	}

	private List<WorkProcessSubTaskSpec> getWorkPrcocessSubTaskSpecsForSync(String parentWorkSpecIds) {
		return effortDao.getWorkPrcocessSubTaskSpecsForSync(parentWorkSpecIds);
	}

	private WorkSpec getWorkSpec(String workSpecId) {
		return effortDao.getWorkSpecByWorkSpecId(workSpecId);
	}
	public void copyTemplatesFromSkeletonNew(WebUser webUser, FormSpecContainer formSpecContainer,
			Map<Long, Long> entitySpecsIdMap,
			Map<Long, EntitySpec> entitySpecMap, Map<Long, Long> formSpecIdsMap,
			Map<String, String> formSpecUniqueIdsMap, Map<String, String> formFieldSpecUniqueIdsMap,
			Map<String, String> formSectionFieldSpecUniqueIdsMap, Map<Long, Long> formFieldSpecsIdMap,
			Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> entityFieldSpecsIdMap, Boolean isSkeleton,
			boolean isWorkSpec, Map<String, String> formSectionSpecUniqueIdsMap, Map<Long, Long> formSectionSpecsIdMap,
			Map<String, String> customEntitySpecIdsMap, Map<Long, CustomEntitySpec> customEntitySpecsMap,
			Map<String, String> innerformSpecUniqueIdsMap,boolean cloneInnerFormSpecs) {
		
		
		List<FormPageSpec> formPageSpecs = formSpecContainer.getPageSpecs();
		List<FormSpec> formSpecList = formSpecContainer.getFormSpecs();
		
		
		String printTemplate = "";
		String emailTemplate = "";
		String mobilePrintTemplate = "";
		
		String printTemplatePdfSaveNameFieldUniqueId = null;

		List<FormFieldSpec> formFieldSpecs = formSpecContainer.getFields();
		List<FormSectionSpec> formSectionSpecs = formSpecContainer.getSections();
		
		List<FormFieldGroupSpec> formFieldGroupSpecs = formSpecContainer.getFormFieldGroupSpecs();
		List<FormSectionFieldSpec> formSectionFieldSpecs = formSpecContainer.getSectionFields();
		LOGGER.info("formFieldSpecs size: " + (formFieldSpecs != null ? formFieldSpecs.size() : "null"));
		LOGGER.info("formSectionFieldSpecs size: " + (formSectionFieldSpecs != null ? formSectionFieldSpecs.size() : "null"));
		List<FormFieldSpecValidValue> formFieldSpecValidValues = formSpecContainer.getFieldValidValues();

		List<FormSectionFieldSpecValidValue> formSectionFieldSpecValidValues = formSpecContainer
				.getSectionFieldValidValues();
		

		for(FormSpec formSpec : formSpecList) {
			
			FormSpec formSpecBySkeletonSpecId = effortDao.getFormSpecBySkeletonSpecId(formSpec.getFormSpecId());
			/*
			 * if(formSpecBySkeletonSpecId != null) {
			 * LOGGER.info(" FormSpec already Exported  : "+formSpecBySkeletonSpecId.
			 * getFormSpecId()); continue; }
			 */
			
			Long oldFormSpecId = formSpec.getFormSpecId();
			String oldFormSpecUniqueId = formSpec.getUniqueId();
			formSpec.setParentId(formSpec.getFormSpecId());
			
			formSpec.setUniqueId(null);
			

			if (formSpec.getSkeletonFormSpecId() == null) {
				formSpec.setSkeletonFormSpecId(formSpec.getFormSpecId());
			} else {
				formSpec.setSkeletonFormSpecId(formSpec.getSkeletonFormSpecId());
			}

			if(formSpec.getPurpose()==100 || formSpec.getPurpose() == FormSpec.PURPOUSE_SIGN_IN_SIGN_OUT_UPDATE_FORM) {
			     formSpec.setIsSystemDefined(0);
			     formSpec.setPurpose(-1);
			}else if(formSpec.getPurpose() == 12) {
				formSpec.setIsSystemDefined(1);
			}
			else if(formSpec.getPurpose() == 7) {
				formSpec.setIsSystemDefined(1);
			}
			else{
				if (isWorkSpec) {
					formSpec.setIsSystemDefined(0);
				} else {
					formSpec.setIsSystemDefined(1);
				}
				
			}
			
			formSpec.setIsPublic(true);
			formSpec.setAllAccess(true);
			effortDao.insertFormSpec(formSpec, webUser.getCompanyId(),
					webUser.getEmpId());
			FormSpec formSpecForUniqueId = getFormSpec(formSpec.getFormSpecId()
					+ "");
			formSpecIdsMap.put(oldFormSpecId, formSpec.getFormSpecId());
			formSpecUniqueIdsMap.put(oldFormSpecUniqueId,
					formSpecForUniqueId.getUniqueId());
			if(formSpec.isEnableCustomerActivity()) {
				Activity activity = new Activity();
				activity.setFormSpecId(formSpec.getFormSpecId());
				activity.setActivityName(formSpec.getFormTitle());
				activity.setActivityVisibility("false");
				createActivity(webUser, activity);
			}
			effortDao.updateFormSpecForInitialFormSpecId(formSpec);
			LOGGER.info(" insertFormSpec done ... "+formSpec.getFormSpecId());
		}
		resolveFormPageSpecs(formPageSpecs,formSpecIdsMap);
		effortDao.insertFormPageSpec(formPageSpecs);
		
		resolveFormFieldGroupSpec(formFieldGroupSpecs,formSpecIdsMap);
		effortDao.insertFormFieldGroupSpec(formFieldGroupSpecs,webUser.getCompanyId());
		
		if (formFieldSpecs != null) {
			for (FormFieldSpec formFieldSpec : formFieldSpecs) {
				long oldFieldSpecId = formFieldSpec.getFieldSpecId();
				boolean mandatory = formFieldSpec.isMandatory();
				boolean visible = formFieldSpec.getIsVisible();
				String oldFieldSpecUniqueId = formFieldSpec.getUniqueId();
				if ((formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST 
						|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_BUTTON_GROUPING)
						&& !Api.isEmptyString(formFieldSpec.getFieldTypeExtra())) {
					if (entitySpecsIdMap.size() > 0) {
						Long entitySpecId = entitySpecsIdMap.get(Long
								.parseLong(formFieldSpec.getFieldTypeExtra()));
						formFieldSpec.setFieldTypeExtra(entitySpecId + "");
					}
				}
				if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY || 
					     formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOM_ENTITY 
					    && !Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {
					if (formFieldSpec.getFieldTypeExtraCustomEntity() != null) {
						String fieldTypeExtraCustomEntity =  customEntitySpecIdsMap.get(formFieldSpec.getFieldTypeExtraCustomEntity());
						if (fieldTypeExtraCustomEntity != null) {
							formFieldSpec.setFieldTypeExtraCustomEntity(fieldTypeExtraCustomEntity);
						}
					}

				}//Grk -1
				
				if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_DATE_TIME) {
					if(!Api.isEmptyString(formFieldSpec.getRemainderRemarksFields())) {
						List<String> remainderFields = Api.csvToList(formFieldSpec.getRemainderRemarksFields());
						formFieldSpec.setRemainderRemarksFieldsCsv(formFieldSpec.getRemainderRemarksFields());
						if(remainderFields != null && remainderFields.size() > 0) {
							formFieldSpec.setRemainderRemarksFields(remainderFields.get(0));
						}
					}
				}
				formFieldSpec.setUniqueId(null);
				
				if (formFieldSpec.getSkeletonFormFieldSpecId() != null) {
					formFieldSpec.setSkeletonFormFieldSpecId(formFieldSpec
							.getSkeletonFormFieldSpecId());
				} 

				formFieldSpec.setMandatory(mandatory);
				formFieldSpec.setIsVisible(visible);
				Long newFormSpecId = formSpecIdsMap.get(formFieldSpec.getFormSpecId());
				if(newFormSpecId == null) {
					continue;
				}
				effortDao.insertFormFieldSpec(formFieldSpec,
						newFormSpecId);
				effortDao.updateInitialFormFieldSpecId(formFieldSpec);
				FormFieldSpec formFieldSpec2 = getFormFieldSpec(formFieldSpec
						.getFieldSpecId() + "");
				
				printTemplate = printTemplate.replace("id=\"F" + oldFieldSpecId
						+ "\"", "id=\"F" + formFieldSpec.getFieldSpecId()
						+ "\"");
				emailTemplate = emailTemplate.replaceAll("F" + oldFieldSpecId,
						"F" + formFieldSpec.getFieldSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<F"
						+ oldFieldSpecId + ">",
						"<F" + formFieldSpec.getFieldSpecId() + ">");

				formFieldSpecsIdMap.put(oldFieldSpecId,
						formFieldSpec.getFieldSpecId());
				formFieldSpecUniqueIdsMap.put(oldFieldSpecUniqueId,
						formFieldSpec2.getUniqueId());
			}
		}

		if (formSectionSpecs != null) {
			for (FormSectionSpec formSectionSpec : formSectionSpecs) {
				long oldSectionSpecId = formSectionSpec.getSectionSpecId();
				String oldSectionSpecUniqueId = formSectionSpec.getSectionSpecUniqueId();

				if (isSkeleton)
					formSectionSpec
							.setSkeletonFormSectionSpecId(oldSectionSpecId);
				else
					formSectionSpec
							.setSkeletonFormSectionSpecId(formSectionSpec
									.getSkeletonFormSectionSpecId());

				formSectionSpec.setSectionSpecUniqueId(null);
				Long newFormSpecId = formSpecIdsMap.get(formSectionSpec.getFormSpecId());
				if(newFormSpecId == null) {
					continue;
				}
				if (!Api.isEmptyString(formSectionSpec.getAutoCreateFieldTypeExtra())) {
		            long oldEntitySpecId = Long.parseLong(formSectionSpec.getAutoCreateFieldTypeExtra());

		            Long newEntitySpecId = entitySpecsIdMap.get(oldEntitySpecId);

		            if (newEntitySpecId != null) {
		                formSectionSpec.setAutoCreateFieldTypeExtra(String.valueOf(newEntitySpecId));
		            } else {
		                formSectionSpec.setAutoCreateFieldTypeExtra(String.valueOf(oldEntitySpecId));
		            }
		        }
				effortDao.insertFormSectionSpec(formSectionSpec,
						newFormSpecId, webUser.getCompanyId());
				effortDao.updateInitialFormSectionSpecId(formSectionSpec);
				
				printTemplate = printTemplate.replace("id=\"S"
						+ oldSectionSpecId + "\"",
						"id=\"S" + formSectionSpec.getSectionSpecId() + "\"");
				emailTemplate = emailTemplate.replaceAll(
						"S" + oldSectionSpecId,
						"S" + formSectionSpec.getSectionSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<S"
						+ oldSectionSpecId + ">",
						"<S" + formSectionSpec.getSectionSpecId() + ">");

				formSectionSpecsIdMap.put(oldSectionSpecId,
						formSectionSpec.getSectionSpecId());
				
				if(formSectionSpecUniqueIdsMap != null)
				{
					formSectionSpecUniqueIdsMap.put(oldSectionSpecUniqueId,
							formSectionSpec.getSectionSpecUniqueId());
				}
			}
		}
//commnted for refernece of auto create create instance
		if (formSectionFieldSpecs != null) {
			for (FormSectionFieldSpec formSectionFieldSpec : formSectionFieldSpecs) {
				long oldSectionSpecId = formSectionFieldSpec.getSectionSpecId();
				long oldSectionFieldSpecId = formSectionFieldSpec
						.getSectionFieldSpecId();
				String oldSectionFieldSpecUniqueId = formSectionFieldSpec
						.getUniqueId();

				long sectionSpecId = formSectionSpecsIdMap
						.get(formSectionFieldSpec.getSectionSpecId());
				if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
						|| formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST
						|| formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_BUTTON_GROUPING) {
					if(!Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtra()))
					{
						Long entitySpecId = entitySpecsIdMap
								.get(Long.parseLong(formSectionFieldSpec
										.getFieldTypeExtra()));
						formSectionFieldSpec.setFieldTypeExtra(entitySpecId + "");
					}
					
				}
				if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY || 
						formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOM_ENTITY
						&& !Api.isEmptyString(formSectionFieldSpec.getFieldTypeExtraCustomEntity())) {
					if (formSectionFieldSpec.getFieldTypeExtraCustomEntity() != null) {
						String fieldTypeExtraCustomEntity =  customEntitySpecIdsMap.get(formSectionFieldSpec.getFieldTypeExtraCustomEntity());
						if (fieldTypeExtraCustomEntity != null) {
							formSectionFieldSpec.setFieldTypeExtraCustomEntity(fieldTypeExtraCustomEntity);
						}
					}

				}
				formSectionFieldSpec.setUniqueId(null);

				if (formSectionFieldSpec.getSkeletonFormSectionFieldSpecId() == null) {
					formSectionFieldSpec
							.setSkeletonFormSectionFieldSpecId(oldSectionFieldSpecId);
				} else {
					formSectionFieldSpec
							.setSkeletonFormSectionFieldSpecId(formSectionFieldSpec
									.getSkeletonFormSectionFieldSpecId());
				}

				Long newFormSpecId = formSpecIdsMap.get(formSectionFieldSpec.getFormSpecId());
				if(newFormSpecId == null) {
					continue;
				}
				
				effortDao.insertFormSectionFieldSpec(formSectionFieldSpec,
						newFormSpecId, sectionSpecId);
				effortDao.updateFormSectionFieldSpecId(formSectionFieldSpec);
				FormSectionFieldSpec formSectionFieldSpec2 = getFormSectionFieldSpec(formSectionFieldSpec
						.getSectionFieldSpecId() + "");
				
				printTemplate = printTemplate.replace("id=\"T"
						+ oldSectionFieldSpecId + "\"", "id=\"T"
						+ formSectionFieldSpec.getSectionFieldSpecId() + "\"");
				emailTemplate = emailTemplate.replaceAll("T"
						+ oldSectionFieldSpecId,
						"T" + formSectionFieldSpec.getSectionFieldSpecId());

				mobilePrintTemplate = mobilePrintTemplate.replace("<S"
						+ oldSectionSpecId + "",
						"<S" + formSectionFieldSpec.getSectionSpecId() + "");
				mobilePrintTemplate = mobilePrintTemplate.replace("F"
						+ oldSectionFieldSpecId + ">", "F"
						+ formSectionFieldSpec.getSectionFieldSpecId() + ">");

				formSectionFieldSpecsIdMap.put(oldSectionFieldSpecId,
						formSectionFieldSpec.getSectionFieldSpecId());
				formSectionFieldSpecUniqueIdsMap.put(
						oldSectionFieldSpecUniqueId,
						formSectionFieldSpec2.getUniqueId());
			}
		}

		for (FormFieldSpecValidValue fieldSpecValidValue : formFieldSpecValidValues) {
			long fieldSpecId = formFieldSpecsIdMap.get(fieldSpecValidValue
					.getFieldSpecId());
			FormFieldSpec formFieldSpec = effortDao.getFormFieldSpec(fieldSpecId+"");
				
			effortDao.insertFormFieldSpecValidValue(fieldSpecId,
					fieldSpecValidValue.getValue());
		}

		for (FormSectionFieldSpecValidValue sectionFieldSpecValidValue : formSectionFieldSpecValidValues) {
			long sectionFieldSpecId = formSectionFieldSpecsIdMap
					.get(sectionFieldSpecValidValue.getSectionFieldSpecId());

			FormSectionFieldSpec formSectionFieldSpec = effortDao.getFormSectionFieldSpec(sectionFieldSpecValidValue.getSectionFieldSpecId()+"");
			
			effortDao.insertFormSectionFieldSpecValidValue(sectionFieldSpecId,
					sectionFieldSpecValidValue.getValue());
		}


		List<FieldValidation> fieldValidations = formSpecContainer.getFieldValidations();
		List<FormFieldSpecsExtra> fieldsExtra = formSpecContainer.getFieldsExtra();
		List<FormSectionFieldSpecsExtra> formSectionFieldSpecExtra = formSpecContainer.getSectionFieldsExtra();
		List<VisibilityDependencyCriteria> visibilityDependencyCriterias = formSpecContainer.getVisibilityDependencyCriterias();
		List<ListFilteringCritiria> listFilteringCritirias = formSpecContainer.getListFilteringCriterias();
		List<CustomerFilteringCritiria> customerFilteringCritirias = formSpecContainer.getCustomerFilteringCriterias();
		List<FieldValidationCritiria> fieldValidationCritirias = formSpecContainer.getFieldValidationCritirias();
		List<FieldRestrictionCritiria> fieldRestrictionCritirias = formSpecContainer.getFieldRestrictionCritirias();
		List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations = formSpecContainer.getAutoGenereteSequenceSpecConfiguarations();
		List<FormFieldsColorDependencyCriterias> formFieldColorDependencyCriterias = formSpecContainer.getFormFieldsColorDependencyCriterias();
		List<FieldSpecFilter> fieldSpecFilters = formSpecContainer.getFormFieldSpecFilters();
		List<FieldSpecFilter> sectionFieldSpecFilters = formSpecContainer.getFormSectionFieldSpecFilters();
		
		List<AutoGenereteSequenceSpecConfiguarationField> autoGenereteSequenceSpecConfiguarationFields = new ArrayList<AutoGenereteSequenceSpecConfiguarationField>();
		List<RemainderFieldsMap> remainderFieldsMaps = formSpecContainer.getRemainderFieldsMap();
		
		resolveRemainderFieldsMaps(remainderFieldsMaps,formFieldSpecsIdMap,formSpecIdsMap);
		
	    effortDao.insertRemainderFieldsMap(remainderFieldsMaps);
		
	    resolveFieldValidations(fieldValidations,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFieldValidations(fieldValidations); //  have to set values in bean
		
		resolveFormFieldsExtra(fieldsExtra, formFieldSpecsIdMap, formSectionFieldSpecsIdMap, formSpecIdsMap);
		effortDao.insertFormFieldSpecsExtra(fieldsExtra);
		
		resolveFormSectionFieldsExtra(formSectionFieldSpecExtra, formFieldSpecsIdMap, formSectionFieldSpecsIdMap, formSpecIdsMap);
		effortDao.insertFormSectionFieldSpecsExtra(formSectionFieldSpecExtra);
		
		resolveListFilterCritrias(listFilteringCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap,entityFieldSpecsIdMap);
		effortDao.insertListFilterCritria(listFilteringCritirias);
		
		resolveVisibilityDependencyCritirias(visibilityDependencyCriterias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertVisibilityDependencyCritirias(visibilityDependencyCriterias);
		
		resolveCustomerFilterCritria(customerFilteringCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertCustomerFilterCritria(customerFilteringCritirias);
		
		resolveFieldValidationCritria(fieldValidationCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFieldValidationCritria(fieldValidationCritirias);
		
		resolveFieldRestrictionCritiria(fieldRestrictionCritirias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFieldRestrictionCritria(fieldRestrictionCritirias);
		
		resolveFormFieldsColorDependencyCriterias(formFieldColorDependencyCriterias,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFormFieldsColorDependencyCriterias(formFieldColorDependencyCriterias);
		if(autoGenereteSequenceSpecConfiguarations!=null && !autoGenereteSequenceSpecConfiguarations.isEmpty())
		{
			resolveAutoGeneratedSequenceSpecConfigurations(autoGenereteSequenceSpecConfiguarations,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
			effortDao.insertAutoGeneratedSequenceSpecConfigurations(autoGenereteSequenceSpecConfiguarations);
		}
		if(autoGenereteSequenceSpecConfiguarationFields!=null && !autoGenereteSequenceSpecConfiguarationFields.isEmpty())
		{
			resolveAutoGenereteSequenceSpecConfiguarationFields(autoGenereteSequenceSpecConfiguarationFields,formFieldSpecUniqueIdsMap,formSpecUniqueIdsMap);
			effortDao.insertAutoGenereteSequenceSpecConfiguarationFields(autoGenereteSequenceSpecConfiguarationFields);
		}
		
		resolveFormFieldSpecFilters(fieldSpecFilters,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFormFieldSpecFilters(fieldSpecFilters);
		resolveFormSectionFieldSpecFilters(fieldSpecFilters,formFieldSpecsIdMap,formSectionFieldSpecsIdMap,formSpecIdsMap);
		effortDao.insertFormSectionFieldSpecFilters(sectionFieldSpecFilters);
		
	}

	private void resolveFormFieldGroupSpec(List<FormFieldGroupSpec> formFieldGroupSpecs,
			Map<Long, Long> formSpecIdsMap) {
		try {
				Iterator<FormFieldGroupSpec> formPageSpecsIterator = formFieldGroupSpecs.iterator();
				while (formPageSpecsIterator.hasNext()) {
					FormFieldGroupSpec formFieldGroupSpec = (FormFieldGroupSpec) formPageSpecsIterator.next();
					Long newFormSpecId = formSpecIdsMap.get(formFieldGroupSpec.getFormSpecId());
					if(newFormSpecId != null) {
						formFieldGroupSpec.setFormSpecId(newFormSpecId);
					}else {
						formPageSpecsIterator.remove();
					}
				}
			} catch (Exception e) {
				formFieldGroupSpecs = new ArrayList<>();
			}
		}

	private void resolveFormPageSpecs(List<FormPageSpec> formPageSpecs, Map<Long, Long> formSpecIdsMap) {
		try {
			Iterator<FormPageSpec> formPageSpecsIterator = formPageSpecs.iterator();
			while (formPageSpecsIterator.hasNext()) {
				FormPageSpec formPageSpec = (FormPageSpec) formPageSpecsIterator.next();
				Long newFormSpecId = formSpecIdsMap.get(formPageSpec.getFormSpecId());
				if(newFormSpecId != null) {
					formPageSpec.setFormSpecId(newFormSpecId);
				}else {
					formPageSpecsIterator.remove();
				}
			}
		} catch (Exception e) {
			formPageSpecs = new ArrayList<>();
		}
		
	}

	public void exportEntityAndCustomEntitySpecs(FormSpecContainer formSpecContainer,boolean exportData) {
		try {
			List<FormFieldSpec> formFieldSpecs = formSpecContainer.getFields();
			List<FormSectionFieldSpec> formSectionFieldSpecs = formSpecContainer.getSectionFields();
			Set<String> entitySpecIds = new HashSet<String>();
			Set<String> customEntitySpecIds = new HashSet<String>();
			Set<String> innerFormSpecUniqueIds = new HashSet<String>();
			if (formFieldSpecs != null && formFieldSpecs.size() > 0) {
				for (FormFieldSpec formFieldSpec : formFieldSpecs) {
					if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
									|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_BUTTON_GROUPING
							|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST	){
						if (!Api.isEmptyString(formFieldSpec.getFieldTypeExtra())) {
							entitySpecIds.add(formFieldSpec.getFieldTypeExtra());
						}

					}
					//grk 2
					
					if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY ||formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOM_ENTITY)
					{
						if (!Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {

							customEntitySpecIds.add(formFieldSpec.getFieldTypeExtraCustomEntity());
						}

					}
				}
				
			}
			
			if (formSectionFieldSpecs != null && formSectionFieldSpecs.size() > 0) {
				for (FormSectionFieldSpec formFieldSpec : formSectionFieldSpecs) {
					if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST
							|| formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_LIST
							||formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_BUTTON_GROUPING) {
						if (!Api.isEmptyString(formFieldSpec.getFieldTypeExtra())) {
							entitySpecIds.add(formFieldSpec.getFieldTypeExtra());
						}

					}
					if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY) {
						if (!Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {

							customEntitySpecIds.add(formFieldSpec.getFieldTypeExtraCustomEntity());
						}

					}
				}
			}
			
			Set<String> subEntitySpecIds = new HashSet<String>();
			Set<String> subCustomEntitySpecIds = new HashSet<String>();
			// NEW: Collect subInnerFormSpecUniqueIds for nested forms in entity fields
	        Set<String> subInnerFormSpecUniqueIds = new HashSet<String>();
			if (entitySpecIds != null && entitySpecIds.size() > 0) {
				List<EntitySpec> entitySpecs = effortDao.getEntitySpecsIn(Api.toCSV(entitySpecIds));
				if (entitySpecs != null && entitySpecs.size() > 0) {
					formSpecContainer.setEntitySpecs(entitySpecs);

					List<Long> specIds = Api.listToLongList(entitySpecs, "entitySpecId");

					List<EntityFieldSpec> entityFieldSpecs = effortDao.getEntityFieldSpecs(Api.toCSV(specIds));
					formSpecContainer.setEntityFields(entityFieldSpecs);
					for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
						if(entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_LIST || entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_BUTTON_GROUPING)
						 {
							if(!entitySpecIds.contains(entityFieldSpec.getFieldTypeExtra())){
							subEntitySpecIds.add(entityFieldSpec.getFieldTypeExtra());
							}
						}
						if(entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY ||
								entityFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOM_ENTITY) {
							if(!customEntitySpecIds.contains(entityFieldSpec.getFieldTypeExtraCustomEntity())){
								subCustomEntitySpecIds.add(entityFieldSpec.getFieldTypeExtraCustomEntity());
							}
						}
					}
					
					List<EntitySectionSpec> entitySectionSpecs = effortDao.getEntitySectionSpecForIn(Api.toCSV(specIds));
					formSpecContainer.setEntitySections(entitySectionSpecs);

					List<EntitySectionFieldSpec> entitySectionFieldSpecs = effortDao
							.getEntitySectionFieldSpecs(Api.toCSV(specIds));
					formSpecContainer.setEntitySectionFields(entitySectionFieldSpecs);
				}
				
				
				if (exportData) {
					List<Entity> entities = effortDao.getEntitiesByEntitySpecIds(Api.toCSV(entitySpecIds));

					if (entities != null && entities.size() > 0) {
						formSpecContainer.setEntities(entities);
						List<EntityField> entityFields = effortDao.getEntityFieldByEntityIn(entities);

						formSpecContainer.setEntityFieldsData(entityFields);

						List<EntitySectionField> sectionFields = effortDao.getEntitySectionFieldsByEntityIn(entities);

						formSpecContainer.setEntitySectionFieldsData(sectionFields);
					}
				}

			}
			
			if (subEntitySpecIds != null && subEntitySpecIds.size() > 0) {
				List<EntitySpec> entitySpecs = effortDao.getEntitySpecsIn(Api.toCSV(subEntitySpecIds));
				if (entitySpecs != null && entitySpecs.size() > 0) {
					formSpecContainer.getEntitySpecs().addAll(entitySpecs);

					List<Long> specIds = Api.listToLongList(entitySpecs, "entitySpecId");

					List<EntityFieldSpec> entityFieldSpecs = effortDao.getEntityFieldSpecs(Api.toCSV(specIds));
					formSpecContainer.getEntityFields().addAll(entityFieldSpecs);
				}
				
				
				if (exportData) {
					List<Entity> entities = effortDao.getEntitiesByEntitySpecIds(Api.toCSV(subEntitySpecIds));

					if (entities != null && entities.size() > 0) {
						formSpecContainer.getEntities().addAll(entities);
						List<EntityField> entityFields = effortDao.getEntityFieldByEntityIn(entities);

						formSpecContainer.getEntityFieldsData().addAll(entityFields);
					}
				}

			}
			
			if (customEntitySpecIds != null && customEntitySpecIds.size() > 0) {
				List<CustomEntitySpec> customEntitySpecs = effortDao.getCustomEntitySpecsByIds(Api.toCSV(customEntitySpecIds));
				if (customEntitySpecs != null && customEntitySpecs.size() > 0) {
					formSpecContainer.setCustomEntitySpecs(customEntitySpecs);
					Set<String> formSpecUniqueIds = new HashSet<>();
					for(CustomEntitySpec customEntitySpec : customEntitySpecs) {
						formSpecUniqueIds.add(customEntitySpec.getFormSpecUniqueId());
					}
					getExportFormSpecData(formSpecUniqueIds,formSpecContainer);
				}
			}
			if (subCustomEntitySpecIds != null && subCustomEntitySpecIds.size() > 0) {
				List<CustomEntitySpec> customEntitySpecs = effortDao.getCustomEntitySpecsByIds(Api.toCSV(subCustomEntitySpecIds));
				if (customEntitySpecs != null && customEntitySpecs.size() > 0) {
					formSpecContainer.getCustomEntitySpecs().addAll(customEntitySpecs);
					Set<String> formSpecUniqueIds = new HashSet<>();
					for(CustomEntitySpec customEntitySpec : customEntitySpecs) {
						formSpecUniqueIds.add(customEntitySpec.getFormSpecUniqueId());
					}
					getExportFormSpecData(formSpecUniqueIds,formSpecContainer);
				}
			}
		}catch(Exception e) {
			
		}
	}

	public void copyEntitySpecsFromSqlite(Map<Long, Long> entitySpecsIdMap, Map<Long, Long> entityFieldSpecsIdMap,
			Map<Long, Long> entitySectionSpecsIdMap, Map<Long, Long> entitySectionFieldSpecsIdMap,
			FormSpecContainer formSpecContainer,WebUser webUser) {
		
		List<EntitySpec> entitySpecs = formSpecContainer.getEntitySpecs();
		List<EntityFieldSpec> entityFieldSpecs = formSpecContainer.getEntityFields();
		List<EntitySectionSpec> entitySectionSpecs = formSpecContainer.getEntitySections();
		List<EntitySectionFieldSpec> entitySectionFieldSpecs = formSpecContainer.getEntitySectionFields();
		
		
		List<Entity> entities = formSpecContainer.getEntities();
		List<EntityField> entityFields = formSpecContainer.getEntityFieldsData();
		List<EntitySectionField> entitySectionFieldsData = formSpecContainer.getEntitySectionFieldsData();
		
		
		Map<Long,Boolean> entitySpecsMap = new HashMap<Long,Boolean>();
		
		for (EntitySpec entitySpec : entitySpecs) {
				long oldEntitySpecId = entitySpec.getEntitySpecId();

				EntitySpec existingEntitySpec = effortDao.getEntitySpecBySkeletonSpecId(oldEntitySpecId,webUser.getCompanyId());
				if(existingEntitySpec != null) {
					entitySpecsMap.put(oldEntitySpecId, true);
					
					entitySpecsIdMap.put(oldEntitySpecId,
							existingEntitySpec.getEntitySpecId());
					
					List<EntityFieldSpec> existingEntityFieldSpecs = effortDao.getEntityFieldSpecs(existingEntitySpec.getEntitySpecId());
					for(EntityFieldSpec entityFieldSpec : existingEntityFieldSpecs) {
						entityFieldSpecsIdMap.put(entityFieldSpec.getSkeletonEntityFieldSpecId(),
								entityFieldSpec.getEntityFieldSpecId());
					}
					
				}else {
					entitySpec.setParentId(oldEntitySpecId);
					entitySpec.setSkeletonEntitySpecId(oldEntitySpecId);
					entitySpec.setIsSystemDefined(0);
					effortDao.insertEntitySpec(entitySpec, webUser.getCompanyId(),
							webUser.getEmpId());
					entitySpecsIdMap.put(oldEntitySpecId,
							entitySpec.getEntitySpecId());
				}
		}

		if (entityFieldSpecs != null) {
			for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
				long oldEntityFieldSpecId = entityFieldSpec
						.getEntityFieldSpecId();
				if(entitySpecsMap.get(entityFieldSpec.getEntitySpecId()) == null){
					long entitySpecId = entitySpecsIdMap.get(entityFieldSpec
							.getEntitySpecId());
						entityFieldSpec
								.setSkeletonEntityFieldSpecId(oldEntityFieldSpecId);
					effortDao.insertEntityFieldSpec(entityFieldSpec, entitySpecId);
					entityFieldSpecsIdMap.put(oldEntityFieldSpecId,
							entityFieldSpec.getEntityFieldSpecId());
					}
			}

		}
		
		if (entitySectionSpecs != null) {
			for (EntitySectionSpec entitySectionSpec : entitySectionSpecs) {
				long oldEntitySectionSpecId = entitySectionSpec
						.getSectionSpecId();
				if(entitySpecsMap.get(entitySectionSpec.getEntitySpecId()) == null){
				long entitySpecId = entitySpecsIdMap.get(entitySectionSpec
						.getEntitySpecId());
				entitySectionSpec.setInitialEntitySectionSpecId(null);
				entitySectionSpec
							.setSkeletonEntitySectionSpecId(oldEntitySectionSpecId);
				effortDao.insertEntitySectionSpec(entitySectionSpec, entitySpecId,webUser.getCompanyId());
				entitySectionSpecsIdMap.put(oldEntitySectionSpecId,
						entitySectionSpec.getSectionSpecId());
				}
			}

		}
		
		if (entitySectionFieldSpecs != null) {
			for (EntitySectionFieldSpec entitySectionFieldSpec : entitySectionFieldSpecs) {
                long oldEntitySectionFieldSpecId = entitySectionFieldSpec
                                .getSectionSpecId();
                if(entitySpecsMap.get(entitySectionFieldSpec.getEntitySpecId()) == null){
                long entitySpecId = entitySpecsIdMap.get(entitySectionFieldSpec
                                .getEntitySpecId());
                long sectionSpecId = entitySpecsIdMap.get(entitySectionFieldSpec.getSectionSpecId());
                entitySectionFieldSpec.setSectionSpecId(sectionSpecId);
                entitySectionFieldSpec.setInitialEntitySectionFieldSpecId(null);
                entitySectionFieldSpec
                                        .setSkeletonEntitySectionFieldSpecId(oldEntitySectionFieldSpecId);
                effortDao.insertEntitySectionFieldSpec(entitySectionFieldSpec, entitySpecId,webUser.getCompanyId());
                entitySectionSpecsIdMap.put(oldEntitySectionFieldSpecId,
                                entitySectionFieldSpec.getSectionFieldSpecId());
                }
        }

		}

		
		// 
		Map<Long,Long> entityIdsMap = new HashMap<Long,Long>();
		
		/*for(Entity entity : entities) {
		if(entitySpecsMap.get(entity.getEntitySpecId()) == null){
			Long oldEntityId = entity.getEntityId();
			
			Long entitySpecId = entitySpecsIdMap.get(entity
					.getEntitySpecId());
			entity.setEntitySpecId(entitySpecId);
			entity.setClientSideId(null);
			long entityId = effortDao.insertEntity(entity,webUser.getCompanyId(),webUser.getEmpId(),null);
			entityIdsMap.put(oldEntityId, entityId);
		}
	}*/
	for (Entity entity : entities) {
	    if (entitySpecsMap.get(entity.getEntitySpecId()) == null) {
	        Long oldEntityId = entity.getEntityId();
	        Long entitySpecId = entitySpecsIdMap.get(entity.getEntitySpecId());
	        if (entitySpecId == null) {
	            LOGGER.warn("Skipping entity because entitySpecId mapping is null -> entity.getEntitySpecId(): {}, map keys: {}",
	                        entity.getEntitySpecId(), entitySpecsIdMap.keySet());
	            continue;
	        }
	        entity.setEntitySpecId(entitySpecId);
	        entity.setClientSideId(null);

	        long entityId = effortDao.insertEntity(
	            entity, webUser.getCompanyId(), webUser.getEmpId(), null
	        );
	        entityIdsMap.put(oldEntityId, entityId);
	    }
	}
		
		for(EntityField entityField : entityFields) {
			if(entityIdsMap.get(entityField.getEntityId()) != null){
				Long entityId = entityIdsMap.get(entityField.getEntityId());
				long entitySpecId = entitySpecsIdMap.get(entityField
						.getEntitySpecId());
				entityField.setEntityId(entityId);
				entityField.setEntitySpecId(entitySpecId);
				entityField.setClientSideId(null);
				if(entityFieldSpecsIdMap.get(entityField.getEntityFieldSpecId()) != null) {
					entityField.setEntityFieldSpecId(entityFieldSpecsIdMap.get(entityField.getEntityFieldSpecId()));
				}
				 effortDao.insertEntityField(entityField);
			}
		}
		
		
		
	}

	public void copyWorkSpecTemplate(WebUser webUser, WorkSpecContainer workSpecContainer,
			Map<Long, Long> entitySpecsIdMap, Map<Long, EntitySpec> entitySpecMap, Map<Long, Long> formSpecIdsMap,
			Map<String, String> formSpecUniqueIdsMap, Map<String, String> formFieldSpecUniqueIdsMap,
			Map<String, String> formSectionFieldSpecUniqueIdsMap, Map<Long, Long> formFieldSpecsIdMap,
			Map<Long, Long> formSectionFieldSpecsIdMap, Map<Long, Long> entityFieldSpecsIdMap,
			Map<String, String> formSectionSpecUniqueIdsMap, Map<Long, Long> formSectionSpecsIdMap,
			Map<String, String> customEntitySpecIdsMap, Map<Long, CustomEntitySpec> customEntitySpecsMap,
			Map<String, String> innerformSpecUniqueIdsMap) {
		
		Map<Long, Long> workSpecsCopied = new HashMap<Long, Long>();
		Map<Long, Long> workActionSpecsCopied = new HashMap<Long, Long>();
		
		List<WorkSpec> workSpecs = workSpecContainer.getWorkSpecs();
		for(WorkSpec workSpec : workSpecs) {
			
			Long newWorkSpecId = null;
			Long oldWorkSpecId = null;
			
			WorkSpec newWorkSpec = new WorkSpec();
			newWorkSpec = workSpec;
			String uniqueId = workSpec.getFormSpecUniqueId();
			oldWorkSpecId = workSpec.getWorkSpecId();
			

			String newUniqueId = null;
			if (!Api.isEmptyString(uniqueId))
				newUniqueId = formSpecUniqueIdsMap.get(uniqueId);
			if (newUniqueId == null && !Api.isEmptyString(uniqueId)) {

			}
			newWorkSpec.setFormSpecUniqueId(newUniqueId);

			newWorkSpec.setCopiedFrom(workSpec.getWorkSpecId());
			newWorkSpec.setCopiedFromTitle(workSpec.getWorkSpecTitle());
			newWorkSpec.setIsSystemDefined(0);
			newWorkSpec.setSkeletonWorkSpecId(workSpec.getWorkSpecId());
			newWorkSpec.setWorkSpecTitle(workSpec.getWorkSpecTitle());
			newWorkSpec.setWorkSpecDescription(workSpec
						.getWorkSpecDescription());

			newWorkSpec.setProductId(workSpec.getProductId());
			newWorkSpecId = effortDao.insertIntoWorkSpecs(newWorkSpec,
					webUser.getCompanyId(), webUser.getEmpId(), newWorkSpec.getFormSpecUniqueId());
			workSpecsCopied.put(oldWorkSpecId, newWorkSpecId);
			LOGGER.info(" insertIntoWorkSpecs done ... "+newWorkSpecId);
		}
		
		List<WorkActionSpec> workActionSpecs = workSpecContainer.getWorkActionSpecs();
		for (WorkActionSpec workActionSpecOld : workActionSpecs) {

			String uniqueId = workActionSpecOld.getFormSpecUniqueId();
			String newUniqueId = null;
			Long workActionSpecId = workActionSpecOld.getWorkActionSpecId();

			if (!Api.isEmptyString(uniqueId))
				newUniqueId = formSpecUniqueIdsMap.get(uniqueId);
			
			Long newWorkSpecId  = workSpecsCopied.get(workActionSpecOld.getWorkSpecId());
			
			if(newWorkSpecId != null) {
				WorkActionSpec workActionSpecNew = workActionSpecOld;
				workActionSpecNew.setWorkSpecId(newWorkSpecId);
				workActionSpecNew.setCreatedBy(webUser.getEmpId());
				workActionSpecNew.setModifiedBy(webUser.getEmpId());
				workActionSpecNew.setFormSpecUniqueId(newUniqueId);

				workActionSpecNew.setSkeletonWorkActionSpecId(workActionSpecOld.getWorkActionSpecId());

				Long newWorkActionSpecId = null;
				newWorkActionSpecId = effortDao.insertIntoWorkActionSpecs(workActionSpecNew, newWorkSpecId, webUser.getEmpId());
				workActionSpecsCopied.put(workActionSpecId, newWorkActionSpecId);
			}

			
		}
		
		List<NextActionSpec> nextActionSpecs = workSpecContainer.getNextActionSpecs();
		resolveNextActionSpecs(nextActionSpecs,workActionSpecsCopied,workSpecsCopied);
		for(NextActionSpec nextActionSpec : nextActionSpecs) {
			effortDao.insertNextActionSpec(nextActionSpec);
		}
		
		List<WorkActionSpecConditions> workActionSpecConditions = workSpecContainer.getWorkActionSpecConditions();
		for (WorkActionSpecConditions workActionSpecCondition : workActionSpecConditions) {
			workActionSpecCondition
					.setActionSpecId(workActionSpecsCopied.get(workActionSpecCondition.getActionSpecId()));
			if(workActionSpecsCopied.get(workActionSpecCondition.getNextActionSpecId()) != null) {
				workActionSpecCondition
				.setNextActionSpecId(workActionSpecsCopied.get(workActionSpecCondition.getNextActionSpecId()));
			}
			Long newWorkSpecId  = workSpecsCopied.get(workActionSpecCondition.getWorkSpecId());
			effortDao.insertWorkActionSpecCondition(workActionSpecCondition, newWorkSpecId);
		}
		
		List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList = workSpecContainer.getWorkActionSpecVisibilityConditions();
		
		for (WorkActionSpecVisibilityCondition workActionSpecVisibilityCondition : workActionSpecVisibilityConditionList) {
			Long newWorkSpecId  = workSpecsCopied.get(workActionSpecVisibilityCondition.getWorkSpecId());
			workActionSpecVisibilityCondition.setWorkSpecId(newWorkSpecId);
			workActionSpecVisibilityCondition
			.setWorkActionSpecId(workActionSpecsCopied.get(workActionSpecVisibilityCondition.getWorkActionSpecId()));
			if (workActionSpecsCopied != null) {
				workActionSpecVisibilityCondition.setConditionalWorkActionSpecId(workActionSpecsCopied
						.get(workActionSpecVisibilityCondition.getConditionalWorkActionSpecId()));
				if (workActionSpecVisibilityCondition.getFieldCondtionSourceActionSpecId() != null
						&& workActionSpecVisibilityCondition.getFieldCondtionSourceActionSpecId() > 0) {
					workActionSpecVisibilityCondition.setFieldCondtionSourceActionSpecId(workActionSpecsCopied
							.get(workActionSpecVisibilityCondition.getFieldCondtionSourceActionSpecId()));
				}

			}
			if (workActionSpecVisibilityCondition.getTargetFieldExpression().startsWith("F")) {
				workActionSpecVisibilityCondition.setFieldType(
						Long.parseLong(WorkActionSpecVisibilityCondition.FIELD_IS_FORM_FIELD + ""));
			} else if (workActionSpecVisibilityCondition.getTargetFieldExpression().startsWith("SF")) {
				workActionSpecVisibilityCondition.setFieldType(
						Long.parseLong(WorkActionSpecVisibilityCondition.FIELD_IS_SECTION_FIELD + ""));
			} else if (workActionSpecVisibilityCondition.getTargetFieldExpression().startsWith("WA")) {
				workActionSpecVisibilityCondition.setFieldType(
						Long.parseLong(WorkActionSpecVisibilityCondition.FIELD_IS_WORK_ACTION + ""));
				String array[] = workActionSpecVisibilityCondition.getTargetFieldExpression().split("WA");
				workActionSpecVisibilityCondition.setWorkSpecId(Long.parseLong(array[1]));
				if (workActionSpecsCopied != null) {
					workActionSpecVisibilityCondition
							.setWorkSpecId(workActionSpecsCopied.get(Long.parseLong(array[1])));
					workActionSpecVisibilityCondition
							.setTargetFieldExpression("WA" + workActionSpecVisibilityCondition.getWorkSpecId());
				}
			} else if (workActionSpecVisibilityCondition.getTargetFieldExpression().startsWith("ST")) {
				workActionSpecVisibilityCondition
						.setFieldType(Long.parseLong(WorkActionSpecVisibilityCondition.FIELD_IS_SUB_TASK + ""));
				String array[] = workActionSpecVisibilityCondition.getTargetFieldExpression().split("ST");
				workActionSpecVisibilityCondition.setWorkSpecId(Long.parseLong(array[1]));
			}
		}
		effortDao.insertWorkActionSpecVisibilityCondition(workActionSpecVisibilityConditionList);
		
		List<WorkActionSpecEndCondition> workActionSpecEndConditionList = workSpecContainer.getWorkActionSpecEndConditions();

		Iterator<WorkActionSpecEndCondition> iter = workActionSpecEndConditionList.iterator();
		while (iter.hasNext()) {
			WorkActionSpecEndCondition workActionSpecEndCondition = iter.next();
			Long newWorkSpecId  = workSpecsCopied.get(workActionSpecEndCondition.getWorkSpecId());
			Long newWorkActionSpecId = workActionSpecsCopied.get(workActionSpecEndCondition.getWorkActionSpecId());
			if(newWorkActionSpecId == null) {
				iter.remove();
				continue;
			}
			if (workActionSpecEndCondition.getCondition() != null
					&& workActionSpecEndCondition.getConjunction() != null) {
				workActionSpecEndCondition.setWorkSpecId(newWorkSpecId);
				workActionSpecEndCondition.setWorkActionSpecId(newWorkActionSpecId);
				if (workActionSpecEndCondition.getTargetFieldExpression().startsWith("F")) {
					workActionSpecEndCondition.setIsSectionField(0);
				} else if (workActionSpecEndCondition.getTargetFieldExpression().startsWith("SF")) {
					workActionSpecEndCondition.setIsSectionField(1);
				}
			}
		}

		if (workActionSpecEndConditionList != null && workActionSpecEndConditionList.size() > 0) {
			effortDao.insertWorkActionSpecEndCondition(workActionSpecEndConditionList);
		}
		
		List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations = workSpecContainer.getWorkActionVisibilityConfigurations();
		
		for(WorkActionVisibilityConfiguration workActionVisibilityConfiguration : workActionVisibilityConfigurations) {
			Long newWorkSpecId  = workSpecsCopied.get(workActionVisibilityConfiguration.getWorkSpecId());
			Long newWorkActionSpecId = workActionSpecsCopied.get(workActionVisibilityConfiguration.getWorkActionSpecId());
			workActionVisibilityConfiguration.setWorkSpecId(newWorkSpecId);
			workActionVisibilityConfiguration.setWorkActionSpecId(newWorkActionSpecId);
			workActionVisibilityConfiguration.setCreatedBy(webUser.getEmpId());
			workActionVisibilityConfiguration.setModifiedBy(webUser.getEmpId());
			workActionVisibilityConfiguration.setCompanyId(webUser.getCompanyId());
		}
		effortDao.insertOrUpdateWorkActionVisibilityConfiguration(workActionVisibilityConfigurations);
		
		Map<Long, Long> workFormAutoFillIdsMap = new HashMap<Long,Long>();
		
		// Form Fields to Section Auto copy Clone
		List<WorkFormAutoFill> workFormAutoFillConfigurations = workSpecContainer.getWorkFormAutoFillStageConfig();
		List<WorkFormAutoFillField> workFormAutoFillFields = workSpecContainer.getWorkFormAutoFillFieldMapping();
		List<WorkFormAutoFillField> workFormAutoFillFieldsList = new ArrayList<WorkFormAutoFillField>();
		if(workFormAutoFillConfigurations != null && workFormAutoFillConfigurations.size() > 0)
		{
			for(WorkFormAutoFill workFormAutoFill : workFormAutoFillConfigurations)
			{
				Long oldAutoFillId = workFormAutoFill.getWorkFormAutoFillId();
				workFormAutoFill.setFormSpecUniqueId(formSpecUniqueIdsMap.get(workFormAutoFill.getFormSpecUniqueId()));
				workFormAutoFill.setWorkActionSpecId(workActionSpecsCopied.get(workFormAutoFill.getWorkActionSpecId()));
				if(!Api.isEmptyString(workFormAutoFill.getFormSpecUniqueId()))
				{
					effortDao.createWorkAutoFillFormConfig(workFormAutoFill, webUser.getEmpId());
					workFormAutoFillIdsMap.put(oldAutoFillId,workFormAutoFill.getWorkFormAutoFillId());
				}
				
				
			}
			for(WorkFormAutoFillField workFormAutoFillField : workFormAutoFillFields)
			{
				if (workFormAutoFillIdsMap.get(workFormAutoFillField.getWorkFormAutoFillId()) != null) {
					workFormAutoFillField.setWorkFormAutoFillId(
							workFormAutoFillIdsMap.get(workFormAutoFillField.getWorkFormAutoFillId()));
				} else {
					workFormAutoFillField.setWorkFormAutoFillId(-1);
				}

				if (workFormAutoFillField.getSourceSpecId() != null) {
					workFormAutoFillField
							.setSourceSpecId(workActionSpecsCopied.get(workFormAutoFillField.getSourceSpecId()));
				}
				if (!Api.isEmptyString(workFormAutoFillField.getWorkFieldSpecUniqueId())) {
					if(formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getWorkFieldSpecUniqueId())!=null) {
						workFormAutoFillField.setWorkFieldSpecUniqueId(
								formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getWorkFieldSpecUniqueId()));
					}else {
						workFormAutoFillField.setWorkFieldSpecUniqueId(workFormAutoFillField.getWorkFieldSpecUniqueId());
					}
				}
				if (!Api.isEmptyString(workFormAutoFillField.getSourceSpecUniqueId())) {
					workFormAutoFillField.setSourceSpecUniqueId(
							formSpecUniqueIdsMap.get(workFormAutoFillField.getSourceSpecUniqueId()));
				}
				if (workFormAutoFillField.getFieldType() == 2) {
					workFormAutoFillField.setFieldSpecUniqueId(
							formSectionFieldSpecUniqueIdsMap.get(workFormAutoFillField.getFieldSpecUniqueId()));
				} else if(workFormAutoFillField.getFieldType() == 1){
					workFormAutoFillField.setFieldSpecUniqueId(
							formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getFieldSpecUniqueId()));
				}
				if (workFormAutoFillField.getSourceType() != null && workFormAutoFillField.getSourceType() == 1) {
					Long newWorkSpecId  = workSpecsCopied.get(workFormAutoFillField.getSourceSpecId());
					workFormAutoFillField.setSourceSpecId(newWorkSpecId);
				}

				if (workFormAutoFillField.getWorkFormAutoFillId() > 0) {
					workFormAutoFillFieldsList.add(workFormAutoFillField);
				}

			}
			
			effortDao.insertWorkAutoFillFormFields(workFormAutoFillFieldsList);
			
			
		}
		// Section to Section Auto copy Clone
		
		
		Map<Long, Long> formAutoFillSectionConfigurationIdsMap = new HashMap<Long,Long>();
	
			List<FormAutoFillSectionConfiguration> formAutoFillSectionConfigurations = workSpecContainer.getFormAutoFillSectionConfiguration();
			List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration = workSpecContainer.getFormAutoFillSectionFieldsConfiguration();
			
			if (formAutoFillSectionConfigurations != null && formAutoFillSectionConfigurations.size() > 0) {
				for(FormAutoFillSectionConfiguration formAutoFillSectionConfiguration : formAutoFillSectionConfigurations)
				{
					long oldFormAutoFillSectionConfigurationId = formAutoFillSectionConfiguration.getFormAutoFillSectionConfigurationId();
					formAutoFillSectionConfiguration.setSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(formAutoFillSectionConfiguration.getSectionSpecUniqueId()));
					formAutoFillSectionConfiguration.setSourceActionSpecId(workActionSpecsCopied.get(formAutoFillSectionConfiguration.getSourceActionSpecId()));
					formAutoFillSectionConfiguration.setSourceSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(formAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()));
					formAutoFillSectionConfiguration.setWorkFormAutoFillId(workFormAutoFillIdsMap.get(formAutoFillSectionConfiguration.getWorkFormAutoFillId()));
					if (!Api.isEmptyString(formAutoFillSectionConfiguration.getSectionSpecUniqueId()) && !Api
							.isEmptyString(formAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()) && formAutoFillSectionConfiguration.getSourceActionSpecId() != null && formAutoFillSectionConfiguration.getWorkFormAutoFillId() != null) {
						long sectionConfigId = effortDao.insertWorkAutoFillsectionSpecs(
								formAutoFillSectionConfiguration,
								formAutoFillSectionConfiguration.getWorkFormAutoFillId());
						formAutoFillSectionConfigurationIdsMap.put(oldFormAutoFillSectionConfigurationId,
								sectionConfigId);
					}
					
					
				}
				
				if(formAutoFillSectionFieldsConfiguration != null && formAutoFillSectionFieldsConfiguration.size() > 0)
				{
					List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfigurationsList = new ArrayList<FormAutoFillSectionFieldsConfiguration>();
					for(FormAutoFillSectionFieldsConfiguration obj : formAutoFillSectionFieldsConfiguration)
					{
						obj.setFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getFieldSpecUniqueId()));
						obj.setSourceFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getSourceFieldSpecUniqueId()));
						obj.setFormAutoFillSectionConfigurationId(formAutoFillSectionConfigurationIdsMap.get(obj.getFormAutoFillSectionConfigurationId()));
						obj.setWorkFormAutoFillId(workFormAutoFillIdsMap.get(obj.getWorkFormAutoFillId()));
						if(!Api.isEmptyString(obj.getFieldSpecUniqueId()) && !Api.isEmptyString(obj.getSourceFieldSpecUniqueId()) && obj.getFormAutoFillSectionConfigurationId() != null && obj.getWorkFormAutoFillId() != null)
						{
							formAutoFillSectionFieldsConfigurationsList.add(obj);
						}
						
					}
					effortDao.insertIntoFormAutoFillSectionFieldConfigurations(formAutoFillSectionFieldsConfigurationsList);
				}
				
			}
			
			List<FormToWorkAutoFill> formToWorkAutoFillConigurations = workSpecContainer.getFormToWorkAutoFillStageConfig();
			Map<Long, Long> formToWorkAutoFillIds = new HashMap<Long,Long>();
			
			if(formToWorkAutoFillConigurations !=  null && formToWorkAutoFillConigurations.size() > 0)
			{
				List<FormToWorkAutoFillField> formToWorkAutoFillFields = workSpecContainer.getFormToWorkAutoFillFieldMapping();
				
				for(FormToWorkAutoFill formToWorkAutoFill : formToWorkAutoFillConigurations)
				{
					Long oldFormToWorkAutoFillId = formToWorkAutoFill.getFormToWorkAutoFillId();
					Long newWorkSpecId  = workSpecsCopied.get(formToWorkAutoFill.getWorkSpecId());
					formToWorkAutoFill.setWorkSpecId(newWorkSpecId);
					formToWorkAutoFill.setWorkActionSpecId(workActionSpecsCopied.get(formToWorkAutoFill.getWorkActionSpecId()));
					formToWorkAutoFill.setWorkActionFormSpecUniqueId(formSpecUniqueIdsMap.get(formToWorkAutoFill.getWorkActionFormSpecUniqueId()));
					effortDao.createFormToWorkAutoFillFormConfig(formToWorkAutoFill, webUser.getEmpId());
					formToWorkAutoFillIds.put(oldFormToWorkAutoFillId, formToWorkAutoFill.getFormToWorkAutoFillId());
				}
				for(FormToWorkAutoFillField formToWorkAutoFillField : formToWorkAutoFillFields)
				{
					formToWorkAutoFillField.setFormToWorkAutoFillId(
							formToWorkAutoFillIds.get(formToWorkAutoFillField.getFormToWorkAutoFillId()));
					if (formToWorkAutoFillField.getFieldType() == 2) {
						if (formSectionFieldSpecUniqueIdsMap
								.get(formToWorkAutoFillField.getFormFieldSpecUniqueId()) != null) {
							formToWorkAutoFillField.setFormFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap
									.get(formToWorkAutoFillField.getFormFieldSpecUniqueId()));
						}

					} else if (formToWorkAutoFillField.getFieldType() == 1) {
						if (formFieldSpecUniqueIdsMap.get(formToWorkAutoFillField.getFormFieldSpecUniqueId()) != null) {
							formToWorkAutoFillField.setFormFieldSpecUniqueId(
									formFieldSpecUniqueIdsMap.get(formToWorkAutoFillField.getFormFieldSpecUniqueId()));
						}

					}

					formToWorkAutoFillField.setWorkFieldSpecUniqueId(
							formFieldSpecUniqueIdsMap.get(formToWorkAutoFillField.getWorkFieldSpecUniqueId()));
				}
				effortDao.insertFormToWorkAutoFillFormFields(formToWorkAutoFillFields);
				
			}
			
			List<WorkAttachmentAutoFill> workAttachmentFormAutoFillStageConfig = workSpecContainer.getWorkAttachmentFormAutoFillStageConfig();
			
			Map<Long, Long> workAttachmentFormAutoFillIdsMap = new HashMap<Long,Long>();
			
			for(WorkAttachmentAutoFill workFormAutoFill : workAttachmentFormAutoFillStageConfig)
			{
				Long oldAutoFillId = workFormAutoFill.getWorkAttachmentAutoFillId();
				Long newWorkSpecId  = workSpecsCopied.get(workFormAutoFill.getWorkSpecId());
				workFormAutoFill.setFormSpecUniqueId(formSpecUniqueIdsMap.get(workFormAutoFill.getFormSpecUniqueId()));
				workFormAutoFill.setWorkSpecId(newWorkSpecId);
				if(!Api.isEmptyString(workFormAutoFill.getFormSpecUniqueId()))
				{
					effortDao.createWorkAttachementAutoFillFormConfig(workFormAutoFill, webUser.getEmpId());
					workAttachmentFormAutoFillIdsMap.put(oldAutoFillId,workFormAutoFill.getWorkAttachmentAutoFillId());
				}
				
			}
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMapping = workSpecContainer.getWorkAttachmentFormAutoFillFieldMapping();
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMappingList = new ArrayList<WorkAttachmentFormAutoFillField>();
			
			for(WorkAttachmentFormAutoFillField workFormAutoFillField : workAttachmentFormAutoFillFieldMapping)
			{
				if (workAttachmentFormAutoFillIdsMap.get(workFormAutoFillField.getWorkAttachmentAutoFillId()) != null) {
					workFormAutoFillField.setWorkAttachmentAutoFillId(
							workAttachmentFormAutoFillIdsMap.get(workFormAutoFillField.getWorkAttachmentAutoFillId()));
				} else {
					workFormAutoFillField.setWorkAttachmentAutoFillId(-1l);
				}

				if (workFormAutoFillField.getSourceSpecId() != null) {
					workFormAutoFillField
							.setSourceSpecId(workActionSpecsCopied.get(workFormAutoFillField.getSourceSpecId()));
				}
				if (!Api.isEmptyString(workFormAutoFillField.getWorkFieldSpecUniqueId())) {
					if(formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getWorkFieldSpecUniqueId())!=null) {
						workFormAutoFillField.setWorkFieldSpecUniqueId(
								formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getWorkFieldSpecUniqueId()));
					}else {
						workFormAutoFillField.setWorkFieldSpecUniqueId(workFormAutoFillField.getWorkFieldSpecUniqueId());
					}
				}
				if (!Api.isEmptyString(workFormAutoFillField.getSourceSpecUniqueId())) {
					workFormAutoFillField.setSourceSpecUniqueId(
							formSpecUniqueIdsMap.get(workFormAutoFillField.getSourceSpecUniqueId()));
				}
				if (workFormAutoFillField.getFieldType() == 2) {
					workFormAutoFillField.setFieldSpecUniqueId(
							formSectionFieldSpecUniqueIdsMap.get(workFormAutoFillField.getFieldSpecUniqueId()));
				} else if(workFormAutoFillField.getFieldType() == 1){
					workFormAutoFillField.setFieldSpecUniqueId(
							formFieldSpecUniqueIdsMap.get(workFormAutoFillField.getFieldSpecUniqueId()));
				}
				if (workFormAutoFillField.getSourceType() != null && workFormAutoFillField.getSourceType() == 1) {
					Long newWorkSpecId  = workSpecsCopied.get(workFormAutoFillField.getSourceSpecId());
					workFormAutoFillField.setSourceSpecId(newWorkSpecId);
				}

				if (workFormAutoFillField.getWorkAttachmentAutoFillId() > 0) {
					workAttachmentFormAutoFillFieldMappingList.add(workFormAutoFillField);
				}

			}
			
			effortDao.insertWorkAttachmentAutoFillFormFields(workAttachmentFormAutoFillFieldMappingList);
			
			Map<Long, Long> attachmentFormAutoFillSectionConfigurationIdsMap = new HashMap<Long,Long>();
			
			List<AttachmnetFormAutoFillSectionConfiguration> attachmentFormAutoFillSectionConfigurations = workSpecContainer.getAttachmentFormAutoFillSectionConfiguration();
			List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfigurations = workSpecContainer.getAttachmentFormAutoFillSectionFieldsConfiguration();
			
			if (attachmentFormAutoFillSectionConfigurations != null && attachmentFormAutoFillSectionConfigurations.size() > 0) {
				for(AttachmnetFormAutoFillSectionConfiguration attachmentFormAutoFillSectionConfiguration : attachmentFormAutoFillSectionConfigurations)
				{
					long oldFormAutoFillSectionConfigurationId = attachmentFormAutoFillSectionConfiguration.getAttachmnetFormAutoFillSectionConfigurationId();
					attachmentFormAutoFillSectionConfiguration.setSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(attachmentFormAutoFillSectionConfiguration.getSectionSpecUniqueId()));
					attachmentFormAutoFillSectionConfiguration.setSourceActionSpecId(workActionSpecsCopied.get(attachmentFormAutoFillSectionConfiguration.getSourceActionSpecId()));
					attachmentFormAutoFillSectionConfiguration.setSourceSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(attachmentFormAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()));
					attachmentFormAutoFillSectionConfiguration.setWorkAttachmentAutoFillId(workAttachmentFormAutoFillIdsMap.get(attachmentFormAutoFillSectionConfiguration.getWorkAttachmentAutoFillId()));
					if (!Api.isEmptyString(attachmentFormAutoFillSectionConfiguration.getSectionSpecUniqueId()) && !Api
							.isEmptyString(attachmentFormAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()) && attachmentFormAutoFillSectionConfiguration.getSourceActionSpecId() != null && attachmentFormAutoFillSectionConfiguration.getWorkAttachmentAutoFillId() != null) {
						long sectionConfigId = effortDao.insertWorkAttachementAutoFillsectionSpecs(
								attachmentFormAutoFillSectionConfiguration,
								attachmentFormAutoFillSectionConfiguration.getWorkAttachmentAutoFillId());
						attachmentFormAutoFillSectionConfigurationIdsMap.put(oldFormAutoFillSectionConfigurationId,
								sectionConfigId);
					}
					
					
				}
				
				if(attachmentFormAutoFillSectionFieldsConfigurations != null && attachmentFormAutoFillSectionFieldsConfigurations.size() > 0)
				{
					List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfigurationsList = new ArrayList<WorkAttachmentAutoFillSectionFieldsConfiguration>();
					for(WorkAttachmentAutoFillSectionFieldsConfiguration obj : attachmentFormAutoFillSectionFieldsConfigurations)
					{
						obj.setFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getFieldSpecUniqueId()));
						obj.setSourceFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getSourceFieldSpecUniqueId()));
						obj.setAttachmnetFormAutoFillSectionConfigurationId(attachmentFormAutoFillSectionConfigurationIdsMap.get(obj.getAttachmnetFormAutoFillSectionConfigurationId()));
						obj.setWorkAttachmentAutoFillId(workAttachmentFormAutoFillIdsMap.get(obj.getWorkAttachmentAutoFillId()));
						if(!Api.isEmptyString(obj.getFieldSpecUniqueId()) && !Api.isEmptyString(obj.getSourceFieldSpecUniqueId()) && obj.getAttachmnetFormAutoFillSectionConfigurationId() != null && obj.getWorkAttachmentAutoFillId() != null)
						{
							attachmentFormAutoFillSectionFieldsConfigurationsList.add(obj);
						}
						
					}
					effortDao.insertWorkAttachementAutoFillFormSectionFields(attachmentFormAutoFillSectionFieldsConfigurationsList);
				}
				
			}
			
			List<WorkFieldsUniqueConfigurations> fieldsUniqueConfigurations = workSpecContainer.getWorkFieldsUniqueConfigurations();
					
			if(fieldsUniqueConfigurations != null && fieldsUniqueConfigurations.size() > 0)
			{
				for(WorkFieldsUniqueConfigurations workFieldsUniqueConfigurations : fieldsUniqueConfigurations)
				{
					if(formFieldSpecUniqueIdsMap.get(workFieldsUniqueConfigurations.getFormFieldUniqueId()) != null)
					{
						workFieldsUniqueConfigurations.setFormFieldUniqueId(formFieldSpecUniqueIdsMap.get(workFieldsUniqueConfigurations.getFormFieldUniqueId()));
					}
					Long newWorkSpecId  = workSpecsCopied.get(workFieldsUniqueConfigurations.getWorkSpecId());
					workFieldsUniqueConfigurations.setWorkSpecId(newWorkSpecId);
				}
				effortDao.insertWorkFieldUniqueConfigurationsForWorkSpec(webUser,fieldsUniqueConfigurations);
				
			}
			
			List<WorkReassignmentRules> workReassignmentRules = workSpecContainer.getWorkReassignmentRules();
			
			for(WorkReassignmentRules workReassignmentRule : workReassignmentRules) {
				
				Long newWorkSpecId  = workSpecsCopied.get(workReassignmentRule.getWorkSpecId());
				Long workActionSpecId = workActionSpecsCopied.get(workReassignmentRule.getWorkActionSpecId());
				
				workReassignmentRule.setWorkSpecId(newWorkSpecId);
				workReassignmentRule.setWorkActionSpecId(workActionSpecId);
				
				if(!Api.isEmptyString(workReassignmentRule.getReassignFormFieldUniqueId())) {
					String newReassignFormFieldUniqueId = formFieldSpecUniqueIdsMap.get(workReassignmentRule.getReassignFormFieldUniqueId());
					workReassignmentRule.setReassignFormFieldUniqueId(newReassignFormFieldUniqueId);
				}
				workReassignmentRule.setCreatedBy(webUser.getEmpId());
				workReassignmentRule.setModifiedBy(webUser.getEmpId());
			}
			effortDao.insertWorkReassignmentRules(workReassignmentRules);
			
			List<WorkSpecAppLabel> workSpecAppLabels = workSpecContainer.getWorkSpecAppLabels();
			
			Iterator<WorkSpecAppLabel> workSpecAppLabelsIterator = workSpecAppLabels.iterator();
			while (workSpecAppLabelsIterator.hasNext()) {
				WorkSpecAppLabel workSpecAppLabel = (WorkSpecAppLabel) workSpecAppLabelsIterator.next();
				Long newWorkSpecId  = workSpecsCopied.get(workSpecAppLabel.getWorkSpecId());
				if(newWorkSpecId != null) {
					workSpecAppLabel.setWorkSpecId(newWorkSpecId);
				}else {
					workSpecAppLabelsIterator.remove();
					continue;
				}
				workSpecAppLabel.setCompanyId(webUser.getCompanyId());
				workSpecAppLabel.setCreatedBy(webUser.getEmpId());
				workSpecAppLabel.setModifiedBy(webUser.getEmpId());
			}
			effortDao.insertLabelsForMobileApp(workSpecAppLabels);
			
			Map<Long,Long> workUnassignmentCriteriaIdsMap = new HashMap<Long,Long>();
			
			List<WorkUnassignmentCriterias> workUnassignmentCriterias = workSpecContainer.getWorkUnassignmentCriterias();
			for(WorkUnassignmentCriterias workUnassignmentCriteria : workUnassignmentCriterias) {
				Long newWorkSpecId  = workSpecsCopied.get(workUnassignmentCriteria.getWorkSpecId());
				Long oldWorkUnassignmentCriteriaId = workUnassignmentCriteria.getWorkUnassignmentCriteriaId();
				workUnassignmentCriteria.setWorkSpecId(newWorkSpecId);
				
				Long workActionSpecId = workActionSpecsCopied.get(workUnassignmentCriteria.getWorkActionSpecId());
				workUnassignmentCriteria.setWorkActionSpecId(workActionSpecId);
				
				if(!Api.isEmptyString(workUnassignmentCriteria.getFormSpecUniqueId())) {
					String newFormSpecUniqueId = formSpecUniqueIdsMap.get(workUnassignmentCriteria.getFormSpecUniqueId());
					workUnassignmentCriteria.setFormSpecUniqueId(newFormSpecUniqueId);
				}
				
				workUnassignmentCriteria.setCompanyId(Long.parseLong(webUser.getCompanyId()+""));
				workUnassignmentCriteria.setCreatedBy(webUser.getEmpId());
				workUnassignmentCriteria.setModifiedBy(webUser.getEmpId());
				effortDao.insertWorkUnassignmentCriterias(workUnassignmentCriteria);
				workUnassignmentCriteriaIdsMap.put(oldWorkUnassignmentCriteriaId, workUnassignmentCriteria.getWorkUnassignmentCriteriaId());
			}
			
			List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions = workSpecContainer.getWorkAssignmentCriteriaConditions();
			for(WorkAssignmentCriteriaConditions workAssignmentCriteriaCondition : workAssignmentCriteriaConditions) {
				
				workAssignmentCriteriaCondition.setWorkUnassignmentCriteriaId(workUnassignmentCriteriaIdsMap.get(workAssignmentCriteriaCondition.getWorkUnassignmentCriteriaId()));
				if(!Api.isEmptyString(workAssignmentCriteriaCondition.getFieldSpecUniqueId())){
					workAssignmentCriteriaCondition.setFieldSpecUniqueId(formFieldSpecUniqueIdsMap.get(workAssignmentCriteriaCondition.getFieldSpecUniqueId()));
				}
				
			}
			effortDao.insertWorkAssignmentCriteriaConditions(workAssignmentCriteriaConditions);
			
			Map<Long,Long> externalActionConfigurationsIdsMap = new HashMap<Long,Long>();
			
			List<ExternalActionConfiguration> externalActionConfigurations = workSpecContainer.getExternalActionConfigurations();
			
			for(ExternalActionConfiguration externalActionConfiguration : externalActionConfigurations) {
				Long newWorkSpecId  = workSpecsCopied.get(externalActionConfiguration.getWorkSpecId());
				Long oldExternalActionConfigurationId = externalActionConfiguration.getExternalActionConfigurationId();
				externalActionConfiguration.setWorkSpecId(newWorkSpecId);
				
				Long workActionSpecId = workActionSpecsCopied.get(externalActionConfiguration.getWorkActionSpecId());
				externalActionConfiguration.setWorkActionSpecId(workActionSpecId);
				effortDao.insertIntoExternalActionConfiguration(externalActionConfiguration,webUser);
				externalActionConfigurationsIdsMap.put(oldExternalActionConfigurationId, externalActionConfiguration.getExternalActionConfigurationId());
			}
			
			List<FormToWorkAutoFillSectionConfiguration> formToWorkAutoFillSectionConfigurations = workSpecContainer.getFormToWorkAutoFillSectionConfiguration();
			List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfiguration = workSpecContainer.getFormToWorkAutoFillSectionFieldsConfiguration();
			
			
			Map<Long, Long> formToWorkAutoFillSectionConfigurationIdsMap = new HashMap<Long,Long>();
			
			
			if (formToWorkAutoFillSectionConfigurations != null && formToWorkAutoFillSectionConfigurations.size() > 0) {
				for(FormToWorkAutoFillSectionConfiguration formToWorkAutoFillSectionConfiguration : formToWorkAutoFillSectionConfigurations)
				{
					long oldFormAutoFillSectionConfigurationId = formToWorkAutoFillSectionConfiguration.getFormToWorkAutoFillSectionConfigurationId();
					formToWorkAutoFillSectionConfiguration.setSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(formToWorkAutoFillSectionConfiguration.getSectionSpecUniqueId()));
					formToWorkAutoFillSectionConfiguration.setSourceActionSpecId(workActionSpecsCopied.get(formToWorkAutoFillSectionConfiguration.getSourceActionSpecId()));
					formToWorkAutoFillSectionConfiguration.setSourceSectionSpecUniqueId(formSectionSpecUniqueIdsMap.get(formToWorkAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()));
					formToWorkAutoFillSectionConfiguration.setFormToWorkAutoFillId(formToWorkAutoFillIds.get(formToWorkAutoFillSectionConfiguration.getFormToWorkAutoFillId()));
					if (!Api.isEmptyString(formToWorkAutoFillSectionConfiguration.getSectionSpecUniqueId()) && !Api
							.isEmptyString(formToWorkAutoFillSectionConfiguration.getSourceSectionSpecUniqueId()) && formToWorkAutoFillSectionConfiguration.getSourceActionSpecId() != null && formToWorkAutoFillSectionConfiguration.getFormToWorkAutoFillId() != null) {
						long sectionConfigId = effortDao.insertFormToWorkAutoFillsectionSpecs(
								formToWorkAutoFillSectionConfiguration);
						formToWorkAutoFillSectionConfigurationIdsMap.put(oldFormAutoFillSectionConfigurationId,
								sectionConfigId);
					}
					
					
				}
				
				if(formToWorkAutoFillSectionFieldsConfiguration != null && formToWorkAutoFillSectionFieldsConfiguration.size() > 0)
				{
					List<FormToWorkAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfigurationsList = new ArrayList<FormToWorkAutoFillSectionFieldsConfiguration>();
					for(FormToWorkAutoFillSectionFieldsConfiguration obj : formToWorkAutoFillSectionFieldsConfiguration)
					{
						obj.setFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getFieldSpecUniqueId()));
						obj.setSourceFieldSpecUniqueId(formSectionFieldSpecUniqueIdsMap.get(obj.getSourceFieldSpecUniqueId()));
						obj.setFormToWorkAutoFillSectionConfigurationId(formToWorkAutoFillSectionConfigurationIdsMap.get(obj.getFormToWorkAutoFillSectionConfigurationId()));
						obj.setFormToWorkAutoFillId(workAttachmentFormAutoFillIdsMap.get(obj.getFormToWorkAutoFillId()));
						if(!Api.isEmptyString(obj.getFieldSpecUniqueId()) && !Api.isEmptyString(obj.getSourceFieldSpecUniqueId()) && obj.getFormToWorkAutoFillSectionConfigurationId() != null && obj.getFormToWorkAutoFillId() != null)
						{
							attachmentFormAutoFillSectionFieldsConfigurationsList.add(obj);
						}
						
					}
					effortDao.insertFormToWorkAutoFillFormSectionFields(attachmentFormAutoFillSectionFieldsConfigurationsList);
				}
				
			}
			
			List<WorkActionFormVisibility> workActionFormVisibility = workSpecContainer.getWorkActionFormVisibility();
			
			for(WorkActionFormVisibility workActionFormVisibilityObj : workActionFormVisibility) {
				
				Long newWorkSpecId  = workSpecsCopied.get(workActionFormVisibilityObj.getWorkSpecId());
				workActionFormVisibilityObj.setWorkSpecId(newWorkSpecId);
				
				Long workActionSpecId = workActionSpecsCopied.get(workActionFormVisibilityObj.getActionSpecId());
				workActionFormVisibilityObj.setActionSpecId(workActionSpecId);
				workActionFormVisibilityObj.setCompanyId(Long.parseLong(webUser.getCompanyId()+""));
			}
			effortDao.insertIntoWorkActionFormVisibility(workActionFormVisibility);
			
			// 
			List<WorkSpecFormSpecMap> workSpecFormSpecMaps = workSpecContainer.getWorkSpecFormSpecMaps();
			for(WorkSpecFormSpecMap workSpecFormSpecMap : workSpecFormSpecMaps) {
				workSpecFormSpecMap.setFormSpecUniqueId(formSpecUniqueIdsMap.get(workSpecFormSpecMap.getFormSpecUniqueId()));
				Long newWorkSpecId  = workSpecsCopied.get(workSpecFormSpecMap.getWorkSpecId());
				workSpecFormSpecMap.setWorkSpecId(newWorkSpecId);
			}
			effortDao.insertFormSpecUniqueIdsForWorkSpec(workSpecFormSpecMaps);
		
	}

	private void resolveNextActionSpecs(List<NextActionSpec> nextActionSpecs, Map<Long, Long> workActionSpecsCopied,
			Map<Long, Long> workSpecsCopied) {
		try {
			
			Iterator<NextActionSpec> formPageSpecsIterator = nextActionSpecs.iterator();
			while (formPageSpecsIterator.hasNext()) {
				NextActionSpec nextActionSpec = (NextActionSpec) formPageSpecsIterator.next();
				Long newWorkSpecId = workSpecsCopied.get(nextActionSpec.getWorkSpecId());
				if(newWorkSpecId != null) {
					nextActionSpec.setWorkSpecId(newWorkSpecId);
				}else {
					formPageSpecsIterator.remove();
					continue;
				}
				
				Long newWorkActionSpecId = workActionSpecsCopied.get(nextActionSpec.getActionSpecId());
				if(newWorkActionSpecId != null) {
					nextActionSpec.setActionSpecId(newWorkActionSpecId);
				}else {
					formPageSpecsIterator.remove();
					continue;
				}
				
				Long newNextActionSpecId = workActionSpecsCopied.get(nextActionSpec.getNextActionSpecId());
				if(newNextActionSpecId != null) {
					nextActionSpec.setNextActionSpecId(newNextActionSpecId);
				}else {
					formPageSpecsIterator.remove();
					continue;
				}
			}
			
		}catch(Exception e) {
		}
		
	}

	public void copyCustomEntitySpecsFromSqlite(Map<String, String> customEntitySpecIdsMap,
			FormSpecContainer formSpecContainer, WebUser webUser) {
		List<CustomEntitySpec> customEntitySpecs = formSpecContainer.getCustomEntitySpecs();
		
		for(CustomEntitySpec customEntitySpec : customEntitySpecs) {
			Long oldCustomEntitySpecId = customEntitySpec.getCustomEntitySpecId();
			customEntitySpec.setCreatedBy(webUser.getEmpId());
			customEntitySpec.setModifiedBy(webUser.getEmpId());
			
			customEntitySpec.setSkeletonCustomEntitySpecId(customEntitySpec.getCustomEntitySpecId());
			customEntitySpec.setCompanyId(webUser.getCompanyId());
			effortDao.insertCustomEntitySpecs(customEntitySpec);
			customEntitySpecIdsMap.put(oldCustomEntitySpecId+"", customEntitySpec.getCustomEntitySpecId()+"");
		}
		
		
	}

	public void updateCustomEntityFormSpecs(FormSpecContainer formSpecContainer,
			Map<String, String> formSpecUniqueIdsMap) {
		
		List<CustomEntitySpec> customEntitySpecs = formSpecContainer.getCustomEntitySpecs();
		
		for(CustomEntitySpec customEntitySpec : customEntitySpecs) {
			String newFormSpecUniqueId = formSpecUniqueIdsMap.get(customEntitySpec.getFormSpecUniqueId());
			effortDao.updateCustomEntityFormSpec(newFormSpecUniqueId,customEntitySpec.getCustomEntitySpecId());
		}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void fetchDataAndInsertIntoDB(FormSpecContainer formSpecContainer,
			WorkSpecContainer workSpecContainer,Integer companyId, String cloneEntityData) throws Exception {
		Map<Long, Long> formSpecIdsMap = new HashMap<Long, Long>();
		Map<String, String> formSpecUniqueIdsMap = new HashMap<String, String>();
		Map<Long, Long> visitStatesMap = new HashMap<Long, Long>();
		Map<String, String> formFieldSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> formSectionFieldSpecUniqueIdsMap = new HashMap<String, String>();
		Map<Long, Long> formFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> formSectionFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> entityFieldSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> entitySpecsIdMap = new HashMap<Long, Long>();
		Map<Long, EntitySpec> entitySpecMap = new HashMap<Long, EntitySpec>();
		Map<Long, Long> formSectionSpecsIdMap = new HashMap<Long, Long>();
		Map<String, String> formSectionSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> innerformSpecUniqueIdsMap = new HashMap<String, String>();
		Map<String, String> customEntitySpecIdsMap = new HashMap<String, String>();
		Map<Long, CustomEntitySpec> customEntitySpecsMap = new HashMap<Long, CustomEntitySpec>();
		
		Map<Long, Long> entitySectionSpecsIdMap = new HashMap<Long, Long>();
		Map<Long, Long> entitySectionFieldSpecsIdMap = new HashMap<Long, Long>();
		
		Map<Long, Long> workSpecsCopied = new HashMap<Long, Long>();

		WebUser webUser = new WebUser();
		long rootEmpId = effortDao.getRootEmployeeId(companyId);
		Employee employee =effortDao.getEmployeeBasicDetailsAndTimeZoneByEmpId(rootEmpId+"");
		webUser.setEmpId(employee.getEmpId());
		webUser.setTzo(employee.getTimezoneOffset());
		webUser.setCompanyId(employee.getCompanyId());
		LOGGER.info(" fetchDataAndInsertIntoDB Cloning Starts ... ");
		copyEntitySpecsFromSqlite(entitySpecsIdMap,entityFieldSpecsIdMap,entitySectionSpecsIdMap,entitySectionFieldSpecsIdMap,formSpecContainer,webUser);
		LOGGER.info(" fetchDataAndInsertIntoDB copyEntitySpecsFromSqlite clone done ... ");
		copyCustomEntitySpecsFromSqlite(customEntitySpecIdsMap, formSpecContainer, webUser);
		LOGGER.info(" fetchDataAndInsertIntoDB copyCustomEntitySpecsFromSqlite clone done ... ");
		copyTemplatesFromSkeletonNew(webUser, formSpecContainer, entitySpecsIdMap, entitySpecMap, formSpecIdsMap,
				formSpecUniqueIdsMap, formFieldSpecUniqueIdsMap, formSectionFieldSpecUniqueIdsMap, formFieldSpecsIdMap,
				formSectionFieldSpecsIdMap, entityFieldSpecsIdMap, true, true, formSectionSpecUniqueIdsMap,
				formSectionSpecsIdMap, customEntitySpecIdsMap, customEntitySpecsMap, innerformSpecUniqueIdsMap, true);
		LOGGER.info(" fetchDataAndInsertIntoDB copyTemplatesFromSkeletonNew clone done ... ");
		updateCustomEntityFormSpecs(formSpecContainer, formSpecUniqueIdsMap);
		copyWorkSpecTemplate(webUser, workSpecContainer, entitySpecsIdMap, entitySpecMap, formSpecIdsMap,
				formSpecUniqueIdsMap, formFieldSpecUniqueIdsMap, formSectionFieldSpecUniqueIdsMap, formFieldSpecsIdMap,
				formSectionFieldSpecsIdMap, entityFieldSpecsIdMap, formSectionSpecUniqueIdsMap, formSectionSpecsIdMap,
				customEntitySpecIdsMap, customEntitySpecsMap, innerformSpecUniqueIdsMap);
		 
		LOGGER.info(" fetchDataAndInsertIntoDB copyWorkSpecTemplate clone done ... ");
			
		
	}
	
	public Media getMedia(String mediaId){
        return  effortDao.getMedia(mediaId);
    }
	public void createActivity(WebUser webUser, Activity activity){

		activity.setCompanyId((long) webUser.getCompanyId());
		activity.setCreatedBy(webUser.getEmpId());
		activity.setModifiedBy(webUser.getEmpId());
		activity.setIsDeleted((short) 0);
		activity.setCreationTime(Api.getDateTimeInUTC(new Date(System
				.currentTimeMillis())));
		activity.setModifiedTime(Api.getDateTimeInUTC(new Date(System
				.currentTimeMillis())));
		effortDao.insertActivity(activity);

	}

	private void resolveFormFieldsExtra(
	        List<FormFieldSpecsExtra> fieldsExtra,
	        Map<Long, Long> formFieldSpecsIdMap,
	        Map<Long, Long> formSectionFieldSpecsIdMap,
	        Map<Long, Long> formSpecIdsMap) {
	    try {
	        Iterator<FormFieldSpecsExtra> iterator = fieldsExtra.iterator();
	        while (iterator.hasNext()) {
	            FormFieldSpecsExtra extra = iterator.next();

	            Long newFormSpecId = formSpecIdsMap.get(extra.getFormSpecId());
	            if (newFormSpecId != null) {
	                extra.setFormSpecId(newFormSpecId);
	            } else {
	                iterator.remove();
	                continue;
	            }

	            Long fieldSpecId = extra.getFieldSpecId();
	            Long newFieldSpecId = null;

	            if (formFieldSpecsIdMap.containsKey(fieldSpecId)) {
	                newFieldSpecId = formFieldSpecsIdMap.get(fieldSpecId);
	            } else if (formSectionFieldSpecsIdMap.containsKey(fieldSpecId)) {
	                newFieldSpecId = formSectionFieldSpecsIdMap.get(fieldSpecId);
	            }

	            if (newFieldSpecId != null) {
	                extra.setFieldSpecId(newFieldSpecId);
	            } else {
	                iterator.remove();
	            }
	        }
	    } catch (Exception e) {
	        fieldsExtra = new ArrayList<>();
	    }
	}
	private void resolveFormSectionFieldsExtra(
	        List<FormSectionFieldSpecsExtra> sectionFieldsExtra,
	        Map<Long, Long> formFieldSpecsIdMap,
	        Map<Long, Long> formSectionFieldSpecsIdMap,
	        Map<Long, Long> formSpecIdsMap) {
	    try {
	        Iterator<FormSectionFieldSpecsExtra> iterator = sectionFieldsExtra.iterator();

	        while (iterator.hasNext()) {
	            FormSectionFieldSpecsExtra extra = iterator.next();

	            // 1️⃣ Resolve new formSpecId
	            Long newFormSpecId = formSpecIdsMap.get(extra.getFormSpecId());
	            if (newFormSpecId != null) {
	                extra.setFormSpecId(newFormSpecId);
	            } else {
	                iterator.remove();
	                continue;
	            }

	            // 2️⃣ Resolve new sectionFieldSpecId (instead of fieldSpecId)
	            Long sectionFieldSpecId = extra.getSectionFieldSpecId();
	            Long newSectionFieldSpecId = null;

	            // First, check both ID maps
	            if (formSectionFieldSpecsIdMap.containsKey(sectionFieldSpecId)) {
	                newSectionFieldSpecId = formSectionFieldSpecsIdMap.get(sectionFieldSpecId);
	            } else if (formFieldSpecsIdMap.containsKey(sectionFieldSpecId)) {
	                newSectionFieldSpecId = formFieldSpecsIdMap.get(sectionFieldSpecId);
	            }

	            if (newSectionFieldSpecId != null) {
	                extra.setSectionFieldSpecId(newSectionFieldSpecId);
	            } else {
	                iterator.remove();
	            }
	        }
	    } catch (Exception e) {
	        sectionFieldsExtra = new ArrayList<>();
	    }
	}


}
