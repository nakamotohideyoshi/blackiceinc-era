package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCrmHaircutDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCrmHaircut cfgCrmHaircut = (CfgCrmHaircut) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_CRM_HAIRCUT " +
                "(" +
                "ERA_COL_TYPE, " +
                "ERA_ENTITY_TYPE, " +
                "RISK_BUCKET, " +
                "MIN_RESIDUAL_MATURITY, " +
                "MAX_RESIDUAL_MATURITTY, " +
                "HAIRCUT, " +
                "SEQ" +
                ") " +
                "VALUES " +
                "(" +
                ":eraColType, " +
                ":eraEntityType, " +
                ":riskBucket, " +
                ":minResidualMaturity, " +
                ":maxResidualMaturity, " +
                ":haircut, " +
                ":seq" +
                ")")
                .setParameter("eraColType",
                        cfgCrmHaircut.getEraColType(), new StringType())
                .setParameter("eraEntityType",
                        cfgCrmHaircut.getEraEntityType(), new StringType())
                .setParameter("riskBucket",
                        cfgCrmHaircut.getRiskBucket(), new StringType())
                .setParameter("minResidualMaturity",
                        cfgCrmHaircut.getMinResidualMaturity(), new StringType())
                .setParameter("maxResidualMaturity",
                        cfgCrmHaircut.getMaxResidualMaturity(), new StringType())
                .setParameter("haircut",
                        cfgCrmHaircut.getHaircut(), new DoubleType())
                .setParameter("seq",
                        cfgCrmHaircut.getSeq(), new LongType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CRM_HAIRCUT").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
