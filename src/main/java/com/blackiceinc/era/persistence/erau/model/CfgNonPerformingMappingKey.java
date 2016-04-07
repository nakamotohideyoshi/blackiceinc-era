package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgNonPerformingMappingKey implements Serializable {

    private static final long serialVersionUID = 7799395449148446249L;

    @Column(name = "ERA_NPL_CODE")
    private String eraNplCode;

    @Column(name = "PERFORMING_STATUS")
    private String performingStatus;

    public CfgNonPerformingMappingKey() {
        // default constructor
    }

    public CfgNonPerformingMappingKey(String eraNplCode, String performingStatus) {
        this.eraNplCode = eraNplCode;
        this.performingStatus = performingStatus;
    }

    public String getEraNplCode() {
        return eraNplCode;
    }

    public void setEraNplCode(String eraNplCode) {
        this.eraNplCode = eraNplCode;
    }

    public String getPerformingStatus() {
        return performingStatus;
    }

    public void setPerformingStatus(String performingStatus) {
        this.performingStatus = performingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgNonPerformingMappingKey that = (CfgNonPerformingMappingKey) o;

        return new EqualsBuilder()
                .append(eraNplCode, that.eraNplCode)
                .append(performingStatus, that.performingStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(eraNplCode)
                .append(performingStatus)
                .toHashCode();
    }
}
