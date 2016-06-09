package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimension;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyDimensionDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCompanyDimension cfgCompanyDimension = (CfgCompanyDimension) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_COMPANY_DIMENSION " +
                "(COMPANY_CODE, FINANCIAL_BOOK) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgCompanyDimension.getCompanyCode())
                .setParameter(2, cfgCompanyDimension.getFinancialBook())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_COMPANY_DIMENSION").executeUpdate();
    }

}
