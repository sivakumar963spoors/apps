package com.spoors.beans;

import com.spoors.util.Api;

public class FieldValidation {
	public static final int FIELD_IS_FORMFIELD=1;
	public static final int FIELD_IS_SECTIONFIELD=2;
	
	private long id;
	private long formSpecId;
	private long fieldSpecId;
	private String min;
	private String max;
	private int fieldDataType;
	private int fieldType;
	private Integer imageSize = 0;
	private Boolean imageSizeEnabled = false;
	public FieldValidation() {
	
	}
	
	public FieldValidation(long id,long formSpecId,long fieldSpecId,String min,String max,int fieldDataType,int fieldType) {
		this.id=id;
		this.formSpecId=formSpecId;
		this.fieldSpecId=fieldSpecId;
		if(!Api.isEmptyString(min)){
			this.min=min;	
		}
		if(!Api.isEmptyString(max)){
			this.max=max;	
		}
		this.fieldDataType=fieldDataType;
		this.fieldType=fieldType;
		
	}
	
	public FieldValidation(long id,long formSpecId,long fieldSpecId,String min,String max,
			int fieldDataType,int fieldType,Boolean imageSizeEnabled,Integer imageSize) {
		this.id=id;
		this.formSpecId=formSpecId;
		this.fieldSpecId=fieldSpecId;
		if(!Api.isEmptyString(min)){
			this.min=min;	
		}
		if(!Api.isEmptyString(max)){
			this.max=max;	
		}
		this.fieldDataType=fieldDataType;
		this.fieldType=fieldType;
		this.imageSizeEnabled = imageSizeEnabled;
		this.imageSize=imageSize;
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

	public long getFieldSpecId() {
		return fieldSpecId;
	}

	public void setFieldSpecId(long fieldSpecId) {
		this.fieldSpecId = fieldSpecId;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public int getFieldDataType() {
		return fieldDataType;
	}

	public void setFieldDataType(int fieldDataType) {
		this.fieldDataType = fieldDataType;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getImageSize() {
		return imageSize;
	}

	public void setImageSize(Integer imageSize) {
		this.imageSize = imageSize;
	}

	public void setImageSizeEnabled(Boolean imageSizeEnabled) {
		if(imageSizeEnabled == null)
			imageSizeEnabled = false;
		this.imageSizeEnabled = imageSizeEnabled;
	}
	public Boolean getImageSizeEnabled() {
		if(imageSizeEnabled == null)
			return false;
		return imageSizeEnabled;
	}
	public void setMinValue(String min) { this.min = min; }
	public void setMaxValue(String max) { this.max = max; }

}
