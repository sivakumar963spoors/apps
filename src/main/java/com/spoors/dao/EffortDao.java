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
import com.spoors.beans.CustomerFilteringCritiria;
import com.spoors.beans.EmployeeFilteringCritiria;
import com.spoors.beans.FieldValidationCritiria;
import com.spoors.beans.FormCleanUpRule;
import com.spoors.beans.FormFieldGroupSpec;
import com.spoors.beans.FormFieldSpec;
import com.spoors.beans.FormFieldSpecValidValue;
import com.spoors.beans.FormFieldsColorDependencyCriterias;
import com.spoors.beans.FormPageSpec;
import com.spoors.beans.FormSectionFieldSpec;
import com.spoors.beans.FormSectionFieldSpecValidValue;
import com.spoors.beans.FormSectionSpec;
import com.spoors.beans.FormSpec;
import com.spoors.beans.ListFilteringCritiria;
import com.spoors.beans.VisibilityDependencyCriteria;
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
	

	
	
	
}
