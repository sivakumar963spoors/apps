package com.spoors.beans;

import java.io.Serializable;

public class GenericMultiSelect implements Serializable {
	
	private static final long serialVersionUID = 4257021990700347126L;
	private String valueId;
	private String title;
	private int selected;
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "GenericMultiSelect [valueId=" + valueId + ", title=" + title
				+ ", selected=" + selected + "]";
	}
	
	

}
