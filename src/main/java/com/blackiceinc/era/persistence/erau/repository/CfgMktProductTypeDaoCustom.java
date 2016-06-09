package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktProductType;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktProductTypeDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktProductType cfgMktProductType = (CfgMktProductType) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_PRODUCT_TYPE " +
                "(" +
                "MKT_PRODUCT_TYPE, " +
                "MKT_PRODUCT_DESC, " +
                "MKT_PRODUCT_CATEGORY" +
                ") " +
                "VALUES " +
                "(" +
                ":mktProductType, " +
                ":mktProductDesc, " +
                ":mktProductCategory" +
                ")")
                .setParameter("mktProductType",
                        cfgMktProductType.getMktProductType(), new StringType())
                .setParameter("mktProductDesc",
                        cfgMktProductType.getMktProductDesc(), new StringType())
                .setParameter("mktProductCategory",
                        cfgMktProductType.getMktProductCategory(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_PRODUCT_TYPE").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
