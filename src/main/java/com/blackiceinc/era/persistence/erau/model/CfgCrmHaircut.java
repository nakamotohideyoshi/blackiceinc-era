package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "CFG_CRM_HAIRCUT")
public class CfgCrmHaircut {

    @EmbeddedId
    private CfgCrmHaircutKey cfgCrmHaircutKey;

    @Column(name = "HAIRCUT")
    private Double haircut;

    @Column(name = "SEQ")
    private Long seq;

    public CfgCrmHaircut(String eraColType, String eraEntityType, String riskBucket,
                         String minResidualMaturity, String maxResidualMaturity,
                         Double haircut, Long seq) {
        this.cfgCrmHaircutKey = new CfgCrmHaircutKey(eraColType, eraEntityType, riskBucket, minResidualMaturity, maxResidualMaturity);
        this.haircut = haircut;
        this.seq = seq;
    }

    public CfgCrmHaircut() {
        this.cfgCrmHaircutKey = new CfgCrmHaircutKey();
    }

    public String getEraColType() {
        return this.cfgCrmHaircutKey.getEraColType();
    }

    public void setEraColType(String eraColType) {
        this.cfgCrmHaircutKey.setEraColType(eraColType);
    }

    public String getEraEntityType() {
        return this.cfgCrmHaircutKey.getEraEntityType();
    }

    public void setEraEntityType(String eraEntityType) {
        this.cfgCrmHaircutKey.setEraEntityType(eraEntityType);
    }

    public String getRiskBucket() {
        return this.cfgCrmHaircutKey.getRiskBucket();
    }

    public void setRiskBucket(String riskBucket) {
        this.cfgCrmHaircutKey.setRiskBucket(riskBucket);
    }

    public String getMinResidualMaturity() {
        return this.cfgCrmHaircutKey.getMinResidualMaturity();
    }

    public void setMinResidualMaturity(String minResidualMaturity) {
        this.cfgCrmHaircutKey.setMinResidualMaturity(minResidualMaturity);
    }

    public String getMaxResidualMaturity() {
        return this.cfgCrmHaircutKey.getMaxResidualMaturity();
    }

    public void setMaxResidualMaturity(String maxResidualMaturity) {
        this.cfgCrmHaircutKey.setMaxResidualMaturity(maxResidualMaturity);
    }

    public Double getHaircut() {
        return haircut;
    }

    public void setHaircut(Double haircut) {
        this.haircut = haircut;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CfgCrmHaircut that = (CfgCrmHaircut) o;

        return new EqualsBuilder()
                .append(cfgCrmHaircutKey, that.cfgCrmHaircutKey)
                .append(haircut, that.haircut)
                .append(seq, that.seq)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(cfgCrmHaircutKey)
                .append(haircut)
                .append(seq)
                .toHashCode();
    }
}
