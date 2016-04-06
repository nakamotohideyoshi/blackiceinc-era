package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrBandDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktIrrGnrBand cfgMktIrrGnrBand) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_IRR_GNR_BAND " +
                "(" +
                "CODE, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":code, " +
                ":riskWeight" +
                ")")
                .setParameter("code",
                        cfgMktIrrGnrBand.getCode(), new StringType())
                .setParameter("riskWeight",
                        cfgMktIrrGnrBand.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
