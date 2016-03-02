package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
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
public class CfgOpsProductTypeRepositoryTest {

    @Autowired
    private  CfgOpsProductTypeRepository  cfgOpsProductTypeRepository;

    @Test
    public void testSave() {
        CfgOpsProductType cfgOpsProductType = new CfgOpsProductType();
        cfgOpsProductType.setOpsProductType("ops_product_type");
        cfgOpsProductType.setOpsProductDesc("ops_product_desc");
        cfgOpsProductType.setOpsBusIndicator("ops_bus_indicator");

        cfgOpsProductTypeRepository.save(cfgOpsProductType);
    }

}