package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RISK_WEIGHT_MAPPING")
public class CfgRiskWeightMapping {

    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "ERA_NPL_CODE")
    private String eraNplCode;

    @Column(name = "YEAR_OF_ESTABLISHMENT")
    private String yearOfEstablishment;

    @Column(name = "CREDIT_MEASURE_1")
    private String creditMeasure1;

    @Column(name = "CREDIT_MEASURE_1_BEG")
    private String creditMeasure1Beg;

    @Column(name = "CREDIT_MEASURE_1_END")
    private String creditMeasure1End;

    @Column(name = "CREDIT_MEASURE_2")
    private String creditMeasure2;

    @Column(name = "CREDIT_MEASURE_2_BEG")
    private String creditMeasure2Beg;

    @Column(name = "CREDIT_MEASURE_2_END")
    private String creditMeasure2End;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    @Id
    @Column(name = "SEQ")
    private Long seq;

    public CfgRiskWeightMapping(String assetClass, String eraNplCode, String yearOfEstablishment, String creditMeasure1, String creditMeasure1Beg, String creditMeasure1End, String creditMeasure2, String creditMeasure2Beg, String creditMeasure2End, Double riskWeight, Long seq) {
        this.assetClass = assetClass;
        this.eraNplCode = eraNplCode;
        this.yearOfEstablishment = yearOfEstablishment;
        this.creditMeasure1 = creditMeasure1;
        this.creditMeasure1Beg = creditMeasure1Beg;
        this.creditMeasure1End = creditMeasure1End;
        this.creditMeasure2 = creditMeasure2;
        this.creditMeasure2Beg = creditMeasure2Beg;
        this.creditMeasure2End = creditMeasure2End;
        this.riskWeight = riskWeight;
        this.seq = seq;
    }

    public CfgRiskWeightMapping() {

    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getEraNplCode() {
        return eraNplCode;
    }

    public void setEraNplCode(String eraNplCode) {
        this.eraNplCode = eraNplCode;
    }

    public String getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(String yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public String getCreditMeasure1() {
        return creditMeasure1;
    }

    public void setCreditMeasure1(String creditMeasure1) {
        this.creditMeasure1 = creditMeasure1;
    }

    public String getCreditMeasure1Beg() {
        return creditMeasure1Beg;
    }

    public void setCreditMeasure1Beg(String creditMeasure1Beg) {
        this.creditMeasure1Beg = creditMeasure1Beg;
    }

    public String getCreditMeasure1End() {
        return creditMeasure1End;
    }

    public void setCreditMeasure1End(String creditMeasure1End) {
        this.creditMeasure1End = creditMeasure1End;
    }

    public String getCreditMeasure2() {
        return creditMeasure2;
    }

    public void setCreditMeasure2(String creditMeasure2) {
        this.creditMeasure2 = creditMeasure2;
    }

    public String getCreditMeasure2Beg() {
        return creditMeasure2Beg;
    }

    public void setCreditMeasure2Beg(String creditMeasure2Beg) {
        this.creditMeasure2Beg = creditMeasure2Beg;
    }

    public String getCreditMeasure2End() {
        return creditMeasure2End;
    }

    public void setCreditMeasure2End(String creditMeasure2End) {
        this.creditMeasure2End = creditMeasure2End;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
