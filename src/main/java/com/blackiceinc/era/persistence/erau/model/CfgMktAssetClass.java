package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_ASSET_CLASS")
public class CfgMktAssetClass {

    @Id
    @Column(name = "MKT_ASSET_CLASS")
    private String mktAssetClass;

    @Column(name = "MKT_ASSET_CLASS_DESC")
    private String mktAssetClassDesc;

    public CfgMktAssetClass(String mktAssetClass, String mktAssetClassDesc) {
        this.mktAssetClass = mktAssetClass;
        this.mktAssetClassDesc = mktAssetClassDesc;
    }

    public CfgMktAssetClass() {

    }

    public String getMktAssetClass() {
        return mktAssetClass;
    }

    public void setMktAssetClass(String mktAssetClass) {
        this.mktAssetClass = mktAssetClass;
    }

    public String getMktAssetClassDesc() {
        return mktAssetClassDesc;
    }

    public void setMktAssetClassDesc(String mktAssetClassDesc) {
        this.mktAssetClassDesc = mktAssetClassDesc;
    }
}
