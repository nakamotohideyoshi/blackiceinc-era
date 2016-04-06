package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgProductType;
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
public class CfgProductTypeRepositoryTest {

    @Autowired
    private CfgProductTypeRepository cfgProductTypeRepository;

    @Autowired
    private CfgProductTypeDaoCustom cfgProductTypeDaoCustom;

    @Test
    public void testSave(){
        CfgProductType cfgProductType = new CfgProductType();
        cfgProductType.setEraProductType("era_product_type");
        cfgProductType.setEraProductDesc("era_product_desc");
        cfgProductType.setEraProductCategory("era_product_category");

        cfgProductTypeDaoCustom.insert(cfgProductType);
//        cfgProductTypeRepository.save(cfgProductType);
    }

}