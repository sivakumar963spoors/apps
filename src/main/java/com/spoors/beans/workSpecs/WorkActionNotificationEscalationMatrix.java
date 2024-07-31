package com.spoors.beans.workSpecs;

public class WorkActionNotificationEscalationMatrix {

	private long matrixId;
	private String matrixName;
	private long companyId;
	private long createdBy;
	private int deleted;
	private long workSpecId;
	
	public long getMatrixId() {
		return matrixId;
	}
	public void setMatrixId(long matrixId) {
		this.matrixId = matrixId;
	}
	public String getMatrixName() {
		return matrixName;
	}
	public void setMatrixName(String matrixName) {
		this.matrixName = matrixName;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
	}
	
	
}
