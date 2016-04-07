package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgAddOnKey implements Serializable {

    private static final long serialVersionUID = 6790200765537795488L;

    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "MATURITY_START")
    private String maturityStart;

    @Column(name = "MATURITY_END")
    private String maturityEnd;

    public CfgAddOnKey() {
        // default constructor
    }

    public CfgAddOnKey(String eraProductType, String maturityStart, String maturityEnd) {
        this.eraProductType = eraProductType;
        this.maturityStart = maturityStart;
        this.maturityEnd = maturityEnd;
    }

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public String getMaturityStart() {
        return maturityStart;
    }

    public void setMaturityStart(String maturityStart) {
        this.maturityStart = maturityStart;
    }

    public String getMaturityEnd() {
        return maturityEnd;
    }

    public void setMaturityEnd(String maturityEnd) {
        this.maturityEnd = maturityEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgAddOnKey that = (CfgAddOnKey) o;

        return new EqualsBuilder()
                .append(eraProductType, that.eraProductType)
                .append(maturityStart, that.maturityStart)
                .append(maturityEnd, that.maturityEnd)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(eraProductType)
                .append(maturityStart)
                .append(maturityEnd)
                .toHashCode();
    }
}
