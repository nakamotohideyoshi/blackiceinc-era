package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAgencyEligibility;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAgencyEligibilityDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgAgencyEligibility cfgAgencyEligibility = (CfgAgencyEligibility) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_AGENCY_ELIGIBILITY " +
                "(AGENCY_CODE, AGENCY_DESC, AGENCY_TYPE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgAgencyEligibility.getAgencyCode())
                .setParameter(2, cfgAgencyEligibility.getAgencyDesc())
                .setParameter(3, cfgAgencyEligibility.getAgencyType())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_AGENCY_ELIGIBILITY").executeUpdate();
    }

}
