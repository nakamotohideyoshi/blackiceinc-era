package com.blackiceinc.era.persistence.erau.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_FINANCIAL_BOOK")
public class CfgFinancialBook {

    @Id
    @Column(name = "BOOK_CODE")
    private String bookCode;

    @Column(name = "BOOK_DESC")
    private String bookDesc;

    @Column(name = "TRADING_FLAG")
    private String tradingFlag;

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getTradingFlag() {
        return tradingFlag;
    }

    public void setTradingFlag(String tradingFlag) {
        this.tradingFlag = tradingFlag;
    }

}
