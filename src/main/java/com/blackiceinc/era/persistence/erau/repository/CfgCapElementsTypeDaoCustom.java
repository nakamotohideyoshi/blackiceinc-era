package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsTypeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCapElementsType cfgCapElementsType) {
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_TYPE " +
                "(CAP_ELEMENT_TYPE, DESCRIPTION) " +
                "VALUES (?1, ?2)")
                .setParameter(1, cfgCapElementsType.getCapElementType())
                .setParameter(2, cfgCapElementsType.getDescription())

                .executeUpdate();
    }

}
