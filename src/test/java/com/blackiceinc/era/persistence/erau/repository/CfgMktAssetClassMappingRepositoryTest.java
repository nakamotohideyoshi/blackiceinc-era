package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClassMapping;
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
public class CfgMktAssetClassMappingRepositoryTest {

    @Autowired
    private CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;

    @Autowired
    private CfgMktAssetClassMappingDaoCustom cfgMktAssetClassMappingDaoCustom;

    @Test
    public void testSave() {
        CfgMktAssetClassMapping cfgMktAssetClassMapping = new CfgMktAssetClassMapping();
        cfgMktAssetClassMapping.setMktAssetClass("mkt_asset_class");
        cfgMktAssetClassMapping.setEraEntityType("era_entity_type");
        cfgMktAssetClassMapping.setMktProductType("mkt_product_type");

        cfgMktAssetClassMappingDaoCustom.insert(cfgMktAssetClassMapping);
//        cfgMktAssetClassMappingRepository.save(cfgMktAssetClassMapping);
    }

}