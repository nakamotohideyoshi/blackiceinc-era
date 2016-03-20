package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgEntityTypeMapping;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgEntityTypeMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgEntityTypeMapping cfgEntityTypeMapping) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_ENTITY_TYPE_MAPPING " +
                "(" +
                "ERA_ENTITY_TYPE, " +
                "CUSTOMER_TYPE, " +
                "CUSTOMER_SUB_TYPE" +
                ") " +
                "VALUES " +
                "(" +
                ":eraEntityType, " +
                ":customerType, " +
                ":customerSubType" +
                ")")
                .setParameter("eraEntityType",
                        cfgEntityTypeMapping.getEraEntityType(), new StringType())
                .setParameter("customerType",
                        cfgEntityTypeMapping.getCustomerType(), new StringType())
                .setParameter("customerSubType",
                        cfgEntityTypeMapping.getCustomerSubType(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
