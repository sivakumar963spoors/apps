package com.spoors.beans;

public class AutoGenereteSequenceSpecConfiguaration {

	private long id;
	private long formSpecId;
	private long formFieldSpecId;
	private String formFieldSpecUniqueId;
	private Integer fieldType;
	private Integer fieldDataType;
	private Integer prefix;
	private Integer seperator;
	private Integer include;
	private Integer sequenceLength;
	private String customisedPrefix;
	private String customisedSufix;
	private Integer sufix;
	private boolean prefixExpression;
	private boolean sufixExpression;
	private String prefixFormFieldExpression;
	private String sufixFormFieldExpression;
	
	private String inputFormFieldExpression;
	
	public String getCustomisedPrefix() {
        return customisedPrefix;
    }
    public void setCustomisedPrefix(String customisedPrefix) {
        this.customisedPrefix = customisedPrefix;
    }
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFormSpecId() {
		return formSpecId;
	}
	public void setFormSpecId(long formSpecId) {
		this.formSpecId = formSpecId;
	}
	public long getFormFieldSpecId() {
		return formFieldSpecId;
	}
	public void setFormFieldSpecId(long formFieldSpecId) {
		this.formFieldSpecId = formFieldSpecId;
	}
	public String getFormFieldSpecUniqueId() {
		return formFieldSpecUniqueId;
	}
	public void setFormFieldSpecUniqueId(String formFieldSpecUniqueId) {
		this.formFieldSpecUniqueId = formFieldSpecUniqueId;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public Integer getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(Integer fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public Integer getPrefix() {
		return prefix;
	}
	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}
	public Integer getSeperator() {
		return seperator;
	}
	public void setSeperator(Integer seperator) {
		this.seperator = seperator;
	}
	public Integer getInclude() {
		return include;
	}
	public void setInclude(Integer include) {
		this.include = include;
	}
	public Integer getSequenceLength() {
		return sequenceLength;
	}
	public void setSequenceLength(Integer sequenceLength) {
		this.sequenceLength = sequenceLength;
	}
	
    @Override
    public String toString() {
        return "AutoGenereteSequenceSpecConfiguaration [id=" + id + ", formSpecId=" + formSpecId + ", formFieldSpecId="
                + formFieldSpecId + ", formFieldSpecUniqueId=" + formFieldSpecUniqueId + ", fieldType=" + fieldType
                + ", fieldDataType=" + fieldDataType + ", prefix=" + prefix + ", seperator=" + seperator + ", include="
                + include + ", sequenceLength=" + sequenceLength + ", customisedPrefix=" + customisedPrefix + ",customisedSufix="+customisedSufix+","
      			+ "sufix="+sufix+",prefixExpression="+prefixExpression+",sufixExpression="+sufixExpression+",prefixFormFieldExpression="+prefixFormFieldExpression+","
				+ "sufixFormFieldExpression="+sufixFormFieldExpression+"]";
    }
	public String getInputFormFieldExpression() {
		return inputFormFieldExpression;
	}
	public void setInputFormFieldExpression(String inputFormFieldExpression) {
		this.inputFormFieldExpression = inputFormFieldExpression;
	}
	public String getCustomisedSufix() {
		return customisedSufix;
	}
	public void setCustomisedSufix(String customisedSufix) {
		this.customisedSufix = customisedSufix;
	}
	public Integer getSufix() {
		return sufix;
	}
	public void setSufix(Integer sufix) {
		this.sufix = sufix;
	}
	public boolean isPrefixExpression() {
		return prefixExpression;
	}
	public void setPrefixExpression(boolean prefixExpression) {
		this.prefixExpression = prefixExpression;
	}
	public boolean isSufixExpression() {
		return sufixExpression;
	}
	public void setSufixExpression(boolean sufixExpression) {
		this.sufixExpression = sufixExpression;
	}
	public String getPrefixFormFieldExpression() {
		return prefixFormFieldExpression;
	}
	public void setPrefixFormFieldExpression(String prefixFormFieldExpression) {
		this.prefixFormFieldExpression = prefixFormFieldExpression;
	}
	public String getSufixFormFieldExpression() {
		return sufixFormFieldExpression;
	}
	public void setSufixFormFieldExpression(String sufixFormFieldExpression) {
		this.sufixFormFieldExpression = sufixFormFieldExpression;
	}
	
}
