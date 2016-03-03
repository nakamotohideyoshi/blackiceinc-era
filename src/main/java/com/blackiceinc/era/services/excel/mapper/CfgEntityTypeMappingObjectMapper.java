package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgEntityTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgEntityTypeMappingObjectMapper {

    CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository;

    @Autowired
    public CfgEntityTypeMappingObjectMapper(CfgEntityTypeMappingRepository cfgEntityTypeMappingRepository) {
        this.cfgEntityTypeMappingRepository = cfgEntityTypeMappingRepository;
    }

    public List<CfgEntityTypeMapping> extractCfgEntityTypeMappings(XSSFSheet sheet) {
        List<CfgEntityTypeMapping> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgEntityTypeMappingObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgEntityTypeMapping createCfgEntityTypeMappingObj(Row row) {

        CfgEntityTypeMapping cfgEntityTypeMapping = new CfgEntityTypeMapping();

        cfgEntityTypeMapping.setEraEntityType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgEntityTypeMapping.setCustomerType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgEntityTypeMapping.setCustomerSubType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgEntityTypeMapping;
    }

    public void importCfgEntityTypeMappings(XSSFSheet sheet) {
        List<CfgEntityTypeMapping> all = cfgEntityTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgEntityTypeMapping cfgFcfgEntityTypeMappingnancialBook : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgFcfgEntityTypeMappingnancialBook.getEraEntityType());
            row.createCell(1).setCellValue(cfgFcfgEntityTypeMappingnancialBook.getCustomerType());
            row.createCell(2).setCellValue(cfgFcfgEntityTypeMappingnancialBook.getCustomerSubType());

            rowIndex++;
        }
    }
}
