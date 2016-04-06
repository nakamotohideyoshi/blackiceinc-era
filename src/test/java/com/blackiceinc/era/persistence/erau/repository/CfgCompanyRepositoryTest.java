package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCompany;
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
public class CfgCompanyRepositoryTest {

    @Autowired
    private CfgCompanyRepository cfgCompanyRepository;

    @Autowired
    private CfgCompanyDaoCustom cfgCompanyDaoCustom;

    @Test
    public void testSave(){
        CfgCompany cfgCompany = new CfgCompany();
        cfgCompany.setCompanyCode("comp_code");
        cfgCompany.setCompanyName("comp_name");
        cfgCompany.setIncorporationCountry("incorporation_country");

        cfgCompanyDaoCustom.insert(cfgCompany);
//        cfgCompanyRepository.save(cfgCompany);
    }

}