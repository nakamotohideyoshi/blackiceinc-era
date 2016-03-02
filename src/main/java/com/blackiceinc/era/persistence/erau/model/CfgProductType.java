package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_PRODUCT_TYPE")
public class CfgProductType {

    @Id
    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "ERA_PRODUCT_DESC")
    private String eraProductDesc;

    @Column(name = "ERA_PRODUCT_CATEGORY")
    private String eraProductCategory;

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public String getEraProductDesc() {
        return eraProductDesc;
    }

    public void setEraProductDesc(String eraProductDesc) {
        this.eraProductDesc = eraProductDesc;
    }

    public String getEraProductCategory() {
        return eraProductCategory;
    }

    public void setEraProductCategory(String eraProductCategory) {
        this.eraProductCategory = eraProductCategory;
    }
}
