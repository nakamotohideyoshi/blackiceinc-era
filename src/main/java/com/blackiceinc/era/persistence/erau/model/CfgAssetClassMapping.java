package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_ASSET_CLASS_MAPPING")
public class CfgAssetClassMapping extends CfgObject {

    @Id
    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "ENTITY_TYPE")
    private String entityType;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    public CfgAssetClassMapping(String assetClass, String entityType, String productType) {
        this.assetClass = assetClass;
        this.entityType = entityType;
        this.productType = productType;
    }

    public CfgAssetClassMapping() {

    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
