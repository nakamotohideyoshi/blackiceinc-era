package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgNonPerformingMappingKey implements Serializable {

    @Column(name = "ERA_NPL_CODE")
    private String eraNplCode;

    @Column(name = "PERFORMING_STATUS")
    private String performingStatus;

    public CfgNonPerformingMappingKey() {
    }

    public CfgNonPerformingMappingKey(String eraNplCode, String performingStatus) {
        this.eraNplCode = eraNplCode;
        this.performingStatus = performingStatus;
    }

    public String getEraNplCode() {
        return eraNplCode;
    }

    public void setEraNplCode(String eraNplCode) {
        this.eraNplCode = eraNplCode;
    }

    public String getPerformingStatus() {
        return performingStatus;
    }

    public void setPerformingStatus(String performingStatus) {
        this.performingStatus = performingStatus;
    }
}
