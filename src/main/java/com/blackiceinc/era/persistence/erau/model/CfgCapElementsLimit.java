package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CAP_ELEMENTS_LIMIT")
public class CfgCapElementsLimit {

    @Id
    @Column(name = "LIMIT_TYPE")
    private String limitType;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "THRESHOLD")
    private Double threshold;

    @Column(name = "CONSO_TABLE")
    private String consoTable;

    @Column(name = "CONSO_FIELD")
    private String consoField;

    @Column(name = "CONSO_FIELD_VALUE")
    private String consoFieldValue;

    @Column(name = "CONSO_AMT")
    private String consoAmt;

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public String getConsoTable() {
        return consoTable;
    }

    public void setConsoTable(String consoTable) {
        this.consoTable = consoTable;
    }

    public String getConsoField() {
        return consoField;
    }

    public void setConsoField(String consoField) {
        this.consoField = consoField;
    }

    public String getConsoFieldValue() {
        return consoFieldValue;
    }

    public void setConsoFieldValue(String consoFieldValue) {
        this.consoFieldValue = consoFieldValue;
    }

    public String getConsoAmt() {
        return consoAmt;
    }

    public void setConsoAmt(String consoAmt) {
        this.consoAmt = consoAmt;
    }
}
