package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgReclass;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgReclassDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgReclass cfgReclass = (CfgReclass) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_RECLASS (CHECK_NO, DESCRIPTION, ERA_ENTITY_TYPE_IN, ERA_PRODUCT_TYPE_IN, \"CHECK\", ERA_ENTITY_TYPE_OUT, ERA_ASSET_CLASS_OUT) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
                .setParameter(1, cfgReclass.getCheckNo())
                .setParameter(2, cfgReclass.getDescription())
                .setParameter(3, cfgReclass.getEraEntityTypeIn())
                .setParameter(4, cfgReclass.getEraProductTypeIn())
                .setParameter(5, cfgReclass.getCheck())
                .setParameter(6, cfgReclass.getEraEntityTypeOut())
                .setParameter(7, cfgReclass.getEraAssetClassOut())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_RECLASS").executeUpdate();
    }

}
