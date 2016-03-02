package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_COMPANY_DIMENSION")
public class CfgCompanyDimension {

    @Id
    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "FINANCIAL_BOOK")
    private String financialBook;

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
