package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgCompanyDimensionKey implements Serializable {

    private static final long serialVersionUID = -5976865064930634610L;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "FINANCIAL_BOOK")
    private String financialBook;

    public CfgCompanyDimensionKey() {
        // default constructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgCompanyDimensionKey that = (CfgCompanyDimensionKey) o;

        return new EqualsBuilder()
                .append(companyCode, that.companyCode)
                .append(financialBook, that.financialBook)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(companyCode)
                .append(financialBook)
                .toHashCode();
    }
}
