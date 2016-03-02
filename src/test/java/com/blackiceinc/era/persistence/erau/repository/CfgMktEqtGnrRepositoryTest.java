package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktEqtGnr;
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
public class CfgMktEqtGnrRepositoryTest {

    @Autowired
    private CfgMktEqtGnrRepository cfgMktEqtGnrRepository;

    @Test
    public void testSave() {
        CfgMktEqtGnr cfgMktEqtGnr = new CfgMktEqtGnr();
        cfgMktEqtGnr.setMktProductType("mkt_product_type");
        cfgMktEqtGnr.setUnderlying("underlying");
        cfgMktEqtGnr.setRiskWeight(new Double(0.6));

        cfgMktEqtGnrRepository.save(cfgMktEqtGnr);
    }

}