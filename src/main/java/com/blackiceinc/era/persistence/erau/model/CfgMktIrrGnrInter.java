package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_IRR_GNR_INTER")
public class CfgMktIrrGnrInter {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "ZONE_CODE_1")
    private String zoneCode1;

    @Column(name = "ZONE_CODE_2")
    private String zoneCode2;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktIrrGnrInter() {
    }

    public CfgMktIrrGnrInter(String code, String zoneCode1, String zoneCode2, Double riskWeight) {
        this.code = code;
        this.zoneCode1 = zoneCode1;
        this.zoneCode2 = zoneCode2;
        this.riskWeight = riskWeight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZoneCode1() {
        return zoneCode1;
    }

    public void setZoneCode1(String zoneCode1) {
        this.zoneCode1 = zoneCode1;
    }

    public String getZoneCode2() {
        return zoneCode2;
    }

    public void setZoneCode2(String zoneCode2) {
        this.zoneCode2 = zoneCode2;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
