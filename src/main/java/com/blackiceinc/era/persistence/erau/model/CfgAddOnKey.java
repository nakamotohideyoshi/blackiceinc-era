package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CfgAddOnKey implements Serializable {

    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "MATURITY_START")
    private String maturityStart;

    @Column(name = "MATURITY_END")
    private String maturityEnd;

    public CfgAddOnKey() {
    }

    public CfgAddOnKey(String eraProductType, String maturityStart, String maturityEnd) {
        this.eraProductType = eraProductType;
        this.maturityStart = maturityStart;
        this.maturityEnd = maturityEnd;
    }

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public String getMaturityStart() {
        return maturityStart;
    }

    public void setMaturityStart(String maturityStart) {
        this.maturityStart = maturityStart;
    }

    public String getMaturityEnd() {
        return maturityEnd;
    }

    public void setMaturityEnd(String maturityEnd) {
        this.maturityEnd = maturityEnd;
    }
}
