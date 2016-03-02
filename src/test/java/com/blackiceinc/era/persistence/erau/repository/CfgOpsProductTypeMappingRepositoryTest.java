package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
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
public class CfgOpsProductTypeMappingRepositoryTest {

    @Autowired
    private CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;

    @Test
    public void testSave() {
        CfgOpsProductTypeMapping cfgOpsProductTypeMapping = new CfgOpsProductTypeMapping();
        cfgOpsProductTypeMapping.setOpsProductType("ops_product_type");
        cfgOpsProductTypeMapping.setOpsGlCode("ops_gl_code");
        cfgOpsProductTypeMapping.setOpsVibCode("ops_vib_code");
        cfgOpsProductTypeMapping.setDescription("description");

        cfgOpsProductTypeMappingRepository.save(cfgOpsProductTypeMapping);
    }

}