package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOpsFormula;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsFormulaDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgOpsFormula cfgOpsFormula = (CfgOpsFormula) cfgObject;
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

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_OPS_FORMULA").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
