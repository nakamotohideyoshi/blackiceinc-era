package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgFxProdMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgFxProdMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgFxProdMapping cfgFxProdMapping = (CfgFxProdMapping) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_FX_PROD_MAPPING " +
                "(" +
                "GL_CODE, " +
                "FX_PROD_TYPE, " +
                "GL_CODE_DESC " +
                ") " +
                "VALUES " +
                "(" +
                ":glCode, " +
                ":fxProdType, " +
                ":glCodeDesc " +
                ")")
                .setParameter("glCode",
                        cfgFxProdMapping.getGlCode(), new StringType())
                .setParameter("fxProdType",
                        cfgFxProdMapping.getFxProdType(), new StringType())
                .setParameter("glCodeDesc",
                        cfgFxProdMapping.getGlCodeDesc(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_FX_PROD_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
