package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * The persistent class for the measurement_sensitivity database table.
 */
@Entity
@Table(name = "MEASUREMENT_SENSITIVITY")
public class MeasurementSensitivity {

    @Id
    @Column(name = "MEASUREMENT_NBR")
    private String measurementNbr;

    @Column(name = "SNAPSHOT_DATE")
    private Date snapshotDate;

    @Column(name = "ASSET_CLASS_FINAL")
    private String assetClassFinal;

    @Column(name = "EXPOSURE_TYPE_CODE")
    private String exposureTypeCode;

    @Column(name = "ERA_ENTITY_TYPE")
    private String eraEntityType;

    @Column(name = "ERA_PRODUCT_TYPE_FINAL")
    private String eraProductTypeFinal;

    @Column(name = "RISK_WEIGHT_FINAL")
    private Double riskWeightFinal;

    @Column(name = "EAD_BEFORE_CCF_LCY_AMT")
    private Double eadBeforeCcfLcyAmt;

    @Column(name = "RWA_AMT")
    private Double rwaAmt;

    @Column(name = "REG_CAP")
    private Double regCap;

    @Column(name = "LOAD_JOB_NBR")
    private Long loadJobNbr;

    @Column(name = "SCENARIO_ID")
    private String scenarioId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID", insertable=false, updatable=false),
            @JoinColumn(name = "SNAPSHOT_DATE", insertable=false, updatable=false)
            })
    private Customer customer;

    @Column(name = "ORG_UNIT")
    private String orgUnit;

    public MeasurementSensitivity() {
    }

    public String getMeasurementNbr() {
        return this.measurementNbr;
    }

    public void setMeasurementNbr(String measurementNbr) {
        this.measurementNbr = measurementNbr;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public String getAssetClassFinal() {
        return assetClassFinal;
    }

    public void setAssetClassFinal(String assetClassFinal) {
        this.assetClassFinal = assetClassFinal;
    }

    public String getExposureTypeCode() {
        return exposureTypeCode;
    }

    public void setExposureTypeCode(String exposureTypeCode) {
        this.exposureTypeCode = exposureTypeCode;
    }

    public String getEraEntityType() {
        return eraEntityType;
    }

    public void setEraEntityType(String eraEntityType) {
        this.eraEntityType = eraEntityType;
    }

    public String getEraProductTypeFinal() {
        return eraProductTypeFinal;
    }

    public void setEraProductTypeFinal(String eraProductTypeFinal) {
        this.eraProductTypeFinal = eraProductTypeFinal;
    }

    public Double getRiskWeightFinal() {
        return riskWeightFinal;
    }

    public void setRiskWeightFinal(Double riskWeightFinal) {
        this.riskWeightFinal = riskWeightFinal;
    }

    public Double getEadBeforeCcfLcyAmt() {
        return eadBeforeCcfLcyAmt;
    }

    public void setEadBeforeCcfLcyAmt(Double eadBeforeCcfLcyAmt) {
        this.eadBeforeCcfLcyAmt = eadBeforeCcfLcyAmt;
    }

    public Double getRwaAmt() {
        return rwaAmt;
    }

    public void setRwaAmt(Double rwaAmt) {
        this.rwaAmt = rwaAmt;
    }

    public Double getRegCap() {
        return regCap;
    }

    public void setRegCap(Double regCap) {
        this.regCap = regCap;
    }

    public Long getLoadJobNbr() {
        return loadJobNbr;
    }

    public void setLoadJobNbr(Long loadJobNbr) {
        this.loadJobNbr = loadJobNbr;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }
}