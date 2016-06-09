package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrBandDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktIrrGnrBand cfgMktIrrGnrBand = (CfgMktIrrGnrBand) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_IRR_GNR_BAND").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
