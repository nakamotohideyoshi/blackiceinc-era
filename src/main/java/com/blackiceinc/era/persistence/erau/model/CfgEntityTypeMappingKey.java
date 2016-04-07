package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgEntityTypeMappingKey implements Serializable {

    private static final long serialVersionUID = -6869198650289946405L;

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "CUSTOMER_TYPE")
    private String customerType;

    @Column(name = "CUSTOMER_SUB_TYPE")
    private String customerSubType;

    public CfgEntityTypeMappingKey() {
        // default constructor
    }

    public CfgEntityTypeMappingKey(String eraEntityType, String customerType, String customerSubType) {
        this.eraEntityType = eraEntityType;
        this.customerType = customerType;
        this.customerSubType = customerSubType;
    }

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerSubType() {
        return customerSubType;
    }

    public void setCustomerSubType(String customerSubType) {
        this.customerSubType = customerSubType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgEntityTypeMappingKey that = (CfgEntityTypeMappingKey) o;

        return new EqualsBuilder()
                .append(eraEntityType, that.eraEntityType)
                .append(customerType, that.customerType)
                .append(customerSubType, that.customerSubType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(eraEntityType)
                .append(customerType)
                .append(customerSubType)
                .toHashCode();
    }
}
