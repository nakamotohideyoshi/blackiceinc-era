package com.blackiceinc.era.web.rest.model;

public class CRUDResponseObj extends Response {
    private String id;
    private Boolean success;

    public CRUDResponseObj(String id, Boolean success, String message) {
        this.id = id;
        this.success = success;
        this.setMessage(message);
    }

    public CRUDResponseObj(String id, boolean success) {
        this.id = id;
        this.success = success;
    }

    public CRUDResponseObj() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}