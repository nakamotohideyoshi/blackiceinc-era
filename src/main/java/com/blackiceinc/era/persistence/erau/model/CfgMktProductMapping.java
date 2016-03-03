package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_PRODUCT_MAPPING")
public class CfgMktProductMapping {

    @Id
    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    @Column(name = "EXCHANGED_TRADED")
    private String exchangedTraded;

    @Column(name = "INSTRUMENT_TYPE")
    private String instrumentType;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "UNDERLYING_TYPE")
    private String underlyingType;

    public CfgMktProductMapping(String mktProductType, String contractType, String exchangedTraded, String instrumentType, String tableName, String underlyingType) {
        this.mktProductType = mktProductType;
        this.contractType = contractType;
        this.exchangedTraded = exchangedTraded;
        this.instrumentType = instrumentType;
        this.tableName = tableName;
        this.underlyingType = underlyingType;
    }

    public String getMktProductType() {
        return mktProductType;
    }

    public void setMktProductType(String mktProductType) {
        this.mktProductType = mktProductType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getExchangedTraded() {
        return exchangedTraded;
    }

    public void setExchangedTraded(String exchangedTraded) {
        this.exchangedTraded = exchangedTraded;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUnderlyingType() {
        return underlyingType;
    }

    public void setUnderlyingType(String underlyingType) {
        this.underlyingType = underlyingType;
    }
}
