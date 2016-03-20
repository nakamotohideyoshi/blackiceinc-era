package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgRatingKey that = (CfgRatingKey) o;

        return new EqualsBuilder()
                .append(agencyCode, that.agencyCode)
                .append(rating, that.rating)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(agencyCode)
                .append(rating)
                .toHashCode();
    }
}
