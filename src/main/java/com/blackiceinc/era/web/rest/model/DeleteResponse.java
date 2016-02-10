package com.blackiceinc.era.web.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteResponse extends Response{
	private List<FailedCRUDResponseObj> failedDeleteRecords;
	private HashMap<String, Boolean> deleteSuccessResultMap;
	
	public DeleteResponse() {
		failedDeleteRecords = new ArrayList<FailedCRUDResponseObj>();
		deleteSuccessResultMap = new HashMap<String, Boolean>();
	}
	
	public void addFailedDeleteRecord(FailedCRUDResponseObj singleFailedResponse) {
		failedDeleteRecords.add(singleFailedResponse);
	}
	
	public List<FailedCRUDResponseObj> getFailedDeleteRecords() {
		return this.failedDeleteRecords;
	}
	
	public void modifyDeleteSuccessResultMap(String id, Boolean deleteSuccess) {
		deleteSuccessResultMap.put(id, deleteSuccess);
	}
}