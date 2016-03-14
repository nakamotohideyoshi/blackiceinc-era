package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.query.Parameter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@Transactional
public class CfgMktIrrGnrRiskRepositoryTest {

    @Autowired
    private CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;

    @Autowired
    private CfgMktIrrGnrRiskDaoCustom cfgMktIrrGnrRiskDaoCustom;

    @Test
    public void testSave() {
        CfgMktIrrGnrRisk cfgMktIrrGnrRisk = new CfgMktIrrGnrRisk();
        cfgMktIrrGnrRisk.setZoneCode("zone_code");
        cfgMktIrrGnrRisk.setBandCode("band_code");
        cfgMktIrrGnrRisk.setCurrency("currency");
        cfgMktIrrGnrRisk.setCouponRateStart(new Double(5.1));
        cfgMktIrrGnrRisk.setCouponRateEnd(new Double(4.1));
        cfgMktIrrGnrRisk.setMaturityBandStart(new Double(3.1));
        cfgMktIrrGnrRisk.setMaturityBandEnd(null);
        cfgMktIrrGnrRisk.setRiskWeight(new Double(4.1));

        cfgMktIrrGnrRiskDaoCustom.insert(cfgMktIrrGnrRisk);
//        cfgMktIrrGnrRiskRepository.save(cfgMktIrrGnrRisk);
    }

}