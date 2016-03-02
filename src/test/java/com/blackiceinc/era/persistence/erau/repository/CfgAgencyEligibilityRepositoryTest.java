package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgAgencyEligibility;
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
public class CfgAgencyEligibilityRepositoryTest {
@Autowired
    private CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;

    @Test
    public void testSave() {
        CfgAgencyEligibility cfgAgencyEligibility = new CfgAgencyEligibility();
        cfgAgencyEligibility.setAgencyCode("agency_code");
        cfgAgencyEligibility.setAgencyDesc("agency_desc");
        cfgAgencyEligibility.setAgencyType("agency_type");

        cfgAgencyEligibilityRepository.save(cfgAgencyEligibility);
    }
}