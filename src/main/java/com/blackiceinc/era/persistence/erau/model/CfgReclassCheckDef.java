package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RECLASS_CHECK_DEF")
public class CfgReclassCheckDef {

    @Id
    @Column(name = "CHECK_DEF_NO")
    private String checkDefNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CHECK_TYPE")
    private String checkType;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "THRESHOLD")
    private Double threshold;

    @Column(name = "CURRENCY")
    private String currency;

    public CfgReclassCheckDef(String checkDefNo, String description, String checkType, String operator, Double threshold, String currency) {
        this.checkDefNo = checkDefNo;
        this.description = description;
        this.checkType = checkType;
        this.operator = operator;
        this.threshold = threshold;
        this.currency = currency;
    }

    public CfgReclassCheckDef() {

    }

    public String getCheckDefNo() {
        return checkDefNo;
    }

    public void setCheckDefNo(String checkDefNo) {
        this.checkDefNo = checkDefNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
