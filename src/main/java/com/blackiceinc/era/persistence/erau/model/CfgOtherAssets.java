package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_OTHER_ASSETS")
public class CfgOtherAssets extends CfgObject {

    @Id
    @Column(name = "GL_CODE")
    private String glCode;

    @Column(name = "GROUP_CHECK")
    private String groupCheck;

    @Column(name = "HEADING")
    private String heading;

    @Column(name = "GL_DESC")
    private String glDesc;

    @Column(name = "ERA_CONTRACT_TYPE")
    private String eraContractType;

    @Column(name = "CHECK_CRITERIA")
    private String checkCriteria;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgOtherAssets(String glCode, String groupCheck, String heading, String glDesc, String eraContractType, String checkCriteria, Double riskWeight) {
        this.glCode = glCode;
        this.groupCheck = groupCheck;
        this.heading = heading;
        this.glDesc = glDesc;
        this.eraContractType = eraContractType;
        this.checkCriteria = checkCriteria;
        this.riskWeight = riskWeight;
    }

    public CfgOtherAssets() {
        // default constructor
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getGroupCheck() {
        return groupCheck;
    }

    public void setGroupCheck(String groupCheck) {
        this.groupCheck = groupCheck;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getGlDesc() {
        return glDesc;
    }

    public void setGlDesc(String glDesc) {
        this.glDesc = glDesc;
    }

    public String getEraContractType() {
        return eraContractType;
    }

    public void setEraContractType(String eraContractType) {
        this.eraContractType = eraContractType;
    }

    public String getCheckCriteria() {
        return checkCriteria;
    }

    public void setCheckCriteria(String checkCriteria) {
        this.checkCriteria = checkCriteria;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
