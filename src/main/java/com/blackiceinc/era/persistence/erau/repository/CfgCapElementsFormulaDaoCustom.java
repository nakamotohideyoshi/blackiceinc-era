package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsFormula;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgCapElementsFormulaDaoCustom extends CfgRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(CfgObject cfgObject) {
        CfgCapElementsFormula cfgCapElementsFormula = (CfgCapElementsFormula) cfgObject;
        this.em.createNativeQuery("INSERT INTO CFG_CAP_ELEMENTS_FORMULA " +
                "(CAP_ELEMENTS, DESCRIPTION, FORMULA) " +
                "VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgCapElementsFormula.getCapElements())
                .setParameter(2, cfgCapElementsFormula.getDescription())
                .setParameter(3, cfgCapElementsFormula.getFormula())

                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        this.em.createNativeQuery("delete from CFG_CAP_ELEMENTS_FORMULA").executeUpdate();
    }

}
