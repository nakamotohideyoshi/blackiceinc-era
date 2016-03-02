package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktEqtSpc;
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
public class CfgMktEqtSpcRepositoryTest {

    @Autowired
    private CfgMktEqtSpcRepository cfgMktEqtSpcRepository;

    @Test
    public void testSave(){
        CfgMktEqtSpc cfgMktEqtSpc = new CfgMktEqtSpc();
        cfgMktEqtSpc.setMktProductType("mkt_product_type");
        cfgMktEqtSpc.setUnderlying("underlying");
        cfgMktEqtSpc.setDiversifiedEquity("diversified_equity");
        cfgMktEqtSpc.setDiversifiedIndex("diversified_index");
        cfgMktEqtSpc.setLiquidEquity("liquid_equity");
        cfgMktEqtSpc.setRiskWeight(new Double(4));

        cfgMktEqtSpcRepository.save(cfgMktEqtSpc);
    }

}