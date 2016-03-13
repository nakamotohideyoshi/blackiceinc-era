package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAgencyEligibility;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAgencyEligibilityDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgAgencyEligibility cfgAgencyEligibility) {
        this.em.createNativeQuery("INSERT INTO CFG_AGENCY_ELIGIBILITY " +
                "(AGENCY_CODE, AGENCY_DESC, AGENCY_TYPE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgAgencyEligibility.getAgencyCode())
                .setParameter(2, cfgAgencyEligibility.getAgencyDesc())
                .setParameter(3, cfgAgencyEligibility.getAgencyType())

                .executeUpdate();
    }

}
