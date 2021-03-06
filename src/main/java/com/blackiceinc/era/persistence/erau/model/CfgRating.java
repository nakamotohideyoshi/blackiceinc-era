package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RATING")
public class CfgRating extends CfgObject {

    @EmbeddedId
    private CfgRatingKey cfgRatingKey;

    @Column(name = "QUALIFYING")
    private String qualifying;

    @Column(name = "RISK_BUCKET")
    private Long riskBucket;

    public CfgRating(String agencyCode, String rating, String qualifying, Long riskBucket) {
        this.cfgRatingKey = new CfgRatingKey(agencyCode, rating);
        this.qualifying = qualifying;
        this.riskBucket = riskBucket;
    }

    public CfgRating() {
        this.cfgRatingKey = new CfgRatingKey();
    }

    public String getAgencyCode() {
        return this.cfgRatingKey.getAgencyCode();
    }

    public void setAgencyCode(String agencyCode) {
        this.cfgRatingKey.setAgencyCode(agencyCode);
    }

    public String getRating() {
        return this.cfgRatingKey.getRating();
    }

    public void setRating(String rating) {
        this.cfgRatingKey.setRating(rating);
    }

    public String getQualifying() {
        return qualifying;
    }

    public void setQualifying(String qualifying) {
        this.qualifying = qualifying;
    }

    public Long getRiskBucket() {
        return riskBucket;
    }

    public void setRiskBucket(Long riskBucket) {
        this.riskBucket = riskBucket;
    }
}
