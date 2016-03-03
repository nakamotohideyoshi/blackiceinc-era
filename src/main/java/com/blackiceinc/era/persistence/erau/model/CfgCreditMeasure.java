package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CREDIT_MEASURE")
public class CfgCreditMeasure {

    @Id
    @Column(name = "CREDIT_MEASURE")
    private String creditMeasure;

    @Column(name = "CREDIT_MEASURE_DESC")
    private String creditMeasureDesc;

    public CfgCreditMeasure(String creditMeasure, String creditMeasureDesc) {
        this.creditMeasure = creditMeasure;
        this.creditMeasureDesc = creditMeasureDesc;
    }

    public CfgCreditMeasure() {

    }

    public String getCreditMeasure() {
        return creditMeasure;
    }

    public void setCreditMeasure(String creditMeasure) {
        this.creditMeasure = creditMeasure;
    }

    public String getCreditMeasureDesc() {
        return creditMeasureDesc;
    }

    public void setCreditMeasureDesc(String creditMeasureDesc) {
        this.creditMeasureDesc = creditMeasureDesc;
    }
}
