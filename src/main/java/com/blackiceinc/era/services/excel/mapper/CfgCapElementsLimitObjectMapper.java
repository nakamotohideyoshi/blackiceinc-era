package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsLimit;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsLimitRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsLimitObjectMapper {

    CfgCapElementsLimitRepository cfgCapElementsLimitRepository;

    @Autowired
    public CfgCapElementsLimitObjectMapper(CfgCapElementsLimitRepository cfgCapElementsLimitRepository){
        this.cfgCapElementsLimitRepository = cfgCapElementsLimitRepository;
    }

    public List<CfgCapElementsLimit> extractData(XSSFSheet sheet) {
        List<CfgCapElementsLimit> result = new ArrayList<>();

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

    private CfgCapElementsLimit createRow(Row row) {

        CfgCapElementsLimit cfgCapElementsLimit = new CfgCapElementsLimit();

        cfgCapElementsLimit.setLimitType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCapElementsLimit.setOperator(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCapElementsLimit.setThreshold(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);
        cfgCapElementsLimit.setConsoTable(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgCapElementsLimit.setConsoField(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgCapElementsLimit.setConsoFieldValue(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgCapElementsLimit.setConsoAmt(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);

        return cfgCapElementsLimit;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsLimit> all = cfgCapElementsLimitRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsLimit cfgCapElementsLimit:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElementsLimit.getLimitType());
            row.createCell(1).setCellValue(cfgCapElementsLimit.getOperator());
            row.createCell(2).setCellValue(cfgCapElementsLimit.getThreshold());
            row.createCell(3).setCellValue(cfgCapElementsLimit.getConsoTable());
            row.createCell(4).setCellValue(cfgCapElementsLimit.getConsoField());
            row.createCell(5).setCellValue(cfgCapElementsLimit.getConsoFieldValue());
            row.createCell(6).setCellValue(cfgCapElementsLimit.getConsoAmt());

            rowIndex++;
        }
    }
}
