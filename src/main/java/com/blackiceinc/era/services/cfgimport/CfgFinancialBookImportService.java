package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgFinancialBookObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgFinancialBookImportService implements CfgImportService {
    public static final String FINANCIAL_BOOK = "FINANCIAL_BOOK";

    @Autowired
    private CfgFinancialBookObjectMapper cfgFinancialBookObjectMapper;

    @Autowired
    private CfgFinancialBookDaoCustom cfgFinancialBookDaoCustom;

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(FINANCIAL_BOOK, workbook,
                cfgFinancialBookObjectMapper, cfgFinancialBookDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet financialBookSheet = ImportUtil.getSheet(FINANCIAL_BOOK, workbook);
        cfgFinancialBookObjectMapper.importData(financialBookSheet);
    }
}
