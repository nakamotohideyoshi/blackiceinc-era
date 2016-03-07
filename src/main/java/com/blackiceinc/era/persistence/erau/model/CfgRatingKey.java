package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgRatingKey implements Serializable {

    @Column(name = "AGENCY_CODE")
    private String agencyCode;

    @Column(name = "RATING")
    private String rating;

    public CfgRatingKey() {
    }

    public CfgRatingKey(String agencyCode, String rating) {
        this.agencyCode = agencyCode;
        this.rating = rating;
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
}
