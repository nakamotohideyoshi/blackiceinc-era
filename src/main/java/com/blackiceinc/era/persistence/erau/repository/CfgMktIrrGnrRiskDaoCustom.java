package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrRiskDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktIrrGnrRisk cfgMktIrrGnrRisk = (CfgMktIrrGnrRisk) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_IRR_GNR_RISK " +
                "(" +
                "ZONE_CODE, " +
                "BAND_CODE, " +
                "CURRENCY, " +
                "COUPON_RATE_START, " +
                "COUPON_RATE_END, " +
                "MATURITY_BAND_START, " +
                "MATURITY_BAND_END, " +
                "RISK_WEIGHT," +
                "SEQ" +
                ") " +
                "VALUES " +
                "(" +
                ":zoneCode, " +
                ":bandCode, " +
                ":currency, " +
                ":couponRateStart, " +
                ":couponRateEnd, " +
                ":maturityBandStart, " +
                ":maturityBandEnd, " +
                ":riskWeight," +
                ":seq" +
                ")")
                .setParameter("zoneCode",
                        cfgMktIrrGnrRisk.getZoneCode(), new StringType())
                .setParameter("bandCode",
                        cfgMktIrrGnrRisk.getBandCode(), new StringType())
                .setParameter("currency",
                        cfgMktIrrGnrRisk.getCurrency(), new StringType())
                .setParameter("couponRateStart",
                        cfgMktIrrGnrRisk.getCouponRateStart(), new DoubleType())
                .setParameter("couponRateEnd",
                        cfgMktIrrGnrRisk.getCouponRateEnd(), new DoubleType())
                .setParameter("maturityBandStart",
                        cfgMktIrrGnrRisk.getMaturityBandStart(), new DoubleType())
                .setParameter("maturityBandEnd",
                        cfgMktIrrGnrRisk.getMaturityBandEnd(), new DoubleType())
                .setParameter("riskWeight",
                        cfgMktIrrGnrRisk.getRiskWeight(), new DoubleType())
                .setParameter("seq",
                        cfgMktIrrGnrRisk.getSeq(), new LongType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_IRR_GNR_RISK").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
