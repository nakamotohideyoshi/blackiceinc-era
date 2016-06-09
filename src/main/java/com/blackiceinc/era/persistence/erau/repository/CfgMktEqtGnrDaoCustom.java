package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtGnr;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktEqtGnrDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktEqtGnr cfgMktEqtGnr = (CfgMktEqtGnr) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_EQT_GNR").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
