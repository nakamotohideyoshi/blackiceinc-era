package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyLinkage;
import oracle.sql.NUMBER;
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
public class CfgCompanyLinkageRepositoryTest {

    @Autowired
    private CfgCompanyLinkageRepository cfgCompanyLinkageRepository;

    @Test
    public void testSave(){
        CfgCompanyLinkage cfgCompanyLinkage = new CfgCompanyLinkage();
        cfgCompanyLinkage.setChildCode("child_code");
        cfgCompanyLinkage.setMotherCode("mother_code");
        cfgCompanyLinkage.setLinkWeight(new Double(1));

        cfgCompanyLinkageRepository.save(cfgCompanyLinkage);
    }

}