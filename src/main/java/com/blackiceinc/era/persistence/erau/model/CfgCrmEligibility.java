package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CRM_ELIGIBILITY")
public class CfgCrmEligibility {

    @Id
    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "RISK_BUCKET")
    private String riskBucket;

    @Column(name = "RISK_WEIGHT")
    private String riskWeight;

    @Column(name = "ELIGIBILITY")
    private String eligibility;

    @Column(name = "SEQ")
    private Long seq;

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public String getRiskBucket() {
        return riskBucket;
    }

    public void setRiskBucket(String riskBucket) {
        this.riskBucket = riskBucket;
    }

    public String getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(String riskWeight) {
        this.riskWeight = riskWeight;
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
