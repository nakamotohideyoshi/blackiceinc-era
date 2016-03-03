package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_EQT_GNR")
public class CfgMktEqtGnr {

    @Id
    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    @Column(name = "UNDERLYING")
    private String underlying;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktEqtGnr() {
    }

    public CfgMktEqtGnr(String mktProductType, String underlying, Double riskWeight) {
        this.mktProductType = mktProductType;
        this.underlying = underlying;
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

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
