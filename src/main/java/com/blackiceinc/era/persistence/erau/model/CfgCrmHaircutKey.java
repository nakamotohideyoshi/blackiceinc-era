package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgCrmHaircutKey implements Serializable {

    @Column(name = "ERA_COL_TYPE")
    private String eraColType;

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "RISK_BUCKET")
    private String riskBucket;

    @Column(name = "MIN_RESIDUAL_MATURITY")
    private String minResidualMaturity;

    @Column(name = "MAX_RESIDUAL_MATURITTY")
    private String maxResidualMaturity;

    public CfgCrmHaircutKey() {
    }

    public CfgCrmHaircutKey(String eraColType, String eraEntityType, String riskBucket, String minResidualMaturity, String maxResidualMaturity) {
        this.eraColType = eraColType;
        this.eraEntityType = eraEntityType;
        this.riskBucket = riskBucket;
        this.minResidualMaturity = minResidualMaturity;
        this.maxResidualMaturity = maxResidualMaturity;
    }

    public String getEraColType() {
        return eraColType;
    }

    public void setEraColType(String eraColType) {
        this.eraColType = eraColType;
    }

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getRiskBucket() {
        return riskBucket;
    }

    public void setRiskBucket(String riskBucket) {
        this.riskBucket = riskBucket;
    }

    public String getMinResidualMaturity() {
        return minResidualMaturity;
    }

    public void setMinResidualMaturity(String minResidualMaturity) {
        this.minResidualMaturity = minResidualMaturity;
    }

    public String getMaxResidualMaturity() {
        return maxResidualMaturity;
    }

    public void setMaxResidualMaturity(String maxResidualMaturity) {
        this.maxResidualMaturity = maxResidualMaturity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgCrmHaircutKey that = (CfgCrmHaircutKey) o;

        return new EqualsBuilder()
                .append(eraColType, that.eraColType)
                .append(eraEntityType, that.eraEntityType)
                .append(riskBucket, that.riskBucket)
                .append(minResidualMaturity, that.minResidualMaturity)
                .append(maxResidualMaturity, that.maxResidualMaturity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(eraColType)
                .append(eraEntityType)
                .append(riskBucket)
                .append(minResidualMaturity)
                .append(maxResidualMaturity)
                .toHashCode();
    }
}
