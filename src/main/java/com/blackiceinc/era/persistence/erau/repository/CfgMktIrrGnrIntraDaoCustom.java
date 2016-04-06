package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrIntraDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktIrrGnrIntra cfgMktIrrGnrIntra) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_IRR_GNR_INTRA " +
                "(" +
                "CODE, " +
                "ZONE_CODE, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":code, " +
                ":zoneCode, " +
                ":riskWeight" +
                ")")
                .setParameter("code",
                        cfgMktIrrGnrIntra.getCode(), new StringType())
                .setParameter("zoneCode",
                        cfgMktIrrGnrIntra.getZoneCode(), new StringType())
                .setParameter("riskWeight",
                        cfgMktIrrGnrIntra.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
