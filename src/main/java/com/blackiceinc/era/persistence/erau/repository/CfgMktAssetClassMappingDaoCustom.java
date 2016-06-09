package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClassMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktAssetClassMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktAssetClassMapping cfgMktAssetClassMapping = (CfgMktAssetClassMapping) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_ASSET_CLASS_MAPPING " +
                "(" +
                "MKT_ASSET_CLASS, " +
                "ERA_ENTITY_TYPE, " +
                "MKT_PRODUCT_TYPE" +
                ") " +
                "VALUES " +
                "(" +
                ":mktAssetClass, " +
                ":eraEntityType, " +
                ":mktProductType" +
                ")")
                .setParameter("mktAssetClass",
                        cfgMktAssetClassMapping.getMktAssetClass(), new StringType())
                .setParameter("eraEntityType",
                        cfgMktAssetClassMapping.getEraEntityType(), new StringType())
                .setParameter("mktProductType",
                        cfgMktAssetClassMapping.getMktProductType(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_ASSET_CLASS_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
