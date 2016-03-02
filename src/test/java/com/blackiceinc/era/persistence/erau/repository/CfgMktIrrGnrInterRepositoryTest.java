package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
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
public class CfgMktIrrGnrInterRepositoryTest {

    @Autowired
    private CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Test
    public void testSave() {
        CfgMktIrrGnrInter cfgMktIrrGnrInter = new CfgMktIrrGnrInter();
        cfgMktIrrGnrInter.setCode("code");
        cfgMktIrrGnrInter.setZoneCode1("zone_code_1");
        cfgMktIrrGnrInter.setZoneCode2("zone_code_2");
        cfgMktIrrGnrInter.setRiskWeight(new Double(5));

        cfgMktIrrGnrInterRepository.save(cfgMktIrrGnrInter);
    }

}