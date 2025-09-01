package com.spoors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.objectweb.asm.Type;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.spoors.beans.AutoGenereteSequenceSpecConfiguaration;
import com.spoors.beans.AutoGenereteSequenceSpecConfiguarationField;
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
import com.spoors.beans.EntityFieldSpecValidValue;
import com.spoors.beans.EntitySectionField;
import com.spoors.beans.EntitySectionFieldSpec;
import com.spoors.beans.EntitySectionSpec;
import com.spoors.beans.EntitySpec;
import com.spoors.beans.FieldSpecFilter;
import com.spoors.beans.FieldValidation;
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
	
	@Autowired
	public Constants constants;
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
	    if (formSpecIds == null || formSpecIds.trim().isEmpty()) {
	        return Collections.emptyList();
	    }
	    String sql = Sqls.SELECT_FORM_CLEAN_UP_RULES_BY_FORM_SPEC_ID.replace(":formSpecIds", formSpecIds);
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FormCleanUpRule.class));
	}

	public List<FormFieldsColorDependencyCriterias> getFormFieldsColorDependencyCriteriasByFormSpecId(String formSpecIds) {
		if (formSpecIds == null || formSpecIds.trim().isEmpty()) {
	        return Collections.emptyList();
	    }
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
	    if (formSpecIds == null || formSpecIds.trim().isEmpty()) {
	        return Collections.emptyList(); 
	    }

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
		if (formSpecIds == null || formSpecIds.trim().isEmpty()) {
	        return Collections.emptyList(); // safe exit
	    }
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
		   if (uniqueIds == null || uniqueIds.trim().isEmpty()) {
		        return Collections.emptyList();
		    }
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
	public List<EntitySpec> getEntitySpecsForCompany(long companyId,
			Integer showType) {
		String sql = Sqls.SELECT_ENTITY_SPEC_COMPANY;
		if (showType != null) {
			sql = sql.replace(":condition", " AND `isSystemDefined`="
					+ showType);
		} else {
			sql = sql.replace(":condition", "");
		}
		List<EntitySpec> entitySpecs = jdbcTemplate.query(sql,
				new Object[] { companyId },new int[] {Types.BIGINT},
				new BeanPropertyRowMapper<EntitySpec>(EntitySpec.class));
		return entitySpecs;
	}
	public List<CustomEntitySpec> getCustomEntitySpecs(Integer companyId) 
	{
		List<CustomEntitySpec> customEntitySpecs = new ArrayList<CustomEntitySpec>();
		 try{
			  String sql=Sqls.SELECT_CUSTOM_ENTITY_SPECS;
			  customEntitySpecs = jdbcTemplate
						.query(sql,new Object[] {companyId},new int[] {Types.INTEGER},
								new BeanPropertyRowMapper<CustomEntitySpec>(CustomEntitySpec.class));
		 }catch(Exception e){
		 }
		return customEntitySpecs;
	}
	public WebUser getWebUserEmp(long empId) {
		WebUser webUser = jdbcTemplate.queryForObject(Sqls.SELECT_WEB_USER_EMP, new Object[] { empId },new int[] {Types.BIGINT},
				new BeanPropertyRowMapper<WebUser>(WebUser.class));
		webUser.setType(WebUser.TYPE_EMPLOYEE);
		return webUser;
	}
	public Long getRootEmployeeId(long companyId) {
		Long rootEmpId = null;
		try {
			rootEmpId = Long.parseLong(getCompanySetting(companyId,
					constants.getRootEmpKey()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rootEmpId;
	}
	public String getCompanySetting(long companyId, String key){
		
//		String value = null;
//		try{
		String	value = jdbcTemplate.queryForObject(Sqls.SELECT_COMPANY_SETTINGS, new Object[] { key, companyId },new int[] {Types.VARCHAR,Types.BIGINT}, String.class);
//		}catch(Exception e){
			
//		}
		return value;
	}
	public Employee getEmployeeBasicDetailsAndTimeZoneByEmpId(String empId) {
		Employee employee = null;
		try {
			employee = jdbcTemplate.queryForObject(Sqls.SELECT_EMPLOYEE_BASIC_DETAILS_AND_TIME_ZONE_BY_ID, new Object[] { empId },new int[] {Types.VARCHAR},
					new BeanPropertyRowMapper<Employee>(Employee.class));
		} catch (Exception e) {
			//StackTraceElement[] stackTrace = e.getStackTrace();
			//getWebExtensionManager().sendExceptionDetailsMail("Exception Occured in getEmployeeBasicDetailsAndTimeZoneByEmpId method",e.toString(),stackTrace,0);
			e.printStackTrace();
		}
		return employee;
	}
	public List<EntityFieldSpec> getEntityFieldSpecs(String ids) {
		if (!Api.isEmptyString(ids)) {
			String sql = Sqls.SELECT_ENTITY_FIELD_SPECS_IN.replace(":ids", ids);
			List<EntityFieldSpec> entityFieldSpecs = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<EntityFieldSpec>(
							EntityFieldSpec.class));
			return entityFieldSpecs;
		}

		return new ArrayList<EntityFieldSpec>();
	}
	
	public List<EntityFieldSpec> getEntityFieldSpecs(
			List<EntitySpec> entitySpecs) {
		String ids = "";
		for (EntitySpec entitySpec : entitySpecs) {
			if (!Api.isEmptyString(ids)) {
				ids += ",";
			}

			ids += "'" + entitySpec.getEntitySpecId() + "'";
		}

		return getEntityFieldSpecs(ids);
	}
	
	
	public EntitySpec getEntitySpec(String id) {
		EntitySpec entitySpec = null;
		try{
		 entitySpec = jdbcTemplate.queryForObject(
				Sqls.SELECT_ENTITY_SPEC, new Object[] { id }, new int[] {Types.VARCHAR},
				new BeanPropertyRowMapper<EntitySpec>(EntitySpec.class));
		return entitySpec;
		}catch (Exception e) {
			return entitySpec;
		}
		
	}
	
	public List<EntityFieldSpec> getEntityFieldSpecs(long entitySpecId) {
		List<EntityFieldSpec> entityFieldSpecs = jdbcTemplate.query(
				Sqls.SELECT_ENTITY_FIELD_SPECS, new Object[] { entitySpecId },new int[] {Types.BIGINT},
				new BeanPropertyRowMapper<EntityFieldSpec>(
						EntityFieldSpec.class));
		return entityFieldSpecs;
	}
	
	public long insertEntitySpec(final EntitySpec entitySpec,
			final long companyId, final long by ) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_ENTITY_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, companyId);
				ps.setString(2, entitySpec.getEntityTitle());
				ps.setLong(3, by);
				ps.setBoolean(4, entitySpec.isPublic());
				if (entitySpec.getParentId() == null) {
					ps.setNull(5, Types.BIGINT);
				} else {
					ps.setLong(5, entitySpec.getParentId());
				}
				ps.setString(6, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(7, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setInt(8, entitySpec.getIsSystemDefined());
				if (entitySpec.getSkeletonEntitySpecId() == null) {
					ps.setNull(9, Types.BIGINT);
				} else {
					ps.setLong(9, entitySpec.getSkeletonEntitySpecId());
				}

				ps.setLong(10, entitySpec.getPurpose());
				
				ps.setBoolean(11, entitySpec.isSyncAll());
				
                if(entitySpec.getExternalId()!=null){
                	ps.setString(12, entitySpec.getExternalId());
                }else{
                	ps.setNull(12, Types.VARCHAR);
                }
                if(entitySpec.getSyncScope() == null){
                	ps.setInt(13, 2);
                }else{
                	ps.setInt(13, entitySpec.getSyncScope());
                }
                if (entitySpec.getFilteredEntitySpecId() == null) {
					ps.setNull(14, Types.BIGINT);
				} else {
					ps.setLong(14, entitySpec.getFilteredEntitySpecId());
				}
                if (entitySpec.getFilteredEntityFieldSpecId() == null) {
					ps.setNull(15, Types.BIGINT);
				} else {
					ps.setLong(15, entitySpec.getFilteredEntityFieldSpecId());
				}
            	//ps.setInt(13, entitySpec.getSyncScope());

				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		entitySpec.setEntitySpecId(id);

		return id;
	}
	
	public long insertEntityFieldSpec(final EntityFieldSpec entityFieldSpec,
			final long entitySpecId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_ENTITY_FIELD_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, entitySpecId);
				ps.setString(2, entityFieldSpec.getFieldLabel());
				ps.setInt(3, entityFieldSpec.getFieldType());
				ps.setString(4, entityFieldSpec.getFieldTypeExtra());
				ps.setBoolean(5, entityFieldSpec.isRequired());
				ps.setInt(6, entityFieldSpec.getDisplayOrder());
				ps.setString(7, entityFieldSpec.getExpression());
				ps.setBoolean(8, entityFieldSpec.isIdentifier());
				ps.setString(9, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(10, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				if (entityFieldSpec.getSkeletonEntityFieldSpecId() != null)
					ps.setLong(11,
							entityFieldSpec.getSkeletonEntityFieldSpecId());
				else
					ps.setNull(11, Types.BIGINT);
				ps.setString(12, entityFieldSpec.getFieldTypeExtraCustomEntity());

				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		entityFieldSpec.setEntityFieldSpecId(id);

		return id;
	}

	public long insertEntityFieldSpecValidValue(final long entityFieldSpecId,
			final String value) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_ENTITY_FIELD_VALID_VALUE,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, entityFieldSpecId);
				ps.setString(2, value);
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();

		return id;
	}
	public List<EntityFieldSpecValidValue> getEntityFieldSpecValidValuesIn(
			List<EntityFieldSpec> entityFieldSpecs) {
		String ids = "";
		for (EntityFieldSpec entityFieldSpec : entityFieldSpecs) {
			if (!Api.isEmptyString(ids)) {
				ids += ",";
			}

			ids += "'" + entityFieldSpec.getEntityFieldSpecId() + "'";
		}

		return getEntityFieldSpecValidValuesIn(ids);
	}
	public List<EntityFieldSpecValidValue> getEntityFieldSpecValidValuesIn(
			String ids) {
		if (!Api.isEmptyString(ids)) {
			String sql = Sqls.SELECT_ENTITY_FIELD_VALID_VALUES_IN.replace(
					":ids", ids);
			List<EntityFieldSpecValidValue> entityFieldSpecValidValues = jdbcTemplate
					.query(sql,	new BeanPropertyRowMapper<EntityFieldSpecValidValue>(
									EntityFieldSpecValidValue.class));
			return entityFieldSpecValidValues;
		}

		return new ArrayList<EntityFieldSpecValidValue>();
	}
	
	public long insertFormSpec(final FormSpec formSpec, final long companyId,
			final long by) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_SPEC, Statement.RETURN_GENERATED_KEYS);
				
				String uniqueId = Api.isEmptyString(formSpec.getUniqueId()) ? UUID
						.randomUUID().toString() : formSpec.getUniqueId();
						
				formSpec.setUniqueId(uniqueId);
				ps.setString(1,formSpec.getUniqueId());
				ps.setLong(2, companyId);
				ps.setString(3, formSpec.getFormTitle());
				ps.setLong(4, by);
				ps.setBoolean(5, formSpec.isPublic());
				ps.setInt(6, formSpec.getVersion());
				if (formSpec.getParentId() == null) {
					ps.setNull(7, Types.BIGINT);
				} else {
					ps.setLong(7, formSpec.getParentId());
				}
				ps.setString(8, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(9, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));

				ps.setInt(10, formSpec.getPurpose());

				if (formSpec.getInitialFormSpecId() == null) {
					ps.setNull(11, Types.BIGINT);
				} else {
					ps.setLong(11, formSpec.getInitialFormSpecId());
				}

				if (formSpec.getSkeletonFormSpecId() == null) {
					ps.setNull(12, Types.BIGINT);
				} else {
					ps.setLong(12, formSpec.getSkeletonFormSpecId());
				}
				ps.setInt(13, formSpec.getIsSystemDefined());
				ps.setBoolean(14, formSpec.getIsOnlineForm());
				ps.setInt(15, formSpec.getStockForm());
				ps.setInt(16, formSpec.getUserEditRestriction());
				if (Api.isEmptyString(formSpec.getUserEditRestrictionReason())) {
					ps.setNull(17, Types.VARCHAR);
				} else {
					ps.setString(17, formSpec.getUserEditRestrictionReason());
				}
				ps.setBoolean(18,formSpec.isAllAccess());
				ps.setBoolean(19, formSpec.getIsPublicLinkForm());
				ps.setBoolean(20, formSpec.isAvoidFormWebCreate());
				
				if(!Api.isEmptyString(formSpec.getAvoidFormWebCreateMessage()))
					ps.setString(21, formSpec.getAvoidFormWebCreateMessage());
				else
					ps.setNull(21, Types.VARCHAR);
				ps.setBoolean(22, formSpec.isUpdateOnFormApproval());
				ps.setBoolean(23, formSpec.isEnableDraftForm());
				ps.setLong(24,formSpec.getAutoSaveInterval());
				ps.setBoolean(25,formSpec.isEnableOnlineApiForm());
				ps.setBoolean(26,formSpec.isSendDraftFormsInSync());
				ps.setBoolean(27, formSpec.isEnableDataSourceInOfflineMode());
				ps.setString(28,formSpec.getShowHelpText());

				if (formSpec.getHelpDocumentMediaId() == null) {
					ps.setNull(29, Types.BIGINT);
				} else {
					ps.setLong(29, formSpec.getHelpDocumentMediaId());
				}
				if(!Api.isEmptyString(formSpec.getLinkText()))
					ps.setString(30,formSpec.getLinkText());
				else
					ps.setNull(30, Types.VARCHAR);
				ps.setInt(31, formSpec.getIsAdvancedWorkPrintTemplate());
				ps.setBoolean(32, formSpec.isOfflineCustomEntityUpdateOnApproval());
				ps.setBoolean(33,formSpec.isEnableWebPrintOnMobile());
				ps.setBoolean(34, formSpec.isHiddenFieldsToDisplayInViewMode());
				ps.setBoolean(35, formSpec.isEvaluateHiddenFields());
				ps.setInt(36, formSpec.getEnableMultiPrintTemplate());
				if (formSpec.getQrCodeMediaId() == null) {
					ps.setNull(37, Types.BIGINT);
				} else {
					ps.setLong(37, formSpec.getQrCodeMediaId());
				}
				ps.setBoolean(38, formSpec.isSendPublicFormForApproval());
				ps.setBoolean(39, formSpec.isOcrForm());
				ps.setBoolean(40, formSpec.isSendPublicFormForApprovalForCustomerReq());
				ps.setBoolean(41, formSpec.isSendPublicFormForApprovalForEntityReq());
				if (formSpec.getResetAutoGenerateSequence() == null) {
					ps.setNull(42, Types.BIGINT);
				}else {
					ps.setInt(42, formSpec.getResetAutoGenerateSequence());
				}
				ps.setBoolean(43, formSpec.isSendPublicFormForApprovalForEmployeeReq());
				ps.setInt(44, formSpec.getLaunchFormForSectionInstance());
				ps.setBoolean(45, formSpec.isEnableFormSubmissionFrequency());
				if(formSpec.getFormSubmissionFrequencyInDays() == null) {
					ps.setNull(46, Types.INTEGER);
				}else {
					ps.setInt(46, formSpec.getFormSubmissionFrequencyInDays());
				}
				if(formSpec.getEnableDraftFormForMobile() == null) {
					ps.setBoolean(47, false);
				}else {
					ps.setBoolean(47, formSpec.getEnableDraftFormForMobile());
				}


				if(Api.isEmptyString(formSpec.getMobileLayout())) {
					ps.setNull(48, Types.VARCHAR);
				}else {
					ps.setString(48, formSpec.getMobileLayout());
				}
				ps.setBoolean(49, formSpec.isRemoveBlankLines());
				ps.setString(50, formSpec.getDynamicSaveButton());
				ps.setString(51, formSpec.getDynamicSuccessMessage());
				//ps.setBoolean(47, formSpec.isEnableDraftFormForMobile());

				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		formSpec.setFormSpecId(id);

		return id;
	}
	public FormSpec getFormSpec(String id) {
		FormSpec formSpec = null;

		try {
		 formSpec = jdbcTemplate.queryForObject(Sqls.SELECT_FORM_SPEC,
				new Object[] { id },new int[] {Types.VARCHAR}, new BeanPropertyRowMapper<FormSpec>(
						FormSpec.class));
		return formSpec;
		}catch (Exception e) {
			return formSpec;
		}
	}
	public void updateFormSpecForInitialFormSpecId(FormSpec formSpec) {
		jdbcTemplate.update(
				Sqls.UPDATE_FORMSPEC_FOR_INITIALFORMSPECID,
				new Object[] { formSpec.getFormSpecId(),
						formSpec.getFormSpecId() });
	}
	public long insertFormPageSpec(final FormPageSpec formPageSpec,
			final long formSpecId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_PAGE_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, formPageSpec.getPageId());
				ps.setLong(2, formSpecId);
				ps.setString(3, formPageSpec.getPageTitle());
				ps.setString(4, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(5, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setInt(6, formPageSpec.getPageOrder());
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		// formPageSpec.setSectionSpecId(id);

		return id;
	}
	public long insertFormFieldGroupSpec(final FormFieldGroupSpec formFieldGroupSpec,
			final long formSpecId, final long companyId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_FIELD_GROUP_SPECS,Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, formSpecId);
				ps.setLong(2, companyId);
				ps.setString(3, formFieldGroupSpec.getGroupTitle());
				ps.setString(4, formFieldGroupSpec.getPrintTemplate());
				ps.setInt(5, formFieldGroupSpec.getDisplayOrder());
				ps.setInt(6, formFieldGroupSpec.getPageId());
				ps.setString(7, formFieldGroupSpec.getExpression());
				ps.setString(8, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
				ps.setString(9, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
				
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		formFieldGroupSpec.setFormFieldGroupSpecId(id);

		return id;
	}
	public long insertFormFieldSpec(final FormFieldSpec formFieldSpec,
			final long formSpecId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_FIELD_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				String uniqueId = Api.isEmptyString(formFieldSpec.getUniqueId()) ? UUID
						.randomUUID().toString() : formFieldSpec
						.getUniqueId();
				formFieldSpec.setUniqueId(uniqueId);
				ps.setString(1,uniqueId);
				ps.setLong(2, formSpecId);
				ps.setString(3, formFieldSpec.getFieldLabel());
				ps.setInt(4, formFieldSpec.getFieldType());
				ps.setString(5, formFieldSpec.getFieldTypeExtra());
				ps.setBoolean(6, formFieldSpec.isComputedField());
				ps.setBoolean(7, formFieldSpec.isBarcodeField());
				ps.setString(8, formFieldSpec.getFormula());
				ps.setBoolean(9, formFieldSpec.isRequired());
				ps.setInt(10, formFieldSpec.getDisplayOrder());
				if (formFieldSpec.getPageId() == null) {
					ps.setNull(11, Types.BIGINT);
				} else {
					ps.setInt(11, formFieldSpec.getPageId());
				}
				ps.setString(12, formFieldSpec.getExpression());
				ps.setBoolean(13, formFieldSpec.isIdentifier());
				ps.setString(14, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(15, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setBoolean(16, formFieldSpec.isDefaultField());

				if (formFieldSpec.getInitialFormFieldSpecId() == null) {
					ps.setNull(17, Types.BIGINT);
				} else {
					ps.setLong(17, formFieldSpec.getInitialFormFieldSpecId());
				}

				if (formFieldSpec.getSkeletonFormFieldSpecId() == null) {
					ps.setNull(18, Types.BIGINT);
				} else {
					ps.setLong(18, formFieldSpec.getSkeletonFormFieldSpecId());
				}
				ps.setBoolean(19, formFieldSpec.isMandatory());
				ps.setBoolean(20, formFieldSpec.getIsVisible());
				ps.setInt(21, formFieldSpec.getIsRemoteField());
				ps.setString(22, formFieldSpec.getFieldTypeExtraForm());
				if (formFieldSpec.getMediaPickCondition() == null) {
					ps.setNull(23, Types.TINYINT);
				} else {
					ps.setInt(23, formFieldSpec.getMediaPickCondition());
				}
				ps.setString(24, formFieldSpec.getExternalLabel()==null ? "":formFieldSpec.getExternalLabel());
				ps.setInt(25, formFieldSpec.getLocationPickCondition());
				
				if (formFieldSpec.getValidationExpr() == null) {
					ps.setNull(26, Types.VARCHAR);
				} else {
					ps.setString(26, formFieldSpec.getValidationExpr());
				}
				
				if (formFieldSpec.getValidationErrorMsg() == null) {
					ps.setNull(27, Types.VARCHAR);
				} else {
					ps.setString(27, formFieldSpec.getValidationErrorMsg());
				}
				
				ps.setBoolean(28, formFieldSpec.isVisibleForCreation());
				
				ps.setBoolean(29, formFieldSpec.isSearchableField());
				
				ps.setInt(30, formFieldSpec.getRadioButtonCondition());
				
				if(formFieldSpec.getRadioButtonDefaultValue()!= null)
				{
					ps.setString(31, formFieldSpec.getRadioButtonDefaultValue());
				}
				else
				{
					ps.setString(31, "-1");
				}
				
				if(formFieldSpec.getIsGroupFieldSpec() == null){
					ps.setInt(32, 0);
				}else{
					ps.setInt(32, formFieldSpec.getIsGroupFieldSpec());
				}
				if(formFieldSpec.getGroupExpression() == null){
					ps.setNull(33, Types.VARCHAR);
				}else{
					ps.setString(33, formFieldSpec.getGroupExpression());
				}
				if(formFieldSpec.getFormFieldGroupSpecId() == null){
					ps.setNull(34, Types.BIGINT);
				}else{
					ps.setLong(34, formFieldSpec.getFormFieldGroupSpecId());
				}
				if (formFieldSpec.getEnableSpinnerCondition() == null) {
					ps.setNull(35, Types.TINYINT);
				} else {
					ps.setInt(35, formFieldSpec.getEnableSpinnerCondition());
				}
				if (formFieldSpec.getTextFieldMultiLineValue() == null) {
					ps.setNull(36, Types.TINYINT);
				} else {
					ps.setInt(36, formFieldSpec.getTextFieldMultiLineValue());
				}
				if (formFieldSpec.getDecimalValueLimit() == null) {
					ps.setInt(37, 0);
				} else {
					ps.setInt(37, formFieldSpec.getDecimalValueLimit());
				}
				
				if (formFieldSpec.getOtpExpiryTimeInSeconds() == null) {
					ps.setNull(38, Types.INTEGER);
				} else {
					ps.setInt(38, formFieldSpec.getOtpExpiryTimeInSeconds());
				}
				if (formFieldSpec.getNoOfOtpDigits() == null) {
					ps.setNull(39, Types.INTEGER);
				} else {
					ps.setInt(39, formFieldSpec.getNoOfOtpDigits());
				}
				if(formFieldSpec.getReminderConfigEnabled() == null)
				{
					ps.setInt(40, 0);
				}else {
					ps.setInt(40, formFieldSpec.getReminderConfigEnabled());
				}
				if(!Api.isEmptyString(formFieldSpec.getRemainderRemarksFields()))
				{
					ps.setString(41, formFieldSpec.getRemainderRemarksFields());
				}
				else
				{
					ps.setNull(41, Types.VARCHAR);
				}
				if(formFieldSpec.getReadOnlyCondition() !=null)
				{
					ps.setInt(42, formFieldSpec.getReadOnlyCondition());
				}
				else
				{
					ps.setInt(42, 0);
				}
				if(formFieldSpec.getUpdateFormAsProcessed() !=null)
				{
					ps.setInt(43, formFieldSpec.getUpdateFormAsProcessed());
				}
				else
				{
					ps.setInt(43, 0);
				}
				if(formFieldSpec.getRestrictToCaps() !=null)
				{
					ps.setInt(44, formFieldSpec.getRestrictToCaps());
				}
				else
				{
					ps.setInt(44, 0);
				}
				if(formFieldSpec.getPickDiffCustomer() !=null)
				{
					ps.setInt(45, formFieldSpec.getPickDiffCustomer());
				}
				else
				{
					ps.setInt(45, 0);
				}
				if(formFieldSpec.getFieldTypeExtraCustomEntity() != null)
				{
					ps.setString(46, formFieldSpec.getFieldTypeExtraCustomEntity());
				}
				else
				{
					ps.setNull(46, Types.VARCHAR);
				}
				if(formFieldSpec.getPickEmployeesFromGroupIds() != null)
				{
					ps.setString(47, formFieldSpec.getPickEmployeesFromGroupIds());
				}
				else
				{
					ps.setNull(47, Types.VARCHAR);
				}
				ps.setBoolean(48, formFieldSpec.isSimpleSearch());
				
				/*Date: 2018-05-28
				*Change Purpose: added company font feature for form fields to change font 
				*Resource: Alekhya*/
				
				if (formFieldSpec.getFieldLabelFontId() == null) {
					ps.setNull(49, Types.BIGINT);
				} else {
					ps.setLong(49, formFieldSpec.getFieldLabelFontId());
				}
				
				if (formFieldSpec.getFieldValueFontId() == null) {
					ps.setNull(50, Types.BIGINT);
				} else {
					ps.setLong(50, formFieldSpec.getFieldValueFontId());
				}
				
				if(formFieldSpec.getGuidField() !=null)
				{
					ps.setInt(51, formFieldSpec.getGuidField());
				}
				else
				{
					ps.setInt(51, 0);
				}
				ps.setBoolean(52, formFieldSpec.isFunctionField());
				
				if(!Api.isEmptyString(formFieldSpec.getFunctionName()))
				{
					ps.setString(53, formFieldSpec.getFunctionName());
				}
				else
				{
					ps.setNull(53, Types.VARCHAR);
				}
				if (Api.isEmptyObj(formFieldSpec.getReadDataFrom())) {
					ps.setInt(54, 0);
				} else {
					ps.setInt(54, formFieldSpec.getReadDataFrom());
				}
				ps.setBoolean(55, formFieldSpec.isRestrictDataFromMobile());
				
				ps.setBoolean(56, formFieldSpec.isGroupIdentifier());
				if (formFieldSpec.getTimeStepValue() == null) {
					ps.setInt(57, 5);
				} else {
					ps.setInt(57, formFieldSpec.getTimeStepValue());
				}
				if (formFieldSpec.getEnableMediaDelete() == null) {
					ps.setInt(58, 0);
				} else {
					ps.setInt(58, formFieldSpec.getEnableMediaDelete());
				}
				if (formFieldSpec.getEnableLaunchChatAppForMobile() == null) {
					ps.setInt(59, 0);
				} else {
					ps.setInt(59, formFieldSpec.getEnableLaunchChatAppForMobile());
				}
				
				ps.setBoolean(60, formFieldSpec.isOcrField());
				
				if (formFieldSpec.getOcrTypeId() == null) {
					ps.setInt(61, 0);
				} else {
					ps.setInt(61, formFieldSpec.getOcrTypeId());
				}
				
				ps.setBoolean(62, formFieldSpec.isDependentField());
				
				if (formFieldSpec.getDependentFieldExpression() != null) {
					ps.setString(63, formFieldSpec.getDependentFieldExpression());
				} else {
					ps.setNull(63, Types.VARCHAR);
				}
				if (formFieldSpec.getDependentFieldLabel() != null) {
					ps.setString(64, formFieldSpec.getDependentFieldLabel());
				} else {
					ps.setNull(64, Types.VARCHAR);
				}
				
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		formFieldSpec.setFieldSpecId(id);

		return id;
	}
	public void updateInitialFormFieldSpecId(FormFieldSpec formFieldSpec) {
		jdbcTemplate.update(
				Sqls.UPDATE_FORMFIELDSPEC_FOR_INITIALFORMFIELDSPECID,
				new Object[] { formFieldSpec.getFieldSpecId(),
						formFieldSpec.getFieldSpecId() });
	}
	public FormFieldSpec getFormFieldSpec(String id) {
		FormFieldSpec formFieldSpec = jdbcTemplate.queryForObject(
				Sqls.SELECT_FORM_FIELD_SPEC, new Object[] { id },new int[] {Types.VARCHAR},
				new BeanPropertyRowMapper<FormFieldSpec>(FormFieldSpec.class));
		return formFieldSpec;
	}
	public String getFormSpecPrintTemplate(String id) {
		String printTemplate = jdbcTemplate.queryForObject(
				Sqls.SELECT_FORM_SPEC_PRINT_TEMPLATE, new Object[] { id },new int[] {Types.VARCHAR},
				String.class);
		return printTemplate;
	}

	public String getFormSpecEmailTemplate(String id) {
		String printTemplate = jdbcTemplate.queryForObject(
				Sqls.SELECT_FORM_SPEC_EMAIL_TEMPLATE, new Object[] { id },new int[] {Types.VARCHAR},
				String.class);
		return printTemplate;
	}

	public String getFormSpecMobilePrintTemplate(String id) {
		String printTemplate = jdbcTemplate.queryForObject(
				Sqls.SELECT_FORM_SPEC_MOBILE_PRINT_TEMPLATE,
				new Object[] { id },new int[] {Types.VARCHAR}, String.class);
		return printTemplate;
	}
	public long insertFormSectionSpec(final FormSectionSpec formSectionSpec,
			final long formSpecId, final long companyId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_SECTION_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				String uniqueId = Api.isEmptyString(formSectionSpec.getSectionSpecUniqueId()) ? UUID
						.randomUUID().toString() : formSectionSpec.getSectionSpecUniqueId();
						
				if(uniqueId.contains("undefined")) {
					uniqueId = uniqueId.contains("undefined") ? UUID.randomUUID().toString() : formSectionSpec.getSectionSpecUniqueId();
					formSectionSpec.setSectionSpecUniqueId(uniqueId);
				}else {
					formSectionSpec.setSectionSpecUniqueId(uniqueId);
				}
				formSectionSpec.setSectionSpecUniqueId(uniqueId);
				ps.setLong(1, formSpecId);
				ps.setLong(2, companyId);
				ps.setString(3, formSectionSpec.getSectionTitle());
				ps.setInt(4, formSectionSpec.getMinEntrys());
				ps.setInt(5, formSectionSpec.getMaxEntrys());
				ps.setInt(6, formSectionSpec.getDisplayOrder());
				if (formSectionSpec.getPageId() == null) {
					ps.setNull(7, Types.BIGINT);
				} else {
					ps.setInt(7, formSectionSpec.getPageId());
				}
				ps.setString(8, formSectionSpec.getExpression());
				ps.setString(9, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(10, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));

				if (formSectionSpec.getInitialFormSectionSpecId() == null) {
					ps.setNull(11, Types.BIGINT);
				} else {
					ps.setLong(11,
							formSectionSpec.getInitialFormSectionSpecId());
				}
				if (formSectionSpec.getSkeletonFormSectionSpecId() == null) {
					ps.setNull(12, Types.BIGINT);
				} else {
					ps.setLong(12,
							formSectionSpec.getSkeletonFormSectionSpecId());
				}
				ps.setBoolean(13, formSectionSpec.isInstanceNumbering());
				ps.setBoolean(14, formSectionSpec.isAutoCreateSectionInstance());
				ps.setString(15,
						formSectionSpec.getAutoCreateFieldTypeExtra());
				ps.setString(16,formSectionSpec.getGroupByCategories());
				ps.setBoolean(17, formSectionSpec.isManualSelection());
				if(Api.isEmptyString(formSectionSpec.getExternalLabel())){
					ps.setNull(18, Types.VARCHAR);
				}else{
					ps.setString(18,formSectionSpec.getExternalLabel());
				}
				ps.setInt(19, formSectionSpec.getSelectionTypeInDevice());
				ps.setString(20, formSectionSpec.getSectionSpecUniqueId());
				ps.setInt(21, formSectionSpec.getDisplayType());
				if (formSectionSpec.getAutoCreateFieldCategoryType() == 0) {
					ps.setInt(22, FormSectionSpec.AUTO_CREATE_FIELD_CATEGORY_TYPE_FOR_ENTITY_SPECID);
				}else {
					ps.setInt(22, formSectionSpec.getAutoCreateFieldCategoryType());
				}
				ps.setBoolean(23, formSectionSpec.isDefaultInstanceCollapse());
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		formSectionSpec.setSectionSpecId(id);

		return id;
	}
	public void updateInitialFormSectionSpecId(FormSectionSpec formSectionSpec) {
		jdbcTemplate.update(
				Sqls.UPDATE_FORMSECTIONSPEC_FOR_INITIALFORMSECTIONSPECID,
				new Object[] { formSectionSpec.getSectionSpecId(),
						formSectionSpec.getSectionSpecId() });
	}
	public long insertFormSectionFieldSpec(
			final FormSectionFieldSpec formSectionFieldSpec,
			final long formSpecId, final long sectionSpecId) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_SECTION_FIELD_SPEC,
						Statement.RETURN_GENERATED_KEYS);
				String uniqueId = Api.isEmptyString(formSectionFieldSpec
						.getUniqueId()) ? UUID.randomUUID().toString()
						: formSectionFieldSpec.getUniqueId();
						formSectionFieldSpec.setUniqueId(uniqueId);	
				ps.setString(1, uniqueId);
				ps.setLong(2, formSpecId);
				ps.setLong(3, sectionSpecId);
				ps.setString(4, formSectionFieldSpec.getFieldLabel());
				ps.setInt(5, formSectionFieldSpec.getFieldType());
				ps.setString(6, formSectionFieldSpec.getFieldTypeExtra());
				ps.setBoolean(7, formSectionFieldSpec.isComputedField());
				ps.setBoolean(8, formSectionFieldSpec.isBarcodeField());
				ps.setString(9, formSectionFieldSpec.getFormula());
				ps.setBoolean(10, formSectionFieldSpec.isRequired());
				ps.setInt(11, formSectionFieldSpec.getDisplayOrder());
				ps.setString(12, formSectionFieldSpec.getExpression());
				ps.setBoolean(13, formSectionFieldSpec.isIdentifier());
				ps.setString(14, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setString(15, Api.getDateTimeInUTC(new Date(System
						.currentTimeMillis())));
				ps.setBoolean(16, formSectionFieldSpec.isDefaultField());

				if (formSectionFieldSpec.getInitialFormSectionFieldSpecId() != null)
					ps.setLong(17, formSectionFieldSpec
							.getInitialFormSectionFieldSpecId());
				else
					ps.setNull(17, Types.BIGINT);

				if (formSectionFieldSpec.getSkeletonFormSectionFieldSpecId() != null)
					ps.setLong(18, formSectionFieldSpec
							.getSkeletonFormSectionFieldSpecId());
				else
					ps.setNull(18, Types.BIGINT);
				
				ps.setInt(19, formSectionFieldSpec.getIsRemoteField());
				ps.setString(20, formSectionFieldSpec.getFieldTypeExtraForm());
				if (formSectionFieldSpec.getMediaPickCondition() == null) {
					ps.setNull(21, Types.TINYINT);
				} else {
					ps.setInt(21, formSectionFieldSpec.getMediaPickCondition());
				}
				ps.setInt(22, formSectionFieldSpec.getLocationPickCondition());
				
				ps.setString(23, formSectionFieldSpec.getExternalLabel()==null ? "":formSectionFieldSpec.getExternalLabel());
				
				if (formSectionFieldSpec.getValidationExpr() == null) {
					ps.setNull(24, Types.VARCHAR);
				} else {
					ps.setString(24, formSectionFieldSpec.getValidationExpr());
				}
				
				if (formSectionFieldSpec.getValidationErrorMsg() == null) {
					ps.setNull(25, Types.VARCHAR);
				} else {
					ps.setString(25, formSectionFieldSpec.getValidationErrorMsg());
				}
				
				ps.setInt(26, formSectionFieldSpec.getRadioButtonCondition());
				if(formSectionFieldSpec.getRadioButtonDefaultValue() != null && 
						!Api.isEmptyString(formSectionFieldSpec.getRadioButtonDefaultValue()))
				{
					ps.setString(27, formSectionFieldSpec.getRadioButtonDefaultValue());
				}
				else
				{
					ps.setString(27, "-1");
				}
				if (formSectionFieldSpec.getEnableSpinnerCondition() == null) {
					ps.setNull(28, Types.TINYINT);
				} else {
					ps.setInt(28, formSectionFieldSpec.getEnableSpinnerCondition());
				}
				if (formSectionFieldSpec.getTextFieldMultiLineValue() == null) {
					ps.setNull(29, Types.TINYINT);
				} else {
					ps.setInt(29, formSectionFieldSpec.getTextFieldMultiLineValue());
				}
				if (formSectionFieldSpec.getDecimalValueLimit() == null) {
					ps.setInt(30, 0);
				} else {
					ps.setInt(30, formSectionFieldSpec.getDecimalValueLimit());
				}
				ps.setInt(31, formSectionFieldSpec.getValuesFilter());
				
				if (formSectionFieldSpec.getOtpExpiryTimeInSeconds() == null) {
					ps.setNull(32, Types.INTEGER);
				} else {
					ps.setInt(32, formSectionFieldSpec.getOtpExpiryTimeInSeconds());
				}
				if (formSectionFieldSpec.getNoOfOtpDigits() == null) {
					ps.setNull(33, Types.INTEGER);
				} else {
					ps.setInt(33, formSectionFieldSpec.getNoOfOtpDigits());
				}
				if(formSectionFieldSpec.getUpdateFormAsProcessed() !=null)
				{
					ps.setInt(34, formSectionFieldSpec.getUpdateFormAsProcessed());
				}
				else
				{
					ps.setInt(34, 0);
				}
				if(formSectionFieldSpec.getRestrictToCaps() !=null)
				{
					ps.setInt(35, formSectionFieldSpec.getRestrictToCaps());
				}
				else
				{
					ps.setInt(35, 0);
				}
				if(formSectionFieldSpec.getPickDiffCustomer() !=null)
				{
					ps.setInt(36, formSectionFieldSpec.getPickDiffCustomer());
				}
				else
				{
					ps.setInt(36, 0);
				}
				if(formSectionFieldSpec.getFieldTypeExtraCustomEntity() !=null)
				{
					ps.setString(37, formSectionFieldSpec.getFieldTypeExtraCustomEntity());
				}
				else
				{
					ps.setNull(37, Types.VARCHAR);
				}
				if (formSectionFieldSpec.getFieldLabelFontId() != null)
					ps.setLong(38, formSectionFieldSpec.getFieldLabelFontId());
				else
					ps.setNull(38, Types.BIGINT);
				
				if (formSectionFieldSpec.getFieldValueFontId() == null) {
					ps.setNull(39, Types.BIGINT);
				} else {
					ps.setLong(39, formSectionFieldSpec.getFieldValueFontId());
				}
				if(formSectionFieldSpec.getPickEmployeesFromGroupIds() != null)
				{
					ps.setString(40, formSectionFieldSpec.getPickEmployeesFromGroupIds());
				}
				else
				{
					ps.setNull(40, Types.VARCHAR);
				}
				if(formSectionFieldSpec.getGuidField() !=null)
				{
					ps.setInt(41, formSectionFieldSpec.getGuidField());
				}
				else
				{
					ps.setInt(41, 0);
				}
				ps.setBoolean(42, formSectionFieldSpec.isFunctionField());
				
				if(!Api.isEmptyString(formSectionFieldSpec.getFunctionName()))
					ps.setString(43, formSectionFieldSpec.getFunctionName());
				else
					ps.setNull(43, Types.VARCHAR);
				
				if (Api.isEmptyObj(formSectionFieldSpec.getReadDataFrom())) {
					ps.setInt(44, 0);
				} else {
					ps.setInt(44, formSectionFieldSpec.getReadDataFrom());
				}
				ps.setBoolean(45, formSectionFieldSpec.isRestrictDataFromMobile());
				if (formSectionFieldSpec.getTimeStepValue() == null) {
					ps.setInt(46, 5);
				} else {
					ps.setInt(46, formSectionFieldSpec.getTimeStepValue());
				}
				if (formSectionFieldSpec.getEnableMediaDelete() == null) {
					ps.setInt(47, 0);
				} else {
					ps.setInt(47, formSectionFieldSpec.getEnableMediaDelete());
				}
				if (formSectionFieldSpec.getEnableLaunchChatAppForMobile() == null) {
					ps.setInt(48, 0);
				} else {
					ps.setInt(48, formSectionFieldSpec.getEnableLaunchChatAppForMobile());
				}
				if(formSectionFieldSpec.getReminderConfigEnabled() == null)
				{
					ps.setInt(49, 0);
				}else {
					ps.setInt(49, formSectionFieldSpec.getReminderConfigEnabled());
				}
				if(!Api.isEmptyString(formSectionFieldSpec.getRemainderRemarksFields()))
				{
					ps.setString(50, formSectionFieldSpec.getRemainderRemarksFields());
				}
				else
				{
					ps.setNull(50, Types.VARCHAR);
				}
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();
		formSectionFieldSpec.setSectionFieldSpecId(id);

		return id;
	}
	public void updateFormSectionFieldSpecId(
			FormSectionFieldSpec formSectionFieldSpec) {
		jdbcTemplate
				.update(Sqls.UPDATE_FORMSECTIONFIELDSPEC_FOR_INITIALFORMSECTIONFIELDSPECID,
						new Object[] {
								formSectionFieldSpec.getSectionFieldSpecId(),
								formSectionFieldSpec.getSectionFieldSpecId() });
	}
	public FormSectionFieldSpec getFormSectionFieldSpec(String sectionSpecId) {
		FormSectionFieldSpec formSectionFieldSpec = null;
		try{
			formSectionFieldSpec = jdbcTemplate
					.queryForObject(
							Sqls.SELECT_FORM_SECTION_FIELD_SPECS_BY_SECTION_FIELD_SPEC_ID,
							new Object[] { sectionSpecId },new int[] {Types.VARCHAR},
							new BeanPropertyRowMapper<FormSectionFieldSpec>(
									FormSectionFieldSpec.class));
		}catch(Exception e){
		}
		return formSectionFieldSpec;
	}
	public long insertFormFieldSpecValidValue(final long fieldSpecId,
			final String value) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_FIELD_VALID_VALUE,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, fieldSpecId);
				ps.setString(2, value);
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();

		return id;
	}
	public long insertFormSectionFieldSpecValidValue(
			final long sectionFieldSpecId, final String value) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						Sqls.INSERT_FORM_SECTION_FIELD_VALID_VALUE,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, sectionFieldSpecId);
				ps.setString(2, value);
				return ps;
			}
		}, keyHolder);

		long id = keyHolder.getKey().longValue();

		return id;
	}
	public int updateFormSpecPrintTemplate(long formSpecId,
			String printTemplate, String uniqueId) {
		return jdbcTemplate.update(Sqls.UPDATE_FORM_SPEC_PRINT_TEMPLATE,
				new Object[] { printTemplate, uniqueId,
						Api.getDateTimeInUTC(new Date(System
								.currentTimeMillis())),formSpecId });
	}

	public int updateFormSpecEmailTemplate(long formSpecId,
			String emailTemplate, String uniqueId) {
		return jdbcTemplate.update(Sqls.UPDATE_FORM_SPEC_EMAIL_TEMPLATE,
				new Object[] { emailTemplate, uniqueId, formSpecId });
	}

	public int updateFormSpecMobilePrintTemplate(long formSpecId,
			String mobilePrintTemplate) {
		return jdbcTemplate.update(
				Sqls.UPDATE_FORM_SPEC_MOBILE_PRINT_TEMPLATE,
				new Object[] {
						mobilePrintTemplate,
						Api.getDateTimeInUTC(new Date(System
								.currentTimeMillis())), formSpecId });
	}

	public FormSpec getFormSpecBySkeletonSpecId(long formSpecId) {
		FormSpec formSpec = null;
		try{
			formSpec = jdbcTemplate
					.queryForObject(
							Sqls.SELECT_FORM_SPEC_BY_SKELETON_SPEC_ID,
							new Object[] { formSpecId },new int[] {Types.BIGINT},
							new BeanPropertyRowMapper<FormSpec>(
									FormSpec.class));
		}catch(Exception e){
		}
		return formSpec;
	}
	public void insertRemainderFieldsMap(
			final List<RemainderFieldsMap> remainderFields) {

		jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_REMAINDER_FIELDS_MAP,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						RemainderFieldsMap remainderField = remainderFields
								.get(i);
						ps.setLong(1, remainderField.getFormSpecId());
						ps.setLong(2, remainderField.getFieldSpecId());
						ps.setLong(3,
								remainderField.getReferenceFieldSpecId());
						ps.setString(4, remainderField
								.getReferenceFieldExpressionId());
					}

					@Override
					public int getBatchSize() {
						return remainderFields.size();
					}
				});

	}
	public void insertFieldValidations(
			final List<FieldValidation> fieldValidations) {

		jdbcTemplate.batchUpdate(Sqls.INSERT_FIELD_VALIDATIONS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						FieldValidation fieldValidation = fieldValidations
								.get(i);
						ps.setLong(1, fieldValidation.getFormSpecId());
						ps.setLong(2, fieldValidation.getFieldSpecId());
						ps.setString(3, fieldValidation.getMin());
						ps.setString(4, fieldValidation.getMax());
						ps.setInt(5, fieldValidation.getFieldDataType());
						ps.setInt(6, fieldValidation.getFieldType());

						if(fieldValidation.getImageSize() == null)
							ps.setNull(7, Types.INTEGER);
						else
							ps.setInt(7, fieldValidation.getImageSize());
						
						if(fieldValidation.getImageSizeEnabled() == null)
							ps.setNull(8, Types.BOOLEAN);
						else
							ps.setBoolean(8, fieldValidation.getImageSizeEnabled());
						
					}

					@Override
					public int getBatchSize() {
						return fieldValidations.size();
					}
				});

	}

	public void insertListFilterCritria(
			final List<ListFilteringCritiria> listFilteringCritirias) {

		jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_LIST_FILTERING_CRITIRIA,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ListFilteringCritiria listFilteringCritiria = listFilteringCritirias
								.get(i);
						ps.setLong(1, listFilteringCritiria.getFormSpecId());
						ps.setLong(2, listFilteringCritiria.getFieldSpecId());
						ps.setLong(3,
								listFilteringCritiria.getListFieldSpecId());
						ps.setString(4, listFilteringCritiria
								.getReferenceFieldExpressionId());
						ps.setString(5, listFilteringCritiria.getValue());
						ps.setInt(6, listFilteringCritiria.getType());
						ps.setInt(7, listFilteringCritiria.getCondition());
					}

					@Override
					public int getBatchSize() {
						return listFilteringCritirias.size();
					}
				});

	}

	public void insertVisibilityDependencyCritirias(
			final List<VisibilityDependencyCriteria> visibilityDependencyCriterias) {

		jdbcTemplate.batchUpdate(
				Sqls.INSERT_INTO_VISIBILITY_DEPENDENCY_CRITIRIAS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						VisibilityDependencyCriteria visibilityDependencyCriteria = visibilityDependencyCriterias
								.get(i);
						ps.setLong(1,
								visibilityDependencyCriteria.getFormSpecId());
						ps.setLong(2,
								visibilityDependencyCriteria.getFieldSpecId());
						ps.setInt(3,
								visibilityDependencyCriteria.getFieldType());
						ps.setString(4, visibilityDependencyCriteria
								.getTargetFieldExpression());
						ps.setString(5,
								visibilityDependencyCriteria.getValueIds());
						ps.setString(6, visibilityDependencyCriteria.getValue());
						ps.setInt(7,
								visibilityDependencyCriteria.getFieldDataType());
						ps.setInt(8,
								visibilityDependencyCriteria.getCondition());
						ps.setInt(9, visibilityDependencyCriteria
								.getVisibilityType());
						ps.setInt(10,
								visibilityDependencyCriteria.getConjunction());
						ps.setBoolean(11,
								visibilityDependencyCriteria.isIgnoreHiddenFields());
						ps.setString(12, visibilityDependencyCriteria
								.getDestinationFieldExpression());
					}

					@Override
					public int getBatchSize() {
						return visibilityDependencyCriterias.size();
					}
				});

	}
	public void insertCustomerFilterCritria(
			final List<CustomerFilteringCritiria> customerFilteringCritirias) {

		jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_CUSTOMER_FILTERING_CRITIRIA,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						CustomerFilteringCritiria customerFilteringCritiria = customerFilteringCritirias
								.get(i);
						ps.setLong(1, customerFilteringCritiria.getFormSpecId());
						ps.setLong(2, customerFilteringCritiria.getFieldSpecId());
						ps.setLong(3,
								customerFilteringCritiria.getFieldOptionId());
						ps.setString(4, customerFilteringCritiria
								.getReferenceFieldExpressionId());
						ps.setString(5, customerFilteringCritiria.getValue());
						ps.setInt(6, customerFilteringCritiria.getType());
						ps.setInt(7, customerFilteringCritiria.getCondition());
					}

					@Override
					public int getBatchSize() {
						return customerFilteringCritirias.size();
					}
				});

	}
	public void insertFieldValidationCritria(
			final List<FieldValidationCritiria> fieldValidationCritrias) {

		jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_FIELD_VALIDATION_CRITIRIA,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						FieldValidationCritiria fieldValidationCritria = fieldValidationCritrias
								.get(i);
						ps.setLong(1, fieldValidationCritria.getFormSpecId());
						ps.setLong(2, fieldValidationCritria.getFieldSpecId());
						if(Api.isEmptyString(fieldValidationCritria
								.getReferenceFieldExpressionId()))
						{
							ps.setNull(3, Types.VARCHAR);
						}
						else
						{
							ps.setString(3, fieldValidationCritria
									.getReferenceFieldExpressionId());
						}
						
						if(Api.isEmptyString(fieldValidationCritria
								.getValue()))
						{
							ps.setNull(4, Types.VARCHAR);
						}
						else
						{
							ps.setString(4, fieldValidationCritria
									.getValue());
						}
						
						ps.setInt(5, fieldValidationCritria.getCondition());
						ps.setInt(6, fieldValidationCritria.getValidationType());
						
						if(Api.isEmptyString(fieldValidationCritria
								.getValidationMessage()))
						{
							ps.setNull(7, Types.VARCHAR);
						}
						else
						{
							ps.setString(7, fieldValidationCritria
									.getValidationMessage());
						}
						
						ps.setInt(8, fieldValidationCritria.isSectionField() ? 1 : 0);
						ps.setInt(9, fieldValidationCritria.getConjunction());
						
						if(Api.isEmptyString(fieldValidationCritria
								.getReferenceFieldExpressionId2()))
						{
							ps.setNull(10, Types.VARCHAR);
						}
						else
						{
							ps.setString(10, fieldValidationCritria
									.getReferenceFieldExpressionId2());
						}
						
						if(Api.isEmptyString(fieldValidationCritria
								.getValue2()))
						{
							ps.setNull(11, Types.VARCHAR);
						}
						else
						{
							ps.setString(11, fieldValidationCritria
									.getValue2());
						}
					}

					@Override
					public int getBatchSize() {
						return fieldValidationCritrias.size();
					}
				});

	}
	public void insertFormFieldsColorDependencyCriterias(
			final List<FormFieldsColorDependencyCriterias> formFieldColorDependencyCriterias) {
			jdbcTemplate.batchUpdate(Sqls.INSERT_FORM_FIELD_COLOR_DEPENDENCY_CRITERIAS,

					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							
							FormFieldsColorDependencyCriterias criteria = formFieldColorDependencyCriterias.get(i);
							ps.setLong(1, criteria.getFormSpecId());
							ps.setLong(2, criteria.getFieldSpecId());
							ps.setInt(3, criteria.getFieldType());
							ps.setInt(4, criteria.getDependencyType());
							ps.setString(5, criteria.getTargetFieldExpression());
							ps.setString(6, criteria.getValue());
							ps.setString(7, criteria.getOtherValue());
							ps.setInt(8, criteria.getFieldDataType());
							if(criteria.getCondition() != null)
								ps.setInt(9, criteria.getCondition());
							else
								ps.setNull(9, Types.INTEGER);
							ps.setString(10, criteria.getBackgroundColor());
							if(Api.isEmptyString(criteria.getFieldExpression1()))
								ps.setNull(11, Types.VARCHAR);
							else
								ps.setString(11, criteria.getFieldExpression1());
							if(Api.isEmptyString(criteria.getFieldExpression2()))
								ps.setNull(12, Types.VARCHAR);
							else
								ps.setString(12, criteria.getFieldExpression2());
						}

						@Override
						public int getBatchSize() {
							return formFieldColorDependencyCriterias.size();
						}

					});
		}
	public void insertAutoGeneratedSequenceSpecConfigurations(
			final List<AutoGenereteSequenceSpecConfiguaration> autoGenereteSequenceSpecConfiguarations) {

		jdbcTemplate.batchUpdate(
				Sqls.INSERT_INTO_AUTO_GENERATED_SEQUENCE_SPEC_CONFIGURATIONS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						AutoGenereteSequenceSpecConfiguaration autoGenereteSequenceSpecConfiguaration =
								autoGenereteSequenceSpecConfiguarations.get(i);
						
						ps.setLong(1,autoGenereteSequenceSpecConfiguaration.getFormSpecId());
						ps.setLong(2,autoGenereteSequenceSpecConfiguaration.getFormFieldSpecId());
						ps.setInt(3, autoGenereteSequenceSpecConfiguaration
								.getFieldDataType());
						if(autoGenereteSequenceSpecConfiguaration.getPrefix() !=null)
						{
							ps.setInt(4,
									autoGenereteSequenceSpecConfiguaration.getPrefix());
						}
						else
						{
							ps.setNull(4, Type.INT);
						}
						if(autoGenereteSequenceSpecConfiguaration.getSeperator() != null)
						{
							ps.setInt(5,
									autoGenereteSequenceSpecConfiguaration.getSeperator());
						}
						else
						{
							ps.setNull(5, Type.INT);
						}
						
						if(autoGenereteSequenceSpecConfiguaration.getInclude() != null)
						{
							ps.setInt(6, autoGenereteSequenceSpecConfiguaration.getInclude());
						}
						else
						{
							ps.setInt(6, 0);
						}
						
						if(autoGenereteSequenceSpecConfiguaration.getSequenceLength() !=null)
						{
							ps.setInt(7,
									autoGenereteSequenceSpecConfiguaration.getSequenceLength());
						}
						else
						{
							ps.setInt(7, 6);
						}
                        if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguaration.getCustomisedPrefix())){
                            ps.setString(8, autoGenereteSequenceSpecConfiguaration.getCustomisedPrefix());
                        }
                        else
                        {
                            ps.setString(8, null);
                        }
                        if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguaration.getCustomisedSufix())){
                            ps.setString(9, autoGenereteSequenceSpecConfiguaration.getCustomisedSufix());
                        }
                        else
                        {
                            ps.setString(9, null);
                        }
                        if(autoGenereteSequenceSpecConfiguaration.getSufix() !=null)
						{
							ps.setInt(10,autoGenereteSequenceSpecConfiguaration.getSufix());
						}
						else
						{
							ps.setNull(10, Type.INT);
						}
						ps.setBoolean(11,autoGenereteSequenceSpecConfiguaration.isPrefixExpression());
						ps.setBoolean(12,autoGenereteSequenceSpecConfiguaration.isSufixExpression());                      

					}

					@Override
					public int getBatchSize() {
						return autoGenereteSequenceSpecConfiguarations.size();
					}
				});

	}
	public void insertAutoGenereteSequenceSpecConfiguarationFields(
			List<AutoGenereteSequenceSpecConfiguarationField> autoGenereteSequenceSpecConfiguarationFields) {
		
		jdbcTemplate.batchUpdate(
				Sqls.INSERT_INTO_AUTO_GENERATED_SEQUENCE_SPEC_CONFIGURATION_FIELDS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						AutoGenereteSequenceSpecConfiguarationField autoGenereteSequenceSpecConfiguarationField =
								autoGenereteSequenceSpecConfiguarationFields.get(i);
						
						if(autoGenereteSequenceSpecConfiguarationField.getAutoGenereteSequenceSpecConfigId() != null) {
							ps.setLong(1,autoGenereteSequenceSpecConfiguarationField.getAutoGenereteSequenceSpecConfigId());
						}else{
							ps.setNull(1, Types.BIGINT);
						}
						if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguarationField.getFormSpecUniqueId())) {
							ps.setString(2,autoGenereteSequenceSpecConfiguarationField.getFormSpecUniqueId());
						}else {
							ps.setString(2, null);
						}
						if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguarationField.getFieldSpecUniqueId())) {
							ps.setString(3,autoGenereteSequenceSpecConfiguarationField.getFieldSpecUniqueId());
						}else {
							ps.setString(3, null);
						}
						ps.setInt(4, autoGenereteSequenceSpecConfiguarationField.getFieldDataType());
						if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguarationField.getInputFormFieldExpression()))
						{
							ps.setString(5,autoGenereteSequenceSpecConfiguarationField.getInputFormFieldExpression());
						}else{
							ps.setString(5, null);
						}
						if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguarationField.getPrefixFormFieldExpression()))
						{
							ps.setString(6,autoGenereteSequenceSpecConfiguarationField.getPrefixFormFieldExpression());
						}else{
							ps.setString(6, null);
						}
						if(!Api.isEmptyString(autoGenereteSequenceSpecConfiguarationField.getSufixFormFieldExpression()))
						{
							ps.setString(7,autoGenereteSequenceSpecConfiguarationField.getSufixFormFieldExpression());
						}else{
							ps.setString(7, null);
						}
					}

					@Override
					public int getBatchSize() {
						return autoGenereteSequenceSpecConfiguarationFields.size();
					}
				});
		
	}
	public void insertFormFieldSpecFilters(
			final List<FieldSpecFilter> fieldSpecFilters) 
	{
		jdbcTemplate.batchUpdate(
				Sqls.INSERT_INTO_FORM_FIELD_SPEC_FILTERS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						FieldSpecFilter fieldSpecFilter = fieldSpecFilters
								.get(i);
						ps.setString(1,
								"" + fieldSpecFilter.getFormSpecId());
						ps.setString(
								2,
								""
										+ fieldSpecFilter
												.getFormFieldSpecId());
						ps.setString(3,
								"" + fieldSpecFilter.getType());
						ps.setString(4,
								"" + fieldSpecFilter.getValueId());
					}

					@Override
					public int getBatchSize() {
						return fieldSpecFilters.size();
					}
				});
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
	
	public void insertFormSectionFieldSpecFilters(
			final List<FieldSpecFilter> sectionFieldSpecFilters) 
	{
		jdbcTemplate.batchUpdate(
				Sqls.INSERT_INTO_FORM_SECTION_FIELD_SPEC_FILTERS,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						FieldSpecFilter fieldSpecFilter = sectionFieldSpecFilters
								.get(i);
						ps.setString(1,
								"" + fieldSpecFilter.getFormSpecId());
						ps.setString(
								2,
								""
										+ fieldSpecFilter
												.getFormSectionFieldSpecId());
						ps.setString(3,
								"" + fieldSpecFilter.getType());
						ps.setString(4,
								"" + fieldSpecFilter.getValueId());
					}

					@Override
					public int getBatchSize() {
						return sectionFieldSpecFilters.size();
					}
				});
		
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
			
			WorkFieldsUniqueConfigurations = jdbcTemplate.query(sql,new Object[] {companyId,syncDate}, new int[] {Types.INTEGER,Types.VARCHAR},
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

	public List<EntitySpec> getEntitySpecsIn(String ids) {
		if (!Api.isEmptyString(ids)) {
			try {
				String sql = Sqls.SELECT_ENTITY_SPECS_IN.replace(":ids", ids);
				List<EntitySpec> entitySpecs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EntitySpec>(EntitySpec.class));
				return entitySpecs;
			} catch (Exception e) {
				return new ArrayList<EntitySpec>();
			}
		} else {
			return new ArrayList<EntitySpec>();
		}
	}

		public List<EntitySectionFieldSpec> getEntitySectionFieldSpecs(String ids) {
			if (!Api.isEmptyString(ids)) {
				try {
					String sql = Sqls.SELECT_ENTITY_SECTION_FIELDS_BY_ENTITY_SPEC.replace(":ids", ids);
					return jdbcTemplate.query(sql, new BeanPropertyRowMapper<EntitySectionFieldSpec>(EntitySectionFieldSpec.class));
				} catch (Exception e) {
					return new ArrayList<EntitySectionFieldSpec>();
				}
			}
			return new ArrayList<EntitySectionFieldSpec>();
		}
	 
		public List<EntitySectionSpec> getEntitySectionSpecForIn(String ids) {
			if (!Api.isEmptyString(ids)) {
				try {
				List<EntitySectionSpec> entitySectionSpecs = jdbcTemplate.query(
						Sqls.SELECT_ENTITY_SECTION_SPECS_IN.replace(":ids", ids),
						new BeanPropertyRowMapper<EntitySectionSpec>(
								EntitySectionSpec.class));

				return entitySectionSpecs;
				}catch(Exception e) {
					
				}
			}

			return new ArrayList<EntitySectionSpec>();
		}
		public long insertEntitySectionSpec(final EntitySectionSpec entitySectionSpec,
				final long entitySpecId, final long companyId) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_ENTITY_SECTION_SPEC,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, entitySpecId);
					ps.setLong(2, companyId);
					ps.setString(3, entitySectionSpec.getSectionTitle());
					ps.setInt(4, entitySectionSpec.getMinEntrys());
					ps.setInt(5, entitySectionSpec.getMaxEntrys());
					ps.setInt(6, entitySectionSpec.getDisplayOrder());
					
					ps.setString(7, entitySectionSpec.getExpression());
					ps.setString(8, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(9, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));

					if (entitySectionSpec.getInitialEntitySectionSpecId() == null) {
						ps.setNull(10, Types.BIGINT);
					} else {
						ps.setLong(10,
								entitySectionSpec.getInitialEntitySectionSpecId());
					}
					if (entitySectionSpec.getSkeletonEntitySectionSpecId() == null) {
						ps.setNull(11, Types.BIGINT);
					} else {
						ps.setLong(11,
								entitySectionSpec.getSkeletonEntitySectionSpecId());
					}
					ps.setBoolean(12, entitySectionSpec.isInstanceNumbering());
					ps.setBoolean(13, entitySectionSpec.isAutoCreateSectionInstance());
					ps.setString(14,
							entitySectionSpec.getAutoCreateFieldTypeExtra());
					ps.setString(15,entitySectionSpec.getGroupByCategories());
					ps.setBoolean(16, entitySectionSpec.isManualSelection());
					
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			entitySectionSpec.setSectionSpecId(id);

			return id;
		}
		
		public long insertEntitySectionFieldSpec(
				final EntitySectionFieldSpec entitySectionFieldSpec,
				final long entitySpecId, final long sectionSpecId) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_ENTITY_SECTION_FIELD_SPEC,
							Statement.RETURN_GENERATED_KEYS);
	/*				ps.setString(1, Api.isEmptyString(entitySectionFieldSpec
							.getUniqueId()) ? UUID.randomUUID().toString()
							: formSectionFieldSpec.getUniqueId());*/
					boolean b = entitySectionFieldSpec.isRequired();
					ps.setLong(1, entitySpecId);
					ps.setLong(2, sectionSpecId);
					ps.setString(3, entitySectionFieldSpec.getFieldLabel());
					ps.setInt(4, entitySectionFieldSpec.getFieldType());
					ps.setString(5, entitySectionFieldSpec.getFieldTypeExtra());
					ps.setBoolean(6, entitySectionFieldSpec.isComputedField());
					ps.setBoolean(7, entitySectionFieldSpec.isBarcodeField());
					ps.setString(8, entitySectionFieldSpec.getFormula());
					ps.setBoolean(9, entitySectionFieldSpec.isRequired());
					ps.setInt(10, entitySectionFieldSpec.getDisplayOrder());
					ps.setString(11, entitySectionFieldSpec.getExpression());
					ps.setBoolean(12, entitySectionFieldSpec.isIdentifier());
					ps.setString(13, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(14, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setBoolean(15, entitySectionFieldSpec.isDefaultField());

					if (entitySectionFieldSpec.getInitialEntitySectionFieldSpecId() != null)
						ps.setLong(16, entitySectionFieldSpec
								.getInitialEntitySectionFieldSpecId());
					else
						ps.setNull(16, Types.BIGINT);

					if (entitySectionFieldSpec.getSkeletonEntitySectionFieldSpecId() != null)
						ps.setLong(17, entitySectionFieldSpec
								.getSkeletonEntitySectionFieldSpecId());
					else
						ps.setNull(17, Types.BIGINT);
					
					ps.setInt(18, entitySectionFieldSpec.getIsRemoteField());
					ps.setString(19, entitySectionFieldSpec.getFieldTypeExtraForm());
					if (entitySectionFieldSpec.getMediaPickCondition() == null) {
						ps.setNull(20, Types.TINYINT);
					} else {
						ps.setInt(20, entitySectionFieldSpec.getMediaPickCondition());
					}
					ps.setInt(21, entitySectionFieldSpec.getLocationPickCondition());
					
					ps.setString(22, entitySectionFieldSpec.getExternalLabel()==null ? "":entitySectionFieldSpec.getExternalLabel());
					
					if (entitySectionFieldSpec.getValidationExpr() == null) {
						ps.setNull(23, Types.VARCHAR);
					} else {
						ps.setString(23, entitySectionFieldSpec.getValidationExpr());
					}
					
					if (entitySectionFieldSpec.getValidationErrorMsg() == null) {
						ps.setNull(24, Types.VARCHAR);
					} else {
						ps.setString(24, entitySectionFieldSpec.getValidationErrorMsg());
					}
					ps.setString(25, entitySectionFieldSpec.getFieldTypeExtraCustomEntity());
					

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			entitySectionFieldSpec.setSectionFieldSpecId(id);

			return id;
		}
		
		public long insertEntitySectionFieldSpecValidValue(
				final long sectionFieldSpecId, final String value) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_ENTITY_SECTION_FIELD_VALID_VALUES,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, sectionFieldSpecId);
					ps.setString(2, value);
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();

			return id;
		}
		public void insertFormPageSpec(
				final List<FormPageSpec> formPageSpecs) {

			jdbcTemplate.batchUpdate(Sqls.INSERT_FORM_PAGE_SPEC,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							FormPageSpec formPageSpec = formPageSpecs
									.get(i);
							
							ps.setInt(1, formPageSpec.getPageId());
							ps.setLong(2, formPageSpec.getFormSpecId());
							ps.setString(3, formPageSpec.getPageTitle());
							ps.setString(4, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							ps.setString(5, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							ps.setInt(6, formPageSpec.getPageOrder());
						}

						@Override
						public int getBatchSize() {
							return formPageSpecs.size();
						}
					});

		}
		
		public void insertFormFieldGroupSpec(
				final List<FormFieldGroupSpec> formFieldGroupSpecs, final long companyId) {

			jdbcTemplate.batchUpdate(Sqls.INSERT_FORM_FIELD_GROUP_SPECS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							FormFieldGroupSpec formFieldGroupSpec = formFieldGroupSpecs
									.get(i);
							
							ps.setLong(1, formFieldGroupSpec.getFormSpecId());
							ps.setLong(2, companyId);
							ps.setString(3, formFieldGroupSpec.getGroupTitle());
							ps.setString(4, formFieldGroupSpec.getPrintTemplate());
							ps.setInt(5, formFieldGroupSpec.getDisplayOrder());
							ps.setInt(6, formFieldGroupSpec.getPageId());
							ps.setString(7, formFieldGroupSpec.getExpression());
							ps.setString(8, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							ps.setString(9, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));

						}

						@Override
						public int getBatchSize() {
							return formFieldGroupSpecs.size();
						}
					});

		}
		public long insertIntoWorkSpecs(final WorkSpec workSpec,
				final long companyId, final long by, final String uniqueId) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection
							.prepareStatement(Sqls.INSERT_WORK_SPECS,
									Statement.RETURN_GENERATED_KEYS);

					
					ps.setString(1, workSpec.getWorkSpecTitle());
					ps.setString(2, workSpec.getWorkSpecDescription());
					ps.setString(3, uniqueId);
					ps.setLong(4, by);
					ps.setLong(5, by);
					ps.setString(6, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(7, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setLong(8, companyId);
					ps.setLong(9, workSpec.getIsSystemDefined());
					ps.setLong(10, workSpec.getPurpose());

					if (workSpec.getCopiedFrom() != null)
						ps.setLong(11, workSpec.getCopiedFrom());
					else
						ps.setNull(11, Types.VARCHAR);

					if (workSpec.getSkeletonWorkSpecId() != null)
						ps.setLong(12, workSpec.getSkeletonWorkSpecId());
					else
						ps.setNull(12, Types.VARCHAR);

					ps.setInt(13, workSpec.getProductId());
					ps.setBoolean(14, workSpec.isWorkTreeStructureEnabled());
					ps.setBoolean(15, workSpec.isWorkActionFlowMandatory());
					ps.setBoolean(16, workSpec.isWorkProcessFlowRequired());
					ps.setString(17, workSpec.getMobileLayout());
					ps.setBoolean(18, workSpec.isRemoveBlankLines());
					ps.setBoolean(19, workSpec.isCaseManagement());
					ps.setBoolean(20, workSpec.isPerformWorkAtWorkLocation());
					if(workSpec.getWorkActivityGeoLocationDeviationAllowedRadius() != null) {
					ps.setLong(21, workSpec.getWorkActivityGeoLocationDeviationAllowedRadius());
					}else {
						ps.setNull(21, Types.VARCHAR);
					}
					ps.setBoolean(22, workSpec.isEnableWorkCheckIn());
					ps.setBoolean(23, workSpec.isEnableCheckInFormSubmission());
					if(workSpec.getCheckInFormSpecUniqueId() != null)
					{
						ps.setString(24, workSpec.getCheckInFormSpecUniqueId());
					}else {
						ps.setNull(24, Types.VARCHAR);
					}
					ps.setBoolean(25, workSpec.isForceWorkCheckInCheckOut());
					ps.setBoolean(26, workSpec.isEnforceWorkCheckIn());
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			workSpec.setWorkSpecId(id);
			return id;

		}
		public long insertIntoWorkActionSpecs(final WorkActionSpec workActionSpecs,
				final long actionId, final long by) {

			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

					PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_WORK_ACTIONS_SPECS,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, actionId);
					ps.setString(2, workActionSpecs.getActionName());
					ps.setString(3, workActionSpecs.getActionDesccription());
					if (workActionSpecs.getFormSpecUniqueId() != null
							&& !Api.isEmptyString(workActionSpecs.getFormSpecUniqueId()))
						ps.setString(4, workActionSpecs.getFormSpecUniqueId());
					else
						ps.setNull(4, Types.VARCHAR);

					ps.setLong(5, by);
					ps.setLong(6, by);
					ps.setString(7, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setString(8, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setBoolean(9, false);
					ps.setBoolean(10, workActionSpecs.getIsStartAction());
					ps.setBoolean(11, workActionSpecs.getIsEndAction());

					if (workActionSpecs.getFormReUse() != null)
						ps.setInt(12, workActionSpecs.getFormReUse());
					else
						ps.setInt(12, WorkActionSpec.FORM_RE_USE_OPEN_NEW_FORM);
					ps.setBoolean(13, workActionSpecs.getWorkFlowApplicable());
					if (workActionSpecs.getWorkActionActionableType() != null
							&& workActionSpecs.getWorkActionActionableType() == WorkActionSpec.ACTION_TYPE_SYSTEM)
						ps.setBoolean(14, true);
					else
						ps.setBoolean(14, false);
					if (workActionSpecs.getWorkActionGroupId() == null)
						ps.setNull(15, Types.BIGINT);
					else
						ps.setLong(15, workActionSpecs.getWorkActionGroupId());

					if (workActionSpecs.getWorkActionActionableType() != null)
						ps.setInt(16, workActionSpecs.getWorkActionActionableType());
					else
						ps.setInt(16, WorkActionSpec.ACTION_TYPE_USER);
					ps.setBoolean(17, workActionSpecs.isEnableOnlineApiForm());
					ps.setBoolean(18, workActionSpecs.isRestrictRepetition());
					if (workActionSpecs.getActionTat() != null) {
						ps.setLong(19, workActionSpecs.getActionTat());
					} else
						ps.setLong(19, 0);
					ps.setBoolean(20, workActionSpecs.isCaseManagementAction());
					ps.setBoolean(21, workActionSpecs.isWorkCheckInRequiredForActivity());
					/*
					 * commented below and kept default value false has this not not required as of
					 * now in future if needed comment the default and enable value reffer from bean
					 */
					
					//ps.setBoolean(22, workActionSpecs.isGeoLocationCheckRequiredForActivity());
					ps.setBoolean(22, false);
					if (workActionSpecs.getSequenceOrder() == null)
						ps.setNull(23, Types.BIGINT);
					else
						ps.setLong(23, workActionSpecs.getSequenceOrder());


					return ps;
				}
			}, keyHolder);
			long id = keyHolder.getKey().longValue();
			workActionSpecs.setWorkActionSpecId(id);
			return id;

		}
		public long insertNextActionSpec(final NextActionSpec nextActionSpec) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_NEXTACTIONSPEC,
							Statement.RETURN_GENERATED_KEYS);
					if (nextActionSpec.getActionSpecId() != null)
						ps.setLong(1, nextActionSpec.getActionSpecId());
					else
						ps.setNull(1, Types.BIGINT);

					if (nextActionSpec.getNextActionSpecId() != null)
						ps.setLong(2, nextActionSpec.getNextActionSpecId());
					else
						ps.setNull(2, Types.BIGINT);

					/*
					 * if(nextActionSpec.getIsStartAction() != null)
					 * ps.setBoolean(3, nextActionSpec.getIsStartAction()); else
					 * ps.setBoolean(3, false);
					 * 
					 * if(nextActionSpec.getIsEndAction() != null) ps.setBoolean(4,
					 * nextActionSpec.getIsEndAction()); else ps.setBoolean(4,
					 * false);
					 */

					ps.setString(3, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(4, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));

					if (nextActionSpec.getNextActionSpecId() != null)
						ps.setLong(5, nextActionSpec.getWorkSpecId());
					else
						ps.setNull(5, Types.BIGINT);
					
					ps.setInt(6, nextActionSpec.getRepetitionType());
					
					ps.setString(7, nextActionSpec.getRuleName());

					return ps;
				}
			}, keyHolder);

			Long id = keyHolder.getKey().longValue();
			nextActionSpec.setNextActionSpecId(id);

			return id;
		}
		public long insertWorkActionSpecCondition(
				final WorkActionSpecConditions workActionSpecConditions,
				final Long workSpecId) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_WORK_ACTION_SPEC_CONDITION,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, workActionSpecConditions.getActionSpecId());
					ps.setLong(2, workActionSpecConditions.getNextActionSpecId());
					ps.setLong(3, workActionSpecConditions.getFieldType());
					ps.setString(4,
							workActionSpecConditions.getTargetFieldExpression());
					ps.setString(5, workActionSpecConditions.getValue());
					ps.setInt(6, workActionSpecConditions.getFieldDataType());
					ps.setInt(7, workActionSpecConditions.getCondition());
					/* ps.setInt(8, workActionSpecConditions.getVisibilityType()); */
					ps.setInt(8, workActionSpecConditions.getConjunction());
					ps.setLong(9, workSpecId);
					ps.setInt(10, workActionSpecConditions.getType());
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			workActionSpecConditions.setId(id);

			return id;
		}
		public void insertWorkActionSpecVisibilityCondition(
				final List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList) {
			
			jdbcTemplate.batchUpdate(
					Sqls.INSERT_INTO_WORKACTIONSPECVISIBILITYCONDITION,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkActionSpecVisibilityCondition workActionSpecVisibilityCondition = workActionSpecVisibilityConditionList
									.get(i);
							ps.setLong(1,
									workActionSpecVisibilityCondition.getWorkActionSpecId());
							
							if(workActionSpecVisibilityCondition.getFieldType() == 3)
							{
								ps.setNull(2, Types.BIGINT);
								if(workActionSpecVisibilityCondition.getWorkSpecId()!=null)
									ps.setLong(3,workActionSpecVisibilityCondition.getWorkSpecId());
								else
									ps.setNull(3, Types.BIGINT);
								ps.setNull(4, Types.BIGINT);
							}
							else if(workActionSpecVisibilityCondition.getFieldType() == 4)
							{
								ps.setNull(2, Types.BIGINT);
								ps.setNull(3, Types.BIGINT);
								if(workActionSpecVisibilityCondition.getWorkSpecId()!=null)
									ps.setLong(4,workActionSpecVisibilityCondition.getWorkSpecId());
								else
									ps.setNull(4, Types.BIGINT);
							}
							else
							{
								if(workActionSpecVisibilityCondition.getWorkSpecId()!=null)
									ps.setLong(2,workActionSpecVisibilityCondition.getWorkSpecId());
								else
									ps.setNull(2, Types.BIGINT);
								ps.setNull(3, Types.BIGINT);
								ps.setNull(4, Types.BIGINT);
							}
							
							
							ps.setInt(5,
									Integer.parseInt(workActionSpecVisibilityCondition.getFieldType()+""));
							ps.setString(6, workActionSpecVisibilityCondition
									.getTargetFieldExpression());
							ps.setString(7,
									workActionSpecVisibilityCondition.getValueIds());
							ps.setString(8, workActionSpecVisibilityCondition.getValue());
							if(workActionSpecVisibilityCondition.getFieldDataType()!=null)
							ps.setInt(9,
									Integer.parseInt(workActionSpecVisibilityCondition.getFieldDataType()+""));
							else
							ps.setNull(9, Types.INTEGER);	
							ps.setInt(10,
									Integer.parseInt(workActionSpecVisibilityCondition.getCondition()+""));
							ps.setInt(11,Integer.parseInt( workActionSpecVisibilityCondition
									.getVisibilityType()+""));
							ps.setInt(12,
									Integer.parseInt(workActionSpecVisibilityCondition.getConjunction()+""));
							ps.setString(13, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							ps.setString(14, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							
							if(workActionSpecVisibilityCondition.getOperator2()!=null && Api.isNumber(workActionSpecVisibilityCondition.getOperator2()))
								ps.setInt(15,workActionSpecVisibilityCondition.getOperator2());
								else
								ps.setNull(15, Types.INTEGER);	
							if(workActionSpecVisibilityCondition.getValue2()!=null)
								ps.setString(16,workActionSpecVisibilityCondition.getValue2());
								else
								ps.setNull(16, Types.VARCHAR);	
							if(workActionSpecVisibilityCondition.getErrorMessage()!=null)
								ps.setString(17,workActionSpecVisibilityCondition.getErrorMessage());
								else
								ps.setNull(17, Types.VARCHAR);	
							if(workActionSpecVisibilityCondition.getAdvancedDateCriteria()!=null)
								ps.setInt(18,workActionSpecVisibilityCondition.getAdvancedDateCriteria());
							else
								ps.setInt(18, 0);	
							if(workActionSpecVisibilityCondition.getFieldCondtionSourceActionSpecId()!=null)
								ps.setLong(19,workActionSpecVisibilityCondition.getFieldCondtionSourceActionSpecId());
								else
								ps.setNull(19, Types.BIGINT);
							ps.setBoolean(20, workActionSpecVisibilityCondition.isConsiderDraftForm());
							
						}
							@Override
							public int getBatchSize() {
							return workActionSpecVisibilityConditionList.size();
							
						}
					});
		}
		
		public void insertWorkActionSpecEndCondition(
				final List<WorkActionSpecEndCondition> workActionSpecEndConditionList) 
		{
			
			jdbcTemplate.batchUpdate(
					Sqls.INSERT_INTO_WORK_ACTION_SPEC_END_CONDITION,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkActionSpecEndCondition workActionSpecEndCondition = workActionSpecEndConditionList
									.get(i);
							ps.setLong(1,
									workActionSpecEndCondition.getWorkActionSpecId());
							ps.setLong(2,workActionSpecEndCondition.getWorkSpecId());
							ps.setInt(3,workActionSpecEndCondition.getFieldType());
							ps.setString(4, workActionSpecEndCondition
									.getTargetFieldExpression());
							
							if(!Api.isEmptyString(workActionSpecEndCondition.getValue()))
							{
								ps.setString(5, workActionSpecEndCondition.getValue());
							}
							else
							{
								ps.setNull(5, Types.VARCHAR);
							}
							if(workActionSpecEndCondition.getIsSectionField()!=null)
								ps.setInt(6,workActionSpecEndCondition.getIsSectionField());
							else
								ps.setNull(6, Types.INTEGER);	
							ps.setInt(7,workActionSpecEndCondition.getCondition());
							ps.setInt(8,workActionSpecEndCondition.getConditionType());
							ps.setInt(9, workActionSpecEndCondition.getConjunction());
							ps.setString(10, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							ps.setString(11, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
						}
							@Override
							public int getBatchSize() {
							return workActionSpecEndConditionList.size();
							
						}
					});
		}
	
		public long insertOrUpdateWorkActionVisibilityConfiguration(final List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurationList)
		{
			jdbcTemplate.batchUpdate(
					Sqls.INSERT_OR_WORK_ACTION_VISIBILITY_CONFIGURATION,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException 
						{
							
							WorkActionVisibilityConfiguration workActionVisibilityConfiguration = workActionVisibilityConfigurationList.get(i);
							ps.setInt(1,workActionVisibilityConfiguration.getCompanyId());
							ps.setLong(2,workActionVisibilityConfiguration.getWorkSpecId());
							ps.setLong(3, workActionVisibilityConfiguration.getWorkActionSpecId());
							ps.setLong(4, workActionVisibilityConfiguration.getCreatedBy());
							ps.setLong(5, workActionVisibilityConfiguration.getModifiedBy());
							ps.setString(6, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							ps.setString(7, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							ps.setString(8, workActionVisibilityConfiguration.getWorkFieldEmpExpression());
							ps.setBoolean(9, workActionVisibilityConfiguration.isWeb());
							ps.setBoolean(10, workActionVisibilityConfiguration.isMobile());
						}
							@Override
							public int getBatchSize() {
							return workActionVisibilityConfigurationList.size();
							
						}
					});
			return workActionVisibilityConfigurationList.size();
		}
		public long createWorkAutoFillFormConfig(final WorkFormAutoFill workFormAutoFill,
				final long by) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {

					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_INTO_WORK_FORM_AUTO_FILL,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, workFormAutoFill.getFormSpecUniqueId());
					ps.setBoolean(2, workFormAutoFill.isEnable());
					ps.setString(3, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(4, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setLong(5, by);
					ps.setLong(6, workFormAutoFill.getWorkActionSpecId());
					ps.setBoolean(7, workFormAutoFill.isSectionAutoFill());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			workFormAutoFill.setWorkFormAutoFillId(id);
	        return id;
		}
		
		public void insertWorkAutoFillFormFields(
				final List<WorkFormAutoFillField> workFormAutoFillFieldList) {
			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_WORK_FORM_AUTO_FILL_FIELDS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkFormAutoFillField workFormAutoFillField = workFormAutoFillFieldList
									.get(i);

							ps.setLong(1, workFormAutoFillField.getWorkFormAutoFillId());
							ps.setInt(2, workFormAutoFillField.getFieldType());
							ps.setString(3,
									workFormAutoFillField.getFieldSpecUniqueId());
							ps.setString(4, workFormAutoFillField.getWorkFieldSpecUniqueId());
							
							//added on 6-may-2015 by ameer for pickAnother value feature
							ps.setInt(5, workFormAutoFillField.getPickAnother());
							ps.setInt(6, workFormAutoFillField.getSourceType());
							if(Api.isEmptyString(workFormAutoFillField.getSourceSpecUniqueId())){
								ps.setNull(7, Types.VARCHAR);
							}else{
								ps.setString(7, workFormAutoFillField.getSourceSpecUniqueId());
							}
							if (workFormAutoFillField.getSourceSpecId() != null) {
								ps.setLong(8, workFormAutoFillField.getSourceSpecId());
							} else {
								ps.setNull(8, Types.BIGINT);
							}
							
						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return workFormAutoFillFieldList.size();
						}
					});
		}
		public long insertWorkAutoFillsectionSpecs(FormAutoFillSectionConfiguration formAutoFillSectionConfiguration, Long workFormAutofillId) {

			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_INTO_WORK_FORM_AUTO_FILL_SECTION,
							Statement.RETURN_GENERATED_KEYS);

					ps.setLong(1, workFormAutofillId);
					ps.setString(2, formAutoFillSectionConfiguration.getSectionSpecUniqueId());
					ps.setLong(3, formAutoFillSectionConfiguration.getSourceActionSpecId());
					ps.setString(4, formAutoFillSectionConfiguration.getSourceSectionSpecUniqueId());
					ps.setBoolean(5, formAutoFillSectionConfiguration.isDeleteSectionInstance());
					ps.setBoolean(6, formAutoFillSectionConfiguration.isAddSectionInstance());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			formAutoFillSectionConfiguration.setFormAutoFillSectionConfigurationId(id);
			return id;

		}
		public void insertIntoFormAutoFillSectionFieldConfigurations(
				List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration) {

			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_WORK_FORM_AUTO_FILL_SECTION_FILEDS_CONFIGURATION,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							
							FormAutoFillSectionFieldsConfiguration obj = formAutoFillSectionFieldsConfiguration.get(i);
							ps.setLong(1, obj.getFormAutoFillSectionConfigurationId());
							ps.setLong(2, obj.getWorkFormAutoFillId());
							ps.setString(3,obj.getFieldSpecUniqueId());
							ps.setString(4, obj.getSourceFieldSpecUniqueId());
							ps.setInt(5,obj.getPickAnother());
						}
		
						@Override
						public int getBatchSize() {
							return formAutoFillSectionFieldsConfiguration.size();
						}
						
			});
			
		}
		public void createFormToWorkAutoFillFormConfig(final FormToWorkAutoFill formToWorkAutoFill,
				final long by) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {

					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_INTO_FORM_TO_WORK_AUTO_FILL,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, formToWorkAutoFill.getWorkSpecId());
					ps.setLong(2, formToWorkAutoFill.getWorkActionSpecId());
					ps.setString(3, formToWorkAutoFill.getWorkActionFormSpecUniqueId());
					ps.setBoolean(4, formToWorkAutoFill.isEnable());
					ps.setString(5, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(6, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setLong(7, by);
					ps.setLong(8, by);
					ps.setBoolean(9, formToWorkAutoFill.isSectionAutoFill());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			formToWorkAutoFill.setFormToWorkAutoFillId(id);

		}
		public void insertFormToWorkAutoFillFormFields(
				final List<FormToWorkAutoFillField> formToWorkAutoFillFieldList) {
			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_FORM_TO_WORK_AUTO_FILL_FIELDS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							FormToWorkAutoFillField formToWorkAutoFillField = formToWorkAutoFillFieldList
									.get(i);

							ps.setLong(1, formToWorkAutoFillField.getFormToWorkAutoFillId());
							ps.setString(2,formToWorkAutoFillField.getWorkFieldSpecUniqueId());
							ps.setString(3,formToWorkAutoFillField.getFormFieldSpecUniqueId());
							ps.setInt(4, formToWorkAutoFillField.getFieldType());
							ps.setBoolean(5, formToWorkAutoFillField.isAllowNull());
							
						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return formToWorkAutoFillFieldList.size();
						}
					});
		}
		public void createWorkAttachementAutoFillFormConfig(final WorkAttachmentAutoFill workAttachmentAutoFill,
				final long by) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {

					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_INTO_WORK_ATTACHEMENT_FORM_AUTO_FILL,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, workAttachmentAutoFill.getFormSpecUniqueId());
					ps.setBoolean(2, workAttachmentAutoFill.isEnable());
					ps.setString(3, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(4, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setLong(5, by);
					ps.setLong(6, workAttachmentAutoFill.getWorkSpecId());
					ps.setBoolean(7, workAttachmentAutoFill.isAttachmentSectionAutoFill());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			workAttachmentAutoFill.setWorkAttachmentAutoFillId(id);

		}
		
		public void insertWorkAttachmentAutoFillFormFields(
				final List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldList) {
			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_WORK_ATTACHEMENT_FORM_AUTO_FILL_FIELDS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkAttachmentFormAutoFillField workAttachementFormAutoFillField = workAttachmentFormAutoFillFieldList
									.get(i);

							ps.setLong(1, workAttachementFormAutoFillField.getWorkAttachmentAutoFillId());
							ps.setInt(2, workAttachementFormAutoFillField.getFieldType());
							ps.setString(3,
									workAttachementFormAutoFillField.getFieldSpecUniqueId());
							ps.setString(4, workAttachementFormAutoFillField.getWorkFieldSpecUniqueId());
							
							//added on 6-may-2015 by ameer for pickAnother value feature
							ps.setInt(5, workAttachementFormAutoFillField.getPickAnother());
							ps.setInt(6, workAttachementFormAutoFillField.getSourceType());
							if(Api.isEmptyString(workAttachementFormAutoFillField.getSourceSpecUniqueId())){
								ps.setNull(7, Types.VARCHAR);
							}else{
								ps.setString(7, workAttachementFormAutoFillField.getSourceSpecUniqueId());
							}
							ps.setLong(8, workAttachementFormAutoFillField.getSourceSpecId());
							
						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return workAttachmentFormAutoFillFieldList.size();
						}
					});
		}
		public long insertWorkAttachementAutoFillsectionSpecs(AttachmnetFormAutoFillSectionConfiguration attachmnetFormAutoFillSectionConfiguration, Long workAttachmentAutoFillId) {

			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_INTO_WORK_ATTACHMENT_FORM_AUTO_FILL_SECTION,
							Statement.RETURN_GENERATED_KEYS);

					ps.setLong(1, workAttachmentAutoFillId);
					ps.setString(2, attachmnetFormAutoFillSectionConfiguration.getSectionSpecUniqueId());
					ps.setLong(3, attachmnetFormAutoFillSectionConfiguration.getSourceActionSpecId());
					ps.setString(4, attachmnetFormAutoFillSectionConfiguration.getSourceSectionSpecUniqueId());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			attachmnetFormAutoFillSectionConfiguration.setAttachmnetFormAutoFillSectionConfigurationId(id);
			return id;

		}
		
		public void insertWorkAttachementAutoFillFormSectionFields(
				final List<WorkAttachmentAutoFillSectionFieldsConfiguration> workAttachmentAutoFillSectionFieldsConfiguration) {
			
			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_WORK_ATTACHMENT_AUTO_FILL_SECTION_FIELDS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkAttachmentAutoFillSectionFieldsConfiguration workFormAutoFillField = workAttachmentAutoFillSectionFieldsConfiguration
									.get(i);
							ps.setLong(1, workFormAutoFillField.getAttachmnetFormAutoFillSectionConfigurationId());
							ps.setLong(2, workFormAutoFillField.getWorkAttachmentAutoFillId());
							
							ps.setString(3,
									workFormAutoFillField.getFieldSpecUniqueId());
							if(Api.isEmptyString(workFormAutoFillField.getSourceFieldSpecUniqueId())){
								ps.setNull(4, Types.VARCHAR);
							}else{
								ps.setString(4, workFormAutoFillField.getSourceFieldSpecUniqueId());
							}
							if(!Api.isEmptyString(workFormAutoFillField.getPickAnother()+""))
								ps.setInt(5, workFormAutoFillField.getPickAnother());
							else
								ps.setInt(5, 0);
							
						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return workAttachmentAutoFillSectionFieldsConfiguration.size();
						}
			});
		}
		public void insertWorkFieldUniqueConfigurationsForWorkSpec(final WebUser webUser,
				final List<WorkFieldsUniqueConfigurations> fieldsUniqueConfigurations) {

			jdbcTemplate.batchUpdate(Sqls.INSERT_WORK_FIELD_UNIQUE_CONFIGURATIONS_FOR_WORK_SPEC,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkFieldsUniqueConfigurations WorkFieldsUnique = fieldsUniqueConfigurations.get(i);
							ps.setLong(1,WorkFieldsUnique.getWorkSpecId());
							ps.setString(2,WorkFieldsUnique.getFormFieldUniqueId());
							if(WorkFieldsUnique.getUniqueCheck()!=null)
								ps.setInt(3,WorkFieldsUnique.getUniqueCheck());
							else
								ps.setInt(3,0);
							ps.setLong(4,webUser.getCompanyId());
							ps.setLong(5,webUser.getEmpId());
							ps.setString(6, Api.getDateTimeInUTC(new Date(System				
									.currentTimeMillis())));
						}
						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return fieldsUniqueConfigurations.size();
						}

					});

			
		}
		public void insertWorkReassignmentRules(
				final List<WorkReassignmentRules> workReassignmentRulesList) {
			
			jdbcTemplate.batchUpdate(
					Sqls.INSERT_INTO_WORK_RE_ASSIGNMENT_RULES,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkReassignmentRules workReassignmentRules = workReassignmentRulesList
									.get(i);
							ps.setLong(1,workReassignmentRules.getWorkSpecId());
							ps.setLong(2,workReassignmentRules.getWorkActionSpecId());
							ps.setBoolean(3,workReassignmentRules.isEnableWorkReassignment());
							ps.setString(4,workReassignmentRules.getReassignFormFieldUniqueId());
							ps.setString(5,workReassignmentRules.getConditionRule());
							if(!Api.isEmptyString(workReassignmentRules.getConjunction()))
								ps.setString(6,workReassignmentRules.getConjunction());
							else
								ps.setNull(6,Types.VARCHAR);
							if(!Api.isEmptyString(workReassignmentRules.getTargetFieldExpression()))
								ps.setString(7,workReassignmentRules.getTargetFieldExpression());
							else
								ps.setNull(7,Types.VARCHAR);
							if(!Api.isEmptyString(workReassignmentRules.getValue()))
								ps.setString(8,workReassignmentRules.getValue());
							else
								ps.setNull(8,Types.VARCHAR);
							if(workReassignmentRules.getFieldDataType() != null)
								ps.setInt(9,workReassignmentRules.getFieldDataType());
							else
								ps.setInt(9,Types.INTEGER);
							if(!Api.isEmptyString(workReassignmentRules.getCondition()))
								ps.setString(10,workReassignmentRules.getCondition());
							else
								ps.setNull(10,Types.VARCHAR);
							ps.setLong(11,workReassignmentRules.getCreatedBy());
							ps.setLong(12,workReassignmentRules.getModifiedBy());
							ps.setString(13, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							ps.setString(14, Api.getDateTimeInUTC(new Date(System
									.currentTimeMillis())));
							
						}
							@Override
							public int getBatchSize() {
							return workReassignmentRulesList.size();
							
						}
					});
		}

		public int insertLabelsForMobileApp(final List<WorkSpecAppLabel> workSpecAppLabels) {
			
			jdbcTemplate.batchUpdate(Sqls.INSERT_WORK_SPEC_APP_LABELS_FOR_COMPANY,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							
							WorkSpecAppLabel workSpecAppLabel = workSpecAppLabels.get(i);
						    ps.setString(1, workSpecAppLabel.getItemId());
							ps.setString(2,workSpecAppLabel.getLabelName());
							ps.setString(3, workSpecAppLabel.getCustomeLabelName());
							ps.setInt(4, workSpecAppLabel.getDisplayOrder());
							ps.setInt(5, workSpecAppLabel.getItemType());
							ps.setBoolean(6, workSpecAppLabel.isVisible());
							ps.setLong(7, workSpecAppLabel.getCompanyId());
							ps.setLong(8, workSpecAppLabel.getWorkSpecId());
							ps.setLong(9, workSpecAppLabel.getCreatedBy());
							ps.setLong(10, workSpecAppLabel.getModifiedBy());
							ps.setString(11, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							ps.setString(12, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							
						}

						@Override
						public int getBatchSize() {
							return workSpecAppLabels.size();
						}

					});
			return workSpecAppLabels.size();
		}
		
		public long insertWorkUnassignmentCriterias(final WorkUnassignmentCriterias workUnassignmentCriterias) {
			
			Long id = 0l;
			try{
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					
					PreparedStatement ps = connection.prepareStatement
							(Sqls.INSERT_WORK_UNASSIGNMENT_CRITERIAS,Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, workUnassignmentCriterias.getWorkSpecId());
					ps.setLong(2, workUnassignmentCriterias.getWorkActionSpecId());
					ps.setString(3, workUnassignmentCriterias.getFormSpecUniqueId());
					ps.setLong(4, workUnassignmentCriterias.getCompanyId());
					ps.setInt(5, workUnassignmentCriterias.getConjunction());
					ps.setLong(6, workUnassignmentCriterias.getCreatedBy());
					ps.setLong(7, workUnassignmentCriterias.getModifiedBy());
					ps.setString(8, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setString(9, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					return ps;
				}
				
			}, keyHolder);
			
			id = keyHolder.getKey().longValue();
			workUnassignmentCriterias.setWorkUnassignmentCriteriaId(id);
			return id;
			}//try
			catch(Exception e){
				return id;
			}
		}
		public int[] insertWorkAssignmentCriteriaConditions(final List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions) {
			
			return jdbcTemplate.batchUpdate(Sqls.INSERT_WORK_ASSIGNMENT_CRITERIA_CONDITIONS,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							WorkAssignmentCriteriaConditions workAssignmentCriteriaCondition = workAssignmentCriteriaConditions.get(i);
							ps.setLong(1, workAssignmentCriteriaCondition.getWorkUnassignmentCriteriaId());
							ps.setString(2, workAssignmentCriteriaCondition.getFieldSpecUniqueId());
							ps.setString(3, workAssignmentCriteriaCondition.getFieldExpression());
							ps.setBoolean(4, workAssignmentCriteriaCondition.isSection());
							if(workAssignmentCriteriaCondition.getFieldDataType() == null)
								ps.setNull(5,Types.LONGNVARCHAR);
							else
								ps.setLong(5, workAssignmentCriteriaCondition.getFieldDataType());
							
							ps.setInt(6, workAssignmentCriteriaCondition.getCondition());
							
							if(workAssignmentCriteriaCondition.getConditionValue() == null)
								ps.setString(7, "0");
							else
								ps.setString(7, workAssignmentCriteriaCondition.getConditionValue());
							
							if(workAssignmentCriteriaCondition.getCriteriaType() == 0)
								ps.setInt(8, workAssignmentCriteriaCondition.CRITERIA_TYPE_ACTION_FORM_FIELDS);
							else
								ps.setInt(8, workAssignmentCriteriaCondition.getCriteriaType());
								
							if(workAssignmentCriteriaCondition.getActionCount() == null)
								ps.setLong(9, 0);
							else {
								ps.setLong(9, workAssignmentCriteriaCondition.getActionCount());
							}
						}
		
						@Override
						public int getBatchSize() {
							return workAssignmentCriteriaConditions.size();
						}
					});
		}
		public Long insertIntoExternalActionConfiguration(ExternalActionConfiguration externalActionConfiguration,
				WebUser webUser) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_INTO_EXTERNAL_ACTION_CONFIGURATION,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, externalActionConfiguration.getWorkSpecId());
					ps.setLong(2, externalActionConfiguration.getWorkActionSpecId());
					//ps.setLong(3, webUser.getCompanyId());
					ps.setBoolean(3, externalActionConfiguration.isCanPerformActionByCustomer());
					ps.setInt(4, externalActionConfiguration.getExpiryTimeLimit());
					ps.setBoolean(5, externalActionConfiguration.getCanRegenerateNewLink());
					ps.setLong(6, webUser.getEmpId());
					ps.setLong(7, webUser.getEmpId());
					ps.setString(8, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setString(9, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setLong(10, webUser.getCompanyId());
					ps.setBoolean(11, externalActionConfiguration.getEnableAutomaticEmailTrigger());
					ps.setString(12, externalActionConfiguration.getMessageBody());
					ps.setBoolean(13, externalActionConfiguration.isCanOnlyPerformByExternalUser());
					ps.setBoolean(14, externalActionConfiguration.isSendRemainderNotification());
					ps.setInt(15, externalActionConfiguration.getReminderCount());
					ps.setInt(16, externalActionConfiguration.getReminderFrequency());
					ps.setString(17, externalActionConfiguration.getRemainderNotificationMessageBody());
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			externalActionConfiguration.setExternalActionConfigurationId(id);
			return id;
		}
		public long insertFormToWorkAutoFillsectionSpecs(
				FormToWorkAutoFillSectionConfiguration formToWorkAutoFillSectionConfiguration) {

			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_INTO_FORM_TO_WORK_AUTO_FILL_SECTION,
							Statement.RETURN_GENERATED_KEYS);

					ps.setLong(1, formToWorkAutoFillSectionConfiguration.getFormToWorkAutoFillId());
					ps.setString(2, formToWorkAutoFillSectionConfiguration.getSectionSpecUniqueId());
					ps.setLong(3, formToWorkAutoFillSectionConfiguration.getSourceActionSpecId());
					ps.setString(4, formToWorkAutoFillSectionConfiguration.getSourceSectionSpecUniqueId());

					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			formToWorkAutoFillSectionConfiguration.setFormToWorkAutoFillSectionConfigurationId(id);
			return id;

		}
		public void insertFormToWorkAutoFillFormSectionFields(
				final List<FormToWorkAutoFillSectionFieldsConfiguration> FormToWorkAutoFillFieldList) {

			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_FORM_TO_WORK_AUTO_FILL_SECTION_FIELDS,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							FormToWorkAutoFillSectionFieldsConfiguration FormToWorkAutoFillField = FormToWorkAutoFillFieldList
									.get(i);
							ps.setLong(1, FormToWorkAutoFillField.getFormToWorkAutoFillSectionConfigurationId());
							ps.setLong(2, FormToWorkAutoFillField.getFormToWorkAutoFillId());

							ps.setString(3, FormToWorkAutoFillField.getFieldSpecUniqueId());
							if (Api.isEmptyString(FormToWorkAutoFillField.getSourceFieldSpecUniqueId())) {
								ps.setNull(4, Types.VARCHAR);
							} else {
								ps.setString(4, FormToWorkAutoFillField.getSourceFieldSpecUniqueId());
							}
							if (!Api.isEmptyString(FormToWorkAutoFillField.getPickAnother() + ""))
								ps.setInt(5, FormToWorkAutoFillField.getPickAnother());
							else
								ps.setInt(5, 0);

						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return FormToWorkAutoFillFieldList.size();
						}
					});
		}

		public void insertIntoWorkActionFormVisibility(List<WorkActionFormVisibility> workActionFormVisibility) {
			jdbcTemplate.batchUpdate(Sqls.INSERT_INTO_WORK_ACTION_FORM_VISIBILITY,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							WorkActionFormVisibility workActionObj = workActionFormVisibility
									.get(i);
							ps.setLong(1, workActionObj.getWorkSpecId());
							ps.setLong(2, workActionObj.getCompanyId());
							ps.setLong(3, workActionObj.getActionSpecId());
							ps.setString(4,Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
							ps.setString(5, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));

						}

						@Override
						public int getBatchSize() {
							// TODO Auto-generated method stub
							return workActionFormVisibility.size();
						}
					});
			
		}
		public int[] insertFormSpecUniqueIdsForWorkSpec(
				final List<WorkSpecFormSpecMap> workSpecFormSpecMaps) {
			return jdbcTemplate.batchUpdate(
					Sqls.INSERT_FORMSPECUNIQUEIDS_FOR_WORKSPEC,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							WorkSpecFormSpecMap workSpecFormSpecMap = workSpecFormSpecMaps
									.get(i);
							ps.setString(1, workSpecFormSpecMap.getWorkSpecId()+"");
							ps.setString(2, workSpecFormSpecMap.getFormSpecUniqueId());
						}

						@Override
						public int getBatchSize() {
							return workSpecFormSpecMaps.size();
						}
					});
		}
		public List<CustomEntitySpec> getCustomEntitySpecsByIds(String customEntitySpecIds) {
			List<CustomEntitySpec> customEntitySpecs = new ArrayList<CustomEntitySpec>();
			try
			{
				String sql = Sqls.SELECT_CUSTOM_ENTITY_SPECS_BY_IDS.replace(":customEntitySpecIds", customEntitySpecIds);
				customEntitySpecs = jdbcTemplate.query(sql,new Object[] {},
						new BeanPropertyRowMapper<CustomEntitySpec>(CustomEntitySpec.class));
			}
			catch(Exception e)
			{
			}
			return customEntitySpecs;
		}
		public long insertCustomEntitySpecs(final CustomEntitySpec customEntitySpec) {
			
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection
							.prepareStatement(Sqls.INSERT_CUSTOM_ENTITY_SPECS,
									Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, customEntitySpec.getTitle());
					ps.setString(2, customEntitySpec.getDescription());
					ps.setString(3, customEntitySpec.getFormSpecUniqueId());
					ps.setLong(4, customEntitySpec.getCreatedBy());
					ps.setLong(5, customEntitySpec.getModifiedBy());
					ps.setString(6, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(7, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setLong(8, customEntitySpec.getCompanyId());
					ps.setLong(9, customEntitySpec.getIsSystemDefined());
					ps.setLong(10, customEntitySpec.getPurpose());

					if (customEntitySpec.getCopiedFrom() != null)
						ps.setLong(11, customEntitySpec.getCopiedFrom());
					else
						ps.setNull(11, Types.VARCHAR);

					if (customEntitySpec.getCopiedFrom() != null)
						ps.setLong(11, customEntitySpec.getCopiedFrom());
					else
						ps.setNull(11, Types.VARCHAR);

					if (customEntitySpec.getSkeletonCustomEntitySpecId() != null)
						ps.setLong(12, customEntitySpec.getSkeletonCustomEntitySpecId());
					else
						ps.setNull(12, Types.VARCHAR);

					ps.setBoolean(13, customEntitySpec.isEnableCustomEntityCheckIn());
					ps.setBoolean(14, customEntitySpec.isDayPlanAllowd());
					ps.setBoolean(15, customEntitySpec.isEnableOnlineSearchForUniqueness());
					
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			customEntitySpec.setCustomEntitySpecId(id);
			return id;

		}

		public void updateCustomEntityFormSpec(String newFormSpecUniqueId, Long customEntitySpecId) {
			try {
				jdbcTemplate.update(Sqls.UPDATE_CUSTOM_ENTITY_FORM_SPEC, new Object[] { newFormSpecUniqueId,
						Api.getDateTimeInUTC(new Date(System.currentTimeMillis())), customEntitySpecId });
			} catch (Exception e) {

			}
		}

		public List<Entity> getEntitiesByEntitySpecIds(String entitySpecIds) {
			List<Entity> entities = new ArrayList<Entity>();
			try {
				String sql = Sqls.SELECT_ENTITIES_BY_ENTITY_SPEC_IDS.replace(":entitySpecIds", entitySpecIds);
				entities = jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<Entity>(Entity.class));
			} catch (Exception e) {
			}
			return entities;
		}
		
		public List<EntityField> getEntityFieldByEntityIn(List<Entity> entities) {

			String ids = "";
			ids = Api.toCSV(entities, "entityId", CsvOptions.NONE);

			return getEntityFieldByEntityIn(ids);
		}

		public List<EntityField> getEntityFieldByEntityIn(String entityIds) {
			if (!Api.isEmptyString(entityIds)) {
				String sql = Sqls.SELECT_ENTITY_FIELD_BY_ENTITY_IN.replace(":ids",
						entityIds);
				List<EntityField> entityFields = jdbcTemplate.query(sql,
						new Object[] {}, new BeanPropertyRowMapper<EntityField>(
								EntityField.class));
				return entityFields;
			} else {
				return new ArrayList<EntityField>();
			}
		}
		public List<EntitySectionField> getEntitySectionFieldsByEntityIn(List<Entity> entities) {
			
			String ids = Api.toCSV(entities, "entityId", CsvOptions.NONE);

			return getEntitySectionFieldsByEntityIn(ids);
		}

		public List<EntitySectionField> getEntitySectionFieldsByEntityIn(String entityIds) {
			
			if(!Api.isEmptyString(entityIds))
			{
				try {
					String sql = Sqls.SELECT_ENTITY_SECTION_FIELD_BY_ENTITY_IN.replace(":ids",
							entityIds);
					List<EntitySectionField> entitySectionFields = jdbcTemplate.query(sql,
							new Object[] {}, new BeanPropertyRowMapper<EntitySectionField>(
									EntitySectionField.class));
					return entitySectionFields;
				} catch(Exception e) {
					e.printStackTrace();
					return new ArrayList<EntitySectionField>();
				}
			}
			else
			{
				return new ArrayList<EntitySectionField>();
			}
			
		}

		public EntitySpec getEntitySpecBySkeletonSpecId(long entitySpecId, Integer companyId) {
			EntitySpec entitySpec = null;
			 try {
					String sql = Sqls.SELECT_ENTITY_SPEC_BY_SKELETON_SPEC_ID;
				 entitySpec = jdbcTemplate.queryForObject(sql,new Object[] {companyId,entitySpecId},new int[] {Types.INTEGER,Types.BIGINT},new BeanPropertyRowMapper<EntitySpec>(EntitySpec.class));
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			return entitySpec;
		}
		public long insertEntity(final Entity entity, final long companyId,
				final Long by, final String code) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_ENTITY, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, companyId);
					ps.setLong(2, entity.getEntitySpecId());
					ps.setInt(3, entity.getEntityStatus());
					if (by == null) {
						ps.setNull(4, Types.BIGINT);
					} else {
						ps.setLong(4, by);
					}
					if (by == null) {
						ps.setNull(5, Types.BIGINT);
					} else {
						ps.setLong(5, by);
					}

					ps.setString(6, entity.getClientSideId());
					ps.setString(7, code);
					if (Api.isEmptyString(entity.getExternalId())) {
						ps.setNull(8, Types.VARCHAR);
					} else {
						ps.setString(8, entity.getExternalId());
					}
					if (Api.isEmptyString(entity.getApiUserId())) {
						ps.setNull(9, Types.VARCHAR);
					} else {
						ps.setString(9, entity.getApiUserId());
					}
					ps.setString(10, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(11, Api.getDateTimeInUTC(new Date(System
							.currentTimeMillis())));
					ps.setString(12, Api.makeNullIfEmpty(entity.getChecksum()));
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			entity.setEntityId(id);

			return id;
		}
		
		public long insertEntityField(final EntityField entityField) {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							Sqls.INSERT_ENTITY_FIELD, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, entityField.getEntityId());
					ps.setLong(2, entityField.getEntitySpecId());
					ps.setLong(3, entityField.getEntityFieldSpecId());
					ps.setString(4, entityField.getFieldValue());
					ps.setString(5,  Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setString(6,  Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
					ps.setString(7, entityField.getFieldLabel());
					ps.setInt(8, entityField.getFieldType());
					ps.setInt(9, entityField.getDisplayOrder());
					ps.setInt(10, entityField.getIdentifier());
					ps.setString(11, entityField.getDisplayValue());
					return ps;
				}
			}, keyHolder);

			long id = keyHolder.getKey().longValue();
			entityField.setFieldId(id);
			return id;
		}
		public long saveMedia(final Media media){
		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    
		    jdbcTemplate.update(new PreparedStatementCreator() {

//		        @Override
		        public PreparedStatement createPreparedStatement(
		                Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(Sqls.INSERT_MEDIA, Statement.RETURN_GENERATED_KEYS);
		            ps.setLong(1, media.getCompanyId());
		            ps.setLong(2, media.getEmpId());
		            ps.setString(3, media.getMimeType());
		            ps.setString(4, media.getLocalPath());
		            ps.setString(5, media.getFileName());
		            ps.setString(6, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
		            ps.setString(7, Api.getDateTimeInUTC(new Date(System.currentTimeMillis())));
		            
		            if(media.getConfig() == null){
		                ps.setInt(8, 0);
		            }else{
		                ps.setInt(8, media.getConfig());
		            }
		            
		            if(Api.isEmptyString(media.getExternalMediaId())){
		                ps.setNull(9, Types.VARCHAR);
		            }else{
		                ps.setString(9, media.getExternalMediaId());
		            }
		            return ps;
		        }
		    }, keyHolder);
		    
		    long mediaId = keyHolder.getKey().longValue();
		    media.setId(mediaId);
		    
		    return mediaId;
		}
		
		public Media getMedia(String id){
	        Media media = jdbcTemplate.queryForObject(Sqls.SELECT_MEDIA, new Object[]{id}, new BeanPropertyRowMapper<Media>(Media.class));
	        return media;
	    }


}
