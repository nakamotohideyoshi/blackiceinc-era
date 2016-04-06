package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktProductType;
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
public class CfgMktProductTypeRepositoryTest {

    @Autowired
    private CfgMktProductTypeRepository cfgMktProductTypeRepository;

    @Autowired
    private CfgMktProductTypeDaoCustom cfgMktProductTypeDaoCustom;

    @Test
    public void testSave() {
        CfgMktProductType cfgMktProductType = new CfgMktProductType();
        cfgMktProductType.setMktProductType("mkt_product_type");
        cfgMktProductType.setMktProductDesc("mkt_product_desc");
        cfgMktProductType.setMktProductCategory("mkt_product_category");

        cfgMktProductTypeDaoCustom.insert(cfgMktProductType);
//        cfgMktProductTypeRepository.save(cfgMktProductType);
    }

}