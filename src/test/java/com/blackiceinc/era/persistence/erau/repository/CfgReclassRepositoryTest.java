package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgReclass;
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
public class CfgReclassRepositoryTest {

    @Autowired
    private CfgReclassRepository cfgReclassRepository;

    @Test
    public void testSave() {
        CfgReclass cfgReclass = new CfgReclass();
        cfgReclass.setCheckNo("check_no");
        cfgReclass.setDescription("description");
        cfgReclass.setEraEntityTypeIn("era_entity_type_in");
        cfgReclass.setEraProductTypeIn("era_product_type_in");
        cfgReclass.setCheck("check");
        cfgReclass.setEraEntityTypeOut("era_entity_type_out");
        cfgReclass.setEraProductTypeOut("era_product_type_out");

        cfgReclassRepository.save(cfgReclass);
    }

}