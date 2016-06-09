package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsLimit;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsLimitDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgObject cfgObject) {
        CfgCapElementsLimit cfgCapElementsLimit = (CfgCapElementsLimit) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_LIMIT " +
                "(LIMIT_TYPE, OPERATOR, THRESHOLD, CONSO_TABLE, CONSO_FIELD, CONSO_FIELD_VALUE, CONSO_AMT) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
                .setParameter(1, cfgCapElementsLimit.getLimitType())
                .setParameter(2, cfgCapElementsLimit.getOperator())
                .setParameter(3, cfgCapElementsLimit.getThreshold())
                .setParameter(4, cfgCapElementsLimit.getConsoTable())
                .setParameter(5, cfgCapElementsLimit.getConsoField())
                .setParameter(6, cfgCapElementsLimit.getConsoFieldValue())
                .setParameter(7, cfgCapElementsLimit.getConsoAmt())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CAP_ELEMENTS_LIMIT").executeUpdate();
    }

}
