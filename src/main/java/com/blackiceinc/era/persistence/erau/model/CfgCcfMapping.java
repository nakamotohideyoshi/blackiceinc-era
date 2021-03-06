package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CCF_MAPPING")
public class CfgCcfMapping extends CfgObject {

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

    @Id
    @Column(name = "SEQ")
    private Long seq;

    public CfgCcfMapping(String eraProductType, Double ccf, String unconditionallyCancelable, String maturityStart, String maturityEnd, Long seq) {
        this.eraProductType = eraProductType;
        this.ccf = ccf;
        this.unconditionallyCancelable = unconditionallyCancelable;
        this.maturityStart = maturityStart;
        this.maturityEnd = maturityEnd;
        this.seq = seq;
    }

    public CfgCcfMapping() {
        // default constructor
    }

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
