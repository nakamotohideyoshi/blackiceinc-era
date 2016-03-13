package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAssetClass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAssetClassDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgAssetClass cfgAssetClass) {
        this.em.createNativeQuery("INSERT INTO CFG_ASSET_CLASS " +
                "(ERA_ASSET_CLASS, ERA_ASSET_CLASS_DESC) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgAssetClass.getEraAssetClass())
                .setParameter(2, cfgAssetClass.getEraAssetClassDesc())

                .executeUpdate();
    }

}
