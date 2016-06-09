package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCrmEligibilityDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCrmEligibility cfgCrmEligibility = (CfgCrmEligibility) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_CRM_ELIGIBILITY " +
                "(" +
                "ERA_ENTITY_TYPE, " +
                "ERA_PRODUCT_TYPE, " +
                "RISK_BUCKET, " +
                "RISK_WEIGHT, " +
                "ELIGIBILITY, " +
                "SEQ" +
                ") " +
                "VALUES " +
                "(" +
                ":eraEntityType, " +
                ":eraProductType, " +
                ":riskBucket, " +
                ":riskWeight, " +
                ":eligibility, " +
                ":seq" +
                ")")
                .setParameter("eraEntityType",
                        cfgCrmEligibility.getEraEntityType(), new StringType())
                .setParameter("eraProductType",
                        cfgCrmEligibility.getEraProductType(), new StringType())
                .setParameter("riskBucket",
                        cfgCrmEligibility.getRiskBucket(), new StringType())
                .setParameter("riskWeight",
                        cfgCrmEligibility.getRiskWeight(), new StringType())
                .setParameter("eligibility",
                        cfgCrmEligibility.getEligibility(), new StringType())
                .setParameter("seq",
                        cfgCrmEligibility.getSeq(), new LongType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CRM_ELIGIBILITY").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
