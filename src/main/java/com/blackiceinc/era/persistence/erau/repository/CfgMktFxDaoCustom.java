package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktFx;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktFxDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktFx cfgMktFx) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_FX " +
                "(" +
                "MKT_PRODUCT_TYPE, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":mktProductType, " +
                ":riskWeight" +
                ")")
                .setParameter("mktProductType",
                        cfgMktFx.getMktProductType(), new StringType())
                .setParameter("riskWeight",
                        cfgMktFx.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
