package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_IRR_GNR_RISK")
public class CfgMktIrrGnrRisk extends CfgObject {

    @Id
    @Column(name = "ZONE_CODE")
    private String zoneCode;

    @Column(name = "BAND_CODE")
    private String bandCode;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "COUPON_RATE_START")
    private Double couponRateStart;

    @Column(name = "COUPON_RATE_END")
    private Double couponRateEnd;

    @Column(name = "MATURITY_BAND_START")
    private Double maturityBandStart;

    @Column(name = "MATURITY_BAND_END")
    private Double maturityBandEnd;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    @Column(name = "SEQ")
    private Long seq;

    public CfgMktIrrGnrRisk(String zoneCode, String bandCode, String currency, Double couponRateStart,
                            Double couponRateEnd, Double maturityBandStart, Double maturityBandEnd, Double riskWeight,
                            Long seq) {
        this.zoneCode = zoneCode;
        this.bandCode = bandCode;
        this.currency = currency;
        this.couponRateStart = couponRateStart;
        this.couponRateEnd = couponRateEnd;
        this.maturityBandStart = maturityBandStart;
        this.maturityBandEnd = maturityBandEnd;
        this.riskWeight = riskWeight;
        this.seq = seq;
    }

    public CfgMktIrrGnrRisk() {
        // default constructor
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getBandCode() {
        return bandCode;
    }

    public void setBandCode(String bandCode) {
        this.bandCode = bandCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCouponRateStart() {
        return couponRateStart;
    }

    public void setCouponRateStart(Double couponRateStart) {
        this.couponRateStart = couponRateStart;
    }

    public Double getCouponRateEnd() {
        return couponRateEnd;
    }

    public void setCouponRateEnd(Double couponRateEnd) {
        this.couponRateEnd = couponRateEnd;
    }

    public Double getMaturityBandStart() {
        return maturityBandStart;
    }

    public void setMaturityBandStart(Double maturityBandStart) {
        this.maturityBandStart = maturityBandStart;
    }

    public Double getMaturityBandEnd() {
        return maturityBandEnd;
    }

    public void setMaturityBandEnd(Double maturityBandEnd) {
        this.maturityBandEnd = maturityBandEnd;
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
