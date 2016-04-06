package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCreditMeasure;
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
public class CfgCreditMeasureRepositoryTest {

    @Autowired
    private CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Autowired
    private CfgCreditMeasureDaoCustom cfgCreditMeasureDaoCustom;

    @Test
    public void testSave() {
        CfgCreditMeasure cfgCreditMeasure = new CfgCreditMeasure();
        cfgCreditMeasure.setCreditMeasure("credit_measure");
        cfgCreditMeasure.setCreditMeasureDesc("credit_measure_desc");

        cfgCreditMeasureDaoCustom.insert(cfgCreditMeasure);
//        cfgCreditMeasureRepository.save(cfgCreditMeasure);
    }

}