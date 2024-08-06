package com.spoors.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spoors.beans.CompanyFont;
import com.spoors.beans.CustomEntityFilteringCritiria;
import com.spoors.beans.CustomerAutoFilteringCritiria;
import com.spoors.beans.CustomerFilteringCritiria;
import com.spoors.beans.DataSourceRequestHeader;
import com.spoors.beans.DataSourceRequestParam;
import com.spoors.beans.DataSourceResponseMapping;
import com.spoors.beans.EmployeeFilteringCritiria;
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
import com.spoors.beans.OfflineCustomEntityUpdateConfiguration;
import com.spoors.beans.OfflineListUpdateConfiguration;
import com.spoors.beans.PaymentMapping;
import com.spoors.beans.RemainderFieldsMap;
import com.spoors.beans.StockFormConfiguration;
import com.spoors.beans.VisibilityDependencyCriteria;
import com.spoors.beans.WorkFormFieldMap;
import com.spoors.beans.WorkSpecFormSpecFollowUp;
import com.spoors.beans.workSpecs.ActionableEmployeeGroupSpecs;
import com.spoors.beans.workSpecs.AddingSubTaskEmployeeConfiguration;
import com.spoors.beans.workSpecs.ExternalActionConfiguration;
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
import com.spoors.beans.workSpecs.WorkActionVisibilityConfiguration;
import com.spoors.beans.workSpecs.WorkAssignmentCriteriaConditions;
import com.spoors.beans.workSpecs.WorkFieldsUniqueConfigurations;
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

	public void getExportFormSpecData(Set<String> formSpecUniqueIds, FormSpecContainer formSpecContainer, String logtext)
	{
		try {
			
			List<FormSpec> formSpecs = effortDao.getLatestFormSpecsForUnquids(Api.toCSV(formSpecUniqueIds));
			if(formSpecs!=null) {
				List<String> uniqueIdsList = new ArrayList<>();
				uniqueIdsList.addAll(formSpecUniqueIds);
				
				String uniqueIdsCsv = Api.processStringValuesList(uniqueIdsList);
				
				String formSpecIds = Api.toCSV(formSpecs,"formSpecId",CsvOptions.FILTER_NULL_OR_EMPTY);
				
				List<FormPageSpec> pageSpecs = getFormPageSpecsForFormSpec(formSpecIds);
				
				List<FormFieldSpec> fieldSpecs = getFormFieldSpecForIn(formSpecs);
				
				List<FormFieldSpecsExtra> fieldSpecsExtra =	getFormFieldSpecsExtraForIn(formSpecs);
				
				List<FormFieldSpecValidValue> fieldSpecValidValues = getFormFieldSpecValidValues(fieldSpecs);
				
				List<FormSectionSpec> sectionSpecs = effortDao.getFormSectionSpecForIn(formSpecs);
				
				List<FormSectionFieldSpec> sectionFieldSpecs = getFormSectionFieldSpecForIn(sectionSpecs);
				
				List<FormSectionFieldSpecsExtra> sectionFieldSpecsExtra = getFormSectionFieldSpecsForIn(formSpecs);
				
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
				
				
				formSpecContainer.setFormSpecs(formSpecs);
				formSpecContainer.setPageSpecs(pageSpecs);
				formSpecContainer.setFields(fieldSpecs);
				formSpecContainer.setFieldsExtra(fieldSpecsExtra);
				formSpecContainer.setFieldValidValues(fieldSpecValidValues);
				formSpecContainer.setSections(sectionSpecs);
				formSpecContainer.setSectionFields(sectionFieldSpecs);
				formSpecContainer.setSectionFieldsExtra(sectionFieldSpecsExtra);
				formSpecContainer.setSectionFieldValidValues(sectionFieldSpecValidValues);
				formSpecContainer.setFormFieldGroupSpecs(formFieldGroupSpecs);
				formSpecContainer.setVisibilityDependencyCriterias(visibilityDependencyCriterias);
				formSpecContainer.setFormFieldsColorDependencyCriterias(formFieldsColorDependencyCriterias);
				formSpecContainer.setFormCleanUpRule(formCleanUpRule);
				formSpecContainer.setListFilteringCriterias(lisFilteringCritirias);
				formSpecContainer.setRemainderFieldsMap(remainderFieldsMap);
				formSpecContainer.setCustomerAutoFilteringCritirias(customerAutoFilteringCritirias);
				formSpecContainer.setCustomerFilteringCriterias(customerFilteringCritirias);
				formSpecContainer.setEmployeeFilteringCriterias(employeeFilteringCritirias);
				formSpecContainer.setFieldValidationCritirias(fieldValidationCritirias);
				formSpecContainer.setFormFilteringCriterias(formFilteringCritirias);
				formSpecContainer.setCustomEntityFilteringCritirias(customEntityFilteringCritirias);
				formSpecContainer.setStockFormConfigurations(stockFormConfigurations);
				formSpecContainer.setOfflineListUpdateConfigurations(offlineListUpdateConfiguration);
				formSpecContainer.setOfflineCustomEntityUpdateConfigurations(offlineCustomEntityUpdateConfiguration);
				formSpecContainer.setFormFieldSpecFilters(formFieldSpecFilters);
				formSpecContainer.setFormSectionFieldSpecFilters(formSectionFieldSpecFilters);
				formSpecContainer.setPaymentMappings(paymentMappings);
				formSpecContainer.setFormSpecDataSource(formSpecDataSource);
				
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
	
	

}
