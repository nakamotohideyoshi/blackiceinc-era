package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOtherAssets;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOtherAssetsDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgOtherAssets cfgOtherAssets = (CfgOtherAssets) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_OTHER_ASSETS " +
                "(" +
                "GL_CODE, " +
                "GROUP_CHECK, " +
                "HEADING, " +
                "GL_DESC, " +
                "ERA_CONTRACT_TYPE, " +
                "CHECK_CRITERIA, " +
                "RISK_WEIGHT" +
                ") " +
                "VALUES " +
                "(" +
                ":glCode, " +
                ":groupCheck, " +
                ":heading, " +
                ":glDesc, " +
                ":eraContractType, " +
                ":checkCriteria, " +
                ":riskWeight" +
                ")")
                .setParameter("glCode",
                        cfgOtherAssets.getGlCode(), new StringType())
                .setParameter("groupCheck",
                        cfgOtherAssets.getGroupCheck(), new StringType())
                .setParameter("heading",
                        cfgOtherAssets.getHeading(), new StringType())
                .setParameter("glDesc",
                        cfgOtherAssets.getGlDesc(), new StringType())
                .setParameter("eraContractType",
                        cfgOtherAssets.getEraContractType(), new StringType())
                .setParameter("checkCriteria",
                        cfgOtherAssets.getCheckCriteria(), new StringType())
                .setParameter("riskWeight",
                        cfgOtherAssets.getRiskWeight(), new DoubleType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_OTHER_ASSETS").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
