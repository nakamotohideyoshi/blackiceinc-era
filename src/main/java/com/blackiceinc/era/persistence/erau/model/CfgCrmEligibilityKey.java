package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgCrmEligibilityKey implements Serializable {

    private static final long serialVersionUID = 5454041914848434177L;

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "RISK_BUCKET")
    private String riskBucket;

    @Column(name = "RISK_WEIGHT")
    private String riskWeight;

    public CfgCrmEligibilityKey() {
        // default constructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgCrmEligibilityKey that = (CfgCrmEligibilityKey) o;

        return new EqualsBuilder()
                .append(eraEntityType, that.eraEntityType)
                .append(eraProductType, that.eraProductType)
                .append(riskBucket, that.riskBucket)
                .append(riskWeight, that.riskWeight)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(eraEntityType)
                .append(eraProductType)
                .append(riskBucket)
                .append(riskWeight)
                .toHashCode();
    }
}
