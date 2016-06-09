package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgEntityType;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgEntityTypeDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgEntityType cfgEntityType = (CfgEntityType) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_ENTITY_TYPE").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
