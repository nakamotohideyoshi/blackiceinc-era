package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_OPS_PRODUCT_TYPE_MAPPING")
public class CfgOpsProductTypeMapping {

    @Id
    @Column(name = "OPS_PRODUCT_TYPE")
    private String opsProductType;

    @Column(name = "OPS_GL_CODE")
    private String opsGlCode;

    @Column(name = "OPS_VIB_CODE")
    private String opsVibCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public CfgOpsProductTypeMapping() {
    }

    public CfgOpsProductTypeMapping(String opsProductType, String opsGlCode, String opsVibCode, String description) {
        this.opsProductType = opsProductType;
        this.opsGlCode = opsGlCode;
        this.opsVibCode = opsVibCode;
        this.description = description;
    }

    public String getOpsProductType() {
        return opsProductType;
    }

    public void setOpsProductType(String opsProductType) {
        this.opsProductType = opsProductType;
    }

    public String getOpsGlCode() {
        return opsGlCode;
    }

    public void setOpsGlCode(String opsGlCode) {
        this.opsGlCode = opsGlCode;
    }

    public String getOpsVibCode() {
        return opsVibCode;
    }

    public void setOpsVibCode(String opsVibCode) {
        this.opsVibCode = opsVibCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
