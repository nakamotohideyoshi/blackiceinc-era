package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtGnr;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktEqtGnrDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktEqtGnr cfgMktEqtGnr) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_EQT_GNR " +
                "(" +
                "MKT_PRODUCT_TYPE, " +
                "UNDERLYING, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":mktProductType, " +
                ":underlying, " +
                ":riskWeight" +
                ")")
                .setParameter("mktProductType",
                        cfgMktEqtGnr.getMktProductType(), new StringType())
                .setParameter("underlying",
                        cfgMktEqtGnr.getUnderlying(), new StringType())
                .setParameter("riskWeight",
                        cfgMktEqtGnr.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
