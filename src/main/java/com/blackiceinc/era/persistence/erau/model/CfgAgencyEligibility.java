package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_AGENCY_ELIGIBILITY")
public class CfgAgencyEligibility extends CfgObject {

    @Id
    @Column(name = "AGENCY_CODE")
    private String agencyCode;

    @Column(name = "AGENCY_DESC")
    private String agencyDesc;

    @Column(name = "AGENCY_TYPE")
    private String agencyType;

    public CfgAgencyEligibility(String agencyCode, String agencyDesc, String agencyType) {
        this.agencyCode = agencyCode;
        this.agencyDesc = agencyDesc;
        this.agencyType = agencyType;
    }

    public CfgAgencyEligibility() {
        // default constructor
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyDesc() {
        return agencyDesc;
    }

    public void setAgencyDesc(String agencyDesc) {
        this.agencyDesc = agencyDesc;
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }
}
