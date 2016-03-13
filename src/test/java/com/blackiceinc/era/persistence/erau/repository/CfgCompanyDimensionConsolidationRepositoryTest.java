package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
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
public class CfgCompanyDimensionConsolidationRepositoryTest {

    @Autowired
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;

    @Autowired
    private  CfgCompanyDimensionConsolidationDaoCustom cfgCompanyDimensionConsolidationDaoCustom;

    @Test
    public void testSave(){
        CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation = new CfgCompanyDimensionConsolidation();
        cfgCompanyDimensionConsolidation.setCompanyCode("comp_code");
        cfgCompanyDimensionConsolidation.setEntityCode("entity_code");
        cfgCompanyDimensionConsolidation.setConsoMode("conso_mode");
        cfgCompanyDimensionConsolidation.setConsoPerct(new Double(1));

        cfgCompanyDimensionConsolidationDaoCustom.insert(cfgCompanyDimensionConsolidation);
//        cfgCompanyDimensionConsolidationRepository.save(cfgCompanyDimensionConsolidation);
    }

}