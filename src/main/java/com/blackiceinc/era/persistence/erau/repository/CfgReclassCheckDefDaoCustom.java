package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgReclassCheckDefDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgReclassCheckDef cfgReclassCheckDef) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_RECLASS_CHECK_DEF " +
                "(" +
                "CHECK_DEF_NO, " +
                "DESCRIPTION, " +
                "CHECK_TYPE, " +
                "OPERATOR, " +
                "THRESHOLD, " +
                "CURRENCY" +
                ") " +
                "VALUES " +
                "(" +
                ":checkDefNo, " +
                ":description, " +
                ":checkType, " +
                ":operator, " +
                ":threshold, " +
                ":currency" +
                ")")
                .setParameter("checkDefNo",
                        cfgReclassCheckDef.getCheckDefNo(), new StringType())
                .setParameter("description",
                        cfgReclassCheckDef.getDescription(), new StringType())
                .setParameter("checkType",
                        cfgReclassCheckDef.getCheckType(), new StringType())
                .setParameter("operator",
                        cfgReclassCheckDef.getOperator(), new DoubleType())
                .setParameter("threshold",
                        cfgReclassCheckDef.getThreshold(), new DoubleType())
                .setParameter("currency",
                        cfgReclassCheckDef.getCurrency(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
