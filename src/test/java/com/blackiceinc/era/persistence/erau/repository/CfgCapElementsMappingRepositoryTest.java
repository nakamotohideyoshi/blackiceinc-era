package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
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
public class CfgCapElementsMappingRepositoryTest {

    @Autowired
    private CfgCapElementsMappingRepository cfgCapElementsMappingRepository;

    @Autowired
    private CfgCapElementsMappingDaoCustom cfgCapElementsMappingDaoCustom;

    @Test
    public void testSave() {
        CfgCapElementsMapping cfgCapElementsMapping = new CfgCapElementsMapping();
        cfgCapElementsMapping.setCapElements("cap_elements");
        cfgCapElementsMapping.setGlCode("gl_code");
        cfgCapElementsMapping.setNote("note");

        cfgCapElementsMappingDaoCustom.insert(cfgCapElementsMapping);
//        cfgCapElementsMappingRepository.save(cfgCapElementsMapping);
    }

}