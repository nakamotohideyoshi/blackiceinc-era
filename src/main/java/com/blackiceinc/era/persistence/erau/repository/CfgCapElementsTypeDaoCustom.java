package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsType;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsTypeDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCapElementsType cfgCapElementsType = (CfgCapElementsType) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_TYPE " +
                "(CAP_ELEMENT_TYPE, DESCRIPTION) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgCapElementsType.getCapElementType())
                .setParameter(2, cfgCapElementsType.getDescription())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CAP_ELEMENTS_TYPE").executeUpdate();
    }

}
