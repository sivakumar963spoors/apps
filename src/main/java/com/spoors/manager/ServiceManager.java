package com.spoors.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spoors.beans.EntityFieldSpecValidValue;
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
import com.spoors.beans.EntityFieldSpec;
import com.spoors.beans.EntitySpec;
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
import com.spoors.beans.WebUser;
import com.spoors.beans.WorkFormFieldMap;
import com.spoors.beans.WorkSpecFormSpecFollowUp;
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
	private void copyTemplatesFromSkeleton(WebUser webUser, FormSpecContainer formSpecContainer,
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
				}else if (formFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY && !Api.isEmptyString(formFieldSpec.getFieldTypeExtraCustomEntity())) {
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
		}if(formSpec.getPurpose() == 12) {
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
		effortDao.insertFormSpec(formSpec, webUser.getCompanyId(),
				webUser.getEmpId());
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
				if (formSectionFieldSpec.getFieldType() == Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY) {
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
			/* long validId = extraSupportDao.insertIntoFormFieldSpecUniqueIdWiseValidValues(formFieldSpec,
						webUser.getCompanyId(),fieldSpecValidValue.getValue());
				extraSupportDao.insertFormFieldSpecValidValueUnique(validId, formFieldSpec.getFieldSpecId(), fieldSpecValidValue.getValue());*/
				
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
	

}
