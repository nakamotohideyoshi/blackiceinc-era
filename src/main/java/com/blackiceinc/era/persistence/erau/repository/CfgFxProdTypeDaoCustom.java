package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgFxProdType;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgFxProdTypeDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgFxProdType cfgFxProdType = (CfgFxProdType) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_FX_PROD_TYPE").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
