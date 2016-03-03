package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ENTITY_TYPE_MAPPING")
public class CfgEntityTypeMapping {

    @Id
    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "CUSTOMER_TYPE")
    private String customerType;

    @Column(name = "CUSTOMER_SUB_TYPE")
    private String customerSubType;

    public CfgEntityTypeMapping(String eraEntityType, String customerType, String customerSubType) {
        this.eraEntityType = eraEntityType;
        this.customerType = customerType;
        this.customerSubType = customerSubType;
    }

    public CfgEntityTypeMapping() {

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
}
