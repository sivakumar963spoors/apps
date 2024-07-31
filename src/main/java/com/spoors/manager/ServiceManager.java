package com.spoors.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.spoors.beans.workSpecs.AddingSubTaskEmployeeConfiguration;
import com.spoors.beans.workSpecs.HideAddSubTaskConfiguration;
import com.spoors.beans.workSpecs.WorkProcessSubTaskSpec;
import com.spoors.beans.workSpecs.WorkSpec;
import com.spoors.beans.workSpecs.WorkSpecContainer;
import com.spoors.beans.workSpecs.WorkToSubTaskAutoFillConfiguration;
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

	public void getExportFormSpecData(String formSpecUniqueId, FormSpecContainer formSpecContainer, String logtext)
	{
		try {
			
			FormSpec formSpec = effortDao.getLatestFormSpecByUniqueId(formSpecUniqueId);
			List<FormSpec> formSpecs = new ArrayList<>();
			if(formSpec!=null) {
				formSpecs.add(formSpec);
				List<String> uniqueIdsList = new ArrayList<>();
				uniqueIdsList.add(formSpecUniqueId);
				
				String uniqueIdsCsv = Api.processStringValuesList(uniqueIdsList);
				
				List<FormPageSpec> pageSpecs = getFormPageSpecs(formSpec.getFormSpecId());
				
				List<FormFieldSpec> fieldSpecs = getFormFieldSpecs(formSpec.getFormSpecId());
				
				List<FormFieldSpecsExtra> fieldSpecsExtra =	getFormFieldSpecsExtraForIn(formSpecs);
				
				List<FormFieldSpecValidValue> fieldSpecValidValues = getFormFieldSpecValidValues(fieldSpecs);
				
				List<FormSectionSpec> sectionSpecs = getFormSectionSpecs(formSpec.getFormSpecId());
				
				List<FormSectionFieldSpec> sectionFieldSpecs = getFormSectionFieldSpecForIn(sectionSpecs);
				
				List<FormSectionFieldSpecsExtra> sectionFieldSpecsExtra = getFormSectionFieldSpecsForIn(formSpecs);
				
				List<FormSectionFieldSpecValidValue> sectionFieldSpecValidValues = getFormSectionFieldSpecValidValuesIn(sectionFieldSpecs);
				
				List<FormFieldGroupSpec> formFieldGroupSpecs = getFormFieldGroupSpecForIn(formSpec.getFormSpecId()+"");
				
				List<VisibilityDependencyCriteria> visibilityDependencyCriterias = getVisibilityDependencyCriteriasForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriterias = getFormFieldsColorDependencyCriteriasByFormSpecId(formSpec.getFormSpecId()+"");
				
				List<FormCleanUpRule> formCleanUpRule = getFormDataCleanUpRulesForFormSpecId(formSpec.getFormSpecId()+"");
				
				List<ListFilteringCritiria> lisFilteringCritirias =  getListFilteringCritiriasForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<RemainderFieldsMap> remainderFieldsMap = getRemainderFieldsMapForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<CustomerAutoFilteringCritiria> customerAutoFilteringCritirias = getCustomerAutoFilteringCritiriasForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<CustomerFilteringCritiria> customerFilteringCritirias = getListOfCustomerFilterCriterias(formSpec.getFormSpecId()+"");
				
				List<EmployeeFilteringCritiria> employeeFilteringCritirias = getListOfEmployeeFilterCriterias(formSpec.getFormSpecId()+"");
				
				List<FieldValidationCritiria> fieldValidationCritirias = getFieldValidationCritirias(formSpec.getFormSpecId()+"");
				
				List<FormFilteringCritiria> formFilteringCritirias = getFormFilteringCritiriasForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<CustomEntityFilteringCritiria> customEntityFilteringCritirias = getListOfCustomEntityFilteringCritiriaForFormSpecs(formSpec.getFormSpecId()+"");
				
				List<StockFormConfiguration> stockFormConfigurations = getStockFieldConfigurationsForSync(formSpec.getFormSpecId()+"");
				
				List<OfflineListUpdateConfiguration> offlineListUpdateConfiguration = getOfflineListUpdateConfigurationsForSync(formSpec.getFormSpecId()+"");
				
				List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfiguration = getOfflineCustomEntityUpdateConfigurationForSync(formSpec.getFormSpecId()+"");
				
				List<FieldSpecFilter> formFieldSpecFilters = getFieldSpecFiltersFormFormSpecIds(formSpec.getFormSpecId()+"", FieldSpecFilter.FIELD_IS_FORMFIELD);
				
				List<FieldSpecFilter> formSectionFieldSpecFilters = getFieldSpecFiltersFormFormSpecIds(formSpec.getFormSpecId()+"", FieldSpecFilter.FIELD_IS_SECTIONFIELD);
				
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
				
				List<FormSpecPermission> formSpecPermissions = geFormSpecPermissions(formSpec.getUniqueId(),true);
				formSpecContainer.setFormSpecPermissions(formSpecPermissions);
				
				List<FormSpecConfigSaveOnOtpVerify> saveFormOnOtpVerifyList = getFormSpecConfigSaveOnOtpVerifyList(uniqueIdsCsv);
				formSpecContainer.setSaveFormOnOtpVerify(saveFormOnOtpVerifyList);
				
				List<JobFormMapBean> jobFormMapBeans = getJobFormMapBeans(formSpec.getFormSpecId()+"",null);
				formSpecContainer.setJobFormMapBeans(jobFormMapBeans);
				
				List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUpList = getWorkSpecFormSpecFollowUpForSync(formSpec.getFormSpecId()+"","1970-01-01 00:00:00");
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

	private List<FormPageSpec> getFormPageSpecs(long formSpecId) {
		List<FormPageSpec> formPageSpecs = effortDao
				.getFormPageSpecsForFormSpec(formSpecId);
		if (formPageSpecs.size() == 0) {
			FormPageSpec formPageSpec = new FormPageSpec();
			formPageSpec.setPageId(0);
			formPageSpec.setPageTitle("Page 1");
			formPageSpec.setPageOrder(0);
			formPageSpecs.add(formPageSpec);
		}
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
				
			}
			
		}catch(Exception e) {
			LOGGER.info("Got Exception getExportWorkSpecData  : "+e );
			LOGGER.error("Got Exception getExportWorkSpecData  : "+e );
			e.printStackTrace();
		}
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
