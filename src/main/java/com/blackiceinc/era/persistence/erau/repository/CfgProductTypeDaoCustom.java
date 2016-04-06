package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgProductType;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgProductTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgProductType cfgProductType) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_PRODUCT_TYPE " +
                "(" +
                "ERA_PRODUCT_TYPE, " +
                "ERA_PRODUCT_DESC, " +
                "ERA_PRODUCT_CATEGORY" +
                ") " +
                "VALUES " +
                "(" +
                ":eraProductType, " +
                ":eraProductDesc, " +
                ":eraProductCategory" +
                ")")
                .setParameter("eraProductType",
                        cfgProductType.getEraProductType(), new StringType())
                .setParameter("eraProductDesc",
                        cfgProductType.getEraProductDesc(), new StringType())
                .setParameter("eraProductCategory",
                        cfgProductType.getEraProductCategory(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
