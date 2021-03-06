package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
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
public class CfgFinancialBookRepositoryTest {

    @Autowired
    private CfgFinancialBookRepository cfgFinancialBookRepository;

    @Autowired
    private CfgFinancialBookDaoCustom cfgFinancialBookDaoCustom;

    @Test
    public void testSave() {
        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();
        cfgFinancialBook.setBookCode("code");
        cfgFinancialBook.setBookDesc("desc");
        cfgFinancialBook.setTradingFlag("T");

        cfgFinancialBookDaoCustom.insert(cfgFinancialBook);
//        cfgFinancialBookRepository.save(cfgFinancialBook);
    }


}