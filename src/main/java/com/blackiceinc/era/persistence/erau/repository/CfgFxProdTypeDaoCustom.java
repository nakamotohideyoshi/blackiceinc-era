package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgFxProdType;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgFxProdTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgFxProdType cfgFxProdType) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_FX_PROD_TYPE " +
                "(" +
                "FX_PROD_TYPE, " +
                "FX_PROD_TYPE_DESC " +
                ") " +
                "VALUES " +
                "(" +
                ":fxProdType, " +
                ":fxProdTypeDesc " +
                ")")
                .setParameter("fxProdType",
                        cfgFxProdType.getFxProdType(), new StringType())
                .setParameter("fxProdTypeDesc",
                        cfgFxProdType.getFxProdTypeDesc(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
