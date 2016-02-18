package com.blackiceinc.era.web.rest.model;

import java.util.ArrayList;
import java.util.List;

public class DeleteResponse extends Response {
    private List<CRUDResponseObj> records;

    public DeleteResponse() {
        records = new ArrayList<>();
    }

    public void addRecordResponse(CRUDResponseObj singleResponse) {
        records.add(singleResponse);
    }

    public List<CRUDResponseObj> getRecords() {
        return this.records;
    }

}