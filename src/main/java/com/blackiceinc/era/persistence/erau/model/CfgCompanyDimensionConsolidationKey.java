package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class CfgCompanyDimensionConsolidationKey implements Serializable {

    private static final long serialVersionUID = -8814761536945087360L;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "ENTITY_CODE")
    private String entityCode;

    public CfgCompanyDimensionConsolidationKey() {
        // default constructor
    }

    public CfgCompanyDimensionConsolidationKey(String companyCode, String entityCode) {
        this.companyCode = companyCode;
        this.entityCode = entityCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgCompanyDimensionConsolidationKey that = (CfgCompanyDimensionConsolidationKey) o;

        return new EqualsBuilder()
                .append(companyCode, that.companyCode)
                .append(entityCode, that.entityCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(companyCode)
                .append(entityCode)
                .toHashCode();
    }
}
