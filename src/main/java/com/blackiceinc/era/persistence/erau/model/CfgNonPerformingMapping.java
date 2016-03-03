package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_NON_PERFORMING_MAPPING")
public class CfgNonPerformingMapping {

    @Id
    @Column(name = "ERA_NPL_CODE")
    private String eraNplCode;

    @Column(name = "PERFORMING_STATUS")
    private String performingStatus;

    public CfgNonPerformingMapping(String eraNplCode, String performingStatus) {
        this.eraNplCode = eraNplCode;
        this.performingStatus = performingStatus;
    }

    public CfgNonPerformingMapping() {

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
