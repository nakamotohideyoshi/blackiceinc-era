package com.blackiceinc.era.persistence.erau.model;


import javax.persistence.*;

@Entity
@Table(name = "CFG_CMPNY_DIM_CONSOLIDATION")
public class CfgCompanyDimensionConsolidation {

    @EmbeddedId
    private CfgCompanyDimensionConsolidationKey cfgCompanyDimensionConsolidationKey;

    @Column(name = "CONSO_MODE")
    private String consoMode;

    @Column(name = "CONSO_PERCT")
    private Double consoPerct;

    public CfgCompanyDimensionConsolidation(String companyCode, String entityCode, String consoMode, Double consoPerct) {
        this.cfgCompanyDimensionConsolidationKey = new CfgCompanyDimensionConsolidationKey(companyCode, entityCode);
        this.consoMode = consoMode;
        this.consoPerct = consoPerct;
    }

    public CfgCompanyDimensionConsolidation() {
        this.cfgCompanyDimensionConsolidationKey = new CfgCompanyDimensionConsolidationKey();
    }

    public String getCompanyCode() {
        return cfgCompanyDimensionConsolidationKey.getCompanyCode();
    }

    public void setCompanyCode(String companyCode) {
        this.cfgCompanyDimensionConsolidationKey.setCompanyCode(companyCode);
    }

    public String getEntityCode() {
        return cfgCompanyDimensionConsolidationKey.getEntityCode();
    }

    public void setEntityCode(String entityCode) {
        this.cfgCompanyDimensionConsolidationKey.setEntityCode(entityCode);
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
