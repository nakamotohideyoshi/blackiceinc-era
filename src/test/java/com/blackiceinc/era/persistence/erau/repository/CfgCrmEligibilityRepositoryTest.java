package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
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
public class CfgCrmEligibilityRepositoryTest {

    @Autowired
    private CfgCrmEligibilityRepository cfgCrmEligibilityRepository;

    @Autowired CfgCrmEligibilityDaoCustom cfgCrmEligibilityDaoCustom;

    @Test
    public void testSave() {
        CfgCrmEligibility cfgCrmEligibility = new CfgCrmEligibility();
        cfgCrmEligibility.setEraEntityType("era_entity_type");
        cfgCrmEligibility.setEraProductType("era_product_type");
        cfgCrmEligibility.setRiskBucket("*");
        cfgCrmEligibility.setRiskWeight("*");
        cfgCrmEligibility.setEligibility("T");
        cfgCrmEligibility.setSeq(200L);

        cfgCrmEligibilityDaoCustom.insert(cfgCrmEligibility);
//        cfgCrmEligibilityRepository.save(cfgCrmEligibility);
    }

}