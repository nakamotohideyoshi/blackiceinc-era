package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgNonPerformingMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgNonPerformingMapping cfgNonPerformingMapping = (CfgNonPerformingMapping) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_NON_PERFORMING_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
