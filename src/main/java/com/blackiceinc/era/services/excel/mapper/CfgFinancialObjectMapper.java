package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCompany;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgFinancialObjectMapper {

    CfgFinancialBookRepository cfgFinancialBookRepository;

    @Autowired
    public CfgFinancialObjectMapper(CfgFinancialBookRepository cfgFinancialBookRepository){
        this.cfgFinancialBookRepository = cfgFinancialBookRepository;
    }

    public List<CfgFinancialBook> extractCfgFinancialBooks(XSSFSheet financialBookSheet) {
        List<CfgFinancialBook> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = financialBookSheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgFinancialBookObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgFinancialBook createCfgFinancialBookObj(Row row) {

        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();

        cfgFinancialBook.setBookCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgFinancialBook.setBookDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgFinancialBook.setTradingFlag(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgFinancialBook;
    }

    public void importCfgFinancialBooks(XSSFSheet financialBookSheet) {
        List<CfgFinancialBook> all = cfgFinancialBookRepository.findAll();
        int rowIndex = 1;
        for (CfgFinancialBook cfgFinancialBook:all){
            XSSFRow row = financialBookSheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFinancialBook.getBookCode());
            row.createCell(1).setCellValue(cfgFinancialBook.getBookDesc());
            row.createCell(2).setCellValue(cfgFinancialBook.getTradingFlag());

            rowIndex++;
        }
    }
}
