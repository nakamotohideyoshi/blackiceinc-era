package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgRating;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgRatingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgRating cfgRating = (CfgRating) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT " +
                "INTO CFG_RATING " +
                "(" +
                "AGENCY_CODE, " +
                "RATING, " +
                "QUALIFYING, " +
                "RISK_BUCKET " +
                ") " +
                "VALUES " +
                "(" +
                ":agencyCode, " +
                ":rating, " +
                ":qualifying, " +
                ":riskBucket " +
                ")")
                .setParameter("agencyCode",
                        cfgRating.getAgencyCode(), new StringType())
                .setParameter("rating",
                        cfgRating.getRating(), new StringType())
                .setParameter("qualifying",
                        cfgRating.getQualifying(), new StringType())
                .setParameter("riskBucket",
                        cfgRating.getRiskBucket(), new LongType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_RATING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }
}
