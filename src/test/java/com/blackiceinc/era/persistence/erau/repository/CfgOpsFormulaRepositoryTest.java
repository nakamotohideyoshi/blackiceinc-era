package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgOpsFormula;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@Transactional
public class CfgOpsFormulaRepositoryTest {

    @Autowired
    private  CfgOpsFormulaRepository cfgOpsFormulaRepository;

    @Test
    public void testSave() {
        CfgOpsFormula cfgOpsFormula = new CfgOpsFormula();
        cfgOpsFormula.setBasicIndicator("basic_indicator");
        cfgOpsFormula.setFormula("formula");

        cfgOpsFormulaRepository.save(cfgOpsFormula);
    }

}