package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class CfgCompanyDimensionConsolidationKey implements Serializable {

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "ENTITY_CODE")
    private String entityCode;

    public CfgCompanyDimensionConsolidationKey() {
    }

    public CfgCompanyDimensionConsolidationKey(String companyCode, String entityCode) {
        this.companyCode = companyCode;
        this.entityCode = entityCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
}
