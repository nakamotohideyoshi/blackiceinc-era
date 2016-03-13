package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgRiskWeightMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgRiskWeightMapping cfgRiskWeightMapping) {
        this.em.createNativeQuery("INSERT INTO CFG_RISK_WEIGHT_MAPPING " +
                "(ASSET_CLASS, ERA_NPL_CODE, YEAR_OF_ESTABLISHMENT, CREDIT_MEASURE_1, CREDIT_MEASURE_1_BEG, CREDIT_MEASURE_1_END, CREDIT_MEASURE_2, CREDIT_MEASURE_2_BEG, CREDIT_MEASURE_2_END, RISK_WEIGHT, SEQ) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)")
                .setParameter(1, cfgRiskWeightMapping.getAssetClass())
                .setParameter(2, cfgRiskWeightMapping.getEraNplCode())
                .setParameter(3, cfgRiskWeightMapping.getYearOfEstablishment())
                .setParameter(4, cfgRiskWeightMapping.getCreditMeasure1())
                .setParameter(5, cfgRiskWeightMapping.getCreditMeasure1Beg())
                .setParameter(6, cfgRiskWeightMapping.getCreditMeasure1End())
                .setParameter(7, cfgRiskWeightMapping.getCreditMeasure2())
                .setParameter(8, cfgRiskWeightMapping.getCreditMeasure2Beg())
                .setParameter(9, cfgRiskWeightMapping.getCreditMeasure2End())
                .setParameter(10, cfgRiskWeightMapping.getRiskWeight())
                .setParameter(11, cfgRiskWeightMapping.getSeq())

                .executeUpdate();
    }
}
