package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsProductTypeMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgOpsProductTypeMapping cfgOpsProductTypeMapping = (CfgOpsProductTypeMapping) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT " +
                "INTO CFG_OPS_PRODUCT_TYPE_MAPPING " +
                "(" +
                "OPS_PRODUCT_TYPE, " +
                "OPS_GL_CODE, " +
                "OPS_VIB_CODE, " +
                "DESCRIPTION " +
                ") " +
                "VALUES " +
                "(" +
                ":opsProductType, " +
                ":opsGlCode, " +
                ":opsVibCode, " +
                ":description " +
                ")")
                .setParameter("opsProductType",
                        cfgOpsProductTypeMapping.getOpsProductType(), new StringType())
                .setParameter("opsGlCode",
                        cfgOpsProductTypeMapping.getOpsGlCode(), new StringType())
                .setParameter("opsVibCode",
                        cfgOpsProductTypeMapping.getOpsVibCode(), new StringType())
                .setParameter("description",
                        cfgOpsProductTypeMapping.getDescription(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_OPS_PRODUCT_TYPE_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
