package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgAssetClass;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgAssetClassDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgAssetClass cfgAssetClass = (CfgAssetClass) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_ASSET_CLASS " +
                "(ERA_ASSET_CLASS, ERA_ASSET_CLASS_DESC) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgAssetClass.getEraAssetClass())
                .setParameter(2, cfgAssetClass.getEraAssetClassDesc())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_ASSET_CLASS").executeUpdate();
    }

}
