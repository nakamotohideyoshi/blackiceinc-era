package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_ASSET_CLASS_MAPPING")
public class CfgMktAssetClassMapping {

    @Id
    @Column(name = "MKT_ASSET_CLASS")
    private String mktAssetClass;

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    public String getMktAssetClass() {
        return mktAssetClass;
    }

    public void setMktAssetClass(String mktAssetClass) {
        this.mktAssetClass = mktAssetClass;
    }

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getMktProductType() {
        return mktProductType;
    }

    public void setMktProductType(String mktProductType) {
        this.mktProductType = mktProductType;
    }
}
