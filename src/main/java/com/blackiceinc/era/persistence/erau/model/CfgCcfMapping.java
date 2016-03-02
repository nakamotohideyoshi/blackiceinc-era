package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CCF_MAPPING")
public class CfgCcfMapping {

    @Id
    @Column(name = "ERA_PRODUCT_TYPE")
    private String eraProductType;

    @Column(name = "CCF")
    private Double ccf;

    @Column(name = "UNCONDITIONALLY_CANCELABLE")
    private String unconditionallyCancelable;

    @Column(name = "MATURITY_START")
    private String maturityStart;

    @Column(name = "MATURITY_END")
    private String maturityEnd;

    @Column(name = "SEQ")
    private Long seq;

    public String getEraProductType() {
        return eraProductType;
    }

    public void setEraProductType(String eraProductType) {
        this.eraProductType = eraProductType;
    }

    public Double getCcf() {
        return ccf;
    }

    public void setCcf(Double ccf) {
        this.ccf = ccf;
    }

    public String getUnconditionallyCancelable() {
        return unconditionallyCancelable;
    }

    public void setUnconditionallyCancelable(String unconditionallyCancelable) {
        this.unconditionallyCancelable = unconditionallyCancelable;
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

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
