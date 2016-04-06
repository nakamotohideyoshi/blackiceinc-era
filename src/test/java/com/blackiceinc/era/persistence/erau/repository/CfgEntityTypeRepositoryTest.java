package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgEntityType;
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
public class CfgEntityTypeRepositoryTest {

    @Autowired
    private CfgEntityTypeRepository cfgEntityTypeRepository;

    @Autowired
    private CfgEntityTypeDaoCustom cfgEntityTypeDaoCustom;

    @Test
    public void testSave(){
        CfgEntityType cfgEntityType = new CfgEntityType();
        cfgEntityType.setEraEntityType("era_entity_type");
        cfgEntityType.setEntityDesc("entity_desc");

        cfgEntityTypeDaoCustom.insert(cfgEntityType);
//        cfgEntityTypeRepository.save(cfgEntityType);
    }

}