package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAddOnDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgAddOn cfgAddOn = (CfgAddOn) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_ADD_ON " +
                "(ERA_PRODUCT_TYPE, MATURITY_START, MATURITY_END, RISK_WEIGHT) " +
                "VALUES (?1, ?2, ?3, ?4)")
                .setParameter(1, cfgAddOn.getEraProductType())
                .setParameter(2, cfgAddOn.getMaturityStart())
                .setParameter(3, cfgAddOn.getMaturityEnd())
                .setParameter(4, cfgAddOn.getRiskWeight())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_ADD_ON").executeUpdate();
    }

}
