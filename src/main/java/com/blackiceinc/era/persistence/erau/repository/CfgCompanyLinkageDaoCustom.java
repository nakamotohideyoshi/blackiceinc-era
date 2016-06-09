package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyLinkage;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyLinkageDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCompanyLinkage cfgCompanyLinkage = (CfgCompanyLinkage) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_COMPANY_LINKAGE " +
                "(CHILD_CODE, MOTHER_CODE, LINK_WEIGHT) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCompanyLinkage.getChildCode())
                .setParameter(2, cfgCompanyLinkage.getMotherCode())
                .setParameter(3, cfgCompanyLinkage.getLinkWeight())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_COMPANY_LINKAGE").executeUpdate();
    }

}
