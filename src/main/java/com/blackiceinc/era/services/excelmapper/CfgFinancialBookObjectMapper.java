package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgFinancialBookObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgFinancialBookRepository cfgFinancialBookRepository;

    @Autowired
    public CfgFinancialBookObjectMapper(CfgFinancialBookRepository cfgFinancialBookRepository) {
        this.cfgFinancialBookRepository = cfgFinancialBookRepository;
    }

    @Override
    CfgFinancialBook createRow(Row row) {
        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();

        cfgFinancialBook.setBookCode(getStringValue(row.getCell(0)));
        cfgFinancialBook.setBookDesc(getStringValue(row.getCell(1)));
        cfgFinancialBook.setTradingFlag(getStringValue(row.getCell(2)));

        return cfgFinancialBook;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgFinancialBook> all = cfgFinancialBookRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;

        for (CfgFinancialBook cfgFinancialBook : all) {
            if (cfgFinancialBook!=null){
                XSSFRow row = sheet.createRow(rowIndex);

                createCell(row, 0, cfgFinancialBook.getBookCode());
                createCell(row, 1, cfgFinancialBook.getBookDesc());
                createCell(row, 2, cfgFinancialBook.getTradingFlag());

                rowIndex++;
            }
        }
    }
}
