package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgOpsRisk;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsRiskDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgOpsRisk cfgOpsRisk) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_OPS_RISK " +
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
                        cfgOpsRisk.getCode(), new StringType())
                .setParameter("riskWeight",
                        cfgOpsRisk.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
