package com.zrodo.agriculture.entity;

import java.sql.Timestamp;

public class SysLog {
	private int logId;
	private String menuName;
	private String operationType;
	private String operationContent;
	private String cuserId;
	private Timestamp cdate;
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationContent() {
		return operationContent;
	}
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}
	public String getCuserId() {
		return cuserId;
	}
	public void setCuserId(String cuserId) {
		this.cuserId = cuserId;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
}
