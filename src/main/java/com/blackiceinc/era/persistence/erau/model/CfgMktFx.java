package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_FX")
public class CfgMktFx extends CfgObject {

    @Id
    @Column(name = "MKT_PRODUCT_TYPE")
    private String mktProductType;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    public CfgMktFx() {
        // default constructor
    }

    public CfgMktFx(String mktProductType, Double riskWeight) {
        this.mktProductType = mktProductType;
        this.riskWeight = riskWeight;
    }

    public String getMktProductType() {
        return mktProductType;
    }

    public void setMktProductType(String mktProductType) {
        this.mktProductType = mktProductType;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }
}
