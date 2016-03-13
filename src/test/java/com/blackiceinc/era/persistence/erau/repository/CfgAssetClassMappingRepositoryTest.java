package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgAssetClassMapping;
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
public class CfgAssetClassMappingRepositoryTest {

    @Autowired
    private CfgAssetClassMappingRepository cfgAssetClassMappingRepository;

    @Autowired
    private CfgAssetClassMappingDaoCustom cfgAssetClassMappingDaoCustom;

    @Test
    public void testSave(){
        CfgAssetClassMapping cfgAssetClassMapping = new CfgAssetClassMapping();
        cfgAssetClassMapping.setAssetClass("asset_class");
        cfgAssetClassMapping.setEntityType("entity_type");
        cfgAssetClassMapping.setProductType("product_type");

        cfgAssetClassMappingDaoCustom.insert(cfgAssetClassMapping);

//        cfgAssetClassMappingRepository.save(cfgAssetClassMapping);
    }

}