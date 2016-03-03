package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgOpsProductTypeMappingObjectMapper {

    CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;

    @Autowired
    public CfgOpsProductTypeMappingObjectMapper(CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository){
        this.cfgOpsProductTypeMappingRepository = cfgOpsProductTypeMappingRepository;
    }

    public List<CfgOpsProductTypeMapping> extractData(XSSFSheet sheet) {
        List<CfgOpsProductTypeMapping> result = new ArrayList<>();

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

    private CfgOpsProductTypeMapping createRow(Row row) {

        CfgOpsProductTypeMapping cfgOpsProductTypeMapping = new CfgOpsProductTypeMapping();

        cfgOpsProductTypeMapping.setOpsProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgOpsProductTypeMapping.setOpsGlCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgOpsProductTypeMapping.setOpsVibCode(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgOpsProductTypeMapping.setDescription(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        // comment column is missin in database

        return cfgOpsProductTypeMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsProductTypeMapping> all = cfgOpsProductTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsProductTypeMapping cfgOpsProductTypeMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgOpsProductTypeMapping.getOpsProductType());
            row.createCell(1).setCellValue(cfgOpsProductTypeMapping.getOpsGlCode());
            row.createCell(2).setCellValue(cfgOpsProductTypeMapping.getOpsVibCode());
            row.createCell(3).setCellValue(cfgOpsProductTypeMapping.getDescription());

            rowIndex++;
        }
    }
}
