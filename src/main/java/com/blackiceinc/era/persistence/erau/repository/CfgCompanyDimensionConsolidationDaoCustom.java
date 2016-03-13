package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyDimensionConsolidationDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation) {
        this.em.createNativeQuery("INSERT INTO CFG_CMPNY_DIM_CONSOLIDATION " +
                "(COMPANY_CODE, ENTITY_CODE, CONSO_MODE, CONSO_PERCT) " +
                "VALUES (?1, ?2, ?3, ?4)")
                .setParameter(1, cfgCompanyDimensionConsolidation.getCompanyCode())
                .setParameter(2, cfgCompanyDimensionConsolidation.getEntityCode())
                .setParameter(3, cfgCompanyDimensionConsolidation.getConsoMode())
                .setParameter(4, cfgCompanyDimensionConsolidation.getConsoPerct())

                .executeUpdate();
    }

}
