package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgNonPerformingMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgNonPerformingMappingObjectMapper {

    CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Autowired
    public CfgNonPerformingMappingObjectMapper(CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository){
        this.cfgNonPerformingMappingRepository = cfgNonPerformingMappingRepository;
    }

    public List<CfgNonPerformingMapping> extractData(XSSFSheet sheet) {
        List<CfgNonPerformingMapping> result = new ArrayList<>();

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

    private CfgNonPerformingMapping createRow(Row row) {

        CfgNonPerformingMapping cfgNonPerformingMapping = new CfgNonPerformingMapping();

        cfgNonPerformingMapping.setEraNplCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgNonPerformingMapping.setPerformingStatus(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgNonPerformingMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgNonPerformingMapping> all = cfgNonPerformingMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgNonPerformingMapping cfgNonPerformingMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgNonPerformingMapping.getEraNplCode());
            row.createCell(1).setCellValue(cfgNonPerformingMapping.getPerformingStatus());

            rowIndex++;
        }
    }
}
