package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktComOth;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktComOthDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktComOth cfgMktComOth) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_COM_OTH " +
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
                        cfgMktComOth.getMktProductType(), new StringType())
                .setParameter("riskWeight",
                        cfgMktComOth.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
