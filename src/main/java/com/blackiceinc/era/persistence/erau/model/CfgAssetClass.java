package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ASSET_CLASS")
public class CfgAssetClass {
    @Id
    @Column(name = "ERA_ASSET_CLASS")
    private String eraAssetClass;

    @Column(name = "ERA_ASSET_CLASS_DESC")
    private String eraAssetClassDesc;

    public CfgAssetClass(String eraAssetClass, String eraAssetClassDesc) {
        this.eraAssetClass = eraAssetClass;
        this.eraAssetClassDesc = eraAssetClassDesc;
    }

    public CfgAssetClass() {

    }

    public String getEraAssetClass() {
        return eraAssetClass;
    }

    public void setEraAssetClass(String eraAssetClass) {
        this.eraAssetClass = eraAssetClass;
    }

    public String getEraAssetClassDesc() {
        return eraAssetClassDesc;
    }

    public void setEraAssetClassDesc(String eraAssetClassDesc) {
        this.eraAssetClassDesc = eraAssetClassDesc;
    }
}
