package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CRM_HAIRCUT")
public class CfgCrmHaircut {

    @Id
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

    @Column(name = "HAIRCUT")
    private Double haircut;

    @Column(name = "SEQ")
    private Long seq;

    public CfgCrmHaircut(String eraColType, String eraEntityType, String riskBucket, String minResidualMaturity, String maxResidualMaturity, Double haircut, Long seq) {
        this.eraColType = eraColType;
        this.eraEntityType = eraEntityType;
        this.riskBucket = riskBucket;
        this.minResidualMaturity = minResidualMaturity;
        this.maxResidualMaturity = maxResidualMaturity;
        this.haircut = haircut;
        this.seq = seq;
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

    public Double getHaircut() {
        return haircut;
    }

    public void setHaircut(Double haircut) {
        this.haircut = haircut;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
