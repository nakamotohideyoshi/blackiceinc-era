package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtSpc;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktEqtSpcDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktEqtSpc cfgMktEqtSpc = (CfgMktEqtSpc) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT " +
                "INTO CFG_MKT_EQT_SPC " +
                "(" +
                "MKT_PRODUCT_TYPE, " +
                "UNDERLYING, " +
                "DIVERSIFIED_EQUITY, " +
                "DIVERSIFIED_INDEX, " +
                "LIQUID_EQUITY, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":mktProductType, " +
                ":underlying, " +
                ":diversifiedEquity, " +
                ":diversifiedIndex, " +
                ":liquidEquity, " +
                ":riskWeight" +
                ")")
                .setParameter("mktProductType", cfgMktEqtSpc.getMktProductType(), new StringType())
                .setParameter("underlying", cfgMktEqtSpc.getUnderlying(), new StringType())
                .setParameter("diversifiedEquity", cfgMktEqtSpc.getDiversifiedEquity(), new StringType())
                .setParameter("diversifiedIndex", cfgMktEqtSpc.getDiversifiedIndex(), new StringType())
                .setParameter("liquidEquity", cfgMktEqtSpc.getLiquidEquity(), new StringType())
                .setParameter("riskWeight", cfgMktEqtSpc.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_EQT_SPC").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
