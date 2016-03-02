package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@Transactional
public class CfgMktIrrGnrBandRepositoryTest {

    @Autowired
    private CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;

    @Test
    public void testSave() {
        CfgMktIrrGnrBand cfgMktIrrGnrBand = new CfgMktIrrGnrBand();
        cfgMktIrrGnrBand.setCode("code");
        cfgMktIrrGnrBand.setRiskWeight(new Double(10));

        cfgMktIrrGnrBandRepository.save(cfgMktIrrGnrBand);
    }

}