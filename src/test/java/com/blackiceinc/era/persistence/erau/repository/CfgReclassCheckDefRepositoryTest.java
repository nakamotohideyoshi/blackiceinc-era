package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckDef;
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
public class CfgReclassCheckDefRepositoryTest {

    @Autowired
    private CfgReclassCheckDefRepository cfgReclassCheckDefRepository;

    @Test
    public void testSave() {
        CfgReclassCheckDef cfgReclassCheckDef = new CfgReclassCheckDef();
        cfgReclassCheckDef.setCheckDefNo("check_def_no");
        cfgReclassCheckDef.setDescription("description");
        cfgReclassCheckDef.setCheckType("check_type");
        cfgReclassCheckDef.setOperator("operator");
        cfgReclassCheckDef.setThreshold(new Double(4));
        cfgReclassCheckDef.setCurrency("currency");

        cfgReclassCheckDefRepository.save(cfgReclassCheckDef);
    }

}