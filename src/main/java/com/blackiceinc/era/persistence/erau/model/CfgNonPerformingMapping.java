package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;

@Entity
@Table(name = "CFG_NON_PERFORMING_MAPPING")
public class CfgNonPerformingMapping  {

    @EmbeddedId
    private CfgNonPerformingMappingKey cfgNonPerformingMappingKey;

    public CfgNonPerformingMapping(String eraNplCode, String performingStatus) {
        this.cfgNonPerformingMappingKey = new CfgNonPerformingMappingKey(eraNplCode, performingStatus);
    }

    public CfgNonPerformingMapping() {
        this.cfgNonPerformingMappingKey = new CfgNonPerformingMappingKey();
    }

    public String getEraNplCode() {
        return this.cfgNonPerformingMappingKey.getEraNplCode();
    }

    public void setEraNplCode(String eraNplCode) {
        this.cfgNonPerformingMappingKey.setEraNplCode(eraNplCode);
    }

    public String getPerformingStatus() {
        return this.cfgNonPerformingMappingKey.getPerformingStatus();
    }

    public void setPerformingStatus(String performingStatus) {
        this.cfgNonPerformingMappingKey.setPerformingStatus(performingStatus);
    }
}
