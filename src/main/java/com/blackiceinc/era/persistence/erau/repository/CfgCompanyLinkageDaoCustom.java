package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyLinkage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCompanyLinkageDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCompanyLinkage cfgCompanyLinkage) {
        this.em.createNativeQuery("INSERT INTO CFG_COMPANY_LINKAGE " +
                "(CHILD_CODE, MOTHER_CODE, LINK_WEIGHT) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCompanyLinkage.getChildCode())
                .setParameter(2, cfgCompanyLinkage.getMotherCode())
                .setParameter(3, cfgCompanyLinkage.getLinkWeight())

                .executeUpdate();
    }

}
