package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsMappingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCapElementsMapping cfgCapElementsMapping) {
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_MAPPING " +
                "(CAP_ELEMENTS, GL_CODE, NOTE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCapElementsMapping.getCapElements())
                .setParameter(2, cfgCapElementsMapping.getGlCode())
                .setParameter(3, cfgCapElementsMapping.getNote())

                .executeUpdate();
    }
}
