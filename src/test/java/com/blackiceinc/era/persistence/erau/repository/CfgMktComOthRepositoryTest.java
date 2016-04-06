package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktComOth;
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
public class CfgMktComOthRepositoryTest {

    @Autowired
    private CfgMktComOthRepository cfgMktComOthRepository;

    @Autowired
    private CfgMktComOthDaoCustom cfgMktComOthDaoCustom;

    @Test
    public void testSave() {
        CfgMktComOth cfgMktComOth = new CfgMktComOth();
        cfgMktComOth.setMktProductType("mkt_product_type");
        cfgMktComOth.setRiskWeight(new Double(0.1));

        cfgMktComOthDaoCustom.insert(cfgMktComOth);
//        cfgMktComOthRepository.save(cfgMktComOth);
    }


}