package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElements;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCapElements cfgCapElements) {
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS " +
                "(CAP_ELEMENTS, CAP_ELEMENTS_DESC, TYPE) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCapElements.getCapElements())
                .setParameter(2, cfgCapElements.getCapElementsDesc())
                .setParameter(3, cfgCapElements.getType())

                .executeUpdate();
    }

}
