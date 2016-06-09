package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_PRODUCT_TYPE_MAPPING")
public class CfgProductTypeMapping extends CfgObject {

    @Id
    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "SENIORITY")
    private String seniority;

    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    @Column(name = "ON_OFF")
    private String onOff;

    @Column(name = "F_MAIN_INDEX")
    private String fMainIndex;

    @Column(name = "F_RECOG_EXCH")
    private String fRecogExch;

    @Column(name = "F_RATED")
    private String fRated;

    @Column(name = "F_OCCU")
    private String fOccu;

    @Column(name = "F_COMPLETED")
    private String fCompleted;

    @Column(name = "F_INDEPENDANT_VALUER")
    private String fIndependantValuer;

    @Column(name = "F_LEGALLY_ENFORCE")
    private String fLegallyEnforce;

    @Column(name = "UNDERLYING")
    private String underlying;

    @Column(name = "ERA_CONTRACT_TYPE")
    private String eraContractType;

    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "REPAYMENT_PROPERTY")
    private String repaymentProperty;

    public CfgProductTypeMapping(String productType, String tableName, String seniority, String contractType, String onOff, String fMainIndex, String fRecogExch, String fRated, String fOccu, String fCompleted, String fIndependantValuer, String fLegallyEnforce, String underlying, String eraContractType, Long seq, String repaymentProperty) {
        this.productType = productType;
        this.tableName = tableName;
        this.seniority = seniority;
        this.contractType = contractType;
        this.onOff = onOff;
        this.fMainIndex = fMainIndex;
        this.fRecogExch = fRecogExch;
        this.fRated = fRated;
        this.fOccu = fOccu;
        this.fCompleted = fCompleted;
        this.fIndependantValuer = fIndependantValuer;
        this.fLegallyEnforce = fLegallyEnforce;
        this.underlying = underlying;
        this.eraContractType = eraContractType;
        this.seq = seq;
        this.repaymentProperty = repaymentProperty;
    }

    public CfgProductTypeMapping() {
        // default constructor
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    public String getfMainIndex() {
        return fMainIndex;
    }

    public void setfMainIndex(String fMainIndex) {
        this.fMainIndex = fMainIndex;
    }

    public String getfRecogExch() {
        return fRecogExch;
    }

    public void setfRecogExch(String fRecogExch) {
        this.fRecogExch = fRecogExch;
    }

    public String getfRated() {
        return fRated;
    }

    public void setfRated(String fRated) {
        this.fRated = fRated;
    }

    public String getfOccu() {
        return fOccu;
    }

    public void setfOccu(String fOccu) {
        this.fOccu = fOccu;
    }

    public String getfCompleted() {
        return fCompleted;
    }

    public void setfCompleted(String fCompleted) {
        this.fCompleted = fCompleted;
    }

    public String getfIndependantValuer() {
        return fIndependantValuer;
    }

    public void setfIndependantValuer(String fIndependantValuer) {
        this.fIndependantValuer = fIndependantValuer;
    }

    public String getfLegallyEnforce() {
        return fLegallyEnforce;
    }

    public void setfLegallyEnforce(String fLegallyEnforce) {
        this.fLegallyEnforce = fLegallyEnforce;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getEraContractType() {
        return eraContractType;
    }

    public void setEraContractType(String eraContractType) {
        this.eraContractType = eraContractType;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getRepaymentProperty() {
        return repaymentProperty;
    }

    public void setRepaymentProperty(String repaymentProperty) {
        this.repaymentProperty = repaymentProperty;
    }
}
