package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;

@Entity
@Table(name = "CFG_COMPANY_DIMENSION")
public class CfgCompanyDimension  {

    @EmbeddedId
    private CfgCompanyDimensionKey cfgCompanyDimensionKey;

    public CfgCompanyDimension(CfgCompanyDimensionKey cfgCompanyDimensionKey) {
        this.cfgCompanyDimensionKey = cfgCompanyDimensionKey;
    }

    public CfgCompanyDimension() {
        this.cfgCompanyDimensionKey = new CfgCompanyDimensionKey();
    }

    public CfgCompanyDimension(String companyCode, String financialBook) {
        this.cfgCompanyDimensionKey = new CfgCompanyDimensionKey();
        this.cfgCompanyDimensionKey.setCompanyCode(companyCode);
        this.cfgCompanyDimensionKey.setFinancialBook(financialBook);
    }


    public String getCompanyCode() {
        return cfgCompanyDimensionKey.getCompanyCode();
    }

    public void setCompanyCode(String companyCode) {
        this.cfgCompanyDimensionKey.setCompanyCode(companyCode);
    }

    public String getFinancialBook() {
        return this.cfgCompanyDimensionKey.getFinancialBook();
    }

    public void setFinancialBook(String financialBook) {
        this.cfgCompanyDimensionKey.setFinancialBook(financialBook);
    }


}
