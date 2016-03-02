package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
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
public class CfgCcfMappingRepositoryTest {

    @Autowired
    private CfgCcfMappingRepository cfgCcfMappingRepository;

    @Test
    public void testSave() {
        CfgCcfMapping cfgCcfMapping = new CfgCcfMapping();
        cfgCcfMapping.setEraProductType("era_product_type");
        cfgCcfMapping.setCcf(new Double(2));
        cfgCcfMapping.setUnconditionallyCancelable("*");
        cfgCcfMapping.setMaturityStart("1");
        cfgCcfMapping.setMaturityEnd("1");
        cfgCcfMapping.setSeq(200L);

        cfgCcfMappingRepository.save(cfgCcfMapping);
    }

}