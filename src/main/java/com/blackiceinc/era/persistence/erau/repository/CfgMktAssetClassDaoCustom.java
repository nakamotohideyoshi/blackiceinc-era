package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClass;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktAssetClassDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktAssetClass cfgMktAssetClass) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_ASSET_CLASS " +
                "(" +
                "MKT_ASSET_CLASS, " +
                "MKT_ASSET_CLASS_DESC" +
                ") " +
                "VALUES " +
                "(" +
                ":mktAssetClass, " +
                ":mktAssetClassDesc" +
                ")")
                .setParameter("mktAssetClass",
                        cfgMktAssetClass.getMktAssetClass(), new StringType())
                .setParameter("mktAssetClassDesc",
                        cfgMktAssetClass.getMktAssetClassDesc(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
