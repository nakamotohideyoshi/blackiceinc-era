package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
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
public class CfgNonPerformingMappingRepositoryTest {
    @Autowired
    private CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Autowired
    private CfgNonPerformingMappingDaoCustom cfgNonPerformingMappingDaoCustom;

    @Test
    public void testSave() {
        CfgNonPerformingMapping cfgNonPerformingMapping = new CfgNonPerformingMapping();
        cfgNonPerformingMapping.setEraNplCode("era_npl_code");
        cfgNonPerformingMapping.setPerformingStatus("performing_status");

        cfgNonPerformingMappingDaoCustom.insert(cfgNonPerformingMapping);
//        cfgNonPerformingMappingRepository.save(cfgNonPerformingMapping);
    }
}