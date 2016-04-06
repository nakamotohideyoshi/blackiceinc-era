package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgOpsFormula;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsFormulaDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgOpsFormula cfgOpsFormula) {
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_OPS_FORMULA " +
                "(" +
                "BASIC_INDICATOR, " +
                "FORMULA" +
                ") " +
                "VALUES " +
                "(" +
                ":basicIndicator, " +
                ":formula" +
                ")")
                .setParameter("basicIndicator",
                        cfgOpsFormula.getBasicIndicator(), new StringType())
                .setParameter("formula",
                        cfgOpsFormula.getFormula(), new StringType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
