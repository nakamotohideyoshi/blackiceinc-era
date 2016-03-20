package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCrmEligibilityDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCrmEligibility cfgCrmEligibility) {
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

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
