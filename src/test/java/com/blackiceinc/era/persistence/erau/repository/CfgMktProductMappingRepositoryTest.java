package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgMktProductMapping;
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
public class CfgMktProductMappingRepositoryTest {

    @Autowired
    private CfgMktProductMappingRepository cfgMktProductMappingRepository;

    @Test
    public void testSave(){
        CfgMktProductMapping cfgMktProductMapping = new CfgMktProductMapping();
        cfgMktProductMapping.setMktProductType("mkt_product_type");
        cfgMktProductMapping.setContractType("contract_type");
        cfgMktProductMapping.setExchangedTraded("exchanged_traded");
        cfgMktProductMapping.setInstrumentType("instrument_type");
        cfgMktProductMapping.setTableName("table_name");
        cfgMktProductMapping.setUnderlyingType("underlying_type");

        cfgMktProductMappingRepository.save(cfgMktProductMapping);
    }

}