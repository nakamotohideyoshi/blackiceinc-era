package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
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
public class CfgAddOnRepositoryTest {

    @Autowired
    private CfgAddOnRepository cfgAddOnRepository;

    @Test
    public void testSave(){
        CfgAddOn cfgAddOn = new CfgAddOn();
        cfgAddOn.setEraProductType("era_product_type");
        cfgAddOn.setMaturityStart(1L);
        cfgAddOn.setMaturityEnd(2L);
        cfgAddOn.setRiskWeight(new Double(3));

        cfgAddOnRepository.save(cfgAddOn);
    }

}