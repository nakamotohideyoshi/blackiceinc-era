package com.blackiceinc.era.web.rest.model;

public class FailedCRUDResponseObj extends Response {
	private String id;
	private String errorMessage;
	
	public FailedCRUDResponseObj(String id, String errorMsg){
		this.id = id;
		this.errorMessage = errorMsg;
	}
	
	public FailedCRUDResponseObj() {}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getRecordId() {
		return this.id;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}