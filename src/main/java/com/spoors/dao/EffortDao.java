package com.spoors.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
import com.spoors.beans.workSpecs.WorkActionEmployeeGroupSpecs;
import com.spoors.beans.workSpecs.WorkActionFormVisibility;
import com.spoors.beans.workSpecs.WorkActionGroup;
import com.spoors.beans.workSpecs.WorkActionNotificationEscalationMatrix;
import com.spoors.beans.workSpecs.WorkActionSpec;
import com.spoors.beans.workSpecs.WorkActionSpecConditions;
import com.spoors.beans.workSpecs.WorkActionSpecEndCondition;
import com.spoors.beans.workSpecs.WorkActionSpecVisibilityCondition;
import com.spoors.beans.workSpecs.WorkActionVisibilityConfiguration;
import com.spoors.beans.workSpecs.WorkActionVisibilitySpecs;
import com.spoors.beans.workSpecs.WorkAssignmentCriteriaConditions;
import com.spoors.beans.workSpecs.WorkFieldsUniqueConfigurations;
import com.spoors.beans.workSpecs.WorkProcessSubTaskSpec;
import com.spoors.beans.workSpecs.WorkReassignmentRules;
import com.spoors.beans.workSpecs.WorkSpec;
import com.spoors.beans.workSpecs.WorkSpecAppLabel;
import com.spoors.beans.workSpecs.WorkSpecCustomDashboardConfiguration;
import com.spoors.beans.workSpecs.WorkSpecCustomDashboardMetric;
import com.spoors.beans.workSpecs.WorkSpecCustomerCallApi;
import com.spoors.beans.workSpecs.WorkSpecFormSpecMap;
import com.spoors.beans.workSpecs.WorkSpecListLevelVisibilityConfiguration;
import com.spoors.beans.workSpecs.WorkSpecPermission;
import com.spoors.beans.workSpecs.WorkToSubTaskAutoFillConfiguration;
import com.spoors.beans.workSpecs.WorkUnassignmentCriterias;
import com.spoors.setting.Sqls;
import com.spoors.util.Api;
import com.spoors.util.Api.CsvOptions;

import ch.qos.logback.classic.Logger;

@Repository
public class EffortDao {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(EffortDao.class);
	
    private JdbcTemplate jdbcTemplate;
    
	public EffortDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FormSpec getLatestFormSpecByUniqueId(String formSpecUniqueId) {
		FormSpec formSpec = null;
		try {
			if(!Api.isEmptyString(formSpecUniqueId)) {
				formSpec = jdbcTemplate.queryForObject(
						Sqls.SELECT_LATEST_FORM_SPEC, new Object[] { formSpecUniqueId },new int[] {Types.VARCHAR},
						new BeanPropertyRowMapper<FormSpec>(FormSpec.class));
			}
		}catch(Exception e) {
			LOGGER.info("Got Exception while getLatestFormSpecByUniqueId() :"+e);
			LOGGER.error("Got Exception while getLatestFormSpecByUniqueId() :"+e);
			e.printStackTrace();
		}
		return formSpec;
	}

	public List<FieldValidationCritiria> getFieldValidationCritirias(String formSpecIds) 
	{
		List<FieldValidationCritiria> fieldValidationCritirias = new ArrayList<FieldValidationCritiria>();
		if(!Api.isEmptyString(formSpecIds))
		{
			String sql = Sqls.SELECT_FIELD_VALIDATION_CRITIRIA.replace(":formSpecIds", formSpecIds);
			fieldValidationCritirias = jdbcTemplate.query(
					sql,new BeanPropertyRowMapper<FieldValidationCritiria>(
							FieldValidationCritiria.class));
		}
		return fieldValidationCritirias;
	}

	public List<EmployeeFilteringCritiria> getListOfEmployeeFilterCriterias(String formSpecIds) {
		List<EmployeeFilteringCritiria> employeeFilteringCritiria = new ArrayList<EmployeeFilteringCritiria>();
		if(!Api.isEmptyString(formSpecIds))
		{
			String sql = Sqls.SELECT_EMPLOYEE_FILTER_CRITERIA.replace(":formSpecIds", formSpecIds);
			employeeFilteringCritiria = jdbcTemplate.query(
					sql,new BeanPropertyRowMapper<EmployeeFilteringCritiria>(
							EmployeeFilteringCritiria.class));
		}
		return employeeFilteringCritiria;
	}

	public List<CustomerFilteringCritiria> getListOfCustomerFilterCriterias(String formSpecIds) {
		List<CustomerFilteringCritiria> customerFilteringCritiria = new ArrayList<CustomerFilteringCritiria>();
		if(!Api.isEmptyString(formSpecIds))
		{
			String sql = Sqls.SELECT_CUSTOMER_FILTER_CRITERIA.replace(":formSpecIds", formSpecIds);
			customerFilteringCritiria = jdbcTemplate.query(
					sql,new BeanPropertyRowMapper<CustomerFilteringCritiria>(
							CustomerFilteringCritiria.class));
		}
		return customerFilteringCritiria;
	}

	public List<ListFilteringCritiria> getListFilteringCritiriasForFormSpecs(String formSpecIds) {
		List<ListFilteringCritiria> listFilteringCritiria = new ArrayList<ListFilteringCritiria>();
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_LIST_FILTERING_CRITIRIA_FOR_FORMSPECS
					.replace(":formSpecIds", formSpecIds);
			listFilteringCritiria = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<ListFilteringCritiria>(
							ListFilteringCritiria.class));
		}
		return listFilteringCritiria;
	}

	public List<FormCleanUpRule> getFormDataCleanUpRulesForFormSpecId(String formSpecIds) {
		String sql = Sqls.SELECT_FORM_CLEAN_UP_RULES_BY_FORM_SPEC_ID.replace(":formSpecIds", formSpecIds);;
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<FormCleanUpRule>(FormCleanUpRule.class));
	}

	public List<FormFieldsColorDependencyCriterias> getFormFieldsColorDependencyCriteriasByFormSpecId(String formSpecIds) {
		String sql = Sqls.SELECT_FORM_FIELDS_COLOR_DEPENDENCY_CRITERIAS.replace(":formSpecIds", formSpecIds);
		List<FormFieldsColorDependencyCriterias> colorDependencies = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<FormFieldsColorDependencyCriterias>(FormFieldsColorDependencyCriterias.class));
		return colorDependencies;
	}

	public List<VisibilityDependencyCriteria> getVisibilityDependencyCriteriasForFormSpecs(String formSpecIds) {
		List<VisibilityDependencyCriteria> visibilityDependencyCriteria = new ArrayList<VisibilityDependencyCriteria>();
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_VISIBILITY_DEPENDENCY_CRITIRIAS_FOR_FORMSPECS
					.replace(":formSpecIds", formSpecIds);
			visibilityDependencyCriteria = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<VisibilityDependencyCriteria>(
							VisibilityDependencyCriteria.class));

		}
		return visibilityDependencyCriteria;
	}

	public List<FormFieldGroupSpec> getFormFieldGroupSpecForIn(String formSpecIds) {
		String sql = Sqls.SELECT_FORM_FIELD_GROUP_SPECS_IN.replace(":ids", formSpecIds);
		List<FormFieldGroupSpec> formFieldGroupSpecs = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<FormFieldGroupSpec>(FormFieldGroupSpec.class));
		return formFieldGroupSpecs;
	}

	public List<FormSectionFieldSpecValidValue> getFormSectionFieldSpecValidValuesIn(
			List<FormSectionFieldSpec> formSectionFieldSpecs) {
		String ids = "";
		for (FormSectionFieldSpec formSectionFieldSpec : formSectionFieldSpecs) {
			if (!Api.isEmptyString(ids)) {
				ids += ",";
			}

			ids += "'" + formSectionFieldSpec.getSectionFieldSpecId() + "'";
		}

		return getFormSectionFieldSpecValidValuesIn(ids);
	}

	private List<FormSectionFieldSpecValidValue> getFormSectionFieldSpecValidValuesIn(String ids) {
		if (!Api.isEmptyString(ids)) {
			List<FormSectionFieldSpecValidValue> formSectionFieldSpecValidValues = jdbcTemplate
					.query(Sqls.SELECT_FORM_SECTION_FIELD_VALID_VALUES_IN
							.replace(":ids", ids),new BeanPropertyRowMapper<FormSectionFieldSpecValidValue>(
									FormSectionFieldSpecValidValue.class));
			return formSectionFieldSpecValidValues;
		}
		return new ArrayList<FormSectionFieldSpecValidValue>();
	}

	public List<FormSectionFieldSpec> getFormSectionFieldSpecForIn(List<FormSectionSpec> formSectionSpecs) {
		if (formSectionSpecs != null && formSectionSpecs.size() > 0) {
			String ids = "";
			for (FormSectionSpec formSectionSpec : formSectionSpecs) {
				if (!Api.isEmptyString(ids)) {
					ids += ",";
				}
				ids += formSectionSpec.getSectionSpecId();
			}

			List<FormSectionFieldSpec> formSectionFieldSpec = jdbcTemplate
					.query(Sqls.SELECT_FORM_SECTION_FIELD_SPECS_BY_SECTION_IN
							.replace(":ids", ids), new BeanPropertyRowMapper<FormSectionFieldSpec>(
									FormSectionFieldSpec.class));

			return formSectionFieldSpec;

		}

		return new ArrayList<FormSectionFieldSpec>();
	}

	public List<FormSectionSpec> getFormSectionSpecs(long formSpecId) {
		List<FormSectionSpec> formSectionSpecs = jdbcTemplate.query(
				Sqls.SELECT_FORM_SECTION_SPECS, new Object[] { formSpecId },new int [] {Types.BIGINT},
				new BeanPropertyRowMapper<FormSectionSpec>(
						FormSectionSpec.class));

		return formSectionSpecs;
	}
	
	public Map<Long, List<FormSectionFieldSpec>> getFormSectionFieldSpecMap(
			List<FormSectionSpec> formSectionSpecs) {
		Map<Long, List<FormSectionFieldSpec>> map = new HashMap<Long, List<FormSectionFieldSpec>>();

		if (formSectionSpecs != null && formSectionSpecs.size() > 0) {
			String ids = "";
			for (FormSectionSpec formSectionSpec : formSectionSpecs) {
				if (!Api.isEmptyString(ids)) {
					ids += ",";
				}
				ids += formSectionSpec.getSectionSpecId();
			}
			List<FormSectionFieldSpec> formSectionFieldSpec = jdbcTemplate
					.query(Sqls.SELECT_FORM_SECTION_FIELD_SPECS_BY_SECTION_IN
							.replace(":ids", ids),new BeanPropertyRowMapper<FormSectionFieldSpec>(
									FormSectionFieldSpec.class));
			for (FormSectionFieldSpec formSectionFieldSpecTemp : formSectionFieldSpec) {
				List<FormSectionFieldSpec> sectionFieldSpecs = map
						.get(formSectionFieldSpecTemp.getSectionSpecId());
				if (sectionFieldSpecs == null) {
					sectionFieldSpecs = new ArrayList<FormSectionFieldSpec>();
					map.put(formSectionFieldSpecTemp.getSectionSpecId(),
							sectionFieldSpecs);
				}
				sectionFieldSpecs.add(formSectionFieldSpecTemp);
			}
		}
		return map;
	}

	public List<FormFieldSpecValidValue> getFormFieldSpecValidValues(List<FormFieldSpec> formFieldSpecs) {
		StringBuilder ids = new StringBuilder("");
		for (FormFieldSpec formFieldSpec : formFieldSpecs) {
			if (!Api.isEmptyString(ids.toString())) {
				ids.append(",");
			}

			ids.append("'").append(formFieldSpec.getFieldSpecId()).append("'");
		}

		return getFormFieldSpecValidValuesIn(ids.toString());
	}
	
	public List<FormFieldSpecValidValue> getFormFieldSpecValidValuesIn(String ids) {
		if (!Api.isEmptyString(ids)) {
			List<FormFieldSpecValidValue> fieldSpecValidValues = jdbcTemplate.query(Sqls.SELECT_FORM_FIELD_VALID_VALUES_IN.replace(":ids", ids),
							new BeanPropertyRowMapper<FormFieldSpecValidValue>(FormFieldSpecValidValue.class));
			return fieldSpecValidValues;
		}
		return new ArrayList<FormFieldSpecValidValue>();
	}

	public List<FormFieldSpec> getFormFieldSpecs(long formSpecId) {
		List<FormFieldSpec> formFieldSpecs = jdbcTemplate.query(Sqls.SELECT_FORM_FIELD_SPECS,new Object[] {formSpecId},new int[] {Types.BIGINT},
				new BeanPropertyRowMapper<FormFieldSpec>(FormFieldSpec.class));
		return formFieldSpecs;
	}

	public List<FormPageSpec> getFormPageSpecsForFormSpec(String formSpecIds) {
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_FORM_PAGE_SPECS_FOR_FORM_SPEC_IN.replace(
					":ids", formSpecIds);
			List<FormPageSpec> formPageSpecs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<FormPageSpec>(
							FormPageSpec.class));
			return formPageSpecs;
		} else {
			return new ArrayList<>();
		}
	}
	
	public CompanyFont getCompanyFont(long fontId) {
		CompanyFont companyFont = jdbcTemplate.queryForObject(
				Sqls.SELECT_COMPANY_FONT, new Object[] { fontId },new int[] {Types.BIGINT},
				new BeanPropertyRowMapper<CompanyFont>(CompanyFont.class));
		return companyFont;
	}

	public List<FormFieldSpecsExtra> getFormFieldSpecsExtraForIn(String ids) {
		if (!Api.isEmptyString(ids)) {
			List<FormFieldSpecsExtra> formFieldSpecsExtra = jdbcTemplate.query(
					Sqls.SELECT_FORM_FIELD_SPECS_EXTRA_IN.replace(":ids", ids), new BeanPropertyRowMapper<FormFieldSpecsExtra>(FormFieldSpecsExtra.class));
			return formFieldSpecsExtra;
		}
		return new ArrayList<FormFieldSpecsExtra>();
	}

	public List<FormSectionFieldSpecsExtra> getFormSectionFieldSpecsForIn(String ids)
	{
		if (!Api.isEmptyString(ids)) {
			List<FormSectionFieldSpecsExtra> formSectionFieldSpecsExtra = jdbcTemplate.query(
					Sqls.SELECT_FORM_SECTION_FIELD_SPECS_EXTRA_IN.replace(":ids", ids), new BeanPropertyRowMapper<FormSectionFieldSpecsExtra>(
							FormSectionFieldSpecsExtra.class));
			return formSectionFieldSpecsExtra;
		}
		return new ArrayList<FormSectionFieldSpecsExtra>();
	}

	public List<RemainderFieldsMap> getRemainderFieldsMapForFormSpecs(String formSpecIds) {
		List<RemainderFieldsMap> remainderFieldsMap = new ArrayList<RemainderFieldsMap>();
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_REMAINDER_FIELDS_MAP_FOR_FORMSPECS
					.replace(":formSpecIds", formSpecIds);
			remainderFieldsMap = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<RemainderFieldsMap>(
							RemainderFieldsMap.class));
		}
		return remainderFieldsMap;
	}

	public List<CustomerAutoFilteringCritiria> getCustomerAutoFilteringCritiriasForFormSpecs(String formSpecIds) {
		List<CustomerAutoFilteringCritiria> customerAutoFilteringCritirias = new ArrayList<CustomerAutoFilteringCritiria>();
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_CUSTOMER_AUTO_FILTERING_FOR_FORMSPECS
					.replace(":formSpecIds", formSpecIds);
			customerAutoFilteringCritirias = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<CustomerAutoFilteringCritiria>(
							CustomerAutoFilteringCritiria.class));
		}
		return customerAutoFilteringCritirias;
	}

	public List<FormFilteringCritiria> getFormFilteringCritiriasForFormSpecs(String formSpecIds) {
		List<FormFilteringCritiria> formFilteringCritiria = new ArrayList<FormFilteringCritiria>();
		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_FORM_FILTERING_CRITIRIA_FOR_FORMSPECS
					.replace(":formSpecIds", formSpecIds);
			formFilteringCritiria = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<FormFilteringCritiria>(
							FormFilteringCritiria.class));
		}
		return formFilteringCritiria;
	}

	public List<CustomEntityFilteringCritiria> getListOfCustomEntityFilteringCritiriaForFormSpecs(String formSpecIds) {
		List<CustomEntityFilteringCritiria> customEntityFilteringCritiria = new ArrayList<CustomEntityFilteringCritiria>();
		if(!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_CUSTOM_ENTITY_FILTERING_CRITIRIA_FOR_FORMSPECS.replace(":formSpecIds", formSpecIds);
			customEntityFilteringCritiria = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<CustomEntityFilteringCritiria>(CustomEntityFilteringCritiria.class));
		}
		return customEntityFilteringCritiria;
	}

	public List<StockFormConfiguration> getStockFieldConfigurationsForSync(String formSpecIds) {
		List<StockFormConfiguration> stockFormConfigurations = new ArrayList<StockFormConfiguration>();
		
		if(!Api.isEmptyString(formSpecIds))
		{
			String sql = Sqls.SELECT_STOCK_FORM_CONFIGURATIONS_FOR_FORMSPEC_IDS.replace(":formSpecIds", formSpecIds);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<StockFormConfiguration>(StockFormConfiguration.class));
		}
		return stockFormConfigurations;
	}

	public List<OfflineListUpdateConfiguration> getOfflineListUpdateConfigurationsForSync(String formSpecIds) {
		List<OfflineListUpdateConfiguration> offlineListUpdateConfiguration = new ArrayList<OfflineListUpdateConfiguration>();
		
		if(!Api.isEmptyString(formSpecIds))
		{
			String sql = Sqls.SELECT_OFFLINE_LIST_UPDATE_FORM_CONFIGURATIONS_FOR_FORMSPEC_IDS.replace(":formSpecIds", formSpecIds);
			return jdbcTemplate.query(sql, 	new BeanPropertyRowMapper<OfflineListUpdateConfiguration>(OfflineListUpdateConfiguration.class));
		}
		return offlineListUpdateConfiguration;
	}

	public List<OfflineCustomEntityUpdateConfiguration> getOfflineCustomEntityUpdateConfigurationForSync(
			String formSpecIds) {
		List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfiguration = new ArrayList<OfflineCustomEntityUpdateConfiguration>();
		
		if(!Api.isEmptyString(formSpecIds)) {
			String sql=Sqls.SELECT_OFFLINE_CUSTOM_ENTITY_UPDATE_FORM_CONFIGURATIONS_FOR_FORMSPEC_IDS.replace(":formSpecIds", formSpecIds);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OfflineCustomEntityUpdateConfiguration>(OfflineCustomEntityUpdateConfiguration.class));
		}
		return offlineCustomEntityUpdateConfiguration;
	}

	public List<FieldSpecFilter> getFieldSpecFiltersFormFormSpecIds(String formSpecIds, int formfieldType) {
		String sql = "";
		if (formfieldType == FieldSpecFilter.FIELD_IS_FORMFIELD) {
			sql = Sqls.SELECT_FORM_FIELD_SPEC_FILTERS_FORMSPECIDS.replace(":formSpecIds", formSpecIds);
		} else {
			sql = Sqls.SELECT_FORM_SECTION_FIELD_SPEC_FILTERS_FORMSPECIDS.replace(":formSpecIds", formSpecIds);
		}
		List<FieldSpecFilter> fieldSpecFilters = jdbcTemplate
				.query(sql,	new BeanPropertyRowMapper<FieldSpecFilter>(FieldSpecFilter.class));
		return fieldSpecFilters;
	}

	public List<PaymentMapping> getPaymentMappingByFormSpec(String uniqueIds) {
		List<PaymentMapping> paymentMappings=new ArrayList<PaymentMapping>();
		String sql = Sqls.SELECT_PAYMENT_MAPPINGS_BY_FORMSPEC.replace(":formSpecUniqueIds", uniqueIds);
		
		paymentMappings=jdbcTemplate.query(sql, new BeanPropertyRowMapper<PaymentMapping>(PaymentMapping.class));

		return paymentMappings;
	}

	public List<FormSpecDataSource> getFormSpecDataSourceUsingUniqueId(String formSpecUniqueIdCsv) {
		List<FormSpecDataSource> formSpecDatSource = new ArrayList<FormSpecDataSource>();
		if(!Api.isEmptyString(formSpecUniqueIdCsv)){
		String sql = Sqls.SELECT_FORMSPEC_DATA_SOURCE.replace(":uniqueId",formSpecUniqueIdCsv);
		formSpecDatSource = jdbcTemplate.query(sql, new BeanPropertyRowMapper<FormSpecDataSource>(FormSpecDataSource.class)); 
		}
		return formSpecDatSource;
	}

	public List<DataSourceRequestHeader> getDataSourceRequestHeaders(String formSpecDataSourceIds) {
		String sql = Sqls.SELECT_DATA_SOURCE_REQUEST_HEADERS.replace(":formSpecDataSourceIds", formSpecDataSourceIds);
		List<DataSourceRequestHeader> dataSourceRequestHeader = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<DataSourceRequestHeader>(DataSourceRequestHeader.class));
		return dataSourceRequestHeader ;
	}

	public List<DataSourceRequestParam> getDataSourceRequestParam(
			String formSpecDataSourceIds) {
		String sql = Sqls.SELECT_DATA_SOURCE_REQUEST_PARAMS.replace(":formSpecDataSourceIds", formSpecDataSourceIds);
		List<DataSourceRequestParam> dataSourceRequestParams = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DataSourceRequestParam>(DataSourceRequestParam.class));
		return dataSourceRequestParams ;
	}

	public List<DataSourceResponseMapping> getDataSourceResponseMapping(
			String formSpecDataSourceIds) {
		String sql = Sqls.SELECT_DATA_SOURCE_RESPONSE_MAPPING.replace(":formSpecDataSourceIds", formSpecDataSourceIds);
		List<DataSourceResponseMapping> dataSourceResponseMapping = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<DataSourceResponseMapping>(DataSourceResponseMapping.class));
		return dataSourceResponseMapping ;
	}

	public List<FormSpecPermission> getFormSpecPermissions(String uniqueId, boolean check) {
		List<FormSpecPermission> formSpecPermission = new ArrayList<FormSpecPermission>();
		String sql = "";
		if(check)
		{
			sql = Sqls.SELECT_FORM_SPEC_PERMISSIONS.replace(":AND", "AND enable = 1");
		}else
		{
			sql = Sqls.SELECT_FORM_SPEC_PERMISSIONS.replace(":AND", "");
		}
		try {
			formSpecPermission = jdbcTemplate.query(sql, new Object[] {uniqueId}, new int[] {Types.VARCHAR},
					new BeanPropertyRowMapper<FormSpecPermission>(FormSpecPermission.class));
		} catch (Exception e) {
			LOGGER.info("getFormSpecPermissions formSpecUniqueId :" + uniqueId +" Exception : "+e);
			LOGGER.error("getFormSpecPermissions formSpecUniqueId :" + uniqueId +" Exception : "+e);
			e.printStackTrace();
		}
		return formSpecPermission;
	}
	
	public List<FormSpecConfigSaveOnOtpVerify> getFormSpecConfigSaveOnOtpVerifyList(String uniqueIdsCsv) {
		String sql = Sqls.SELECT_FORMSPEC_CONFIG_SAVE_ON_OTP_VERIFY.replace(":formSpecUniqueIds", uniqueIdsCsv);
		List<FormSpecConfigSaveOnOtpVerify> formSpecConfigSaveOnOtpVerifyList = new ArrayList<>();
		if (!Api.isEmptyString(uniqueIdsCsv)) {
			formSpecConfigSaveOnOtpVerifyList = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<FormSpecConfigSaveOnOtpVerify>(FormSpecConfigSaveOnOtpVerify.class));
		} 
		return formSpecConfigSaveOnOtpVerifyList;
	}

	public List<JobFormMapBean> getJobFormMapBeans(String formSpecIds, String syncDate) {
		if (Api.isEmptyString(syncDate)) {
			syncDate = "1970-01-01T00:00:00Z";
		}
		syncDate = Api.getDateTimeFromXsd(syncDate);

		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_JOB_FORMFIELD_MAPPING.replace(
					":formSpecIds", formSpecIds);
			return jdbcTemplate.query(sql, new Object[] { syncDate },new int[] {Types.VARCHAR},
					new BeanPropertyRowMapper<JobFormMapBean>(
							JobFormMapBean.class));
		}
		return new ArrayList<JobFormMapBean>();
	}

	public List<WorkSpecFormSpecFollowUp> getWorkSpecFormSpecFollowUpForSync(String formSpecIds, String syncDate) {
		if (Api.isEmptyString(syncDate)) {
			syncDate = "1970-01-01T00:00:00Z";
		}
		syncDate = Api.getDateTimeFromXsd(syncDate);

		if (!Api.isEmptyString(formSpecIds)) {
			String sql = Sqls.SELECT_WORKSPEC_FORMSPEC_FOLLOW_UP_FOR_SYNC.replace(
					":formSpecIds", formSpecIds);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<WorkSpecFormSpecFollowUp>(
							WorkSpecFormSpecFollowUp.class));
		}
		return new ArrayList<WorkSpecFormSpecFollowUp>();
	}

	public List<WorkFormFieldMap> getWorkFormFieldMappingByWorkSpecFormSpecFollowUpIds(
			String workSpecFormSpecFollowUpIds) {
		if(!Api.isEmptyString(workSpecFormSpecFollowUpIds))
		{
			String sql = Sqls.SELECT_WORK_FORM_FIELD_MAPS_BY_WORKSPEC_FORMSPEC_FOLLOW_UP_IDS.replace(
					":workSpecFormSpecFollowUpIds", workSpecFormSpecFollowUpIds);;
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<WorkFormFieldMap>(
							WorkFormFieldMap.class));
		}
		return new ArrayList<WorkFormFieldMap>();
	}

	public WorkSpec getWorkSpecByWorkSpecId(String workSpecId) {
		WorkSpec workSpec = null;
		try {
			workSpec = jdbcTemplate.queryForObject(Sqls.SELECT_WORK_SPEC,
					new Object[] { workSpecId }, new int[] {Types.INTEGER},
					new BeanPropertyRowMapper<WorkSpec>(WorkSpec.class));
		}catch(Exception e) {
			LOGGER.info("Got Exception while getWorkSpecByWorkSpecId : "+e);
		}
		return workSpec;
	}

	public List<WorkProcessSubTaskSpec> getWorkPrcocessSubTaskSpecsForSync(String parentWorkSpecIds) {
		List<WorkProcessSubTaskSpec> workProcessSubTaskSpecs = new ArrayList<WorkProcessSubTaskSpec>();
		try
		{
			if(!Api.isEmptyString(parentWorkSpecIds))
			{
				String sql = Sqls.SELECT_WORK_PROCESS_SUB_TASK_SPEC_BY_PARENT_WORKSPEC_IDS_FOR_SYNC
						.replace(":workSpecIds", parentWorkSpecIds);
				workProcessSubTaskSpecs = jdbcTemplate.query(
						sql,new BeanPropertyRowMapper<WorkProcessSubTaskSpec>(
								WorkProcessSubTaskSpec.class));
				
			}
			return workProcessSubTaskSpecs;
		}
		catch(Exception e)
		{
			return workProcessSubTaskSpecs;
		}
	}

	public List<WorkSpec> getWorkSpecsForWorkSpecIdsIn(String subTaskWorkSpecIds) {
		List<WorkSpec> subTaskWorkSpecs = new ArrayList<WorkSpec>();
		try
		{
			if(!Api.isEmptyString(subTaskWorkSpecIds))
			{
				String sql = Sqls.SELECT_WORK_SPECS_FOR_WORK_SPEC_IDS_IN.replace(":subTaskWorkSpecIds", subTaskWorkSpecIds);
				subTaskWorkSpecs =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<WorkSpec>(WorkSpec.class));
			}
			return subTaskWorkSpecs;
		}
		catch(Exception e)
		{
			return subTaskWorkSpecs;
		}
	}

	public List<WorkToSubTaskAutoFillConfiguration> getWorkToSubTaskAutoFillConfirationsForSync(
			List<WorkSpec> workSpecs, int companyId)
	{
		String workSpecIdsCsv = Api.toCSV(workSpecs, "workSpecId",CsvOptions.FILTER_NULL_OR_EMPTY);
		List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations = new ArrayList<WorkToSubTaskAutoFillConfiguration>();
		if(!Api.isEmptyString(workSpecIdsCsv))
		{
			try
			{
				String sql = Sqls.SELECT_WORK_TO_SUB_TASK_AUTO_FILL_CONFIGURATIONS_FOR_SYNC.replace(":parentWorkSpecIds",workSpecIdsCsv );
				workToSubTaskAutoFillConfigurations = jdbcTemplate.query(sql,
						new Object[] {companyId}, new int[] {Types.INTEGER}, new BeanPropertyRowMapper<WorkToSubTaskAutoFillConfiguration>(
								WorkToSubTaskAutoFillConfiguration.class));
			}
			catch(Exception e)
			{
				LOGGER.info("Got Exception in getWorkToSubTaskAutoFillConfirationsForSync :"+e);
			}
	
		}
		return workToSubTaskAutoFillConfigurations;
	}

	public List<AddingSubTaskEmployeeConfiguration> getAddingSubTaskEmployeeConfigurationsForSync(
			String workProcessSubTaskSpecIdsCsv) {
		List<AddingSubTaskEmployeeConfiguration> subTaskEmpConf = new ArrayList<AddingSubTaskEmployeeConfiguration>();
		try
		{
			if(!Api.isEmptyString(workProcessSubTaskSpecIdsCsv))
			{
				String sql = Sqls.SELECT_ADDING_SUB_TASK_EMPLOYEE_CONFIGURATION_BY_WORK_PROCESS_SUB_TASK_SPEC_IDS
						.replace(":workProcessSubTaskSpecIds", workProcessSubTaskSpecIdsCsv);
				subTaskEmpConf = jdbcTemplate.query(
						sql,new BeanPropertyRowMapper<AddingSubTaskEmployeeConfiguration>(
								AddingSubTaskEmployeeConfiguration.class));
			}
			return subTaskEmpConf;
		}
		catch(Exception e)
		{
			LOGGER.info("got exception while getAddingSubTaskEmployeeConfigurationsForSync : "+ e );
			return subTaskEmpConf;
		}
	}

	public List<HideAddSubTaskConfiguration> getHideAddSubTaskConfigurationForSync(String workSpecIds) {
		List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations = new ArrayList<HideAddSubTaskConfiguration>();
		try
		{
			if(!Api.isEmptyString(workSpecIds))
			{
				String sql = Sqls.SELECT_HIDE_ADD_SUB_TASK_CONFIGURATION_BY_WORK_PROCESS_SUB_TASK_SPEC_IDS
						.replace(":parentWorkSpecId", workSpecIds);
				hideAddSubTaskConfigurations = jdbcTemplate.query(
						sql,new BeanPropertyRowMapper<HideAddSubTaskConfiguration>(
								HideAddSubTaskConfiguration.class));
			}
			return hideAddSubTaskConfigurations;
		}
		catch(Exception e)
		{
			LOGGER.info("got exception while getHideAddSubTaskConfigurationForSync : "+ e );
			return hideAddSubTaskConfigurations;
		}
	}

	public List<WorkActionSpec> getWorkActionSpecsWithAllocationConfigForSync(String workSpecIdsCsv) {

		List<WorkActionSpec> workActionSpecs = getWorkActionSpecs(workSpecIdsCsv);
		try {
			String workActionSpecIds = Api.toCSV(workActionSpecs, "workActionSpecId");
			List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList = getWorkActionSpecVisibilityConditionList(workActionSpecIds);
			for (WorkActionSpec workActionSpec : workActionSpecs) {
				List<WorkActionSpecVisibilityCondition> workActionConditionList = new ArrayList<WorkActionSpecVisibilityCondition>();
				for (WorkActionSpecVisibilityCondition workActionSpecVisibilityCondition : workActionSpecVisibilityConditionList) {
					if (workActionSpecVisibilityCondition.getWorkActionSpecId()
							.equals(workActionSpec.getWorkActionSpecId())) {
						workActionConditionList.add(workActionSpecVisibilityCondition);
					}
				}
				workActionSpec.setWorkActionSpecVisibilityConditionList(workActionConditionList);
				
				// sending other action visibility config table records.
				List<WorkActionVisibilitySpecs> workActionVisibilitySpecs = getWorkActionVisibilitySpecsBasedOnWorkSpecIdAndActionSpecId(workActionSpec);
				if(workActionVisibilitySpecs.size()>0)
				{
					workActionSpec.setWorkActionVisibilitySpecs(workActionVisibilitySpecs);
				}
				else
				{
					workActionSpec.setWorkActionVisibilitySpecs(new ArrayList<WorkActionVisibilitySpecs>());
				}
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workActionSpecs;
	
	}
	
	private List<WorkActionVisibilitySpecs> getWorkActionVisibilitySpecsBasedOnWorkSpecIdAndActionSpecId(
			WorkActionSpec workActionSpec) {
		try {
			String sql = Sqls.SELECT_WORK_ACTION_VISIBILITY_SPECS_BASED_ON_WORK_SPECID_AND_ACTION_SPECID;
			
			return jdbcTemplate.query(sql,	new Object[] { workActionSpec.getWorkSpecId(),workActionSpec.getWorkActionSpecId() },
					new int[] {Types.BIGINT,Types.BIGINT},
					new BeanPropertyRowMapper<WorkActionVisibilitySpecs>(WorkActionVisibilitySpecs.class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<WorkActionSpecVisibilityCondition> getWorkActionSpecVisibilityConditionList(String workActionSpecsIds) {
		List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList = new ArrayList<WorkActionSpecVisibilityCondition>();
		if (!Api.isEmptyString(workActionSpecsIds)) {
			String query = Sqls.SELECT_FROM_WORKACTIONSPECVISIBILITYCONDITION.replace(":workActionSpecIds", workActionSpecsIds);
			try {
				workActionSpecVisibilityConditionList = jdbcTemplate
						.query(query, new BeanPropertyRowMapper<WorkActionSpecVisibilityCondition>(
										WorkActionSpecVisibilityCondition.class));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return workActionSpecVisibilityConditionList;
	}

	public List<WorkActionSpec> getWorkActionSpecs(String workSpecIds) {

		List<WorkActionSpec> workActionSpecs = new ArrayList<WorkActionSpec>();
		if (!Api.isEmptyString(workSpecIds)) {
			String sql = Sqls.SELECT_WORK_ACTION_SPECS_WITH_EMPGRPIDS.replace(":workSpecIds",workSpecIds);
			workActionSpecs = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<WorkActionSpec>(WorkActionSpec.class));
		}

		return workActionSpecs;
	}

	public List<NextActionSpec> getNextActionSpecs(String actionSpecIds) {
		if (!Api.isEmptyString(actionSpecIds)) {
			String sql = Sqls.SELECT_NEXT_ACTION_SPECS.replace(
					":actionSpecIds", actionSpecIds);
			return jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<NextActionSpec>(
							NextActionSpec.class));
		}
		return new ArrayList<NextActionSpec>();
	}
	
	public List<NextWorkSpec> getNextWorkSpecs(String actionSpecIds,
			String workSpecIds) {

		if (!Api.isEmptyString(actionSpecIds)) {
			String sql = Sqls.SELECT_NEXT_WORK_SPECS.replace(":actionSpecIds",
					actionSpecIds).replaceAll(":workSpecIds", workSpecIds);
			return jdbcTemplate
					.query(sql, new BeanPropertyRowMapper<NextWorkSpec>(
							NextWorkSpec.class));
		}

		return new ArrayList<NextWorkSpec>();
	}
	
	public List<WorkSpecFormSpecMap> getWorkSpecFormSpecMaps(
			String workSpecIdsCsv) {
		if (!Api.isEmptyString(workSpecIdsCsv)) {
			String sql = Sqls.SELECT_WORKSPEC_FORMSPEC_MAPS.replace(
					":workSpecIds", workSpecIdsCsv);
			return jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<WorkSpecFormSpecMap>(
							WorkSpecFormSpecMap.class));
		}
		return new ArrayList<WorkSpecFormSpecMap>();
	}
	
	public List<WorkActionSpecConditions> getWorkActionSpecConditions(
			String actionSpecIds) {
		if (!Api.isEmptyString(actionSpecIds)) {
			String sql = Sqls.SELECT_WORK_ACTION_SPEC_CONDITIONS.replace(
					":actionSpecIds", actionSpecIds);
			return jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<WorkActionSpecConditions>(
							WorkActionSpecConditions.class));
		}
		return new ArrayList<WorkActionSpecConditions>();
	}
	
	public List<WorkActionSpecEndCondition> getWorkActionSpecEndConditionsForWorkActionSpecIds(
			String workActionSpecIds) 
	{
		String sql = Sqls.SELECT_WORK_ACTION_SPEC_END_CONDITION_BY_ACTION_SPEC_IDS.replace(":workActionSpecIds", workActionSpecIds);
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<WorkActionSpecEndCondition>(WorkActionSpecEndCondition.class));
	}
	
	public List<WorkActionVisibilityConfiguration> getWorkActionVisibilityConfigurationByCompanyIdForSync
	 (Integer companyId,String workSpecIds){
		
		List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurationList = new ArrayList<WorkActionVisibilityConfiguration>(); 
		try
		{
			if(!Api.isEmptyString(workSpecIds))	
			{
				String sql = Sqls.SELECT_VISIBILITY_CONFIGURATION_FOR_COMPANY_ID_AND_WORKSPECIDS;
				sql = sql.replace(":workSpecIds", workSpecIds);
				workActionVisibilityConfigurationList = jdbcTemplate.query(sql,
						new Object[]{companyId}, new int[] {Types.INTEGER}, new BeanPropertyRowMapper<WorkActionVisibilityConfiguration>(WorkActionVisibilityConfiguration.class));
				return workActionVisibilityConfigurationList;
			}
		}
		catch(Exception e)
		{
			LOGGER.error("getWorkActionVisibilityConfigurationByCompanyIdForSync // Exception occured ",e);
			LOGGER.info("getWorkActionVisibilityConfigurationByCompanyIdForSync // Exception occured ",e);
			e.printStackTrace();
		}
		return workActionVisibilityConfigurationList;
	}
	
	public List<ExternalActionConfiguration> getExternalActionConnfigurationForSync(String actionSpecIds) {
		
		try {
			String sql = Sqls.SELECT_EXTERNAL_ACTION_CONFIGURATION_BY_ACTIONSPEC_IDS.replace(":actionSpecIds", actionSpecIds); 
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ExternalActionConfiguration>(
							ExternalActionConfiguration.class));

		} catch (Exception e) {
			return null;
		}
	
	}
	
	
	public List<WorkFieldsUniqueConfigurations> getWorkFieldsUniqueConfigurationsForSync(
			String workSpecIds, String syncDate, int companyId)
	{
		List<WorkFieldsUniqueConfigurations> WorkFieldsUniqueConfigurations = 
				new ArrayList<WorkFieldsUniqueConfigurations>();
		
		if (!Api.isEmptyString(workSpecIds)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.SELECT_WORK_FIELDS_UNIQUE_CONFIGURATIONS_FOR_SYNC.replace(":workSpecIds",workSpecIds);
			
			WorkFieldsUniqueConfigurations = jdbcTemplate.query(sql,new Object[] {companyId,syncDate}, new int[] { Types.INTEGER,Types.VARCHAR },
					new BeanPropertyRowMapper<WorkFieldsUniqueConfigurations>(WorkFieldsUniqueConfigurations.class));
		
		}

		return WorkFieldsUniqueConfigurations;
	}
	
	
	public List<WorkFieldsUniqueConfigurations> getOpenWorkFieldsUniqueConfigurationsForSync(
			String workSpecIds, String syncDate, int companyId)
	{
		List<WorkFieldsUniqueConfigurations> WorkFieldsUniqueConfigurations = 
				new ArrayList<WorkFieldsUniqueConfigurations>();
		
		if (!Api.isEmptyString(workSpecIds)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.SELECT_OPEN_WORK_FIELDS_UNIQUE_CONFIGURATIONS_FOR_SYNC.replace(":workSpecIds",workSpecIds);
			
			WorkFieldsUniqueConfigurations = jdbcTemplate.query(sql,new Object[] {companyId,syncDate}, new int[] {Types.INTEGER,Types.INTEGER},
					new BeanPropertyRowMapper<WorkFieldsUniqueConfigurations>(WorkFieldsUniqueConfigurations.class));
		
		}

		return WorkFieldsUniqueConfigurations;
	}

	public List<WorkReassignmentRules> getWorkReassignmentRulesForSync(String workSpecIdsCsv, String syncDate) {
		List<WorkReassignmentRules> workReassignmentRules = 
				new ArrayList<WorkReassignmentRules>();
		
		if (!Api.isEmptyString(workSpecIdsCsv)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.SELECT_WORKREASSIGNMENTRULES_FOR_SYNC.replace(":workSpecIds",workSpecIdsCsv);
			
			workReassignmentRules = jdbcTemplate.query(sql,	new BeanPropertyRowMapper<WorkReassignmentRules>(WorkReassignmentRules.class));
		
		}

		return workReassignmentRules;
	}

	public List<WorkSpecAppLabel> getWorkSpecAppLabelsForSync(String workSpecIds, String syncDate, int companyId)
	{
		List<WorkSpecAppLabel> workSpecAppLabels = new ArrayList<WorkSpecAppLabel>();
		
		if (!Api.isEmptyString(workSpecIds)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.SELECT_WORKSPEC_APP_LABELS_FOR_SYNC.replace(":workSpecIds",workSpecIds);
			
			workSpecAppLabels = jdbcTemplate.query(sql,new Object[] {companyId,syncDate},new int[] {Types.INTEGER,Types.VARCHAR},
					new BeanPropertyRowMapper<WorkSpecAppLabel>(WorkSpecAppLabel.class));
		
		}

		return workSpecAppLabels;
	}

	public List<WorkSpecPermission> getWorkSpecPermissionsForSync(String workSpecIds, String syncDate,
			int companyId) {
		List<WorkSpecPermission> workSpecPermissions = new ArrayList<WorkSpecPermission>();
		
		if (!Api.isEmptyString(workSpecIds)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.SELECT_WORKSPECPERMISSIONS_FOR_SYNC.replace(":workSpecIds",workSpecIds);
			
			workSpecPermissions = jdbcTemplate.query(sql,new Object[] {companyId,syncDate},new int[] {Types.INTEGER,Types.VARCHAR},
					new BeanPropertyRowMapper<WorkSpecPermission>(WorkSpecPermission.class));
		
		}

		return workSpecPermissions;
	}

	public List<WorkSpecListLevelVisibilityConfiguration> getWorkSpecListLevelVisibilityConfigurationsForSync(
			String workSpecIds, String syncDate, int companyId) {
		List<WorkSpecListLevelVisibilityConfiguration> listLevelVisibilityConfigurations = new ArrayList<WorkSpecListLevelVisibilityConfiguration>();

		if (!Api.isEmptyString(workSpecIds)) {

			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);

			String sql = Sqls.SELECT_WORK_SPEC_LIST_LEVEL_VISIBILITY_CONFIGURATIONS_FOR_SYNC.replace(":workSpecIds",
					workSpecIds);

			listLevelVisibilityConfigurations = jdbcTemplate.query(sql, new Object[] { companyId, syncDate },new int[] {Types.INTEGER,Types.VARCHAR},
					new BeanPropertyRowMapper<WorkSpecListLevelVisibilityConfiguration>(
							WorkSpecListLevelVisibilityConfiguration.class));

		}

		return listLevelVisibilityConfigurations;
	}

	public List<WorkSpecCustomDashboardConfiguration> getWorkSpecCustomDashboardConfiguration(String workSpecIds,
			String syncDate, int companyId) 
	{
		List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations = new ArrayList<WorkSpecCustomDashboardConfiguration>();

		if (!Api.isEmptyString(workSpecIds)) {

			String sql = Sqls.SELECT_WORK_SPEC_CUSTOM_DASHBOARD_CONFIGURATION.replace(":workSpecIds",
					workSpecIds);

			workSpecCustomDashboardConfigurations = jdbcTemplate.query(sql, new Object[] {companyId},new int[] {Types.INTEGER},
					new BeanPropertyRowMapper<WorkSpecCustomDashboardConfiguration>(
							WorkSpecCustomDashboardConfiguration.class));

		}

		return workSpecCustomDashboardConfigurations;
	}

	public List<WorkSpecCustomDashboardMetric> getWorkSpecCustomDashboardMetrics(String workSpecIds, String syncDate,
			int companyId) {
		List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics = new ArrayList<WorkSpecCustomDashboardMetric>();

		if (!Api.isEmptyString(workSpecIds)) {

			String sql = Sqls.SELECT_WORK_SPEC_CUSTOM_DASHBOARD_METRICS.replace(":workSpecIds",
					workSpecIds);

			workSpecCustomDashboardMetrics = jdbcTemplate.query(sql,new BeanPropertyRowMapper<WorkSpecCustomDashboardMetric>(
							WorkSpecCustomDashboardMetric.class));

		}

		return workSpecCustomDashboardMetrics;
	}
	
	public List<WorkActionFormVisibility> getWorkActionFormVisibilityForSync(
			String workSpecIds, String syncDate, int companyId) {
		List<WorkActionFormVisibility> workActionFormVisibility = new ArrayList<WorkActionFormVisibility>();

		if (!Api.isEmptyString(workSpecIds)) {


			String sql = Sqls.SELECT_WORK_ACTION_FORM_VISIBILITY_BASED_ON_WORKSPECIDS_FOR_SYNC.replace(":workSpecIds",
					workSpecIds);

			workActionFormVisibility = jdbcTemplate.query(sql, new Object[] { companyId },new int[] {Types.INTEGER},
					new BeanPropertyRowMapper<WorkActionFormVisibility>(
							WorkActionFormVisibility.class));

		}

		return workActionFormVisibility;
		}
	
	public List<ActionableEmployeeGroupSpecs> getActionableEmployeeGroupSpecs(String workSpecIds,
			String syncDate, int companyId) {
		List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs = new ArrayList<ActionableEmployeeGroupSpecs>();

		if (!Api.isEmptyString(workSpecIds)) {

			String sql = Sqls.SELECT_ACTIONABLE_EMPLOYEE_GROUP_SPECS_CONFIGURATION.replace(":workSpecIds",
					workSpecIds);

			actionableEmployeeGroupSpecs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActionableEmployeeGroupSpecs>(
							ActionableEmployeeGroupSpecs.class));

		}

		return actionableEmployeeGroupSpecs;
	}
	
	public List<WorkActionNotificationEscalationMatrix> getWorkActionNotificationEscalationMatrix(String workSpecIds,
			String syncDate, int companyId) {
		List<WorkActionNotificationEscalationMatrix> actionableEmployeeGroupSpecs = new ArrayList<WorkActionNotificationEscalationMatrix>();

		if (!Api.isEmptyString(workSpecIds)) {

			String sql = Sqls.SELECT_WORK_ACTION_NOTIFICATION_MATRIX_CONFIGURATION.replace(":workSpecIds",
					workSpecIds);

			actionableEmployeeGroupSpecs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<WorkActionNotificationEscalationMatrix>(
							WorkActionNotificationEscalationMatrix.class));

		}

		return actionableEmployeeGroupSpecs;
	}
	
	public List<WorkUnassignmentCriterias> getWorkUnassignmentCriteriasForWorkSpecs(String workSpecIds,
			int companyId) {
			List<WorkUnassignmentCriterias> workUnassignmentCriterias = new ArrayList<WorkUnassignmentCriterias>();
			if (!Api.isEmptyString(workSpecIds)) {
				String sql = Sqls.SELECT_WORK_UNASSIGNMENT_CRITERIAS_BY_WORKSPECID.replace(":workSpecIds",workSpecIds);
				workUnassignmentCriterias = jdbcTemplate.query(sql,new Object[] {companyId},new int[] {Types.INTEGER},
						new BeanPropertyRowMapper<WorkUnassignmentCriterias>(WorkUnassignmentCriterias.class));
			}
			return workUnassignmentCriterias;
	}
	
	public List<WorkAssignmentCriteriaConditions> getWorkAssignmentCriteriaConditionsByCriteriaIds(
			String criteriaIdCsv) {
		String sql = Sqls.SELECT_WORK_ASSIGNMENT_CRITERIA_CONDITIONS_BY_CRITERIA_IDS.replace(":criteriaIds",
				criteriaIdCsv);
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<WorkAssignmentCriteriaConditions>(WorkAssignmentCriteriaConditions.class));
	}
	
	public List<WorkSpecCustomerCallApi> getWorkSpecCustomerCallApis(String workSpecIds) {
		String sql = Sqls.SELECT_WORK_SPEC_CUSTOMER_CALL_APIS.replace(":workSpecIds",workSpecIds);
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<WorkSpecCustomerCallApi>(WorkSpecCustomerCallApi.class));
	}
	
	public List<WorkActionGroup> getWorkActionGroupByWorkSpecIdsForSync(String workSpecIds) {
		if(Api.isEmptyString(workSpecIds))
			return new ArrayList<WorkActionGroup>();
		
		return jdbcTemplate.query(Sqls.SELECT_WORK_ACTION_GROUP_BY_WORK_SPEC_IDS_SYNC.replace(":workSpecIds", workSpecIds),
				    new BeanPropertyRowMapper<WorkActionGroup>(WorkActionGroup.class));
	}
	
	public List<FormSpec> getLatestFormSpecsForUnquids(String uniqueIds) {

		if (!Api.isEmptyString(uniqueIds)) {
			   String formSpecUniqueId = Api.processStringValuesList1(Api.csvToList(uniqueIds));
			String sql = Sqls.SELECT_LATEST_FORM_SPECS.replace(":uniqueIds",
					formSpecUniqueId);
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<FormSpec>(
					FormSpec.class));
		}
		return new ArrayList<FormSpec>();
	}
	
	public List<FormFieldSpec> getFormFieldSpecForIn(String ids) {
		if (!Api.isEmptyString(ids)) {
			List<FormFieldSpec> formFieldSpecs = jdbcTemplate.query(
					Sqls.SELECT_FORM_FIELD_SPECS_IN.replace(":ids", ids), new BeanPropertyRowMapper<FormFieldSpec>(
							FormFieldSpec.class));
			return formFieldSpecs;
		}

		return new ArrayList<FormFieldSpec>();
	}
	
	public List<FormSectionSpec> getFormSectionSpecForIn(
			List<FormSpec> formSpecs) {
		if (formSpecs != null && formSpecs.size() > 0) {
			String ids = "";
			for (FormSpec formSpec : formSpecs) {
				if (!Api.isEmptyString(ids)) {
					ids += ",";
				}
				ids += formSpec.getFormSpecId();
			}

			List<FormSectionSpec> formSectionSpecs = jdbcTemplate.query(
					Sqls.SELECT_FORM_SECTION_SPECS_IN.replace(":ids", ids),
					new BeanPropertyRowMapper<FormSectionSpec>(
							FormSectionSpec.class));

			return formSectionSpecs;
		}

		return new ArrayList<FormSectionSpec>();
	}
	
	 public List<FormSpecPermission> geFormSpecPermissionsForSync(
			 String syncDate,String formSpecIds,String formSpecUniqueIdsCsv)
	{
		List<FormSpecPermission> formSpecPermissions = new ArrayList<FormSpecPermission>();
		String uniqueIds = Api.processStringValuesList1(Api.csvToList(formSpecUniqueIdsCsv));
		if (!Api.isEmptyString(formSpecIds)) {
			
			if (Api.isEmptyString(syncDate)) {
				syncDate = "1970-01-01T00:00:00Z";
			}
			syncDate = Api.getDateTimeFromXsd(syncDate);
			
			String sql = Sqls.FORM_SPEC_PERMISSIONS_FOR_SYNC.replace(":uniqueIds",uniqueIds);
			
			/*formSpecPermissions = jdbcTemplate.query(sql,new Object[] {companyId,syncDate},
					new BeanPropertyRowMapper<FormSpecPermission>(FormSpecPermission.class));*/
			formSpecPermissions = jdbcTemplate.query(sql,new BeanPropertyRowMapper<FormSpecPermission>(FormSpecPermission.class));
		
		}

		return formSpecPermissions;
	}
	
}
