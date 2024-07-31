package com.spoors.beans.workSpecs;

public class WorkSpecFormSpecMap {
	private Long id;
	private String formSpecUniqueId;
	private long workSpecId;
	
	
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
