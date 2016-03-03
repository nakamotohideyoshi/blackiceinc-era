package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ADD_ON")
public class CfgAddOn {

    @Id
    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "MATURITY_START")
    private Long maturityStart;

    @Column(name = "MATURITY_END")
    private Long maturityEnd;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgAddOn(String eraProductType, Long maturityStart, Long maturityEnd, Double riskWeight) {
        this.eraProductType = eraProductType;
        this.maturityStart = maturityStart;
        this.maturityEnd = maturityEnd;
        this.riskWeight = riskWeight;
    }

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public Long getMaturityStart() {
        return maturityStart;
    }

    public void setMaturityStart(Long maturityStart) {
        this.maturityStart = maturityStart;
    }

    public Long getMaturityEnd() {
        return maturityEnd;
    }

    public void setMaturityEnd(Long maturityEnd) {
        this.maturityEnd = maturityEnd;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
