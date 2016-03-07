package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgFinancialBookRepository;
import com.blackiceinc.era.services.excel.mapper.exception.CellMappingException;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgFinancialBookObjectMapper extends AbstractObjectMapper {

//    private static final Logger log = LoggerFactory.getLogger(CfgFinancialBookObjectMapper.class);

    CfgFinancialBookRepository cfgFinancialBookRepository;

    @Autowired
    public CfgFinancialBookObjectMapper(CfgFinancialBookRepository cfgFinancialBookRepository) {
        this.cfgFinancialBookRepository = cfgFinancialBookRepository;
    }

    CfgFinancialBook createRow(Row row) throws PrimaryColumnMappingException {
        CfgFinancialBook cfgFinancialBook = new CfgFinancialBook();
        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            cfgFinancialBook.setBookCode(cell0.getStringCellValue());
        } else {
            log.warn("Null value for a primary column");
            throw new PrimaryColumnMappingException();
        }
        cfgFinancialBook.setBookDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgFinancialBook.setTradingFlag(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgFinancialBook;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgFinancialBook> all = cfgFinancialBookRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgFinancialBook cfgFinancialBook : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFinancialBook.getBookCode());
            row.createCell(1).setCellValue(cfgFinancialBook.getBookDesc());
            row.createCell(2).setCellValue(cfgFinancialBook.getTradingFlag());

            rowIndex++;
        }
    }
}
