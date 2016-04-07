package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ENTITY_TYPE")
public class CfgEntityType  {

    @Id
    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "ENTITY_DESC")
    private String entityDesc;

    public CfgEntityType(String eraEntityType, String entityDesc) {
        this.eraEntityType = eraEntityType;
        this.entityDesc = entityDesc;
    }

    public CfgEntityType() {
        // default constructor
    }

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }
}
