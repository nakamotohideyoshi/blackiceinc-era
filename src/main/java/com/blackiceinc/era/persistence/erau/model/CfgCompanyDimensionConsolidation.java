package com.blackiceinc.era.persistence.erau.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CMPNY_DIM_CONSOLIDATION")
public class CfgCompanyDimensionConsolidation {

    @Id
    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "ENTITY_CODE")
    private String entityCode;

    @Column(name = "CONSO_MODE")
    private String consoMode;

    @Column(name = "CONSO_PERCT")
    private Double consoPerct;

    public CfgCompanyDimensionConsolidation(String companyCode, String entityCode, String consoMode, Double consoPerct) {
        this.companyCode = companyCode;
        this.entityCode = entityCode;
        this.consoMode = consoMode;
        this.consoPerct = consoPerct;
    }

    public CfgCompanyDimensionConsolidation() {

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

    public String getConsoMode() {
        return consoMode;
    }

    public void setConsoMode(String consoMode) {
        this.consoMode = consoMode;
    }

    public Double getConsoPerct() {
        return consoPerct;
    }

    public void setConsoPerct(Double consoPerct) {
        this.consoPerct = consoPerct;
    }
}
