package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimension;
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
public class CfgCompanyDimensionRepositoryTest {

    @Autowired
    private CfgCompanyDimensionRepository companyDimensionRepository;

    @Autowired
    private CfgCompanyDimensionDaoCustom cfgCompanyDimensionDaoCustom;

    @Test
    public void testSave(){
        CfgCompanyDimension cfgCompanyDimension = new CfgCompanyDimension();
        cfgCompanyDimension.setCompanyCode("company_code");
        cfgCompanyDimension.setFinancialBook("financial_book");

        cfgCompanyDimensionDaoCustom.insert(cfgCompanyDimension);

//        companyDimensionRepository.save(cfgCompanyDimension);
    }

}