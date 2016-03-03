package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RATING")
public class CfgRating {

    @Id
    @Column(name = "AGENCY_CODE")
    private String agencyCode;

    @Column(name = "RATING")
    private String rating;

    @Column(name = "QUALIFYING")
    private String qualifying;

    @Column(name = "LONG_SHORT")
    private String longShort;

    @Column(name = "RISK_BUCKET")
    private Long riskBucket;

    public CfgRating(String agencyCode, String rating, String qualifying, String longShort, Long riskBucket) {
        this.agencyCode = agencyCode;
        this.rating = rating;
        this.qualifying = qualifying;
        this.longShort = longShort;
        this.riskBucket = riskBucket;
    }

    public CfgRating() {

    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getQualifying() {
        return qualifying;
    }

    public void setQualifying(String qualifying) {
        this.qualifying = qualifying;
    }

    public String getLongShort() {
        return longShort;
    }

    public void setLongShort(String longShort) {
        this.longShort = longShort;
    }

    public Long getRiskBucket() {
        return riskBucket;
    }

    public void setRiskBucket(Long riskBucket) {
        this.riskBucket = riskBucket;
    }
}
