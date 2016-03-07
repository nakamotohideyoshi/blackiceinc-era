package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;

@Entity
@Table(name = "CFG_ADD_ON")
public class CfgAddOn {

    @EmbeddedId
    private CfgAddOnKey cfgAddOnKey;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgAddOn() {
        this.cfgAddOnKey = new CfgAddOnKey();
    }

    public CfgAddOn(String eraProductType, String maturityStart, String maturityEnd, Double riskWeight) {
        this.cfgAddOnKey = new CfgAddOnKey(eraProductType, maturityStart, maturityEnd);
        this.riskWeight = riskWeight;
    }

    public String getEraProductType() {
        return this.cfgAddOnKey.getEraProductType();
    }

    public void setEraProductType(String eraProductType) {
        this.cfgAddOnKey.setEraProductType(eraProductType);
    }

    public String getMaturityStart() {
        return this.cfgAddOnKey.getMaturityStart();
    }

    public void setMaturityStart(String maturityStart) {
        this.cfgAddOnKey.setMaturityStart(maturityStart);
    }

    public String getMaturityEnd() {
        return this.cfgAddOnKey.getMaturityEnd();
    }

    public void setMaturityEnd(String maturityEnd) {
        this.cfgAddOnKey.setMaturityEnd(maturityEnd);
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
