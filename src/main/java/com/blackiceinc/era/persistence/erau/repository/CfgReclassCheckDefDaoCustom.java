package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgReclassCheckDefDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgReclassCheckDef cfgReclassCheckDef = (CfgReclassCheckDef) cfgObject;
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
                        cfgReclassCheckDef.getOperator(), new StringType())
                .setParameter("threshold",
                        cfgReclassCheckDef.getThreshold(), new DoubleType())
                .setParameter("currency",
                        cfgReclassCheckDef.getCurrency(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_RECLASS_CHECK_DEF").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
