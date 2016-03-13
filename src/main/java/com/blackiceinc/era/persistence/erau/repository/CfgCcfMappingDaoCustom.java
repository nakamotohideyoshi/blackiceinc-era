package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCcfMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCcfMapping cfgCcfMapping) {
        this.em.createNativeQuery("INSERT INTO CFG_CCF_MAPPING " +
                "(ERA_PRODUCT_TYPE, CCF, UNCONDITIONALLY_CANCELABLE, MATURITY_START, MATURITY_END, SEQ) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
                .setParameter(1, cfgCcfMapping.getEraProductType())
                .setParameter(2, cfgCcfMapping.getCcf())
                .setParameter(3, cfgCcfMapping.getUnconditionallyCancelable())
                .setParameter(4, cfgCcfMapping.getMaturityStart())
                .setParameter(5, cfgCcfMapping.getMaturityEnd())
                .setParameter(6, cfgCcfMapping.getSeq())

                .executeUpdate();
    }

}
