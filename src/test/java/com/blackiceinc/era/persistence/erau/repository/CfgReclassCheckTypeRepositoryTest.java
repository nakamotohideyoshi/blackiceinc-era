package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckType;
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
public class CfgReclassCheckTypeRepositoryTest {

    @Autowired
    private CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Autowired
    private CfgReclassCheckTypeDaoCustom cfgReclassCheckTypeDaoCustom;

    @Test
    public void testSave(){
        CfgReclassCheckType cfgReclassCheckType = new CfgReclassCheckType();
        cfgReclassCheckType.setCheckType("check_type");
        cfgReclassCheckType.setCheckDescription("check_description");
        cfgReclassCheckType.setWhereClause("where_clause");
        cfgReclassCheckType.setConsoField("conso_field");
        cfgReclassCheckType.setAmtField("amt_field");

        cfgReclassCheckTypeDaoCustom.insert(cfgReclassCheckType);
//        cfgReclassCheckTypeRepository.save(cfgReclassCheckType);
    }

}