package com.spoors.manager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spoors.beans.CustomEntityFilteringCritiria;
import com.spoors.beans.CustomerAutoFilteringCritiria;
import com.spoors.beans.CustomerFilteringCritiria;
import com.spoors.beans.DataSourceRequestHeader;
import com.spoors.beans.DataSourceRequestParam;
import com.spoors.beans.DataSourceResponseMapping;
import com.spoors.beans.EmployeeFilteringCritiria;
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
import com.spoors.beans.RemainderFieldsMap;
import com.spoors.beans.StockFormConfiguration;
import com.spoors.beans.VisibilityDependencyCriteria;
import com.spoors.beans.WorkFormFieldMap;
import com.spoors.beans.WorkSpecFormSpecFollowUp;
import com.spoors.setting.MobileSqls;
import com.spoors.util.Api;

@Service
public class SqliteManager {

	public void saveFormSpecDataToSqlite(FormSpecContainer formSpecContainer) {
		
			try {
				saveToSqlite(formSpecContainer);
			} catch (SqlJetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void saveToSqlite(FormSpecContainer formSpecContainer) throws SqlJetException {

		FormSpec formSpecObj = formSpecContainer.getFormSpecs().get(0);
		Long currentTime = System.currentTimeMillis();

		String sqliteFileName = formSpecObj.getUniqueId() + ".sqlite"; /// syncSqlitePath.substring(syncSqlitePath.lastIndexOf("/")+1);

		String sqliteFilePath = "/home/spoors/Desktop/SqliteExport/" + sqliteFileName;

		SqlJetDb db = null;
		try {
			File fileToCreate = new File(sqliteFilePath);

			currentTime = System.currentTimeMillis();
			File fileToCreateDir = new File(fileToCreate.getParent());
			if (!fileToCreateDir.exists()) {
				currentTime = System.currentTimeMillis();
				fileToCreateDir.mkdirs();
			}

			File dbFile = new File(sqliteFilePath);

			db = SqlJetDb.open(dbFile, true);

			db.getOptions().setAutovacuum(true);
			db.beginTransaction(SqlJetTransactionMode.WRITE);
			db.getOptions().setUserVersion(1);

			Long t1 = System.currentTimeMillis();
			currentTime = System.currentTimeMillis();
			createSqliteTables(db);

			ISqlJetTable jsonTable = null;

			List<ListFilteringCritiria> listFilteringCritiriaList = formSpecContainer.getListFilteringCriterias();
			if (listFilteringCritiriaList != null) {
				jsonTable = db.getTable("forms_specs_list_filtering_criterias");
				int formSpecListFilteringCriteriaAutoIncrementId = 1;
				for (ListFilteringCritiria listFilteringCritiria : listFilteringCritiriaList) {
					jsonTable.insert(Api.toJson(listFilteringCritiria), 0,
							formSpecListFilteringCriteriaAutoIncrementId);
					formSpecListFilteringCriteriaAutoIncrementId++;
				}
			}

			List<RemainderFieldsMap> remainderFieldsMap = formSpecContainer.getRemainderFieldsMap();
			if (remainderFieldsMap != null) {
				jsonTable = db.getTable("remainder_fields_map");
				int remainderFieldsMapAutoIncrementId = 1;
				for (RemainderFieldsMap remainderFieldsMapObj : remainderFieldsMap) {
					jsonTable.insert(Api.toJson(remainderFieldsMapObj), 0, remainderFieldsMapAutoIncrementId);
					remainderFieldsMapAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSpecPermission> formSpecPermissionList = formSpecContainer.getFormSpecPermissions();
			if (formSpecPermissionList != null) {
				jsonTable = db.getTable("form_spec_permissions");
				int formSpecPermissionAutoIncrementId = 1;
				for (FormSpecPermission formSpecPermission : formSpecPermissionList) {
					jsonTable.insert(Api.toJson(formSpecPermission), 0, formSpecPermissionAutoIncrementId);
					formSpecPermissionAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSpecConfigSaveOnOtpVerify> formSpecConfigSaveOnOtpVerifyList = formSpecContainer
					.getSaveFormOnOtpVerify();
			if (formSpecConfigSaveOnOtpVerifyList != null) {
				jsonTable = db.getTable("save_form_on_otp_verify");
				int formSpecConfigSaveOnOtpVerifyListAutoIncrementId = 1;
				for (FormSpecConfigSaveOnOtpVerify saveOnOtpVerify : formSpecConfigSaveOnOtpVerifyList) {
					jsonTable.insert(Api.toJson(saveOnOtpVerify), 0, formSpecConfigSaveOnOtpVerifyListAutoIncrementId);
					formSpecConfigSaveOnOtpVerifyListAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();

			List<CustomerAutoFilteringCritiria> customerAutoFilteringCritiriasList = formSpecContainer
					.getCustomerAutoFilteringCritirias();
			if (customerAutoFilteringCritiriasList != null) {
				jsonTable = db.getTable("forms_specs_customer_auto_filtering_criterias");
				int customerAutoFilteringCritiriaAutoIncrementId = 1;
				for (CustomerAutoFilteringCritiria customerAutoFilteringCritiria : customerAutoFilteringCritiriasList) {
					jsonTable.insert(Api.toJson(customerAutoFilteringCritiria), 0,
							customerAutoFilteringCritiriaAutoIncrementId);
					customerAutoFilteringCritiriaAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			/* Added for Customer filtering criteria by ameer on 21/1/2015 1:00pm */
			List<CustomerFilteringCritiria> customerFilteringCritiriaList = formSpecContainer
					.getCustomerFilteringCriterias();
			if (customerFilteringCritiriaList != null) {
				jsonTable = db.getTable("forms_specs_customer_filtering_criterias");
				int formSpecCustomerFilteringCriteriaAutoIncrementId = 1;
				for (CustomerFilteringCritiria customerFilteringCritiria : customerFilteringCritiriaList) {
					jsonTable.insert(Api.toJson(customerFilteringCritiria), 0,
							formSpecCustomerFilteringCriteriaAutoIncrementId);
					formSpecCustomerFilteringCriteriaAutoIncrementId++;
				}
			}
			/* customer filtering criteria end */

			// employee filtering , 2018-05-01,Deva
			currentTime = System.currentTimeMillis();
			/* Added for Employee filtering criteria by ameer on 21/1/2015 1:00pm */
			List<EmployeeFilteringCritiria> employeeFilteringCritiriaList = formSpecContainer
					.getEmployeeFilteringCriterias();
			if (employeeFilteringCritiriaList != null) {
				jsonTable = db.getTable("forms_specs_employee_filtering_criterias");
				int formSpecEmployeeFilteringCriteriaAutoIncrementId = 1;
				for (EmployeeFilteringCritiria employeeFilteringCritiria : employeeFilteringCritiriaList) {
					jsonTable.insert(Api.toJson(employeeFilteringCritiria), 0,
							formSpecEmployeeFilteringCriteriaAutoIncrementId);
					formSpecEmployeeFilteringCriteriaAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			/* Added for Customer filtering criteria by ameer on 21/1/2015 1:00pm */
			List<FormFilteringCritiria> formFilteringCritiriaList = formSpecContainer.getFormFilteringCriterias();
			if (formFilteringCritiriaList != null) {
				jsonTable = db.getTable("forms_specs_form_filtering_criterias");
				int formSpecFormFilteringCriteriaAutoIncrementId = 1;
				for (FormFilteringCritiria formFilteringCritiria : formFilteringCritiriaList) {
					jsonTable.insert(Api.toJson(formFilteringCritiria), 0,
							formSpecFormFilteringCriteriaAutoIncrementId);
					formSpecFormFilteringCriteriaAutoIncrementId++;
				}
			}
			/* customer filtering criteria end */

			currentTime = System.currentTimeMillis();
			/* Added for Field Validation critiria by ameer on 31/5/2016 4:47pm */
			List<FieldValidationCritiria> fieldValidationCritiriaList = formSpecContainer.getFieldValidationCritirias();
			if (fieldValidationCritiriaList != null) {
				jsonTable = db.getTable("forms_specs_field_validation_critirias");
				int formSpecFieldValidationCriteriaAutoIncrementId = 1;
				for (FieldValidationCritiria fieldValidationCritiria : fieldValidationCritiriaList) {
					jsonTable.insert(Api.toJson(fieldValidationCritiria), 0,
							formSpecFieldValidationCriteriaAutoIncrementId);
					formSpecFieldValidationCriteriaAutoIncrementId++;
				}
			}
			/* field validation critiria end */

			currentTime = System.currentTimeMillis();
			/* Added for Stock Form Configuration by ameer on 7/6/2016 3:25pm */
			List<StockFormConfiguration> stockFormConfigurationList = formSpecContainer.getStockFormConfigurations();
			if (stockFormConfigurationList != null) {
				jsonTable = db.getTable("forms_specs_stock_form_configurations");
				int formSpecStockFormConfigAutoIncrementId = 1;
				for (StockFormConfiguration stockFormConfiguration : stockFormConfigurationList) {
					jsonTable.insert(Api.toJson(stockFormConfiguration), 0, formSpecStockFormConfigAutoIncrementId);
					formSpecStockFormConfigAutoIncrementId++;
				}
			}
			/* Stock Form Configuration end */

			List<OfflineListUpdateConfiguration> offlineListUpdateConfigurationList = formSpecContainer
					.getOfflineListUpdateConfigurations();
			if (offlineListUpdateConfigurationList != null) {
				jsonTable = db.getTable("forms_specs_offline_list_update_configurations");
				int formSpecOfflineListUpdateConfigAutoIncrementId = 1;
				for (OfflineListUpdateConfiguration offlineListUpdateConfiguration : offlineListUpdateConfigurationList) {
					jsonTable.insert(Api.toJson(offlineListUpdateConfiguration), 0,
							formSpecOfflineListUpdateConfigAutoIncrementId);
					formSpecOfflineListUpdateConfigAutoIncrementId++;
				}
			}

			List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfigurationList = formSpecContainer
					.getOfflineCustomEntityUpdateConfigurations();
			if (offlineCustomEntityUpdateConfigurationList != null) {
				jsonTable = db.getTable("forms_specs_offline_customEntity_update_configurations");
				int formSpecOfflineCustomEntityUpdateConfigAutoIncrementId = 1;
				for (OfflineCustomEntityUpdateConfiguration offlineCustomEntityUpdateConfiguration : offlineCustomEntityUpdateConfigurationList) {
					jsonTable.insert(Api.toJson(offlineCustomEntityUpdateConfiguration), 0,
							formSpecOfflineCustomEntityUpdateConfigAutoIncrementId);
					formSpecOfflineCustomEntityUpdateConfigAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSpecDataSource> formSpecDataSourceList = formSpecContainer.getFormSpecDataSource();
			if (formSpecDataSourceList != null) {
				jsonTable = db.getTable("forms_specs_form_spec_data_source");
				int formSpecDataSourceAutoIncrementId = 1;
				for (FormSpecDataSource formSpecDataSource : formSpecDataSourceList) {
					jsonTable.insert(Api.toJson(formSpecDataSource), 0, formSpecDataSourceAutoIncrementId);
					formSpecDataSourceAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<JobFormMapBean> jobFormMapBeanList = formSpecContainer.getJobFormMapBeans();
			if (jobFormMapBeanList != null) {
				jsonTable = db.getTable("forms_specs_job_form_field_spec_mapping");
				int formSpecsJobFieldAutoIncrementId = 1;
				for (JobFormMapBean jobFormMapBean : jobFormMapBeanList) {
					jsonTable.insert(Api.toJson(jobFormMapBean), 0, formSpecsJobFieldAutoIncrementId);
					formSpecsJobFieldAutoIncrementId++;
				}
			}

			// Follow Up Work sqlite starts

			currentTime = System.currentTimeMillis();
			List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUp = formSpecContainer.getWorkSpecFormSpecFollowUp();
			if (workSpecFormSpecFollowUp != null) {
				jsonTable = db.getTable("forms_specs_workspec_formspec_follow_up");
				int formSpecWorkSpecFormSpecFollowUpAutoIncrementId = 1;
				for (WorkSpecFormSpecFollowUp workSpecFormSpecFollowUpData : workSpecFormSpecFollowUp) {
					jsonTable.insert(Api.toJson(workSpecFormSpecFollowUpData), 0,
							formSpecWorkSpecFormSpecFollowUpAutoIncrementId);
					formSpecWorkSpecFormSpecFollowUpAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkFormFieldMap> workFormFieldMaps = formSpecContainer.getWorkFormFieldMap();
			if (workFormFieldMaps != null) {
				jsonTable = db.getTable("forms_specs_work_form_field_map");
				int formSpecWorkFormFieldMapAutoIncrementId = 1;
				for (WorkFormFieldMap workFormFieldMap : workFormFieldMaps) {
					jsonTable.insert(Api.toJson(workFormFieldMap), 0, formSpecWorkFormFieldMapAutoIncrementId);
					formSpecWorkFormFieldMapAutoIncrementId++;
				}
			}

			// Follow Up Work sqlite ends

			currentTime = System.currentTimeMillis();
			List<DataSourceRequestParam> dataSourceRequestParamList = formSpecContainer.getDataSourceRequestParams();
			if (dataSourceRequestParamList != null) {
				jsonTable = db.getTable("forms_specs_data_source_request_params");
				int requestParamAutoIncrementId = 1;
				for (DataSourceRequestParam dataSourceRequestParam : dataSourceRequestParamList) {
					jsonTable.insert(Api.toJson(dataSourceRequestParam), 0, requestParamAutoIncrementId);
					requestParamAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<DataSourceRequestHeader> dataSourceRequestHeaderList = formSpecContainer.getDataSourceRequestHeaders();
			if (dataSourceRequestHeaderList != null) {
				jsonTable = db.getTable("forms_specs_data_source_request_headers");
				int requestHeadersAutoIncrementId = 1;
				for (DataSourceRequestHeader dataSourceRequestHeader : dataSourceRequestHeaderList) {
					jsonTable.insert(Api.toJson(dataSourceRequestHeader), 0, requestHeadersAutoIncrementId);
					requestHeadersAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<DataSourceResponseMapping> dataSourceResponseMappingList = formSpecContainer
					.getDataSourceResponseMappings();
			if (dataSourceResponseMappingList != null) {
				jsonTable = db.getTable("forms_specs_data_source_response_mappings");
				int responseMappingAutoIncrementId = 1;
				for (DataSourceResponseMapping dataSourceResponseMapping : dataSourceResponseMappingList) {
					jsonTable.insert(Api.toJson(dataSourceResponseMapping), 0, responseMappingAutoIncrementId);
					responseMappingAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormFieldSpec> formFieldSpecList = formSpecContainer.getFields();
			if (formFieldSpecList != null) {
				jsonTable = db.getTable("forms_specs_fields");
				int fieldSpecsAutoIncrementId = 1;
				for (FormFieldSpec formFieldSpec : formFieldSpecList) {
					jsonTable.insert(Api.toJson(formFieldSpec), 0, fieldSpecsAutoIncrementId);
					fieldSpecsAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSectionFieldSpec> formSectionFieldSpecList = formSpecContainer.getSectionFields();
			if (formSectionFieldSpecList != null) {
				jsonTable = db.getTable("forms_specs_section_fields");
				int sectionFieldSpecAutoIncrementId = 1;
				for (FormSectionFieldSpec formSectionFieldSpec : formSectionFieldSpecList) {
					jsonTable.insert(Api.toJson(formSectionFieldSpec), 0, sectionFieldSpecAutoIncrementId);
					sectionFieldSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSpec> formSpecList = formSpecContainer.getFormSpecs();
			if (formSpecList != null) {
				jsonTable = db.getTable("forms_specs_form_specs");
				int formSpecAutoIncrementId = 1;
				for (FormSpec formSpec : formSpecList) {
					jsonTable.insert(Api.toJson(formSpec), 0, formSpecAutoIncrementId);
					formSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormPageSpec> formPageSpecList = formSpecContainer.getPageSpecs();
			if (formPageSpecList != null) {
				jsonTable = db.getTable("forms_specs_page_specs");
				int pageSpecAutoIncrementId = 1;
				for (FormPageSpec formPageSpec : formPageSpecList) {
					jsonTable.insert(Api.toJson(formPageSpec), 0, pageSpecAutoIncrementId);
					pageSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormFieldSpecValidValue> formFieldSpecValidValueList = formSpecContainer.getFieldValidValues();
			if (formFieldSpecValidValueList != null) {
				jsonTable = db.getTable("forms_specs_field_valid_values");
				int validValuesAutoIncrementId = 1;
				for (FormFieldSpecValidValue formFieldSpecValidValue : formFieldSpecValidValueList) {
					jsonTable.insert(Api.toJson(formFieldSpecValidValue), 0, validValuesAutoIncrementId);
					validValuesAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSectionSpec> formSectionSpecList = formSpecContainer.getSections();
			if (formSectionSpecList != null) {
				jsonTable = db.getTable("forms_specs_sections");
				int sectionSpecAutoIncrementId = 1;
				for (FormSectionSpec formSectionSpec : formSectionSpecList) {
					jsonTable.insert(Api.toJson(formSectionSpec), 0, sectionSpecAutoIncrementId);
					sectionSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<FormSectionFieldSpecValidValue> formSectionFieldSpecValidValueList = formSpecContainer
					.getSectionFieldValidValues();
			if (formSectionFieldSpecValidValueList != null) {
				jsonTable = db.getTable("forms_specs_section_field_valid_values");
				int sectionValidValueAutoIncrementId = 1;
				for (FormSectionFieldSpecValidValue formSectionFieldSpecValidValue : formSectionFieldSpecValidValueList) {
					jsonTable.insert(Api.toJson(formSectionFieldSpecValidValue), 0, sectionValidValueAutoIncrementId);
					sectionValidValueAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<VisibilityDependencyCriteria> visibilityDependencyCriteriaList = formSpecContainer
					.getVisibilityDependencyCriterias();
			if (visibilityDependencyCriteriaList != null) {
				jsonTable = db.getTable("forms_specs_visibility_dependency_criterias");
				int visibilityDependencyAutoIncrementId = 1;
				for (VisibilityDependencyCriteria visibilityDependencyCriteria : visibilityDependencyCriteriaList) {
					jsonTable.insert(Api.toJson(visibilityDependencyCriteria), 0, visibilityDependencyAutoIncrementId);
					visibilityDependencyAutoIncrementId++;
				}
			}

			// form fields background color
			currentTime = System.currentTimeMillis();
			List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriteriasList = formSpecContainer
					.getFormFieldsColorDependencyCriterias();
			if (formFieldsColorDependencyCriteriasList != null) {
				jsonTable = db.getTable("form_fields_Color_dependency_criterias");
				int colorDependencyCriteriaAutoIncrementId = 1;
				for (FormFieldsColorDependencyCriterias formFieldsColorDependencyCriterias : formFieldsColorDependencyCriteriasList) {
					jsonTable.insert(Api.toJson(formFieldsColorDependencyCriterias), 0,
							colorDependencyCriteriaAutoIncrementId);
					colorDependencyCriteriaAutoIncrementId++;
				}
			}

			// form clean-up rules
			currentTime = System.currentTimeMillis();
			List<FormCleanUpRule> formCleanUpRuleList = formSpecContainer.getFormCleanUpRule();
			if (formCleanUpRuleList != null) {
				jsonTable = db.getTable("form_clean_up_rules");
				int cleanUpRulesAutoIncrementId = 1;
				for (FormCleanUpRule formCleanUpRule : formCleanUpRuleList) {
					jsonTable.insert(Api.toJson(formCleanUpRule), 0, cleanUpRulesAutoIncrementId);
					cleanUpRulesAutoIncrementId++;
				}
			}

			// sending form field group specs to client
			currentTime = System.currentTimeMillis();
			List<FormFieldGroupSpec> formFieldGroupSpecs = formSpecContainer.getFormFieldGroupSpecs();
			if (formFieldGroupSpecs != null) {
				jsonTable = db.getTable("form_field_group_specs");
				int formFieldGroupSpecAutoIncrementId = 1;
				for (FormFieldGroupSpec formFieldGroupSpec : formFieldGroupSpecs) {
					jsonTable.insert(Api.toJson(formFieldGroupSpec), 0, formFieldGroupSpecAutoIncrementId);
					formFieldGroupSpecAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
 			List<FormFieldSpecsExtra> formFieldSpecExtraList =  formSpecContainer.getFieldsExtra();
 			if(formFieldSpecExtraList != null){
 				jsonTable = db.getTable("form_field_specs_extra");
 				int fieldSpecsExtraAutoIncrementId = 1;
 				for (FormFieldSpecsExtra formFieldSpecExtra : formFieldSpecExtraList ) {
 					jsonTable.insert(Api.toJson(formFieldSpecExtra),0,fieldSpecsExtraAutoIncrementId);
 					fieldSpecsExtraAutoIncrementId++;
 				}
 			}
 			
 			currentTime = System.currentTimeMillis();
 			List<FormSectionFieldSpecsExtra> formSectionFieldSpecExtraList =  formSpecContainer.getSectionFieldsExtra();
 			if(formSectionFieldSpecExtraList != null){
 				jsonTable = db.getTable("form_section_field_specs_extra");
 				int sectionFieldSpecExtraAutoIncrementId = 1;
 				for (FormSectionFieldSpecsExtra formSectionFieldSpecExtra : formSectionFieldSpecExtraList ) {
 					jsonTable.insert(Api.toJson(formSectionFieldSpecExtra),0,sectionFieldSpecExtraAutoIncrementId);
 					sectionFieldSpecExtraAutoIncrementId++;
 				}
 			}
 			
 			currentTime = System.currentTimeMillis();
 			List<CustomEntityFilteringCritiria> customEntityFilteringCritiriaList = formSpecContainer.getCustomEntityFilteringCritirias();
 			if(customEntityFilteringCritiriaList!=null) {
 				jsonTable = db.getTable("forms_specs_custom_entity_filtering_criterias");
 				int formSpecCustomEntityFilteringCriteriaAutoIncrementId = 1;
 				for(CustomEntityFilteringCritiria customEntityFilteringCritiria : customEntityFilteringCritiriaList) {
 					jsonTable.insert(Api.toJson(customEntityFilteringCritiria),0,formSpecCustomEntityFilteringCriteriaAutoIncrementId);
 					formSpecCustomEntityFilteringCriteriaAutoIncrementId++;
 				}
 			}

			db.commit();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.isOpen())
				db.close();

		}

	}

	private void createSqliteTables(SqlJetDb db) {
		try {
		db.createTable(MobileSqls.FORMS_SPECS_LIST_FILTERING_CRITERIAS);
		db.createTable(MobileSqls.REMAINDER_FIELDS_MAP);
		
		db.createTable(MobileSqls.FORMS_SPECS_CUSTOMER_AUTO_FILTERING_CRITERIAS);
		db.createTable(MobileSqls.FORMS_SPECS_FORM_SPECS_VISIBILITY_MAP);
		db.createTable(MobileSqls.FORMS_SPECS_FORM_SPEC_DATA_SOURCE);
		db.createTable(MobileSqls.FORMS_SPECS_JOB_FORM_FIELD_SPEC_MAPPING);
		db.createTable(MobileSqls.FORMS_SPECS_DATA_SOURCE_REQUEST_PARAMS);
		db.createTable(MobileSqls.FORMS_SPECS_DATA_SOURCE_REQUEST_HEADERS);
		db.createTable(MobileSqls.FORMS_SPECS_DATA_SOURCE_RESPONSE_MAPPINGS);
		db.createTable(MobileSqls.FORMS_SPECS_FIELDS);
		db.createTable(MobileSqls.FORMS_SPECS_SECTION_FIELDS);
		db.createTable(MobileSqls.FORMS_SPECS_FORM_SPECS);
		db.createTable(MobileSqls.FORMS_SPECS_PAGE_SPECS);
		db.createTable(MobileSqls.FORMS_SPECS_FIELD_VALID_VALUES);
		db.createTable(MobileSqls.FORMS_SPECS_SECTIONS);
		db.createTable(MobileSqls.FORMS_SPECS_SECTION_FIELD_VALID_VALUES);
		db.createTable(MobileSqls.FORMS_SPECS_VISIBILITY_DEPENDENCY_CRITERIAS);
		db.createTable(MobileSqls.FORM_FIELDS_COLOR_DEPENDENCY_CRITERIAS);
		db.createTable(MobileSqls.FORM_CLEAN_UP_RULES);
		
		db.createTable(MobileSqls.FORMS_SPECS_CUSTOMER_FILTERING_CRITERIAS);
		db.createTable(MobileSqls.FORMS_SPECS_EMPLOYEE_FILTERING_CRITERIAS);
		db.createTable(MobileSqls.FORMS_SPECS_FORM_FILTERING_CRITIRIAS);
		db.createTable(MobileSqls.FORMS_SPECS_FIELD_VALIDATION_CRITIRIAS);
		db.createTable(MobileSqls.FORMS_SPECS_STOCK_FORM_CONFIGURATIONS);
		db.createTable(MobileSqls.FORMS_SPECS_OFFLINE_LIST_UPDATE_CONFIGURATIONS);
		db.createTable(MobileSqls.FORMS_SPECS_OFFLINE_CUSTOMENTITY_UPDATE_CONFIGURATIONS);
		
		db.createTable(MobileSqls.FORMS_SPECS_WORKSPEC_FORMSPEC_FOLLOW_UP);
		db.createTable(MobileSqls.FORMS_SPECS_WORK_FORM_FIELD_MAP);
		db.createTable(MobileSqls.FORM_FIELD_GROUP_SPECS);
		db.createTable(MobileSqls.FORM_FIELD_SPECS_EXTRA);
		db.createTable(MobileSqls.FORM_SECTION_FIELD_SPECS_EXTRA);
		db.createTable(MobileSqls.FORMS_SPECS_CUSTOM_ENTITY_FILTERING_CRITIRIAS);
		
		
	}catch(Exception e) {
		
	}
	}

	public void importFormSpec(String formSpecUniqueId,String companyId) {
		FormSpecContainer formSpecContainer = new FormSpecContainer();
		try {
			String url = "jdbc:sqlite:/home/spoors/Desktop/SqliteExport/"+formSpecUniqueId+".sqlite";
			 Class.forName("org.sqlite.JDBC");
			 Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             
             
             List<ListFilteringCritiria> listFilteringCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_list_filtering_criterias",ListFilteringCritiria.class, stmt);
             formSpecContainer.setListFilteringCriterias(listFilteringCritiriaList);
             
             List<RemainderFieldsMap> remainderFieldsMap = fetchDataFromSqliteAndMapToBean("SELECT * FROM remainder_fields_map",RemainderFieldsMap.class, stmt);
             formSpecContainer.setRemainderFieldsMap(remainderFieldsMap);
 			

             List<FormSpecPermission> formSpecPermissionList = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_spec_permissions",FormSpecPermission.class, stmt);
             formSpecContainer.setFormSpecPermissions(formSpecPermissionList);
             
 			

             List<FormSpecConfigSaveOnOtpVerify> formSpecConfigSaveOnOtpVerifyList = fetchDataFromSqliteAndMapToBean("SELECT * FROM save_form_on_otp_verify",FormSpecConfigSaveOnOtpVerify.class, stmt);
             formSpecContainer.setSaveFormOnOtpVerify(formSpecConfigSaveOnOtpVerifyList);
             
             
             List<CustomerAutoFilteringCritiria> customerAutoFilteringCritiriasList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_customer_auto_filtering_criterias",CustomerAutoFilteringCritiria.class, stmt);
             formSpecContainer.setCustomerAutoFilteringCritirias(customerAutoFilteringCritiriasList);

 			

             List<CustomerFilteringCritiria> customerFilteringCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_customer_filtering_criterias",CustomerFilteringCritiria.class, stmt);
             formSpecContainer.setCustomerFilteringCriterias(customerFilteringCritiriaList);
             
             
             
             List<EmployeeFilteringCritiria> employeeFilteringCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_employee_filtering_criterias",EmployeeFilteringCritiria.class, stmt);
             formSpecContainer.setEmployeeFilteringCriterias(employeeFilteringCritiriaList);
             
 			

             List<FormFilteringCritiria> formFilteringCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_form_filtering_criterias",FormFilteringCritiria.class, stmt);
             formSpecContainer.setFormFilteringCriterias(formFilteringCritiriaList);
 			

             List<FieldValidationCritiria> fieldValidationCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_field_validation_critirias",FieldValidationCritiria.class, stmt);
             formSpecContainer.setFieldValidationCritirias(fieldValidationCritiriaList);
             
             List<StockFormConfiguration> stockFormConfigurationList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_stock_form_configurations",StockFormConfiguration.class, stmt);
             formSpecContainer.setStockFormConfigurations(stockFormConfigurationList);
 			

             List<OfflineListUpdateConfiguration> offlineListUpdateConfigurationList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_offline_list_update_configurations",OfflineListUpdateConfiguration.class, stmt);
             formSpecContainer.setOfflineListUpdateConfigurations(offlineListUpdateConfigurationList);
 			

             List<OfflineCustomEntityUpdateConfiguration> offlineCustomEntityUpdateConfigurationsList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_offline_customEntity_update_configurations",OfflineCustomEntityUpdateConfiguration.class, stmt);
             formSpecContainer.setOfflineCustomEntityUpdateConfigurations(offlineCustomEntityUpdateConfigurationsList);
             
             List<FormSpecDataSource> formSpecDataSourceList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_form_spec_data_source",FormSpecDataSource.class, stmt);
             formSpecContainer.setFormSpecDataSource(formSpecDataSourceList);
 			

             List<JobFormMapBean> jobFormMapBeanList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_job_form_field_spec_mapping",JobFormMapBean.class, stmt);
             formSpecContainer.setJobFormMapBeans(jobFormMapBeanList);
 			

             List<WorkSpecFormSpecFollowUp> workSpecFormSpecFollowUp = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_workspec_formspec_follow_up",WorkSpecFormSpecFollowUp.class, stmt);
             formSpecContainer.setWorkSpecFormSpecFollowUp(workSpecFormSpecFollowUp);
            
             List<WorkFormFieldMap> workFormFieldMaps = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_work_form_field_map",WorkFormFieldMap.class, stmt);
             formSpecContainer.setWorkFormFieldMap(workFormFieldMaps);
             
             List<DataSourceRequestParam> dataSourceRequestParamList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_data_source_request_params",DataSourceRequestParam.class, stmt);
             formSpecContainer.setDataSourceRequestParams(dataSourceRequestParamList);
             
             
             List<DataSourceRequestHeader> dataSourceRequestHeaderList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_data_source_request_headers",DataSourceRequestHeader.class, stmt);
             formSpecContainer.setDataSourceRequestHeaders(dataSourceRequestHeaderList);

 			
             List<DataSourceResponseMapping> dataSourceResponseMappingList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_data_source_response_mappings",DataSourceResponseMapping.class, stmt);
             formSpecContainer.setDataSourceResponseMappings(dataSourceResponseMappingList);

             List<FormFieldSpec> formFieldSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_fields",FormFieldSpec.class, stmt);
             formSpecContainer.setFields(formFieldSpecList);

             List<FormSectionFieldSpec> formSectionFieldSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_section_fields",FormSectionFieldSpec.class, stmt);
             formSpecContainer.setSectionFields(formSectionFieldSpecList);
             
             List<FormSpec> formSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_form_specs",FormSpec.class, stmt);
             formSpecContainer.setFormSpecs(formSpecList);

             List<FormPageSpec> formPageSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_page_specs",FormPageSpec.class, stmt);
             formSpecContainer.setPageSpecs(formPageSpecList);

             List<FormFieldSpecValidValue> formFieldSpecValidValueList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_field_valid_values",FormFieldSpecValidValue.class, stmt);
             formSpecContainer.setFieldValidValues(formFieldSpecValidValueList);

             List<FormSectionSpec> formSectionSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_sections",FormSectionSpec.class, stmt);
             formSpecContainer.setSections(formSectionSpecList);
             
             List<FormSectionFieldSpecValidValue> formSectionFieldSpecValidValueList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_section_field_valid_values",FormSectionFieldSpecValidValue.class, stmt);
             formSpecContainer.setSectionFieldValidValues(formSectionFieldSpecValidValueList);
             
             List<VisibilityDependencyCriteria> visibilityDependencyCriteriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_visibility_dependency_criterias",VisibilityDependencyCriteria.class, stmt);
             formSpecContainer.setVisibilityDependencyCriterias(visibilityDependencyCriteriaList);

             List<FormFieldsColorDependencyCriterias> formFieldsColorDependencyCriteriasList = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_fields_Color_dependency_criterias",FormFieldsColorDependencyCriterias.class, stmt);
             formSpecContainer.setFormFieldsColorDependencyCriterias(formFieldsColorDependencyCriteriasList);

             List<FormCleanUpRule> formCleanUpRuleList = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_clean_up_rules",FormCleanUpRule.class, stmt);
             formSpecContainer.setFormCleanUpRule(formCleanUpRuleList);

             List<FormFieldGroupSpec> formFieldGroupSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_field_group_specs",FormFieldGroupSpec.class, stmt);
             formSpecContainer.setFormFieldGroupSpecs(formFieldGroupSpecs);

             List<FormFieldSpecsExtra> formFieldSpecExtraList = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_field_specs_extra",FormFieldSpecsExtra.class, stmt);
             formSpecContainer.setFieldsExtra(formFieldSpecExtraList);
 			
             List<FormSectionFieldSpecsExtra> formSectionFieldSpecExtraList = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_section_field_specs_extra",FormSectionFieldSpecsExtra.class, stmt);
             formSpecContainer.setSectionFieldsExtra(formSectionFieldSpecExtraList);
  			
             List<CustomEntityFilteringCritiria> customEntityFilteringCritiriaList = fetchDataFromSqliteAndMapToBean("SELECT * FROM forms_specs_custom_entity_filtering_criterias",CustomEntityFilteringCritiria.class, stmt);
             formSpecContainer.setCustomEntityFilteringCritirias(customEntityFilteringCritiriaList);
			
		}catch(Exception e) {
			
		}
		
	}
	 public <T> List<T> fetchDataFromSqliteAndMapToBean(String query, Class<T> clazz, Statement stmt) {
	        ResultSet rs = null;
	        List<T> items = new ArrayList<>();   
	        
	        try {
	            rs = stmt.executeQuery(query);

	            ObjectMapper objectMapper = new ObjectMapper();
	            while (rs.next()) {
	                String jsonData = rs.getString("json_data");

	                try {
	                    T item = objectMapper.readValue(jsonData, clazz);
	                    items.add(item);
	                } catch (JsonParseException | JsonMappingException e) {
	                    System.err.println("Error parsing JSON: " + e.getMessage());
	                } catch (IOException e) {
	                    System.err.println("IO error: " + e.getMessage());
	                }
	            }
	            
	        } catch (SQLException e) {
	            System.err.println("SQL error: " + e.getMessage());
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	            } catch (SQLException e) {
	                System.err.println("Error closing ResultSet: " + e.getMessage());
	            }
	        }
	        return items;
	    }

}
