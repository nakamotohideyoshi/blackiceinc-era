package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
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
public class CfgCrmHaircutRepositoryTest {

    @Autowired
    private CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Test
    public void testSave() {
        CfgCrmHaircut cfgCrmHaircut = new CfgCrmHaircut();
        cfgCrmHaircut.setEraColType("era_col_type");
        cfgCrmHaircut.setEraEntityType("era_entity_type");
        cfgCrmHaircut.setRiskBucket("2");
        cfgCrmHaircut.setMinResidualMaturity("1");
        cfgCrmHaircut.setMaxResidualMaturity("5");
        cfgCrmHaircut.setHaircut(new Double(4));
        cfgCrmHaircut.setSeq(300L);

        cfgCrmHaircutRepository.save(cfgCrmHaircut);
    }

}