package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgFxProdMapping;
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
public class CfgFxProdMappingRepositoryTest {

    @Autowired
    private CfgFxProdMappingDaoCustom cfgFxProdMappingDaoCustom;

    @Test
    public void testSave() {
        CfgFxProdMapping cfgFxProdMapping = new CfgFxProdMapping("gl_code", "fx_prod_type", "gl_code_desc");
        cfgFxProdMappingDaoCustom.insert(cfgFxProdMapping);
    }

}