package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimension;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyDimensionDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCompanyDimension cfgCompanyDimension) {
        this.em.createNativeQuery("INSERT INTO CFG_COMPANY_DIMENSION " +
                "(COMPANY_CODE, FINANCIAL_BOOK) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgCompanyDimension.getCompanyCode())
                .setParameter(2, cfgCompanyDimension.getFinancialBook())

                .executeUpdate();
    }

}
