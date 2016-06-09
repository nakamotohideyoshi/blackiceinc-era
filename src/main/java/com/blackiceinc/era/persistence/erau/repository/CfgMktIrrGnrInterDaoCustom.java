package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrInterDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktIrrGnrInter cfgMktIrrGnrInter = (CfgMktIrrGnrInter) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_IRR_GNR_INTER " +
                "(" +
                "CODE, " +
                "ZONE_CODE_1, " +
                "ZONE_CODE_2, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":code, " +
                ":zoneCode1, " +
                ":zoneCode2, " +
                ":riskWeight" +
                ")")
                .setParameter("code",
                        cfgMktIrrGnrInter.getCode(), new StringType())
                .setParameter("zoneCode1",
                        cfgMktIrrGnrInter.getZoneCode1(), new StringType())
                .setParameter("zoneCode2",
                        cfgMktIrrGnrInter.getZoneCode2(), new StringType())
                .setParameter("riskWeight",
                        cfgMktIrrGnrInter.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_IRR_GNR_INTER").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
