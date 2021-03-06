package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_OPS_RISK")
public class CfgOpsRisk extends CfgObject {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgOpsRisk() {
        // default constructor
    }

    public CfgOpsRisk(String code, Double riskWeight) {
        this.code = code;
        this.riskWeight = riskWeight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
