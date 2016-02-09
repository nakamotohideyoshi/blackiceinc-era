package com.blackiceinc.era.persistence.erau.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the measurement_sensitivity database table.
 * 
 */
@Entity
@Table(name="MEASUREMENT_SENSITIVITY")
public class MeasurementSensitivity {
	
	@Column(name="AGENCY_CODE")
	private String agencyCode;

	@Column(name="ALLOW_AMT")
	private BigDecimal allowAmt;

	@Column(name="ALLOW_LCY_AMT")
	private BigDecimal allowLcyAmt;

	@Column(name="ASSET_CLASS_FINAL")
	private String assetClassFinal;

	@Column(name="ASSET_CLASS_ORG")
	private String assetClassOrg;

	private BigDecimal ccf;

	private String ccy;

	@Column(name="CONTRACT_TYPE_CODE")
	private String contractTypeCode;

	@Column(name="CREDIT_MEASURE_1")
	private String creditMeasure1;

	@Column(name="CREDIT_MEASURE_1_VALUE")
	private String creditMeasure1Value;

	@Column(name="CREDIT_MEASURE_2")
	private String creditMeasure2;

	@Column(name="CREDIT_MEASURE_2_VALUE")
	private String creditMeasure2Value;

	@Column(name="CUSTOMER_ID")
	private String customerId;

	private BigDecimal dsc;

	@Column(name="EAD_AMT")
	private BigDecimal eadAmt;

	@Column(name="EAD_BEFORE_CCF_AMT")
	private BigDecimal eadBeforeCcfAmt;

	@Column(name="EAD_BEFORE_CCF_LCY_AMT")
	private BigDecimal eadBeforeCcfLcyAmt;

	@Column(name="EAD_FIN_AMT")
	private BigDecimal eadFinAmt;

	@Column(name="EAD_FIN_LCY_AMT")
	private BigDecimal eadFinLcyAmt;

	@Column(name="EAD_FINAL_AMT")
	private BigDecimal eadFinalAmt;

	@Column(name="EAD_FINAL_LCY_AMT")
	private BigDecimal eadFinalLcyAmt;

	@Column(name="EAD_GUA_AMT")
	private BigDecimal eadGuaAmt;

	@Column(name="EAD_GUA_LCY_AMT")
	private BigDecimal eadGuaLcyAmt;

	@Column(name="EAD_LCY_AMT")
	private BigDecimal eadLcyAmt;

	@Column(name="EAD_NET_AMT")
	private BigDecimal eadNetAmt;

	@Column(name="EAD_NET_LCY_AMT")
	private BigDecimal eadNetLcyAmt;

	@Column(name="EAD_OTH_AMT")
	private BigDecimal eadOthAmt;

	@Column(name="EAD_OTH_LCY_AMT")
	private BigDecimal eadOthLcyAmt;

	@Column(name="EAD_PRO_AMT")
	private BigDecimal eadProAmt;

	@Column(name="EAD_PRO_LCY_AMT")
	private BigDecimal eadProLcyAmt;

	@Column(name="EAD_REC_AMT")
	private BigDecimal eadRecAmt;

	@Column(name="EAD_REC_LCY_AMT")
	private BigDecimal eadRecLcyAmt;

	@Column(name="ERA_ENTITY_TYPE")
	private String eraEntityType;

	@Column(name="ERA_NPL_CODE")
	private String eraNplCode;

	@Column(name="ERA_PRODUCT_CATEGORY_FINAL")
	private String eraProductCategoryFinal;

	@Column(name="ERA_PRODUCT_CATEGORY_ORG")
	private String eraProductCategoryOrg;

	@Column(name="ERA_PRODUCT_TYPE_FINAL")
	private String eraProductTypeFinal;

	@Column(name="ERA_PRODUCT_TYPE_ORG")
	private String eraProductTypeOrg;

	@Column(name="EXCHANGE_RATE")
	private BigDecimal exchangeRate;

	@Column(name="EXPOSURE_BOOK_CODE")
	private String exposureBookCode;

	@Column(name="EXPOSURE_SUB_TYPE_CODE")
	private String exposureSubTypeCode;

	@Column(name="EXPOSURE_TYPE_CODE")
	private String exposureTypeCode;

	@Column(name="F_COMPLETED")
	private String fCompleted;

	@Column(name="F_INDEPENDANT_VALUER")
	private String fIndependantValuer;

	@Column(name="F_LEGALLY_ENFORCE")
	private String fLegallyEnforce;

	@Column(name="F_REPAYMENT_PROPERTY")
	private String fRepaymentProperty;

	@Column(name="FACILITY_NBR")
	private String facilityNbr;

	@Column(name="GUA_REF_NBR")
	private String guaRefNbr;

	@Column(name="INSTRUMENT_NBR")
	private String instrumentNbr;

	@Column(name="INTER_COMPANY")
	private String interCompany;

	@Column(name="ISSUER_NBR")
	private String issuerNbr;

	@Column(name="LOAD_JOB_NBR")
	private BigDecimal loadJobNbr;

	private BigDecimal ltv;

	@Column(name="MATURITY_ADJUSTMENT_FACTOR")
	private BigDecimal maturityAdjustmentFactor;

	@Column(name="MATURITY_DATE")
	private Date maturityDate;

	@Id
	@Column(name="MEASUREMENT_NBR")
	private String measurementNbr;

	@Column(name="ON_OFF")
	private String onOff;

	@Column(name="ORIGINAL_MATURITY")
	private Double originalMaturity;

	@Column(name="OUTSTANDING_AMT")
	private BigDecimal outstandingAmt;

	@Column(name="OUTSTANDING_LCY_AMT")
	private BigDecimal outstandingLcyAmt;

	@Column(name="PERFORMING_STATUS")
	private String performingStatus;

	private String rating;

	@Column(name="REG_CAP")
	private BigDecimal regCap;

	@Column(name="REG_CAP_BEFORE_CRM")
	private BigDecimal regCapBeforeCrm;

	@Column(name="RESIDUAL_MATURITY")
	private Double residualMaturity;

	@Column(name="RISK_BUCKET")
	private String riskBucket;

	@Column(name="RISK_TYPE")
	private String riskType;

	@Column(name="RISK_WEIGHT_FINAL")
	private BigDecimal riskWeightFinal;

	@Column(name="RISK_WEIGHT_ORG")
	private BigDecimal riskWeightOrg;

	@Column(name="RWA_AMT")
	private BigDecimal rwaAmt;

	@Column(name="RWA_BEFORE_CRM_AMT")
	private BigDecimal rwaBeforeCrmAmt;

	@Column(name="RWA_TREATMENT_CODE")
	private String rwaTreatmentCode;

	@Column(name="SALES_AMT")
	private BigDecimal salesAmt;

	@Column(name="SALES_LCY_AMT")
	private BigDecimal salesLcyAmt;

	@Column(name="SCENARIO_ID")
	private String scenarioId;

	@Column(name="SNAPSHOT_DATE")
	private Date snapshotDate;

	@Column(name="SP_CVG")
	private BigDecimal spCvg;

	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="TRADING_FLAG")
	private String tradingFlag;

	@Column(name="UNCONDITIONALLY_CANCELABLE")
	private String unconditionallyCancelable;

	private String underlying;

	@Column(name="WHOLESALE_RETAIL_IND")
	private String wholesaleRetailInd;

	public MeasurementSensitivity() {
	}

	public String getAgencyCode() {
		return this.agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public BigDecimal getAllowAmt() {
		return this.allowAmt;
	}

	public void setAllowAmt(BigDecimal allowAmt) {
		this.allowAmt = allowAmt;
	}

	public BigDecimal getAllowLcyAmt() {
		return this.allowLcyAmt;
	}

	public void setAllowLcyAmt(BigDecimal allowLcyAmt) {
		this.allowLcyAmt = allowLcyAmt;
	}

	public String getAssetClassFinal() {
		return this.assetClassFinal;
	}

	public void setAssetClassFinal(String assetClassFinal) {
		this.assetClassFinal = assetClassFinal;
	}

	public String getAssetClassOrg() {
		return this.assetClassOrg;
	}

	public void setAssetClassOrg(String assetClassOrg) {
		this.assetClassOrg = assetClassOrg;
	}

	public BigDecimal getCcf() {
		return this.ccf;
	}

	public void setCcf(BigDecimal ccf) {
		this.ccf = ccf;
	}

	public String getCcy() {
		return this.ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getContractTypeCode() {
		return this.contractTypeCode;
	}

	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}

	public String getCreditMeasure1() {
		return this.creditMeasure1;
	}

	public void setCreditMeasure1(String creditMeasure1) {
		this.creditMeasure1 = creditMeasure1;
	}

	public String getCreditMeasure1Value() {
		return this.creditMeasure1Value;
	}

	public void setCreditMeasure1Value(String creditMeasure1Value) {
		this.creditMeasure1Value = creditMeasure1Value;
	}

	public String getCreditMeasure2() {
		return this.creditMeasure2;
	}

	public void setCreditMeasure2(String creditMeasure2) {
		this.creditMeasure2 = creditMeasure2;
	}

	public String getCreditMeasure2Value() {
		return this.creditMeasure2Value;
	}

	public void setCreditMeasure2Value(String creditMeasure2Value) {
		this.creditMeasure2Value = creditMeasure2Value;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getDsc() {
		return this.dsc;
	}

	public void setDsc(BigDecimal dsc) {
		this.dsc = dsc;
	}

	public BigDecimal getEadAmt() {
		return this.eadAmt;
	}

	public void setEadAmt(BigDecimal eadAmt) {
		this.eadAmt = eadAmt;
	}

	public BigDecimal getEadBeforeCcfAmt() {
		return this.eadBeforeCcfAmt;
	}

	public void setEadBeforeCcfAmt(BigDecimal eadBeforeCcfAmt) {
		this.eadBeforeCcfAmt = eadBeforeCcfAmt;
	}

	public BigDecimal getEadBeforeCcfLcyAmt() {
		return this.eadBeforeCcfLcyAmt;
	}

	public void setEadBeforeCcfLcyAmt(BigDecimal eadBeforeCcfLcyAmt) {
		this.eadBeforeCcfLcyAmt = eadBeforeCcfLcyAmt;
	}

	public BigDecimal getEadFinAmt() {
		return this.eadFinAmt;
	}

	public void setEadFinAmt(BigDecimal eadFinAmt) {
		this.eadFinAmt = eadFinAmt;
	}

	public BigDecimal getEadFinLcyAmt() {
		return this.eadFinLcyAmt;
	}

	public void setEadFinLcyAmt(BigDecimal eadFinLcyAmt) {
		this.eadFinLcyAmt = eadFinLcyAmt;
	}

	public BigDecimal getEadFinalAmt() {
		return this.eadFinalAmt;
	}

	public void setEadFinalAmt(BigDecimal eadFinalAmt) {
		this.eadFinalAmt = eadFinalAmt;
	}

	public BigDecimal getEadFinalLcyAmt() {
		return this.eadFinalLcyAmt;
	}

	public void setEadFinalLcyAmt(BigDecimal eadFinalLcyAmt) {
		this.eadFinalLcyAmt = eadFinalLcyAmt;
	}

	public BigDecimal getEadGuaAmt() {
		return this.eadGuaAmt;
	}

	public void setEadGuaAmt(BigDecimal eadGuaAmt) {
		this.eadGuaAmt = eadGuaAmt;
	}

	public BigDecimal getEadGuaLcyAmt() {
		return this.eadGuaLcyAmt;
	}

	public void setEadGuaLcyAmt(BigDecimal eadGuaLcyAmt) {
		this.eadGuaLcyAmt = eadGuaLcyAmt;
	}

	public BigDecimal getEadLcyAmt() {
		return this.eadLcyAmt;
	}

	public void setEadLcyAmt(BigDecimal eadLcyAmt) {
		this.eadLcyAmt = eadLcyAmt;
	}

	public BigDecimal getEadNetAmt() {
		return this.eadNetAmt;
	}

	public void setEadNetAmt(BigDecimal eadNetAmt) {
		this.eadNetAmt = eadNetAmt;
	}

	public BigDecimal getEadNetLcyAmt() {
		return this.eadNetLcyAmt;
	}

	public void setEadNetLcyAmt(BigDecimal eadNetLcyAmt) {
		this.eadNetLcyAmt = eadNetLcyAmt;
	}

	public BigDecimal getEadOthAmt() {
		return this.eadOthAmt;
	}

	public void setEadOthAmt(BigDecimal eadOthAmt) {
		this.eadOthAmt = eadOthAmt;
	}

	public BigDecimal getEadOthLcyAmt() {
		return this.eadOthLcyAmt;
	}

	public void setEadOthLcyAmt(BigDecimal eadOthLcyAmt) {
		this.eadOthLcyAmt = eadOthLcyAmt;
	}

	public BigDecimal getEadProAmt() {
		return this.eadProAmt;
	}

	public void setEadProAmt(BigDecimal eadProAmt) {
		this.eadProAmt = eadProAmt;
	}

	public BigDecimal getEadProLcyAmt() {
		return this.eadProLcyAmt;
	}

	public void setEadProLcyAmt(BigDecimal eadProLcyAmt) {
		this.eadProLcyAmt = eadProLcyAmt;
	}

	public BigDecimal getEadRecAmt() {
		return this.eadRecAmt;
	}

	public void setEadRecAmt(BigDecimal eadRecAmt) {
		this.eadRecAmt = eadRecAmt;
	}

	public BigDecimal getEadRecLcyAmt() {
		return this.eadRecLcyAmt;
	}

	public void setEadRecLcyAmt(BigDecimal eadRecLcyAmt) {
		this.eadRecLcyAmt = eadRecLcyAmt;
	}

	public String getEraEntityType() {
		return this.eraEntityType;
	}

	public void setEraEntityType(String eraEntityType) {
		this.eraEntityType = eraEntityType;
	}

	public String getEraNplCode() {
		return this.eraNplCode;
	}

	public void setEraNplCode(String eraNplCode) {
		this.eraNplCode = eraNplCode;
	}

	public String getEraProductCategoryFinal() {
		return this.eraProductCategoryFinal;
	}

	public void setEraProductCategoryFinal(String eraProductCategoryFinal) {
		this.eraProductCategoryFinal = eraProductCategoryFinal;
	}

	public String getEraProductCategoryOrg() {
		return this.eraProductCategoryOrg;
	}

	public void setEraProductCategoryOrg(String eraProductCategoryOrg) {
		this.eraProductCategoryOrg = eraProductCategoryOrg;
	}

	public String getEraProductTypeFinal() {
		return this.eraProductTypeFinal;
	}

	public void setEraProductTypeFinal(String eraProductTypeFinal) {
		this.eraProductTypeFinal = eraProductTypeFinal;
	}

	public String getEraProductTypeOrg() {
		return this.eraProductTypeOrg;
	}

	public void setEraProductTypeOrg(String eraProductTypeOrg) {
		this.eraProductTypeOrg = eraProductTypeOrg;
	}

	public BigDecimal getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getExposureBookCode() {
		return this.exposureBookCode;
	}

	public void setExposureBookCode(String exposureBookCode) {
		this.exposureBookCode = exposureBookCode;
	}

	public String getExposureSubTypeCode() {
		return this.exposureSubTypeCode;
	}

	public void setExposureSubTypeCode(String exposureSubTypeCode) {
		this.exposureSubTypeCode = exposureSubTypeCode;
	}

	public String getExposureTypeCode() {
		return this.exposureTypeCode;
	}

	public void setExposureTypeCode(String exposureTypeCode) {
		this.exposureTypeCode = exposureTypeCode;
	}

	public String getFCompleted() {
		return this.fCompleted;
	}

	public void setFCompleted(String fCompleted) {
		this.fCompleted = fCompleted;
	}

	public String getFIndependantValuer() {
		return this.fIndependantValuer;
	}

	public void setFIndependantValuer(String fIndependantValuer) {
		this.fIndependantValuer = fIndependantValuer;
	}

	public String getFLegallyEnforce() {
		return this.fLegallyEnforce;
	}

	public void setFLegallyEnforce(String fLegallyEnforce) {
		this.fLegallyEnforce = fLegallyEnforce;
	}

	public String getFRepaymentProperty() {
		return this.fRepaymentProperty;
	}

	public void setFRepaymentProperty(String fRepaymentProperty) {
		this.fRepaymentProperty = fRepaymentProperty;
	}

	public String getFacilityNbr() {
		return this.facilityNbr;
	}

	public void setFacilityNbr(String facilityNbr) {
		this.facilityNbr = facilityNbr;
	}

	public String getGuaRefNbr() {
		return this.guaRefNbr;
	}

	public void setGuaRefNbr(String guaRefNbr) {
		this.guaRefNbr = guaRefNbr;
	}

	public String getInstrumentNbr() {
		return this.instrumentNbr;
	}

	public void setInstrumentNbr(String instrumentNbr) {
		this.instrumentNbr = instrumentNbr;
	}

	public String getInterCompany() {
		return this.interCompany;
	}

	public void setInterCompany(String interCompany) {
		this.interCompany = interCompany;
	}

	public String getIssuerNbr() {
		return this.issuerNbr;
	}

	public void setIssuerNbr(String issuerNbr) {
		this.issuerNbr = issuerNbr;
	}

	public BigDecimal getLoadJobNbr() {
		return this.loadJobNbr;
	}

	public void setLoadJobNbr(BigDecimal loadJobNbr) {
		this.loadJobNbr = loadJobNbr;
	}

	public BigDecimal getLtv() {
		return this.ltv;
	}

	public void setLtv(BigDecimal ltv) {
		this.ltv = ltv;
	}

	public BigDecimal getMaturityAdjustmentFactor() {
		return this.maturityAdjustmentFactor;
	}

	public void setMaturityAdjustmentFactor(BigDecimal maturityAdjustmentFactor) {
		this.maturityAdjustmentFactor = maturityAdjustmentFactor;
	}

	public Date getMaturityDate() {
		return this.maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getMeasurementNbr() {
		return this.measurementNbr;
	}

	public void setMeasurementNbr(String measurementNbr) {
		this.measurementNbr = measurementNbr;
	}

	public String getOnOff() {
		return this.onOff;
	}

	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}

	public Double getOriginalMaturity() {
		return this.originalMaturity;
	}

	public void setOriginalMaturity(Double originalMaturity) {
		this.originalMaturity = originalMaturity;
	}

	public BigDecimal getOutstandingAmt() {
		return this.outstandingAmt;
	}

	public void setOutstandingAmt(BigDecimal outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}

	public BigDecimal getOutstandingLcyAmt() {
		return this.outstandingLcyAmt;
	}

	public void setOutstandingLcyAmt(BigDecimal outstandingLcyAmt) {
		this.outstandingLcyAmt = outstandingLcyAmt;
	}

	public String getPerformingStatus() {
		return this.performingStatus;
	}

	public void setPerformingStatus(String performingStatus) {
		this.performingStatus = performingStatus;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public BigDecimal getRegCap() {
		return this.regCap;
	}

	public void setRegCap(BigDecimal regCap) {
		this.regCap = regCap;
	}

	public BigDecimal getRegCapBeforeCrm() {
		return this.regCapBeforeCrm;
	}

	public void setRegCapBeforeCrm(BigDecimal regCapBeforeCrm) {
		this.regCapBeforeCrm = regCapBeforeCrm;
	}

	public Double getResidualMaturity() {
		return this.residualMaturity;
	}

	public void setResidualMaturity(Double residualMaturity) {
		this.residualMaturity = residualMaturity;
	}

	public String getRiskBucket() {
		return this.riskBucket;
	}

	public void setRiskBucket(String riskBucket) {
		this.riskBucket = riskBucket;
	}

	public String getRiskType() {
		return this.riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public BigDecimal getRiskWeightFinal() {
		return this.riskWeightFinal;
	}

	public void setRiskWeightFinal(BigDecimal riskWeightFinal) {
		this.riskWeightFinal = riskWeightFinal;
	}

	public BigDecimal getRiskWeightOrg() {
		return this.riskWeightOrg;
	}

	public void setRiskWeightOrg(BigDecimal riskWeightOrg) {
		this.riskWeightOrg = riskWeightOrg;
	}

	public BigDecimal getRwaAmt() {
		return this.rwaAmt;
	}

	public void setRwaAmt(BigDecimal rwaAmt) {
		this.rwaAmt = rwaAmt;
	}

	public BigDecimal getRwaBeforeCrmAmt() {
		return this.rwaBeforeCrmAmt;
	}

	public void setRwaBeforeCrmAmt(BigDecimal rwaBeforeCrmAmt) {
		this.rwaBeforeCrmAmt = rwaBeforeCrmAmt;
	}

	public String getRwaTreatmentCode() {
		return this.rwaTreatmentCode;
	}

	public void setRwaTreatmentCode(String rwaTreatmentCode) {
		this.rwaTreatmentCode = rwaTreatmentCode;
	}

	public BigDecimal getSalesAmt() {
		return this.salesAmt;
	}

	public void setSalesAmt(BigDecimal salesAmt) {
		this.salesAmt = salesAmt;
	}

	public BigDecimal getSalesLcyAmt() {
		return this.salesLcyAmt;
	}

	public void setSalesLcyAmt(BigDecimal salesLcyAmt) {
		this.salesLcyAmt = salesLcyAmt;
	}

	public String getScenarioId() {
		return this.scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public Date getSnapshotDate() {
		return this.snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public BigDecimal getSpCvg() {
		return this.spCvg;
	}

	public void setSpCvg(BigDecimal spCvg) {
		this.spCvg = spCvg;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTradingFlag() {
		return this.tradingFlag;
	}

	public void setTradingFlag(String tradingFlag) {
		this.tradingFlag = tradingFlag;
	}

	public String getUnconditionallyCancelable() {
		return this.unconditionallyCancelable;
	}

	public void setUnconditionallyCancelable(String unconditionallyCancelable) {
		this.unconditionallyCancelable = unconditionallyCancelable;
	}

	public String getUnderlying() {
		return this.underlying;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public String getWholesaleRetailInd() {
		return this.wholesaleRetailInd;
	}

	public void setWholesaleRetailInd(String wholesaleRetailInd) {
		this.wholesaleRetailInd = wholesaleRetailInd;
	}
	
}