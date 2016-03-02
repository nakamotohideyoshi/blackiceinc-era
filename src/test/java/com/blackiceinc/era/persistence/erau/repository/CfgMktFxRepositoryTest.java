package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktFx;
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
public class CfgMktFxRepositoryTest {
    @Autowired
    private CfgMktFxRepository cfgMktFxRepository;

    @Test
    public void testSave() {
        CfgMktFx cfgMktComOth = new CfgMktFx();
        cfgMktComOth.setMktProductType("mkt_product_type");
        cfgMktComOth.setRiskWeight(new Double(0.1));

        cfgMktFxRepository.save(cfgMktComOth);
    }
}