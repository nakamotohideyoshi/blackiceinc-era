package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_IRR_GNR_RISK")
public class CfgMktIrrGnrRisk {

    @Id
    @Column(name = "ZONE_CODE")
    private String zoneCode;

    @Column(name = "BAND_CODE")
    private String bandCode;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "COUPON_RATE_START")
    private Long couponRateStart;

    @Column(name = "COUPON_RATE_END")
    private Long couponRateEnd;

    @Column(name = "MATURITY_BAND_START")
    private Long maturityBandStart;

    @Column(name = "MATURITY_BAND_END")
    private Long maturityBandEnd;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktIrrGnrRisk(String zoneCode, String bandCode, String currency, Long couponRateStart, Long couponRateEnd, Long maturityBandStart, Long maturityBandEnd, Double riskWeight) {
        this.zoneCode = zoneCode;
        this.bandCode = bandCode;
        this.currency = currency;
        this.couponRateStart = couponRateStart;
        this.couponRateEnd = couponRateEnd;
        this.maturityBandStart = maturityBandStart;
        this.maturityBandEnd = maturityBandEnd;
        this.riskWeight = riskWeight;
    }

    public CfgMktIrrGnrRisk() {

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

    public Long getCouponRateStart() {
        return couponRateStart;
    }

    public void setCouponRateStart(Long couponRateStart) {
        this.couponRateStart = couponRateStart;
    }

    public Long getCouponRateEnd() {
        return couponRateEnd;
    }

    public void setCouponRateEnd(Long couponRateEnd) {
        this.couponRateEnd = couponRateEnd;
    }

    public Long getMaturityBandStart() {
        return maturityBandStart;
    }

    public void setMaturityBandStart(Long maturityBandStart) {
        this.maturityBandStart = maturityBandStart;
    }

    public Long getMaturityBandEnd() {
        return maturityBandEnd;
    }

    public void setMaturityBandEnd(Long maturityBandEnd) {
        this.maturityBandEnd = maturityBandEnd;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
