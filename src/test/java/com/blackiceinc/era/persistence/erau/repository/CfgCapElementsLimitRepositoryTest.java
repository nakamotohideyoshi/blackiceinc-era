package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCapElementsLimit;
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
public class CfgCapElementsLimitRepositoryTest {

    @Autowired
    private CfgCapElementsLimitRepository cfgCapElementsLimitRepository;

    @Autowired
    private CfgCapElementsLimitDaoCustom cfgCapElementsLimitDaoCustom;

    @Test
    public void testSave() {
        CfgCapElementsLimit cfgCapElementsLimit = new CfgCapElementsLimit();
        cfgCapElementsLimit.setLimitType("limit_type");
        cfgCapElementsLimit.setOperator("operator");
        cfgCapElementsLimit.setThreshold(new Double(2));
        cfgCapElementsLimit.setConsoTable("conso_table");
        cfgCapElementsLimit.setConsoField("conso_field");
        cfgCapElementsLimit.setConsoFieldValue("conso_field_value");
        cfgCapElementsLimit.setConsoAmt("conso_amt");

        cfgCapElementsLimitDaoCustom.insert(cfgCapElementsLimit);

//        cfgCapElementsLimitRepository.save(cfgCapElementsLimit);
    }

}