package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_EQT_SPC")
public class CfgMktEqtSpc extends CfgObject {

    @Id
    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    @Column(name = "UNDERLYING")
    private String underlying;

    @Column(name = "DIVERSIFIED_EQUITY")
    private String diversifiedEquity;

    @Column(name = "DIVERSIFIED_INDEX")
    private String diversifiedIndex;

    @Column(name = "LIQUID_EQUITY")
    private String liquidEquity;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktEqtSpc() {
        // default constructor
    }

    public CfgMktEqtSpc(String mktProductType, String underlying, String diversifiedEquity, String diversifiedIndex, String liquidEquity, Double riskWeight) {
        this.mktProductType = mktProductType;
        this.underlying = underlying;
        this.diversifiedEquity = diversifiedEquity;
        this.diversifiedIndex = diversifiedIndex;
        this.liquidEquity = liquidEquity;
        this.riskWeight = riskWeight;
    }

    public String getMktProductType() {
        return mktProductType;
    }

    public void setMktProductType(String mktProductType) {
        this.mktProductType = mktProductType;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getDiversifiedEquity() {
        return diversifiedEquity;
    }

    public void setDiversifiedEquity(String diversifiedEquity) {
        this.diversifiedEquity = diversifiedEquity;
    }

    public String getDiversifiedIndex() {
        return diversifiedIndex;
    }

    public void setDiversifiedIndex(String diversifiedIndex) {
        this.diversifiedIndex = diversifiedIndex;
    }

    public String getLiquidEquity() {
        return liquidEquity;
    }

    public void setLiquidEquity(String liquidEquity) {
        this.liquidEquity = liquidEquity;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
