package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RECLASS_CHECK_TYPE")
public class CfgReclassCheckType  {

    @Id
    @Column(name = "CHECK_TYPE")
    private String checkType;

    @Column(name = "CHECK_DESCRIPTION")
    private String checkDescription;

    @Column(name = "WHERE_CLAUSE")
    private String whereClause;

    @Column(name = "CONSO_FIELD")
    private String consoField;

    @Column(name = "AMT_FIELD")
    private String amtField;

    public CfgReclassCheckType(String checkType, String checkDescription, String whereClause, String consoField, String amtField) {
        this.checkType = checkType;
        this.checkDescription = checkDescription;
        this.whereClause = whereClause;
        this.consoField = consoField;
        this.amtField = amtField;
    }

    public CfgReclassCheckType() {
        // default constructor
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckDescription() {
        return checkDescription;
    }

    public void setCheckDescription(String checkDescription) {
        this.checkDescription = checkDescription;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getConsoField() {
        return consoField;
    }

    public void setConsoField(String consoField) {
        this.consoField = consoField;
    }

    public String getAmtField() {
        return amtField;
    }

    public void setAmtField(String amtField) {
        this.amtField = amtField;
    }
}
