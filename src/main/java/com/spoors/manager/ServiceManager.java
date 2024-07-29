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
import com.spoors.beans.FormSpecContainer;
import com.spoors.beans.ListFilteringCritiria;
import com.spoors.beans.RemainderFieldsMap;
import com.spoors.beans.VisibilityDependencyCriteria;
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
				
			}
			
		}catch(Exception e) {
			LOGGER.info("Got Exception while getExportFormSpecData() : "+e);
			LOGGER.error("Got Exception while getExportFormSpecData() : "+e);
			e.printStackTrace();
		}
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

}
