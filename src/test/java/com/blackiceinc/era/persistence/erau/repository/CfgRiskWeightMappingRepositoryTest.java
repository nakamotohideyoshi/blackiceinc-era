package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
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
public class CfgRiskWeightMappingRepositoryTest {

    @Autowired
    private CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Autowired
    private CfgRiskWeightMappingDaoCustom cfgRiskWeightMappingDaoCustom;

    @Test
    public void testSave() {
        CfgRiskWeightMapping cfgRiskWeightMapping = new CfgRiskWeightMapping();
        cfgRiskWeightMapping.setAssetClass("asset_class");
        cfgRiskWeightMapping.setEraNplCode("era_npl_code");
        cfgRiskWeightMapping.setYearOfEstablishment("year_of_establishment");
        cfgRiskWeightMapping.setCreditMeasure1("credit_measure_1");
        cfgRiskWeightMapping.setCreditMeasure1Beg("credit_measure_1_beg");
        cfgRiskWeightMapping.setCreditMeasure1End("credit_measure_1_end");
        cfgRiskWeightMapping.setCreditMeasure2("credit_measure_2");
        cfgRiskWeightMapping.setCreditMeasure2Beg("credit_measure_2_beg");
        cfgRiskWeightMapping.setCreditMeasure2End("credit_measure_2_end");
        cfgRiskWeightMapping.setRiskWeight(new Double(1));
        cfgRiskWeightMapping.setSeq(200L);

        cfgRiskWeightMappingDaoCustom.insert(cfgRiskWeightMapping);
//        cfgRiskWeightMappingRepository.save(cfgRiskWeightMapping);
    }

}