package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_FX_PROD_TYPE")
public class CfgFxProdType {

    @Id
    @Column(name = "FX_PROD_TYPE")
    private String fxProdType;

    @Column(name = "FX_PROD_TYPE_DESC")
    private String fxProdTypeDesc;

    public CfgFxProdType(String fxProdType, String fxProdTypeDesc) {
        this.fxProdType = fxProdType;
        this.fxProdTypeDesc = fxProdTypeDesc;
    }

    public CfgFxProdType() {
        // default constructor
    }

    public String getFxProdType() {
        return fxProdType;
    }

    public void setFxProdType(String fxProdType) {
        this.fxProdType = fxProdType;
    }

    public String getFxProdTypeDesc() {
        return fxProdTypeDesc;
    }

    public void setFxProdTypeDesc(String fxProdTypeDesc) {
        this.fxProdTypeDesc = fxProdTypeDesc;
    }
}
