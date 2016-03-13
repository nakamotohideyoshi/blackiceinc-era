package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsFormula;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsFormulaDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgCapElementsFormula cfgCapElementsFormula) {
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_FORMULA " +
                "(CAP_ELEMENTS, DESCRIPTION, FORMULA) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCapElementsFormula.getCapElements())
                .setParameter(2, cfgCapElementsFormula.getDescription())
                .setParameter(3, cfgCapElementsFormula.getFormula())

                .executeUpdate();
    }

}
