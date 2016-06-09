package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgOpsProductTypeDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgOpsProductType cfgOpsProductType = (CfgOpsProductType) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_OPS_PRODUCT_TYPE " +
                "(OPS_PRODUCT_TYPE, OPS_PRODUCT_DESC, OPS_BUS_INDICATOR) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgOpsProductType.getOpsProductType())
                .setParameter(2, cfgOpsProductType.getOpsProductDesc())
                .setParameter(3, cfgOpsProductType.getOpsBusIndicator())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_OPS_PRODUCT_TYPE").executeUpdate();
    }

}
