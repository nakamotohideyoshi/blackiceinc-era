package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;

@Entity
@Table(name = "CFG_CRM_ELIGIBILITY")
public class CfgCrmEligibility {

    @EmbeddedId
    private CfgCrmEligibilityKey cfgCrmEligibilityKey;

    @Column(name = "ELIGIBILITY")
    private String eligibility;

    @Column(name = "SEQ")
    private Long seq;

    public CfgCrmEligibility(String eraEntityType, String eraProductType, String riskBucket, String riskWeight, String eligibility, Long seq) {
        this.cfgCrmEligibilityKey = new CfgCrmEligibilityKey(eraEntityType, eraProductType, riskBucket, riskWeight);
        this.eligibility = eligibility;
        this.seq = seq;
    }

    public CfgCrmEligibility() {
        this.cfgCrmEligibilityKey = new CfgCrmEligibilityKey();
    }

    public String getEraEntityType() {
        return this.cfgCrmEligibilityKey.getEraEntityType();
    }

    public void setEraEntityType(String eraEntityType) {
        this.cfgCrmEligibilityKey.setEraEntityType(eraEntityType);
    }

    public String getEraProductType() {
        return this.cfgCrmEligibilityKey.getEraProductType();
    }

    public void setEraProductType(String eraProductType) {
        this.cfgCrmEligibilityKey.setEraProductType(eraProductType);
    }

    public String getRiskBucket() {
        return this.cfgCrmEligibilityKey.getRiskBucket();
    }

    public void setRiskBucket(String riskBucket) {
        this.cfgCrmEligibilityKey.setRiskBucket(riskBucket);
    }

    public String getRiskWeight() {
        return this.cfgCrmEligibilityKey.getRiskWeight();
    }

    public void setRiskWeight(String riskWeight) {
        this.cfgCrmEligibilityKey.setRiskWeight(riskWeight);
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
