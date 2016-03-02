package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_PRODUCT_TYPE")
public class CfgMktProductType {

    @Id
    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    @Column(name = "MKT_PRODUCT_DESC")
    private String mktProductDesc;

    @Column(name = "MKT_PRODUCT_CATEGORY")
    private String mktProductCategory;

    public String getMktProductType() {
        return mktProductType;
    }

    public void setMktProductType(String mktProductType) {
        this.mktProductType = mktProductType;
    }

    public String getMktProductDesc() {
        return mktProductDesc;
    }

    public void setMktProductDesc(String mktProductDesc) {
        this.mktProductDesc = mktProductDesc;
    }

    public String getMktProductCategory() {
        return mktProductCategory;
    }

    public void setMktProductCategory(String mktProductCategory) {
        this.mktProductCategory = mktProductCategory;
    }
}
