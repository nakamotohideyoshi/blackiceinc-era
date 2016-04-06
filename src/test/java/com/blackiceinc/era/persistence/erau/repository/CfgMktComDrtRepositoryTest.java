package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktComDrt;
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
public class CfgMktComDrtRepositoryTest {

    @Autowired
    private CfgMktComDrtRepository cfgMktComDrtRepository;

    @Autowired
    private CfgMktComDrtDaoCustom cfgMktComDrtDaoCustom;

    @Test
    public void testSave(){
        CfgMktComDrt cfgMktComDrt = new CfgMktComDrt();
        cfgMktComDrt.setMktProductType("mkt_product_type");
        cfgMktComDrt.setRiskWeight(new Double(0.3));

        cfgMktComDrtDaoCustom.insert(cfgMktComDrt);
//        cfgMktComDrtRepository.save(cfgMktComDrt);
    }

}