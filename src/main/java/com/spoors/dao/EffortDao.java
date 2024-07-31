package com.spoors.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spoors.setting.Sqls;
import com.spoors.util.Api;

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

	public List<FormPageSpec> getFormPageSpecsForFormSpec(long formSpecId) {
		return getFormPageSpecsForFormSpec(formSpecId + "");
	}

	private List<FormPageSpec> getFormPageSpecsForFormSpec(String formSpecIds) {
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

	
	
	
}
