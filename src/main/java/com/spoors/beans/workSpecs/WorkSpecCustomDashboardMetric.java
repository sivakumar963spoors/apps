package com.spoors.beans.workSpecs;

public class WorkSpecCustomDashboardMetric {

	// `metricType`, `metricReferenceField`
	private Long id;
	private Long workSpecId;
	private int metricType;
	private String metricReferenceField;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public int getMetricType() {
		return metricType;
	}
	public void setMetricType(int metricType) {
		this.metricType = metricType;
	}
	public String getMetricReferenceField() {
		return metricReferenceField;
	}
	public void setMetricReferenceField(String metricReferenceField) {
		this.metricReferenceField = metricReferenceField;
	}
	
	
	
}
