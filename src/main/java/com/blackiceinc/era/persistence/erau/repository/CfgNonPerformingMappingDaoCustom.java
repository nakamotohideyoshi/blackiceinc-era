package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgNonPerformingMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgNonPerformingMapping cfgNonPerformingMapping) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_NON_PERFORMING_MAPPING " +
                "(" +
                "ERA_NPL_CODE, " +
                "PERFORMING_STATUS" +
                ") " +
                "VALUES " +
                "(" +
                ":eraNplCode, " +
                ":performingStatus" +
                ")")
                .setParameter("eraNplCode",
                        cfgNonPerformingMapping.getEraNplCode(), new StringType())
                .setParameter("performingStatus",
                        cfgNonPerformingMapping.getPerformingStatus(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
