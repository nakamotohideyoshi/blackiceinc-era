package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCreditMeasure;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCreditMeasureDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCreditMeasure cfgCreditMeasure = (CfgCreditMeasure) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_CREDIT_MEASURE " +
                "(" +
                "CREDIT_MEASURE, " +
                "CREDIT_MEASURE_DESC" +
                ") " +
                "VALUES " +
                "(" +
                ":creditMeasure, " +
                ":creditMeasureDesc" +
                ")")
                .setParameter("creditMeasure",
                        cfgCreditMeasure.getCreditMeasure(), new StringType())
                .setParameter("creditMeasureDesc",
                        cfgCreditMeasure.getCreditMeasureDesc(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CREDIT_MEASURE").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
