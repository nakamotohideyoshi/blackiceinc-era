package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOpsRisk;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsRiskDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgOpsRisk cfgOpsRisk = (CfgOpsRisk) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_OPS_RISK").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
