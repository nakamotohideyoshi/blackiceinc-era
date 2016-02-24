package com.blackiceinc.era.web.rest.model;

/**
 * Created by tmanev on 2/24/2016.
 */
public class Configuration {

    private String name;
    private String status;

    public Configuration(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
