package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgEntityType;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgEntityTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgEntityType cfgEntityType) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_ENTITY_TYPE " +
                "(" +
                "ERA_ENTITY_TYPE, " +
                "ENTITY_DESC" +
                ") " +
                "VALUES " +
                "(" +
                ":eraEntityType, " +
                ":entityDesc" +
                ")")
                .setParameter("eraEntityType",
                        cfgEntityType.getEraEntityType(), new StringType())
                .setParameter("entityDesc",
                        cfgEntityType.getEntityDesc(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
