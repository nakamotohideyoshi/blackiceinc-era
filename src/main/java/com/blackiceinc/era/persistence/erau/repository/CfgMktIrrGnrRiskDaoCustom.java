package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrGnrRiskDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgMktIrrGnrRisk cfgMktIrrGnrRisk) {
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
                "RISK_WEIGHT" +
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
                ":riskWeight" +
                ")")
                .setParameter("zoneCode",
                        cfgMktIrrGnrRisk.getZoneCode(), new StringType())
                .setParameter("bandCode",
                        cfgMktIrrGnrRisk.getBandCode(), new StringType())
                .setParameter("currency",
                        cfgMktIrrGnrRisk.getCurrency(), new StringType())
                .setParameter("couponRateStart",
                        cfgMktIrrGnrRisk.getCouponRateStart(), new LongType())
                .setParameter("couponRateEnd",
                        cfgMktIrrGnrRisk.getCouponRateEnd(), new LongType())
                .setParameter("maturityBandStart",
                        cfgMktIrrGnrRisk.getMaturityBandStart(), new LongType())
                .setParameter("maturityBandEnd",
                        cfgMktIrrGnrRisk.getMaturityBandEnd(), new LongType())
                .setParameter("riskWeight",
                        cfgMktIrrGnrRisk.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
