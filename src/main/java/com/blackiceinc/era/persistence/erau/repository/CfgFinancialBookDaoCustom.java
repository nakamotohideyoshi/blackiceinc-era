package com.blackiceinc.era.persistence.erau.repository;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CfgFinancialBookDaoCustom {

    @PersistenceContext
    private EntityManager em;

    public void insert(CfgFinancialBook cfgFinancialBook) {
        this.em.createNativeQuery("INSERT INTO CFG_FINANCIAL_BOOK (BOOK_CODE, BOOK_DESC, TRADING_FLAG) VALUES (?1, ?2, ?3)")
                .setParameter(1, cfgFinancialBook.getBookCode())
                .setParameter(2, cfgFinancialBook.getBookDesc())
                .setParameter(3, cfgFinancialBook.getTradingFlag())

                .executeUpdate();
    }

}
