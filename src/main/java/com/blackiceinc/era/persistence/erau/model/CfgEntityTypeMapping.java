package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ENTITY_TYPE_MAPPING")
public class CfgEntityTypeMapping  {

    @EmbeddedId
    private CfgEntityTypeMappingKey cfgEntityTypeMappingKey;

    public CfgEntityTypeMapping(String eraEntityType, String customerType, String customerSubType) {
        this.cfgEntityTypeMappingKey = new CfgEntityTypeMappingKey(eraEntityType, customerType, customerSubType);
    }

    public CfgEntityTypeMapping() {
        this.cfgEntityTypeMappingKey = new CfgEntityTypeMappingKey();
    }

    public String getEraEntityType() {
        return cfgEntityTypeMappingKey.getEraEntityType();
    }

    public void setEraEntityType(String eraEntityType) {
        this.cfgEntityTypeMappingKey.setEraEntityType(eraEntityType);
    }

    public String getCustomerType() {
        return cfgEntityTypeMappingKey.getCustomerType();
    }

    public void setCustomerType(String customerType) {
        this.cfgEntityTypeMappingKey.setCustomerType(customerType);
    }

    public String getCustomerSubType() {
        return cfgEntityTypeMappingKey.getCustomerSubType();
    }

    public void setCustomerSubType(String customerSubType) {
        this.cfgEntityTypeMappingKey.setCustomerSubType(customerSubType);
    }
}
