package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrSpcRisk;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgMktIrrSpcRiskDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgMktIrrSpcRisk cfgMktIrrSpcRisk = (CfgMktIrrSpcRisk) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_MKT_IRR_SPC_RISK " +
                "(" +
                "MKT_ASSET_CLASS, " +
                "ISSUE_RISK_BUCKET, " +
                "ISSUER_RISK_BUCKET, " +
                "RESIDUAL_MATURITY_START, " +
                "RESIDUAL_MATURITY_END, " +
                "INSTRUMENT_GROUP, " +
                "RISK_WEIGHT, " +
                "SEQ" +
                ") " +
                "VALUES " +
                "(" +
                ":mktAssetClass, " +
                ":issueRiskBucket, " +
                ":issuerRiskBucket, " +
                ":residualMaturityStart, " +
                ":residualMaturityEnd, " +
                ":instrumentGroup, " +
                ":riskWeight, " +
                ":seq" +
                ")")
                .setParameter("mktAssetClass",
                        cfgMktIrrSpcRisk.getMktAssetClass(), new StringType())
                .setParameter("issueRiskBucket",
                        cfgMktIrrSpcRisk.getIssueRiskBucket(), new StringType())
                .setParameter("issuerRiskBucket",
                        cfgMktIrrSpcRisk.getIssuerRiskBucket(), new StringType())
                .setParameter("residualMaturityStart",
                        cfgMktIrrSpcRisk.getResidualMaturityStart(), new StringType())
                .setParameter("residualMaturityEnd",
                        cfgMktIrrSpcRisk.getResidualMaturityEnd(), new StringType())
                .setParameter("instrumentGroup",
                        cfgMktIrrSpcRisk.getInstrumentGroup(), new StringType())
                .setParameter("riskWeight",
                        cfgMktIrrSpcRisk.getRiskWeight(), new DoubleType())
                .setParameter("seq",
                        cfgMktIrrSpcRisk.getSeq(), new LongType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_MKT_IRR_SPC_RISK").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
