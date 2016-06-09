package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyDimensionConsolidationDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation = (CfgCompanyDimensionConsolidation) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_CMPNY_DIM_CONSOLIDATION " +
                "(COMPANY_CODE, ENTITY_CODE, CONSO_MODE, CONSO_PERCT) " +
                "VALUES (?1, ?2, ?3, ?4)")
                .setParameter(1, cfgCompanyDimensionConsolidation.getCompanyCode())
                .setParameter(2, cfgCompanyDimensionConsolidation.getEntityCode())
                .setParameter(3, cfgCompanyDimensionConsolidation.getConsoMode())
                .setParameter(4, cfgCompanyDimensionConsolidation.getConsoPerct())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CMPNY_DIM_CONSOLIDATION").executeUpdate();
    }

}
