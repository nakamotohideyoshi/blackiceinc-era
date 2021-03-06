package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCapElementsType;
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
public class CfgCapElementsTypeRepositoryTest {

    @Autowired
    private CfgCapElementsTypeRepository cfgCapElementsTypeRepository;

    @Autowired
    private CfgCapElementsTypeDaoCustom cfgCapElementsTypeDaoCustom;

    @Test
    public void testSave() {
        CfgCapElementsType cfgCapElementsType = new CfgCapElementsType();
        cfgCapElementsType.setCapElementType("cap_element_type");
        cfgCapElementsType.setDescription("description");

        cfgCapElementsTypeDaoCustom.insert(cfgCapElementsType);
//        cfgCapElementsTypeRepository.save(cfgCapElementsType);
    }

}