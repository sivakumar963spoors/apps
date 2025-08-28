package com.spoors.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.spoors.beans.EntitySectionField;
import com.spoors.beans.EntitySectionFieldSpec;
import com.spoors.beans.EntitySectionSpec;
import com.spoors.beans.EntitySpec;
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
import com.spoors.controller.ApiController;
import com.spoors.dao.EffortDao;
import com.spoors.setting.MobileSqls;
import com.spoors.util.Api;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SqliteManager {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SqliteManager.class);
	
	@Autowired
	public ServiceManager serviceManager;
	
	@Autowired
	public EffortDao effortDao;
	
	@Autowired
	public Constants constants;

	public String saveFormSpecDataToSqlite(FormSpecContainer formSpecContainer, List<WorkSpecContainer> workSpecContainerList, HttpServletResponse servletResponse) {
		
			try {
				return saveToSqlite(formSpecContainer,workSpecContainerList,servletResponse);
			} catch (SqlJetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

	private String saveToSqlite(FormSpecContainer formSpecContainer,List<WorkSpecContainer> workSpecContainerList, HttpServletResponse response) throws SqlJetException {

		Media media = new Media();
		String sqliteName = "exportSqlite_"+System.currentTimeMillis();
		FormSpec formSpecObj = formSpecContainer.getFormSpecs().get(0);
		Long currentTime = System.currentTimeMillis();

		String sqliteFileName = sqliteName+ ".sqlite"; /// syncSqlitePath.substring(syncSqlitePath.lastIndexOf("/")+1);

		String sqliteFilePath = constants.getMediaStoragePath()+"/SqliteExport/" + sqliteFileName;

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
			
			dbFile.delete();
			
			dbFile = new File(sqliteFilePath);

			db = SqlJetDb.open(dbFile, true);

			db.getOptions().setAutovacuum(true);
			db.beginTransaction(SqlJetTransactionMode.WRITE);
			db.getOptions().setUserVersion(1);

			Long t1 = System.currentTimeMillis();
			currentTime = System.currentTimeMillis();
			createSqliteTables(db);
			
			if(workSpecContainerList != null && workSpecContainerList.size() > 0) {
				for(WorkSpecContainer workSpecContainer : workSpecContainerList) {
					saveWorkSpecsDataToSqlite(db,workSpecContainer);
				}
			}
			
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
 			
 		 	currentTime = System.currentTimeMillis();
 		 	List<EntitySpec> entitySpecList =  formSpecContainer.getEntitySpecs();
 		 	if(entitySpecList != null){
 		 		jsonTable = db.getTable("entities_specs_entity_specs");
 		 		int entitySpecListAutoIncrementId = 1;
 		 		for (EntitySpec entitySpec : entitySpecList ) {
 		 			jsonTable.insert(Api.toJson(entitySpec),0,entitySpecListAutoIncrementId);
 		 			entitySpecListAutoIncrementId++;
 		 		}
 		 	}
 		 	
 		 	List<EntityFieldSpec> entityFieldSpecList =  formSpecContainer.getEntityFields();
 		 	if(entityFieldSpecList != null){
 		 				jsonTable = db.getTable("entities_specs_fields");
 		 				int entityFieldSpecListAutoIncrementId = 1;
 		 				for (EntityFieldSpec entityFieldSpec : entityFieldSpecList ) {
 		 					jsonTable.insert(Api.toJson(entityFieldSpec),0,entityFieldSpecListAutoIncrementId);
 		 					entityFieldSpecListAutoIncrementId++;
 		 				}
 		 	}
 		 	
 		 	List<EntitySectionSpec> entitySectionSpecList =  formSpecContainer.getEntitySections();
 			if(entitySectionSpecList != null){
 				jsonTable = db.getTable("entities_specs_sections_specs");
 				int entitySpecSectionSpecAutoIncrementId = 1;
 				for (EntitySectionSpec entitySectionSpec : entitySectionSpecList ) {
 					jsonTable.insert(Api.toJson(entitySectionSpec),0,entitySpecSectionSpecAutoIncrementId);
 					entitySpecSectionSpecAutoIncrementId++;
 				}
 			}
 		 	
 		 	List<EntitySectionFieldSpec> entitySectionFieldSpecList =  formSpecContainer.getEntitySectionFields();
 			if(entitySectionFieldSpecList != null){
 				jsonTable = db.getTable("entities_specs_sections_fields");
 				int entitySpecSectionFieldAutoIncrementId = 1;
 				for (EntitySectionFieldSpec entitySectionFieldSpec : entitySectionFieldSpecList ) {
 					jsonTable.insert(Api.toJson(entitySectionFieldSpec),0,entitySpecSectionFieldAutoIncrementId);
 					entitySpecSectionFieldAutoIncrementId++;
 				}
 			}
 			
 			List<CustomEntitySpec> customEntitySpecs = formSpecContainer.getCustomEntitySpecs();
 			if(customEntitySpecs != null){
 				jsonTable = db.getTable("custom_entity_spec");
 				int customEntitySpecAutoIncrementId = 1;
 				for( CustomEntitySpec customEntitySpec : customEntitySpecs){
 					jsonTable.insert(Api.toJson(customEntitySpec),0,customEntitySpecAutoIncrementId);
 					customEntitySpecAutoIncrementId++;
 				}
 			}
 			
 			currentTime = System.currentTimeMillis();
 			List<Entity> entityAddedList =  formSpecContainer.getEntities();
 			if(entityAddedList  != null){
 				jsonTable = db.getTable("entities_data_added");
 				int entityDataAddedAutoIncrementId = 1;
 				for (Entity entityAdded: entityAddedList ) {
 					jsonTable.insert(Api.toJson(entityAdded),0,entityDataAddedAutoIncrementId);
 					entityDataAddedAutoIncrementId++;
 				}
 			}
 			
 			currentTime = System.currentTimeMillis();
 			List<EntityField> entityFieldList =  formSpecContainer.getEntityFieldsData();
 			if(entityFieldList != null){
 				jsonTable = db.getTable("entities_data_fields");
 				int entityFieldAutoIncrementId=1;
 				for (EntityField entityField : entityFieldList ) {
 					jsonTable.insert(entityField.getEntityId(),Api.toJson(entityField),0,entityFieldAutoIncrementId);
 					entityFieldAutoIncrementId++;
 				}
 			}
 			
 		    // For Entity Section Spec
 			List<EntitySectionField> entitySectionFieldList =  formSpecContainer.getEntitySectionFieldsData();
 			if(entitySectionFieldList != null){
 				jsonTable = db.getTable("entities_data_section_fields");
 				int entityDataSectionFieldAutoIncrementId = 1;
 				for (EntitySectionField entitySectionField : entitySectionFieldList ) {
 					jsonTable.insert(entitySectionField.getEntityId(),Api.toJson(entitySectionField),0,entityDataSectionFieldAutoIncrementId);
 					entityDataSectionFieldAutoIncrementId++;
 				}
 			}

			db.commit();
			db.close();
			
			media.setCompanyId(formSpecObj.getCompanyId());
            media.setEmpId(formSpecObj.getCreatedBy());
            media.setMimeType("application/x-sqlite3");
            media.setLocalPath("/SqliteExport/" + sqliteFileName);
            media.setFileName(sqliteFileName);

            effortDao.saveMedia(media);
			
			/*response.setHeader("Content-Disposition", "attachment; filename=\"" +sqliteFileName + "\";");
			
			OutputStream out = response.getOutputStream();
			InputStream is = new FileInputStream(dbFile);
			
			long fileLenght = dbFile.length();
			if(fileLenght > ((2*1024*1024*1024)-1000)){
				IOUtils.copyLarge(is, out);
			} else {
				IOUtils.copy(is, out);
			}
			out.flush();
			out.close();
			is.close();*/

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.isOpen())
				db.close();

		}
		return media.getId()+"";

	}

	private void saveWorkSpecsDataToSqlite(SqlJetDb db,WorkSpecContainer workSpecContainer) {
		Long currentTime = System.currentTimeMillis();
		ISqlJetTable jsonTable = null;
		try {
			List<WorkSpec> workSpecs = workSpecContainer.getWorkSpecs();
			if(workSpecs != null){
				jsonTable = db.getTable("works_specs_work_specs");
				int workSpecAutoIncrementId = 1;
				for( WorkSpec workSpec : workSpecs){
					jsonTable.insert(Api.toJson(workSpec),0,workSpecAutoIncrementId);
					workSpecAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkSpecCustomerCallApi> workSpecCustomerCallApis = workSpecContainer.getWorkSpecCustomerCallApis();
			if(workSpecCustomerCallApis != null){
				jsonTable = db.getTable("work_spec_customer_call_apis");
				int apiAutoIncrementId = 1;
				for( WorkSpecCustomerCallApi workSpecCustomerCallApi : workSpecCustomerCallApis){
					jsonTable.insert(Api.toJson(workSpecCustomerCallApi),0,apiAutoIncrementId);
					apiAutoIncrementId++;
				}
			}
			currentTime = System.currentTimeMillis();
			List<NextWorkSpec> nextWorkSpecs = workSpecContainer.getNextWorkSpecs();
			if(nextWorkSpecs != null){
				jsonTable = db.getTable("works_specs_next_work_specs");
				int nextWorkSpecAutoIncrementId = 1;
				
				for( NextWorkSpec nextWorkSpec : nextWorkSpecs){
					jsonTable.insert(Api.toJson(nextWorkSpec),0,nextWorkSpecAutoIncrementId);
					nextWorkSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<NextActionSpec> nextActionSpecs = workSpecContainer.getNextActionSpecs();
			if(nextActionSpecs != null){
				jsonTable = db.getTable("works_specs_next_action_specs");
				int nextActionSpecAutoIncrementId = 1;
				for( NextActionSpec nextActionSpec : nextActionSpecs){
					jsonTable.insert(Api.toJson(nextActionSpec),0,nextActionSpecAutoIncrementId);
					nextActionSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkActionSpec> workActionSpecs = workSpecContainer.getWorkActionSpecs();
			if(workActionSpecs != null){
				jsonTable = db.getTable("works_specs_work_action_specs");
				int workActionSpecAutoIncrementId = 1;
				for( WorkActionSpec workActionSpec : workActionSpecs){
					jsonTable.insert(Api.toJson(workActionSpec),0,workActionSpecAutoIncrementId);
					workActionSpecAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkSpecFormSpecMap> workSpecFormSpecMaps = workSpecContainer.getWorkSpecFormSpecMaps();
			if(workSpecFormSpecMaps != null){
				jsonTable = db.getTable("works_specs_work_spec_form_spec_maps");
				int WorkSpecFormSpecMapAutoIncrementId = 1;
				for( WorkSpecFormSpecMap workSpecFormSpecMap : workSpecFormSpecMaps){
					jsonTable.insert(Api.toJson(workSpecFormSpecMap),0,WorkSpecFormSpecMapAutoIncrementId);
					WorkSpecFormSpecMapAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkActionSpecConditions> workActionSpecConditions = workSpecContainer.getWorkActionSpecConditions();
			if(workActionSpecConditions != null){
				jsonTable = db.getTable("works_specs_work_action_spec_conditions");
				int WorkActionSpecConditionAutoIncrementId = 1;
				for( WorkActionSpecConditions workActionSpecCondition : workActionSpecConditions){
					jsonTable.insert(Api.toJson(workActionSpecCondition),0,WorkActionSpecConditionAutoIncrementId);
					WorkActionSpecConditionAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkActionSpecEndCondition> workActionSpecEndConditions = workSpecContainer.getWorkActionSpecEndConditions();
			if(workActionSpecEndConditions != null){
				jsonTable = db.getTable("works_specs_work_action_spec_end_conditions");
				int workActionSpecEndConditionAutoIncrementId = 1;
				for( WorkActionSpecEndCondition workActionSpecEndCondition : workActionSpecEndConditions){
					jsonTable.insert(Api.toJson(workActionSpecEndCondition),0,workActionSpecEndConditionAutoIncrementId);
					workActionSpecEndConditionAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkFormAutoFill> worksFormAutoFillStageConfig = workSpecContainer.getWorkFormAutoFillStageConfig();
			if(worksFormAutoFillStageConfig != null){
				jsonTable = db.getTable("works_specs_form_auto_fill_action_config");
				int workSpecFormAutoFillActionConfigAutoIncrementId = 1;
				for( WorkFormAutoFill formAutoFillStageConfig : worksFormAutoFillStageConfig ){
					jsonTable.insert(Api.toJson(formAutoFillStageConfig),0,workSpecFormAutoFillActionConfigAutoIncrementId);
					workSpecFormAutoFillActionConfigAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkFormAutoFillField> workFormAutoFillFieldMapping = workSpecContainer.getWorkFormAutoFillFieldMapping();
			if(workFormAutoFillFieldMapping != null){
				jsonTable = db.getTable("works_specs_form_auto_fill_field_mapping");
				int workSpecFormAuoFillFieldMappingAutoIncrementId = 1;
				for( WorkFormAutoFillField formAutoFillFieldMapping : workFormAutoFillFieldMapping ){
					jsonTable.insert(Api.toJson(formAutoFillFieldMapping),0,workSpecFormAuoFillFieldMappingAutoIncrementId);
					workSpecFormAuoFillFieldMappingAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkAttachmentAutoFill> worksAttachmentFormAutoFillStageConfig = workSpecContainer.getWorkAttachmentFormAutoFillStageConfig();
			if(worksAttachmentFormAutoFillStageConfig != null){
				jsonTable = db.getTable("works_specs_attchment_form_auto_fill_action_config");
				int workSpecAttachmentFormAutoFillActionConfigAutoIncrementId = 1;
				for( WorkAttachmentAutoFill formAutoFillStageConfig : worksAttachmentFormAutoFillStageConfig ){
					jsonTable.insert(Api.toJson(formAutoFillStageConfig),0,workSpecAttachmentFormAutoFillActionConfigAutoIncrementId);
					workSpecAttachmentFormAutoFillActionConfigAutoIncrementId++;
				}
			}

			currentTime = System.currentTimeMillis();
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMapping = workSpecContainer.getWorkAttachmentFormAutoFillFieldMapping();
			if(workAttachmentFormAutoFillFieldMapping != null){
				jsonTable = db.getTable("works_specs_attchment_form_auto_fill_field_mapping");
				int workSpecAttachmentFormAuoFillFieldMappingAutoIncrementId = 1;
				for( WorkAttachmentFormAutoFillField attachmentormAutoFillFieldMapping : workAttachmentFormAutoFillFieldMapping ){
					jsonTable.insert(Api.toJson(attachmentormAutoFillFieldMapping),0,workSpecAttachmentFormAuoFillFieldMappingAutoIncrementId);
					workSpecAttachmentFormAuoFillFieldMappingAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkActionGroup> workActionGroups = 
					workSpecContainer.getWorkActionGroups();
			if(workActionGroups != null){
				jsonTable = db.getTable("work_specs_work_action_groups");
				int workSpecWorkActionGroupAutoIncrementId = 1;
				for( WorkActionGroup workActionGroup : workActionGroups ){
					jsonTable.insert(Api.toJson(workActionGroup),0,workSpecWorkActionGroupAutoIncrementId);
					workSpecWorkActionGroupAutoIncrementId++;
				}
			}

			/*Date: 2016-04-21
		*Method Purpose: Auto copy from "Work action form fields" to "work fields"
		*Resource: Deva*/

			currentTime = System.currentTimeMillis();
			List<FormToWorkAutoFill> formToWorkAutoFillStageConfig = 
					workSpecContainer.getFormToWorkAutoFillStageConfig();
			if(formToWorkAutoFillStageConfig != null){
				jsonTable = db.getTable("works_specs_form_to_work_auto_fill_action_config");
				int workSpecsFormToWorkAutoFillAutoIncrementId = 1;
				for( FormToWorkAutoFill workAutoFillStageConfig : formToWorkAutoFillStageConfig ){
					jsonTable.insert(Api.toJson(workAutoFillStageConfig),0,workSpecsFormToWorkAutoFillAutoIncrementId);
					workSpecsFormToWorkAutoFillAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<FormToWorkAutoFillField> formToWorkAutoFillFieldMapping = 
					workSpecContainer.getFormToWorkAutoFillFieldMapping();
			if(formToWorkAutoFillFieldMapping != null){
				jsonTable = db.getTable("works_specs_form_to_work_auto_fill_field_mapping");
				int workSpecFormToWorkAutoFillFieldMapAutoIncrementId = 1;
				for( FormToWorkAutoFillField workAutoFillFieldMapping : formToWorkAutoFillFieldMapping ){
					jsonTable.insert(Api.toJson(workAutoFillFieldMapping),0,workSpecFormToWorkAutoFillFieldMapAutoIncrementId);
					workSpecFormToWorkAutoFillFieldMapAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<FormAutoFillSectionConfiguration> formAutoFillSectionConfigMapping = 
					workSpecContainer.getFormAutoFillSectionConfiguration();
			if(formAutoFillSectionConfigMapping != null){
				jsonTable = db.getTable("form_auto_fill_section_config");
				int formAutoFillSectionAutoIncrementId = 1;
				for( FormAutoFillSectionConfiguration formAutoFillSectionConfig : formAutoFillSectionConfigMapping ){
					jsonTable.insert(Api.toJson(formAutoFillSectionConfig),0,formAutoFillSectionAutoIncrementId);
					formAutoFillSectionAutoIncrementId++;
				}
			}
			
			List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfigMapping = 
					workSpecContainer.getFormAutoFillSectionFieldsConfiguration();
			if(formAutoFillSectionFieldsConfigMapping != null){
				jsonTable = db.getTable("form_auto_fill_section_fields_config");
				int formAutoFillSectionFieldsAutoIncrementId = 1;
				for( FormAutoFillSectionFieldsConfiguration formAutoFillSectionFieldsConfig : formAutoFillSectionFieldsConfigMapping ){
					jsonTable.insert(Api.toJson(formAutoFillSectionFieldsConfig),0,formAutoFillSectionFieldsAutoIncrementId);
					formAutoFillSectionFieldsAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<AttachmnetFormAutoFillSectionConfiguration> attachmentFormAutoFillSectionConfigMapping = 
					workSpecContainer.getAttachmentFormAutoFillSectionConfiguration();
			if(attachmentFormAutoFillSectionConfigMapping != null){
				jsonTable = db.getTable("attachment_form_auto_fill_section_config");
				int attachmentFormAutoFillSectionAutoIncrementId = 1;
				for( AttachmnetFormAutoFillSectionConfiguration attachmentormAutoFillSectionConfig : attachmentFormAutoFillSectionConfigMapping ){
					jsonTable.insert(Api.toJson(attachmentormAutoFillSectionConfig),0,attachmentFormAutoFillSectionAutoIncrementId);
					attachmentFormAutoFillSectionAutoIncrementId++;
				}
			}
			
			List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfigMapping = 
					workSpecContainer.getAttachmentFormAutoFillSectionFieldsConfiguration();
			if(attachmentFormAutoFillSectionFieldsConfigMapping != null){
				jsonTable = db.getTable("attachment_form_auto_fill_section_fields_config");
				int attachmentFormAutoFillSectionFieldsAutoIncrementId = 1;
				for( WorkAttachmentAutoFillSectionFieldsConfiguration attachmentFormAutoFillSectionFieldsConfig : attachmentFormAutoFillSectionFieldsConfigMapping ){
					jsonTable.insert(Api.toJson(attachmentFormAutoFillSectionFieldsConfig),0,attachmentFormAutoFillSectionFieldsAutoIncrementId);
					attachmentFormAutoFillSectionFieldsAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<FormToWorkAutoFillSectionConfiguration> formToWorkAutoFillSectionConfigMapping = 
					workSpecContainer.getFormToWorkAutoFillSectionConfiguration();
			if(formAutoFillSectionConfigMapping != null){
				jsonTable = db.getTable("form_to_work_auto_fill_section_config");
				int formToWorkAutoFillSectionAutoIncrementId = 1;
				for( FormToWorkAutoFillSectionConfiguration formToWorkAutoFillSectionConfig : formToWorkAutoFillSectionConfigMapping ){
					jsonTable.insert(Api.toJson(formToWorkAutoFillSectionConfig),0,formToWorkAutoFillSectionAutoIncrementId);
					formToWorkAutoFillSectionAutoIncrementId++;
				}
			}
			
			List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfigMapping = 
					workSpecContainer.getFormToWorkAutoFillSectionFieldsConfiguration();
			if(formAutoFillSectionFieldsConfigMapping != null){
				jsonTable = db.getTable("form_to_work_auto_fill_section_fields_config");
				int formToWorkAutoFillSectionFieldsAutoIncrementId = 1;
				for( FormToWorkAutoFillSectionFieldsConfiguration formToWorkAutoFillSectionFieldsConfig : formToWorkAutoFillSectionFieldsConfigMapping ){
					jsonTable.insert(Api.toJson(formToWorkAutoFillSectionFieldsConfig),0,formToWorkAutoFillSectionFieldsAutoIncrementId);
					formToWorkAutoFillSectionFieldsAutoIncrementId++;
				}
			}
			
			
			
			currentTime = System.currentTimeMillis();
			List<WorkFieldsUniqueConfigurations> workFieldsUniqueConfigurations = 
					workSpecContainer.getWorkFieldsUniqueConfigurations();
			if(workFieldsUniqueConfigurations != null){
				jsonTable = db.getTable("works_specs_work_fields_unique_configurations");
				int workFieldsUniqueConfigurationAutoIncrementId = 1;
				for( WorkFieldsUniqueConfigurations workFieldsUniqueConfiguration : workFieldsUniqueConfigurations ){
					jsonTable.insert(Api.toJson(workFieldsUniqueConfiguration),0,workFieldsUniqueConfigurationAutoIncrementId);
					workFieldsUniqueConfigurationAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkFieldsUniqueConfigurations> openWorkFieldsUniqueConfigurations = 
					workSpecContainer.getOpenWorkFieldsUniqueConfigurations();
			if(openWorkFieldsUniqueConfigurations != null){
				jsonTable = db.getTable("works_specs_open_work_fields_unique_configurations");
				int workFieldsUniqueConfigurationAutoIncrementId = 1;
				for( WorkFieldsUniqueConfigurations workFieldsUniqueConfiguration : openWorkFieldsUniqueConfigurations ){
					jsonTable.insert(Api.toJson(workFieldsUniqueConfiguration),0,workFieldsUniqueConfigurationAutoIncrementId);
					workFieldsUniqueConfigurationAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkReassignmentRules> workReassignmentRulesList = 
					workSpecContainer.getWorkReassignmentRules();
			if(workReassignmentRulesList != null){
				jsonTable = db.getTable("work_re_assignment_rules");
				int workReAssignmentRulesAutoIncrementId = 1;
				for( WorkReassignmentRules workReassignmentRules : workReassignmentRulesList ){
					jsonTable.insert(Api.toJson(workReassignmentRules),0,workReAssignmentRulesAutoIncrementId);
					workReAssignmentRulesAutoIncrementId++;
				}
			}
			
			currentTime = System.currentTimeMillis();
			List<WorkSpecAppLabel> workSpecAppLabels = 
					workSpecContainer.getWorkSpecAppLabels();
			if(workSpecAppLabels != null){
				jsonTable = db.getTable("work_spec_app_labels");
				int workSpecAppLabelAutoIncrementId = 1;
				for( WorkSpecAppLabel workSpecAppLabel : workSpecAppLabels ){
					jsonTable.insert(Api.toJson(workSpecAppLabel),0,workSpecAppLabelAutoIncrementId);
					workSpecAppLabelAutoIncrementId++;
				}
			}
			

				List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations = workSpecContainer.getWorkActionVisibilityConfigurations();
 			if(workActionVisibilityConfigurations != null)
 			{
 				jsonTable = db.getTable("work_action_visibility_configuration");
 				int workActionVisibilityConfigAutoIncrementId = 1;
 				for (WorkActionVisibilityConfiguration workActionVisibilityConfiguration : workActionVisibilityConfigurations) {
 					jsonTable.insert(Api.toJson(workActionVisibilityConfiguration),0,workActionVisibilityConfigAutoIncrementId);
 					workActionVisibilityConfigAutoIncrementId++;
 				}
 			}
 			
 			List<WorkProcessSubTaskSpec> workProcessSubTaskSpecs = workSpecContainer.getWorkProcessSubTaskSpecConfigurations();
 			if(workProcessSubTaskSpecs != null)
 			{
 				jsonTable = db.getTable("work_process_sub_task_spec_configurations");
 				int workProcessSubTaskSpecConfigurationAutoIncrementId=1;
 				for(WorkProcessSubTaskSpec workProcessSubTaskSpec : workProcessSubTaskSpecs)
 				{
 					jsonTable.insert(Api.toJson(workProcessSubTaskSpec),0,workProcessSubTaskSpecConfigurationAutoIncrementId);
 					workProcessSubTaskSpecConfigurationAutoIncrementId++;
 				}
 			}
 			List<AddingSubTaskEmployeeConfiguration> workProcessSubTaskEmployeesConfigurations = workSpecContainer.getWorkProcessSubTaskEmployeesConfigurations();
 			if(workProcessSubTaskEmployeesConfigurations != null)
 			{
 				jsonTable = db.getTable("work_process_sub_task_employees_configurations");
 				int workProcessSubTaskEmployeesConfigurationsAutoIncrementId= 1;
 				for(AddingSubTaskEmployeeConfiguration workProcessSubTaskEmployeesConfiguration :
 					workProcessSubTaskEmployeesConfigurations)
 				{
 					jsonTable.insert(Api.toJson(workProcessSubTaskEmployeesConfiguration),0,workProcessSubTaskEmployeesConfigurationsAutoIncrementId);
 					workProcessSubTaskEmployeesConfigurationsAutoIncrementId++;
 				}
 			}
 			List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations = workSpecContainer.getWorkToSubTaskAutoFillConfigurations();
 			if(workToSubTaskAutoFillConfigurations != null)
 			{
 				jsonTable = db.getTable("work_to_sub_task_auto_fill_configuration");
 				int workToSubTaskAutoFillConfigurationAutoIncrementId = 1;
 				for(WorkToSubTaskAutoFillConfiguration workToSubTaskAutoFillConfiguration :
 					workToSubTaskAutoFillConfigurations)
 				{
 					jsonTable.insert(Api.toJson(workToSubTaskAutoFillConfiguration),0,workToSubTaskAutoFillConfigurationAutoIncrementId);
 					workToSubTaskAutoFillConfigurationAutoIncrementId++;
 				}
 			}
 			List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations = workSpecContainer.getHideAddSubTaskConfigurations();
 			if(hideAddSubTaskConfigurations != null)
 			{
 				jsonTable = db.getTable("hide_add_sub_task_configurations");
 				int hideAddSubTaskConfigAutoIncrementId = 1;
 				for(HideAddSubTaskConfiguration hideAddSubTaskConfiguration :
 					hideAddSubTaskConfigurations)
 				{
 					jsonTable.insert(Api.toJson(hideAddSubTaskConfiguration),0,hideAddSubTaskConfigAutoIncrementId);
 					hideAddSubTaskConfigAutoIncrementId++;
 				}
 			}
 			
 			List<WorkSpecPermission> workSpecPermissions  = 
 					workSpecContainer.getWorkSpecPermissions();
 			if(workSpecPermissions != null){
 				jsonTable = db.getTable("work_spec_permissions");
 				int workSpecPermissionAutoIncrementId = 1;
 				for( WorkSpecPermission workSpecPermission : workSpecPermissions ){
 					jsonTable.insert(Api.toJson(workSpecPermission),0,workSpecPermissionAutoIncrementId);
 					workSpecPermissionAutoIncrementId++;
 				}
 			}
 			
 			List<WorkUnassignmentCriterias> workUnassignmentCriterias  = 
 					workSpecContainer.getWorkUnassignmentCriterias();
 			if(workUnassignmentCriterias != null){
 				jsonTable = db.getTable("work_unassignment_criterias");
 				int workUnassignmentCriteriaAutoIncrementId = 1;
 				for( WorkUnassignmentCriterias workUnassignmentCriteria : workUnassignmentCriterias ){
 					jsonTable.insert(Api.toJson(workUnassignmentCriteria),0,workUnassignmentCriteriaAutoIncrementId);
 					workUnassignmentCriteriaAutoIncrementId++;
 				}
 			}
 			
 			List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions  = 
 					workSpecContainer.getWorkAssignmentCriteriaConditions();
 			if(workAssignmentCriteriaConditions != null){
 				jsonTable = db.getTable("work_assignment_criteria_conditions");
 				int workAssignmentCriteriaCoditionAutoIncrementId = 1;
 				for( WorkAssignmentCriteriaConditions workAssignmentCriteriaCondition : workAssignmentCriteriaConditions ){
 					jsonTable.insert(Api.toJson(workAssignmentCriteriaCondition),0,workAssignmentCriteriaCoditionAutoIncrementId);
 					workAssignmentCriteriaCoditionAutoIncrementId++;
 				}
 			}
 			
 			List<ExternalActionConfiguration> externalActionConfigurations =  workSpecContainer.getExternalActionConfigurations();
 			if(externalActionConfigurations != null){
 				jsonTable = db.getTable("works_specs_work_action_spec_external_action_config");
 				int externalActionConfigurationIncrementId = 1;
 				for (ExternalActionConfiguration externalActionConfiguration : externalActionConfigurations) {
 					jsonTable.insert(Api.toJson(externalActionConfiguration),0,externalActionConfigurationIncrementId);
 					externalActionConfigurationIncrementId++;
 				}
 			}
 			
 			List<WorkSpecListLevelVisibilityConfiguration> workSpecListLevelVisibilityConfigurations = workSpecContainer.getWorkSpecListLevelVisibilityConfigurations();
			if (workSpecListLevelVisibilityConfigurations != null) {
				jsonTable = db.getTable("work_spec_list_level_visibility_configurations");
				int workSpecListLevelVisibilityConfigurationIncrementId = 1;
				for (WorkSpecListLevelVisibilityConfiguration workSpecListLevelVisibilityConfiguration : workSpecListLevelVisibilityConfigurations) {
					jsonTable.insert(Api.toJson(workSpecListLevelVisibilityConfiguration), 0,
							workSpecListLevelVisibilityConfigurationIncrementId);
					workSpecListLevelVisibilityConfigurationIncrementId++;
				}
			}
			List<WorkActionFormVisibility> workActionFormVisibility = workSpecContainer.getWorkActionFormVisibility();
			if (workActionFormVisibility != null) {
				jsonTable = db.getTable("work_action_form_visibility");
				int workActionFormVisileId = 1;
				for (WorkActionFormVisibility workActionFormVisibilityObj : workActionFormVisibility) {
					jsonTable.insert(Api.toJson(workActionFormVisibilityObj), 0,
							workActionFormVisileId);
					workActionFormVisileId++;
				}
			}
			
			List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics = workSpecContainer.getWorkSpecCustomDashboardMetrics();
			if (workSpecCustomDashboardMetrics != null) {
				jsonTable = db.getTable("workSpec_custom_dashboard_metrics");
				int workSpecCustomDashboardMetricIncrementId = 1;
				for (WorkSpecCustomDashboardMetric workSpecCustomDashboardMetric : workSpecCustomDashboardMetrics) {
					jsonTable.insert(Api.toJson(workSpecCustomDashboardMetric), 0,
							workSpecCustomDashboardMetricIncrementId);
					workSpecCustomDashboardMetricIncrementId++;
				}
			}
			
			List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs = workSpecContainer.getActionableEmployeeGroupSpecs();
			if (actionableEmployeeGroupSpecs != null) {
				jsonTable = db.getTable("actionable_employee_group_specs");
				int ActionableEmployeeGroupSpecIncrementId = 1;
				for (ActionableEmployeeGroupSpecs ActionableEmployeeGroupSpec : actionableEmployeeGroupSpecs) {
					jsonTable.insert(Api.toJson(ActionableEmployeeGroupSpec), 0,
							ActionableEmployeeGroupSpecIncrementId);
					ActionableEmployeeGroupSpecIncrementId++;
				}
			}
			
			List<WorkActionNotificationEscalationMatrix> workActionNotificationEscalationMatrix = workSpecContainer.getWorkActionNotificationEscalationMatrix();
			if (workActionNotificationEscalationMatrix != null) {
				jsonTable = db.getTable("work_action_notification_escalation_matrix");
				int workActionNotificationEscalationMatrixIncrementId = 1;
				for (WorkActionNotificationEscalationMatrix workActionNotificationEscalation : workActionNotificationEscalationMatrix) {
					jsonTable.insert(Api.toJson(workActionNotificationEscalation), 0,
							workActionNotificationEscalationMatrixIncrementId);
					workActionNotificationEscalationMatrixIncrementId++;
				}
			}
			
			List<WorkActionExcalatedEmpIds> workActionExcalatedEmpIds = workSpecContainer.getWorkActionExcalatedEmpIds();
			if (workActionExcalatedEmpIds != null) {
				jsonTable = db.getTable("work_action_excalated_empIds");
				int workActionExcalatedEmpIdsIncrementId = 1;
				for (WorkActionExcalatedEmpIds workActionExcalatedEmpId : workActionExcalatedEmpIds) {
					jsonTable.insert(Api.toJson(workActionExcalatedEmpId), 0,
							workActionExcalatedEmpIdsIncrementId);
					workActionExcalatedEmpIdsIncrementId++;
				}
			}
			
			List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations = workSpecContainer.getWorkSpecCustomDashboardConfigurations();
			if (workSpecCustomDashboardConfigurations != null) {
				jsonTable = db.getTable("work_spec_custom_dashboard_configurations");
				int workSpecCustomDashboardConfigurationIncrementId = 1;
				for (WorkSpecCustomDashboardConfiguration workSpecCustomDashboardConfiguration : workSpecCustomDashboardConfigurations) {
					jsonTable.insert(Api.toJson(workSpecCustomDashboardConfiguration), 0,
							workSpecCustomDashboardConfigurationIncrementId);
					workSpecCustomDashboardConfigurationIncrementId++;
				}
			}
			
		}catch(Exception e) {
			
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
		db.createTable(MobileSqls.FORM_SPEC_PERMISSIONS);
		db.createTable(MobileSqls.SAVE_FORM_ON_OTP_VERIFY);
		
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
		
		db.createTable(MobileSqls.WORKS_SPECS_WORK_SPECS);
		db.createTable(MobileSqls.WORKS_SPECS_NEXT_WORK_SPECS);
		db.createTable(MobileSqls.WORKS_SPECS_NEXT_ACTION_SPECS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_ACTION_SPECS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_SPEC_FORM_SPEC_MAPS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_ACTION_SPEC_CONDITIONS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_ACTION_SPEC_END_CONDITIONS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_FIELDS_UNIQUE_CONFIGURATIONS);
		db.createTable(MobileSqls.WORK_SPEC_CUSTOMER_CALL_APIS);
		db.createTable(MobileSqls.WORKS_SPECS_FORM_AUTO_FILL_ACTION_CONFIG);
		db.createTable(MobileSqls.WORKS_SPECS_FORM_AUTO_FILL_FIELD_MAPPING);
		db.createTable(MobileSqls.WORKS_SPECS_FORM_ATTACHMENT_AUTO_FILL_ACTION_CONFIG);
		db.createTable(MobileSqls.WORKS_SPECS_FORM_ATTACHMENT_AUTO_FILL_FIELD_MAPPING);
		db.createTable(MobileSqls.ATTACHMENT_FORM_AUTO_FILL_SECTION_CONFIG);
		db.createTable(MobileSqls.ATTACHMENT_FORM_AUTO_FILL_SECTION_FIELDS_CONFIG);
		db.createTable(MobileSqls.WORK_SPECS_WORK_ACTION_GROUPS);
		db.createTable(MobileSqls.WORK_SPECS_FORM_TO_WORK_AUTO_FILL_ACTION_CONFIG);
		db.createTable(MobileSqls.WORK_SPECS_FORM_TO_WORK_AUTO_FILL__FIELD_MAPPING);
		db.createTable(MobileSqls.FORM_AUTO_FILL_SECTION_CONFIG);
		db.createTable(MobileSqls.FORM_AUTO_FILL_SECTION_FIELDS_CONFIG);
		db.createTable(MobileSqls.FORM_TO_WORK_AUTO_FILL_SECTION_CONFIG);
		db.createTable(MobileSqls.FORM_TO_WORK_AUTO_FILL_SECTION_FIELDS_CONFIG);
		db.createTable(MobileSqls.WORKS_SPECS_OPEN_WORK_FIELDS_UNIQUE_CONFIGURATIONS);
		
		
		db.createTable(MobileSqls.WORK_RE_ASSIGNMENT_RULES);
		db.createTable(MobileSqls.WORK_SPEC_APP_LABELS);
		db.createTable(MobileSqls.WORK_ACTION_VISIBILITY_CONFIGURATION);
		db.createTable(MobileSqls.WORK_ACTION_VISIBILITY_ASSIGNMENTS);
		db.createTable(MobileSqls.WORK_PROCESS_SUB_TASK_SPEC_CONFIGURATIONS);
		db.createTable(MobileSqls.WORK_PROCESS_SUB_TASK_EMPLOYEES_CONFIGURATIONS);
		db.createTable(MobileSqls.WORK_TO_SUBTASK_AUTO_FILL_CONFIGURATION);
		db.createTable(MobileSqls.HIDE_ADD_SUB_TASK_CONFIGURATIONS);
		
		db.createTable(MobileSqls.WORK_SPEC_PERMISSIONS);
		db.createTable(MobileSqls.WORK_UNASSIGNMENT_CRITERIAS);
		db.createTable(MobileSqls.WORK_ASSIGNMENT_CRITERIA_CONDITIONS);
		db.createTable(MobileSqls.WORKFLOW_SPECS_VISIBILITY_CONDITIONS);
		db.createTable(MobileSqls.WORKS_SPECS_WORK_ACTION_SPEC_EXTERNAL_ACTION_CONFIG);
		db.createTable(MobileSqls.WORK_SPEC_LIST_LEVEL_VISIBILITY_CONFIGURATIONS);
		db.createTable(MobileSqls.WORK_ACTION_FORM_VISIBLITY);
		
		
		db.createTable(MobileSqls.WORKSPEC_CUSTOM_DASHBOARD_METRICS);
		db.createTable(MobileSqls.ACTIONABLE_EMPLOYEE_GROUP_SPECS);
		db.createTable(MobileSqls.WORK_ACTION_NOTIFICATION_ESCALATION_MATRIX);
		db.createTable(MobileSqls.WORK_ACTION_EXCALATION_EMPIDS);
		db.createTable(MobileSqls.WORK_SPEC_CUSTOM_DASHBOARD_CONFIGURATIONS);
		
		db.createTable(MobileSqls.ENTITIES_SPECS_FIELDS);
		db.createTable(MobileSqls.ENTITIES_SPECS_ENTITY_SPECS);
		db.createTable(MobileSqls.ENTITIES_SPECS_SECTIONS_SPECS);
		db.createTable(MobileSqls.ENTITIES_SPECS_SECTIONS_FIELDS);
		
		db.createTable(MobileSqls.CUSTOM_ENTITY_SPEC);
		
		db.createTable(MobileSqls.ENTITIES_DATA_ADDED);
		db.createTable(MobileSqls.ENTITIES_DATA_FIELDS);
		db.createTable(MobileSqls.ENTITIES_DATA_SECTION_FIELDS);
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	public FormSpecContainer importFormSpec(String formSpecUniqueId) {
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
		return formSpecContainer;
		
	}
	
	 public <T> List<T> fetchDataFromSqliteAndMapToBean(String query, Class<T> clazz, Statement stmt) {
	        ResultSet rs = null;
	        List<T> items = new ArrayList<>();   
	        
	        try {
	            rs = stmt.executeQuery(query);

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
	 
		public void importDataFromSqlite(String sqliteName, Integer companyId, String cloneEntityData) {
			FormSpecContainer formSpecContainer = new FormSpecContainer();
			WorkSpecContainer workSpecContainer = new WorkSpecContainer();
			try {
				String url = "jdbc:sqlite:"+constants.getMediaStoragePath()+sqliteName;
				 Class.forName("org.sqlite.JDBC");
				 Connection conn = DriverManager.getConnection(url);
	             Statement stmt = conn.createStatement();
	             
	             
	             fetchDataFromSqliteFile(formSpecContainer, workSpecContainer, stmt);
	 			
	 			
	 			serviceManager.fetchDataAndInsertIntoDB(formSpecContainer,workSpecContainer,companyId,cloneEntityData);
	 			
	 			LOGGER.info(" importDataFromSqlite done ... successfully ");
				
			}catch(Exception e) {
				e.printStackTrace();
				LOGGER.info(" importDataFromSqlite Exception Occurred.. ");
			}
			
		}

		public void fetchDataFromSqliteFile(FormSpecContainer formSpecContainer, WorkSpecContainer workSpecContainer,
				Statement stmt) {
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
			 
			 // here...
			 
			 List<EntitySpec> entitySpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_specs_entity_specs",EntitySpec.class, stmt);
			 formSpecContainer.setEntitySpecs(entitySpecList);

			 List<EntityFieldSpec> entityFieldSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_specs_fields",EntityFieldSpec.class, stmt);
			 formSpecContainer.setEntityFields(entityFieldSpecList);
			
			 List<EntitySectionSpec> entitySectionSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_specs_sections_specs",EntitySectionSpec.class, stmt);
			 formSpecContainer.setEntitySections(entitySectionSpecList);
			
			 List<EntitySectionFieldSpec> entitySectionFieldSpecList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_specs_sections_fields",EntitySectionFieldSpec.class, stmt);
			 formSpecContainer.setEntitySectionFields(entitySectionFieldSpecList);
			 
			 
			 List<CustomEntitySpec> customEntitySpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM custom_entity_spec",CustomEntitySpec.class, stmt);
			 formSpecContainer.setCustomEntitySpecs(customEntitySpecs);
			 
			 // here...
			 
			 List<Entity> entityAddedList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_data_added",Entity.class, stmt);
			 formSpecContainer.setEntities(entityAddedList);
			 
			
			 List<EntityField> entityFieldList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_data_fields",EntityField.class, stmt);
			 formSpecContainer.setEntityFieldsData(entityFieldList);
			 
			
			 List<EntitySectionField> entitySectionFieldList = fetchDataFromSqliteAndMapToBean("SELECT * FROM entities_data_section_fields",EntitySectionField.class, stmt);
			 formSpecContainer.setEntitySectionFieldsData(entitySectionFieldList);
			 
			
			 // workspecs 

			List<WorkSpec> workSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_specs",WorkSpec.class, stmt);
			workSpecContainer.setWorkSpecs(workSpecs);
			
			List<WorkSpecCustomerCallApi> workSpecCustomerCallApis = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_spec_customer_call_apis",WorkSpecCustomerCallApi.class, stmt);
			workSpecContainer.setWorkSpecCustomerCallApis(workSpecCustomerCallApis);
			
			List<NextWorkSpec> nextWorkSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_next_work_specs",NextWorkSpec.class, stmt);
			workSpecContainer.setNextWorkSpecs(nextWorkSpecs);
			
			
			List<NextActionSpec> nextActionSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_next_action_specs",NextActionSpec.class, stmt);
			workSpecContainer.setNextActionSpecs(nextActionSpecs);
			
			
			List<WorkActionSpec> workActionSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_action_specs",WorkActionSpec.class, stmt);
			workSpecContainer.setWorkActionSpecs(workActionSpecs);
			
			
			List<WorkSpecFormSpecMap> workSpecFormSpecMaps = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_spec_form_spec_maps",WorkSpecFormSpecMap.class, stmt);
			workSpecContainer.setWorkSpecFormSpecMaps(workSpecFormSpecMaps);

			
			List<WorkActionSpecConditions> workActionSpecConditions = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_action_spec_conditions",WorkActionSpecConditions.class, stmt);
			workSpecContainer.setWorkActionSpecConditions(workActionSpecConditions);

			List<WorkActionSpecEndCondition> workActionSpecEndConditions = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_action_spec_conditions",WorkActionSpecEndCondition.class, stmt);
			workSpecContainer.setWorkActionSpecEndConditions(workActionSpecEndConditions);
			
			List<WorkFormAutoFill> worksFormAutoFillStageConfig = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_form_auto_fill_action_config",WorkFormAutoFill.class, stmt);
			workSpecContainer.setWorkFormAutoFillStageConfig(worksFormAutoFillStageConfig);
			
			List<WorkFormAutoFillField> workFormAutoFillFieldMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_form_auto_fill_field_mapping",WorkFormAutoFillField.class, stmt);
			workSpecContainer.setWorkFormAutoFillFieldMapping(workFormAutoFillFieldMapping);

			List<WorkAttachmentAutoFill> worksAttachmentFormAutoFillStageConfig = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_attchment_form_auto_fill_action_config",WorkAttachmentAutoFill.class, stmt);
			workSpecContainer.setWorkAttachmentFormAutoFillStageConfig(worksAttachmentFormAutoFillStageConfig);

			
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_attchment_form_auto_fill_field_mapping",WorkAttachmentFormAutoFillField.class, stmt);
			workSpecContainer.setWorkAttachmentFormAutoFillFieldMapping(workAttachmentFormAutoFillFieldMapping);

			List<WorkActionGroup> workActionGroups = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_specs_work_action_groups",WorkActionGroup.class, stmt);
			workSpecContainer.setWorkActionGroups(workActionGroups);
			
			
			List<FormToWorkAutoFill> formToWorkAutoFillStageConfig = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_form_to_work_auto_fill_action_config",FormToWorkAutoFill.class, stmt);
			workSpecContainer.setFormToWorkAutoFillStageConfig(formToWorkAutoFillStageConfig);

			List<FormToWorkAutoFillField> formToWorkAutoFillFieldMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_form_to_work_auto_fill_field_mapping",FormToWorkAutoFillField.class, stmt);
			workSpecContainer.setFormToWorkAutoFillFieldMapping(formToWorkAutoFillFieldMapping);
			
			List<FormAutoFillSectionConfiguration> formAutoFillSectionConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_auto_fill_section_config",FormAutoFillSectionConfiguration.class, stmt);
			workSpecContainer.setFormAutoFillSectionConfiguration(formAutoFillSectionConfigMapping);
			
			
			List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_auto_fill_section_fields_config",FormAutoFillSectionFieldsConfiguration.class, stmt);
			workSpecContainer.setFormAutoFillSectionFieldsConfiguration(formAutoFillSectionFieldsConfigMapping);
			
			
			List<AttachmnetFormAutoFillSectionConfiguration> attachmentFormAutoFillSectionConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM attachment_form_auto_fill_section_config",AttachmnetFormAutoFillSectionConfiguration.class, stmt);
			workSpecContainer.setAttachmentFormAutoFillSectionConfiguration(attachmentFormAutoFillSectionConfigMapping);
			
			List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM attachment_form_auto_fill_section_fields_config",WorkAttachmentAutoFillSectionFieldsConfiguration.class, stmt);
			workSpecContainer.setAttachmentFormAutoFillSectionFieldsConfiguration(attachmentFormAutoFillSectionFieldsConfigMapping);
			
			List<FormToWorkAutoFillSectionConfiguration> formToWorkAutoFillSectionConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_to_work_auto_fill_section_config",FormToWorkAutoFillSectionConfiguration.class, stmt);
			workSpecContainer.setFormToWorkAutoFillSectionConfiguration(formToWorkAutoFillSectionConfigMapping);
			
			List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfigMapping = fetchDataFromSqliteAndMapToBean("SELECT * FROM form_to_work_auto_fill_section_fields_config",FormToWorkAutoFillSectionFieldsConfiguration.class, stmt);
			workSpecContainer.setFormToWorkAutoFillSectionFieldsConfiguration(formToWorkAutoFillSectionFieldsConfigMapping);
			
			List<WorkFieldsUniqueConfigurations> workFieldsUniqueConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_fields_unique_configurations",WorkFieldsUniqueConfigurations.class, stmt);
			workSpecContainer.setWorkFieldsUniqueConfigurations(workFieldsUniqueConfigurations);
			
			List<WorkFieldsUniqueConfigurations> openWorkFieldsUniqueConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_open_work_fields_unique_configurations",WorkFieldsUniqueConfigurations.class, stmt);
			workSpecContainer.setOpenWorkFieldsUniqueConfigurations(openWorkFieldsUniqueConfigurations);
			
			List<WorkReassignmentRules> workReassignmentRulesList = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_re_assignment_rules",WorkReassignmentRules.class, stmt);
			workSpecContainer.setWorkReassignmentRules(workReassignmentRulesList);
			
			List<WorkSpecAppLabel> workSpecAppLabels = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_spec_app_labels",WorkSpecAppLabel.class, stmt);
			workSpecContainer.setWorkSpecAppLabels(workSpecAppLabels);
			
			List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_action_visibility_configuration",WorkActionVisibilityConfiguration.class, stmt);
			workSpecContainer.setWorkActionVisibilityConfigurations(workActionVisibilityConfigurations);
			
			List<WorkProcessSubTaskSpec> workProcessSubTaskSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_process_sub_task_spec_configurations",WorkProcessSubTaskSpec.class, stmt);
			workSpecContainer.setWorkProcessSubTaskSpecConfigurations(workProcessSubTaskSpecs);
			
			List<AddingSubTaskEmployeeConfiguration> workProcessSubTaskEmployeesConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_process_sub_task_employees_configurations",AddingSubTaskEmployeeConfiguration.class, stmt);
			workSpecContainer.setWorkProcessSubTaskEmployeesConfigurations(workProcessSubTaskEmployeesConfigurations);
			
			List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_to_sub_task_auto_fill_configuration",WorkToSubTaskAutoFillConfiguration.class, stmt);
			workSpecContainer.setWorkToSubTaskAutoFillConfigurations(workToSubTaskAutoFillConfigurations);
			
			List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM hide_add_sub_task_configurations",HideAddSubTaskConfiguration.class, stmt);
			workSpecContainer.setHideAddSubTaskConfigurations(hideAddSubTaskConfigurations);
			
			List<WorkSpecPermission> workSpecPermissions = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_spec_permissions",WorkSpecPermission.class, stmt);
			workSpecContainer.setWorkSpecPermissions(workSpecPermissions);
			
			List<WorkUnassignmentCriterias> workUnassignmentCriterias = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_unassignment_criterias",WorkUnassignmentCriterias.class, stmt);
			workSpecContainer.setWorkUnassignmentCriterias(workUnassignmentCriterias);
			
			List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_assignment_criteria_conditions",WorkAssignmentCriteriaConditions.class, stmt);
			workSpecContainer.setWorkAssignmentCriteriaConditions(workAssignmentCriteriaConditions);
			
			List<ExternalActionConfiguration> externalActionConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM works_specs_work_action_spec_external_action_config",ExternalActionConfiguration.class, stmt);
			workSpecContainer.setExternalActionConfigurations(externalActionConfigurations);
			
			List<WorkSpecListLevelVisibilityConfiguration> workSpecListLevelVisibilityConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_spec_list_level_visibility_configurations",WorkSpecListLevelVisibilityConfiguration.class, stmt);
			workSpecContainer.setWorkSpecListLevelVisibilityConfigurations(workSpecListLevelVisibilityConfigurations);
			
			
			List<WorkActionFormVisibility> workActionFormVisibility = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_action_form_visibility",WorkActionFormVisibility.class, stmt);
			workSpecContainer.setWorkActionFormVisibility(workActionFormVisibility);
			
			List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics = fetchDataFromSqliteAndMapToBean("SELECT * FROM workSpec_custom_dashboard_metrics",WorkSpecCustomDashboardMetric.class, stmt);
			workSpecContainer.setWorkSpecCustomDashboardMetrics(workSpecCustomDashboardMetrics);
			
			List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs = fetchDataFromSqliteAndMapToBean("SELECT * FROM actionable_employee_group_specs",ActionableEmployeeGroupSpecs.class, stmt);
			workSpecContainer.setActionableEmployeeGroupSpecs(actionableEmployeeGroupSpecs);
			
			List<WorkActionNotificationEscalationMatrix> workActionNotificationEscalationMatrix = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_action_notification_escalation_matrix",WorkActionNotificationEscalationMatrix.class, stmt);
			workSpecContainer.setWorkActionNotificationEscalationMatrix(workActionNotificationEscalationMatrix);
			
			
			List<WorkActionExcalatedEmpIds> workActionExcalatedEmpIds = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_action_excalated_empIds",WorkActionExcalatedEmpIds.class, stmt);
			workSpecContainer.setWorkActionExcalatedEmpIds(workActionExcalatedEmpIds);
			
			
			List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations = fetchDataFromSqliteAndMapToBean("SELECT * FROM work_spec_custom_dashboard_configurations",WorkSpecCustomDashboardConfiguration.class, stmt);
			workSpecContainer.setWorkSpecCustomDashboardConfigurations(workSpecCustomDashboardConfigurations);
		}

		
		

}
