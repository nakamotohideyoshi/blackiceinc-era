package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_IRR_GNR_INTRA")
public class CfgMktIrrGnrIntra  {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "ZONE_CODE")
    private String zoneCode;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktIrrGnrIntra(){
        // default constructor
    }

    public CfgMktIrrGnrIntra(String code, String zoneCode, Double riskWeight) {
        this.code = code;
        this.zoneCode = zoneCode;
        this.riskWeight = riskWeight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
