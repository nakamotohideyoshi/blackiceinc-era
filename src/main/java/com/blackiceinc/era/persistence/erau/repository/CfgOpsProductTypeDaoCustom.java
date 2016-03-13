package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsProductTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgOpsProductType cfgOpsProductType) {
        this.em.createNativeQuery("INSERT INTO CFG_OPS_PRODUCT_TYPE " +
                "(OPS_PRODUCT_TYPE, OPS_PRODUCT_DESC, OPS_BUS_INDICATOR) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgOpsProductType.getOpsProductType())
                .setParameter(2, cfgOpsProductType.getOpsProductDesc())
                .setParameter(3, cfgOpsProductType.getOpsBusIndicator())

                .executeUpdate();
    }

}
