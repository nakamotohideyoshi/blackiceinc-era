package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsMappingDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCapElementsMapping cfgCapElementsMapping = (CfgCapElementsMapping) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_MAPPING " +
                "(CAP_ELEMENTS, GL_CODE, NOTE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCapElementsMapping.getCapElements())
                .setParameter(2, cfgCapElementsMapping.getGlCode())
                .setParameter(3, cfgCapElementsMapping.getNote())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CAP_ELEMENTS_MAPPING").executeUpdate();
    }
}
