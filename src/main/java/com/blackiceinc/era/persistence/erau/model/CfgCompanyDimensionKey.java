package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgCompanyDimensionKey implements Serializable {

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "FINANCIAL_BOOK")
    private String financialBook;

    public CfgCompanyDimensionKey() {
    }

    public CfgCompanyDimensionKey(String companyCode, String financialBook) {
        this.companyCode = companyCode;
        this.financialBook = financialBook;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFinancialBook() {
        return financialBook;
    }

    public void setFinancialBook(String financialBook) {
        this.financialBook = financialBook;
    }

}
