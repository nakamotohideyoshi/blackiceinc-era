package com.blackiceinc.era.services.excel.mapper;

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
public class CfgFinancialBookObjectMapper {

    CfgFinancialBookRepository cfgFinancialBookRepository;

    @Autowired
    public CfgFinancialBookObjectMapper(CfgFinancialBookRepository cfgFinancialBookRepository){
        this.cfgFinancialBookRepository = cfgFinancialBookRepository;
    }

    public List<CfgFinancialBook> extractData(XSSFSheet sheet) {
        List<CfgFinancialBook> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgFinancialBook createRow(Row row) {

        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();

        cfgFinancialBook.setBookCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgFinancialBook.setBookDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgFinancialBook.setTradingFlag(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgFinancialBook;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgFinancialBook> all = cfgFinancialBookRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgFinancialBook cfgFinancialBook:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFinancialBook.getBookCode());
            row.createCell(1).setCellValue(cfgFinancialBook.getBookDesc());
            row.createCell(2).setCellValue(cfgFinancialBook.getTradingFlag());

            rowIndex++;
        }
    }
}
