package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_MKT_IRR_SPC_RISK")
public class CfgMktIrrSpcRisk extends CfgObject {

    @Id
    @Column(name = "MKT_ASSET_CLASS")
    private String mktAssetClass;

    @Column(name = "ISSUE_RISK_BUCKET")
    private String issueRiskBucket;

    @Column(name = "ISSUER_RISK_BUCKET")
    private String issuerRiskBucket;

    @Column(name = "RESIDUAL_MATURITY_START")
    private String residualMaturityStart;

    @Column(name = "RESIDUAL_MATURITY_END")
    private String residualMaturityEnd;

    @Column(name = "INSTRUMENT_GROUP")
    private String instrumentGroup;

    @Column(name = "RISK_WEIGHT")
    private Double riskWeight;

    @Column(name = "SEQ")
    private Long seq;

    public CfgMktIrrSpcRisk(String mktAssetClass, String issueRiskBucket, String issuerRiskBucket,
                            String residualMaturityStart, String residualMaturityEnd, String instrumentGroup,
                            Double riskWeight, Long seq) {
        this.mktAssetClass = mktAssetClass;
        this.issueRiskBucket = issueRiskBucket;
        this.issuerRiskBucket = issuerRiskBucket;
        this.residualMaturityStart = residualMaturityStart;
        this.residualMaturityEnd = residualMaturityEnd;
        this.instrumentGroup = instrumentGroup;
        this.riskWeight = riskWeight;
        this.seq = seq;
    }

    public CfgMktIrrSpcRisk() {
        // default constructor
    }

    public String getMktAssetClass() {
        return mktAssetClass;
    }

    public void setMktAssetClass(String mktAssetClass) {
        this.mktAssetClass = mktAssetClass;
    }

    public String getIssueRiskBucket() {
        return issueRiskBucket;
    }

    public void setIssueRiskBucket(String issueRiskBucket) {
        this.issueRiskBucket = issueRiskBucket;
    }

    public String getIssuerRiskBucket() {
        return issuerRiskBucket;
    }

    public void setIssuerRiskBucket(String issuerRiskBucket) {
        this.issuerRiskBucket = issuerRiskBucket;
    }

    public String getResidualMaturityStart() {
        return residualMaturityStart;
    }

    public void setResidualMaturityStart(String residualMaturityStart) {
        this.residualMaturityStart = residualMaturityStart;
    }

    public String getResidualMaturityEnd() {
        return residualMaturityEnd;
    }

    public void setResidualMaturityEnd(String residualMaturityEnd) {
        this.residualMaturityEnd = residualMaturityEnd;
    }

    public String getInstrumentGroup() {
        return instrumentGroup;
    }

    public void setInstrumentGroup(String instrumentGroup) {
        this.instrumentGroup = instrumentGroup;
    }

    public Double getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Double riskWeight) {
        this.riskWeight = riskWeight;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
