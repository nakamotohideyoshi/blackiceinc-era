package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgCrmEligibilityKey implements Serializable {

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "RISK_BUCKET")
    private String riskBucket;

    @Column(name = "RISK_WEIGHT")
    private String riskWeight;

    public CfgCrmEligibilityKey() {
    }

    public CfgCrmEligibilityKey(String eraEntityType, String eraProductType, String riskBucket, String riskWeight) {
        this.eraEntityType = eraEntityType;
        this.eraProductType = eraProductType;
        this.riskBucket = riskBucket;
        this.riskWeight = riskWeight;
    }

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
}
