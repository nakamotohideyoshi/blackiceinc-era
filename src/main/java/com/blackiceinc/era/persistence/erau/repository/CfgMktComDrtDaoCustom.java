package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktComDrt;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktComDrtDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktComDrt cfgMktComDrt = (CfgMktComDrt) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_COM_DRT " +
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
                        cfgMktComDrt.getMktProductType(), new StringType())
                .setParameter("riskWeight",
                        cfgMktComDrt.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_COM_DRT").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
