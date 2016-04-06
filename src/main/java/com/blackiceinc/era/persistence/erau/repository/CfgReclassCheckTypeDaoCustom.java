package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckType;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgReclassCheckTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgReclassCheckType cfgReclassCheckType) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_RECLASS_CHECK_TYPE " +
                "(" +
                "CHECK_TYPE, " +
                "CHECK_DESCRIPTION, " +
                "WHERE_CLAUSE, " +
                "CONSO_FIELD, " +
                "AMT_FIELD" +
                ") " +
                "VALUES " +
                "(" +
                ":checkType, " +
                ":checkDescription, " +
                ":whereClause, " +
                ":consoField, " +
                ":amtField" +
                ")")
                .setParameter("checkType",
                        cfgReclassCheckType.getCheckType(), new StringType())
                .setParameter("checkDescription",
                        cfgReclassCheckType.getCheckDescription(), new StringType())
                .setParameter("whereClause",
                        cfgReclassCheckType.getWhereClause(), new StringType())
                .setParameter("consoField",
                        cfgReclassCheckType.getConsoField(), new StringType())
                .setParameter("amtField",
                        cfgReclassCheckType.getAmtField(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
