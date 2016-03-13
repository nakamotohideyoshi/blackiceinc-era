package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAssetClassMapping;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAssetClassMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgAssetClassMapping cfgAssetClassMapping) {
        this.em.createNativeQuery("INSERT INTO CFG_ASSET_CLASS_MAPPING " +
                "(ASSET_CLASS, ENTITY_TYPE, PRODUCT_TYPE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgAssetClassMapping.getAssetClass())
                .setParameter(2, cfgAssetClassMapping.getEntityType())
                .setParameter(3, cfgAssetClassMapping.getProductType())

                .executeUpdate();
    }

}
