package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
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
public class CfgMktIrrGnrIntraRepositoryTest {

    @Autowired
    private CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;

    @Autowired
    private CfgMktIrrGnrIntraDaoCustom cfgMktIrrGnrIntraDaoCustom;

    @Test
    public void testSave() {
        CfgMktIrrGnrIntra cfgMktIrrGnrIntra = new CfgMktIrrGnrIntra();
        cfgMktIrrGnrIntra.setCode("code");
        cfgMktIrrGnrIntra.setZoneCode("zone_code");
        cfgMktIrrGnrIntra.setRiskWeight(new Double(0.1));

        cfgMktIrrGnrIntraDaoCustom.insert(cfgMktIrrGnrIntra);
//        cfgMktIrrGnrIntraRepository.save(cfgMktIrrGnrIntra);
    }

}