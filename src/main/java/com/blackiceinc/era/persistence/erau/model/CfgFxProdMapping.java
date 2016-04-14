package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_FX_PROD_MAPPING")
public class CfgFxProdMapping {

    @Id
    @Column(name = "GL_CODE")
    private String glCode;

    @Column(name = "FX_PROD_TYPE")
    private String fxProdType;

    @Column(name = "GL_CODE_DESC")
    private String glCodeDesc;

    public CfgFxProdMapping(String glCode, String fxProdType, String glCodeDesc) {
        this.glCode = glCode;
        this.fxProdType = fxProdType;
        this.glCodeDesc = glCodeDesc;
    }

    public CfgFxProdMapping() {
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getFxProdType() {
        return fxProdType;
    }

    public void setFxProdType(String fxProdType) {
        this.fxProdType = fxProdType;
    }

    public String getGlCodeDesc() {
        return glCodeDesc;
    }

    public void setGlCodeDesc(String glCodeDesc) {
        this.glCodeDesc = glCodeDesc;
    }
}
