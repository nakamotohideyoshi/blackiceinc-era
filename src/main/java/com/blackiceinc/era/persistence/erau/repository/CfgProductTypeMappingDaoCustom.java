package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgProductTypeMapping;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgProductTypeMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgProductTypeMapping cfgProductTypeMapping = (CfgProductTypeMapping) cfgObject;
        Session currentSession = getCurrentSession();
        currentSession.createSQLQuery("INSERT INTO " +
                "CFG_PRODUCT_TYPE_MAPPING " +
                "(" +
                "PRODUCT_TYPE, " +
                "TABLE_NAME, " +
                "SENIORITY, " +
                "CONTRACT_TYPE, " +
                "ON_OFF, " +
                "F_MAIN_INDEX, " +
                "F_RECOG_EXCH, " +
                "F_RATED," +
                "F_OCCU," +
                "F_COMPLETED," +
                "F_INDEPENDANT_VALUER," +
                "F_LEGALLY_ENFORCE," +
                "UNDERLYING," +
                "ERA_CONTRACT_TYPE," +
                "SEQ," +
                "REPAYMENT_PROPERTY" +
                ") " +
                "VALUES " +
                "(" +
                ":productType, " +
                ":tableName, " +
                ":seniority, " +
                ":contractType, " +
                ":onOff, " +
                ":fMainIndex, " +
                ":fRecogExch, " +
                ":fRated," +
                ":fOccu," +
                ":fCompleted," +
                ":fIndependantValuer," +
                ":fLegallyEnforce," +
                ":underlying," +
                ":eraContractType," +
                ":seq," +
                ":repaymentProperty" +
                ")")
                .setParameter("productType",
                        cfgProductTypeMapping.getProductType(), new StringType())
                .setParameter("tableName",
                        cfgProductTypeMapping.getTableName(), new StringType())
                .setParameter("seniority",
                        cfgProductTypeMapping.getSeniority(), new StringType())
                .setParameter("contractType",
                        cfgProductTypeMapping.getContractType(), new StringType())
                .setParameter("onOff",
                        cfgProductTypeMapping.getOnOff(), new StringType())
                .setParameter("fMainIndex",
                        cfgProductTypeMapping.getfMainIndex(), new StringType())
                .setParameter("fRecogExch",
                        cfgProductTypeMapping.getfRecogExch(), new StringType())
                .setParameter("fRated",
                        cfgProductTypeMapping.getfRated(), new StringType())
                .setParameter("fOccu",
                        cfgProductTypeMapping.getfOccu(), new StringType())
                .setParameter("fCompleted",
                        cfgProductTypeMapping.getfCompleted(), new StringType())
                .setParameter("fIndependantValuer",
                        cfgProductTypeMapping.getfIndependantValuer(), new StringType())
                .setParameter("fLegallyEnforce",
                        cfgProductTypeMapping.getfLegallyEnforce(), new StringType())
                .setParameter("underlying",
                        cfgProductTypeMapping.getUnderlying(), new StringType())
                .setParameter("eraContractType",
                        cfgProductTypeMapping.getEraContractType(), new StringType())
                .setParameter("seq",
                        cfgProductTypeMapping.getSeq(), new LongType())
                .setParameter("repaymentProperty",
                        cfgProductTypeMapping.getRepaymentProperty(), new StringType())
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_PRODUCT_TYPE_MAPPING").executeUpdate();
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

}
