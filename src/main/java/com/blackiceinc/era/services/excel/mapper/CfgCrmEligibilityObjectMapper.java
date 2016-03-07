package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmEligibilityRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCrmEligibilityObjectMapper {

    CfgCrmEligibilityRepository cfgCrmEligibilityRepository;

    @Autowired
    public CfgCrmEligibilityObjectMapper(CfgCrmEligibilityRepository cfgCrmEligibilityRepository) {
        this.cfgCrmEligibilityRepository = cfgCrmEligibilityRepository;
    }

    public List<CfgCrmEligibility> extractData(XSSFSheet sheet) {
        List<CfgCrmEligibility> result = new ArrayList<>();

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

    private CfgCrmEligibility createRow(Row row) {

        CfgCrmEligibility cfgCrmEligibility = new CfgCrmEligibility();

        cfgCrmEligibility.setEraEntityType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCrmEligibility.setEraProductType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCrmEligibility.setRiskBucket(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        Cell cell3 = row.getCell(3);
        if (cell3 != null) {
            switch (cell3.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmEligibility.setRiskWeight(cell3 != null ? String.valueOf(cell3.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmEligibility.setRiskWeight(cell3 != null ? cell3.getStringCellValue() : null);
                    break;
            }
        }

        cfgCrmEligibility.setEligibility(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);

        return cfgCrmEligibility;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCrmEligibility> all = cfgCrmEligibilityRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCrmEligibility cfgCrmEligibility : all) {
            if (cfgCrmEligibility!=null){
                XSSFRow row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(cfgCrmEligibility.getEraEntityType());
                row.createCell(1).setCellValue(cfgCrmEligibility.getEraProductType());
                row.createCell(2).setCellValue(cfgCrmEligibility.getRiskBucket());
                row.createCell(3).setCellValue(cfgCrmEligibility.getRiskWeight());
                row.createCell(4).setCellValue(cfgCrmEligibility.getEligibility());

                rowIndex++;
            }
        }
    }
}
