package com.spoors.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormSpecDataSource {

	private Long formSpecDataSourceId;
	private String dataSourceName;
	private Long formSpecId;
	private String formSpecUniqueId;
	private String methodType;
	private String url;
	private String responseContentType;
	private String responseData;
	private String createdTime;
	private String modifiedTime;
	private Long createdBy;
	private Boolean deleted = false;
	private Integer companyId;
	
	private String dataSourceNameError;
	private String urlError;
	private String assignError;
	private boolean staticDataSource;
	private Long sectionSpecId;
	
	List<DataSourceRequestHeader> dataSourceRequestHeaders = new ArrayList<DataSourceRequestHeader>();
	List<DataSourceRequestParam> dataSourceRequestParams = new ArrayList<DataSourceRequestParam>();
	List<DataSourceResponseMapping> dataSourceResponseMappings = new ArrayList<DataSourceResponseMapping>();
	
	private Boolean jspDeleted = true;
	
	public Long getFormSpecDataSourceId() {
		return formSpecDataSourceId;
	}
	public void setFormSpecDataSourceId(Long formSpecDataSourceId) {
		this.formSpecDataSourceId = formSpecDataSourceId;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public Long getFormSpecId() {
		return formSpecId;
	}
	public void setFormSpecId(Long formSpecId) {
		this.formSpecId = formSpecId;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResponseContentType() {
		return responseContentType;
	}
	public void setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public List<DataSourceRequestHeader> getDataSourceRequestHeaders() {
		return dataSourceRequestHeaders;
	}
	public void setDataSourceRequestHeaders(
			List<DataSourceRequestHeader> dataSourceRequestHeaders) {
		this.dataSourceRequestHeaders = dataSourceRequestHeaders;
	}
	public List<DataSourceRequestParam> getDataSourceRequestParams() {
		return dataSourceRequestParams;
	}
	public void setDataSourceRequestParams(
			List<DataSourceRequestParam> dataSourceRequestParams) {
		this.dataSourceRequestParams = dataSourceRequestParams;
	}
	public List<DataSourceResponseMapping> getDataSourceResponseMappings() {
		return dataSourceResponseMappings;
	}
	public void setDataSourceResponseMappings(
			List<DataSourceResponseMapping> dataSourceResponseMappings) {
		this.dataSourceResponseMappings = dataSourceResponseMappings;
	}
	public Boolean getJspDeleted() {
		return jspDeleted;
	}
	public void setJspDeleted(Boolean jspDeleted) {
		this.jspDeleted = jspDeleted;
	}
	public String getDataSourceNameError() {
		return dataSourceNameError;
	}
	public void setDataSourceNameError(String dataSourceNameError) {
		this.dataSourceNameError = dataSourceNameError;
	}
	public String getUrlError() {
		return urlError;
	}
	public void setUrlError(String urlError) {
		this.urlError = urlError;
	}
	public String getAssignError() {
		return assignError;
	}
	public void setAssignError(String assignError) {
		this.assignError = assignError;
	}
	public boolean getStaticDataSource() {
		return staticDataSource;
	}
	public void setStaticDataSource(boolean staticDataSource) {
		this.staticDataSource = staticDataSource;
	}
	public Long getSectionSpecId() {
		return sectionSpecId;
	}
	public void setSectionSpecId(Long sectionSpecId) {
		this.sectionSpecId = sectionSpecId;
	}
	
}
