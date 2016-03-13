package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgProductTypeMapping;
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
public class CfgProductTypeMappingRepositoryTest {
    @Autowired
    private CfgProductTypeMappingRepository cfgProductTypeMappingRepository;

    @Autowired
    private CfgProductTypeMappingDaoCustom cfgProductTypeMappingDaoCustom;

    @Test
    public void testSave() {
        CfgProductTypeMapping cfgProductTypeMapping = new CfgProductTypeMapping();
        cfgProductTypeMapping.setProductType("product_type");
        cfgProductTypeMapping.setTableName("table_name");
        cfgProductTypeMapping.setSeniority("seniority");
        cfgProductTypeMapping.setContractType("contract_type");
        cfgProductTypeMapping.setOnOff("on_off");
        cfgProductTypeMapping.setfMainIndex("f_main_index");
        cfgProductTypeMapping.setfRecogExch("f_recog_exch");
        cfgProductTypeMapping.setfRated("f_rated");
        cfgProductTypeMapping.setfOccu("f_occu");
        cfgProductTypeMapping.setfCompleted("f_completed");
        cfgProductTypeMapping.setfIndependantValuer("f_independent_valuer");
        cfgProductTypeMapping.setfLegallyEnforce("f_legally_enforce");
        cfgProductTypeMapping.setUnderlying("underlying");
        cfgProductTypeMapping.setEraContractType("era_contract_type");
        cfgProductTypeMapping.setSeq(1L);
        cfgProductTypeMapping.setRepaymentProperty("repayment_property");

        cfgProductTypeMappingDaoCustom.insert(cfgProductTypeMapping);

//        cfgProductTypeMappingRepository.save(cfgProductTypeMapping);
    }
}