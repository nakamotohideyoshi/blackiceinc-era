package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgAssetClass;
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
public class CfgAssetClassRepositoryTest {

    @Autowired
    private CfgAssetClassRepository cfgAssetClassRepository;

    @Autowired
    private CfgAssetClassDaoCustom cfgAssetClassDaoCustom;

    @Test
    public void testSave() {
        CfgAssetClass cfgAssetClass = new CfgAssetClass();
        cfgAssetClass.setEraAssetClass("era_asset_class");
        cfgAssetClass.setEraAssetClassDesc("era_asset_class_desc");

        cfgAssetClassDaoCustom.insert(cfgAssetClass);

//        cfgAssetClassRepository.save(cfgAssetClass);
    }

}