package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClass;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktAssetClassDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktAssetClass cfgMktAssetClass = (CfgMktAssetClass) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_ASSET_CLASS").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
