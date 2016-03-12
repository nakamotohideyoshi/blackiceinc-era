package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmEligibilityRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCrmEligibilityObjectMapper extends AbstractObjectMapper {

    CfgCrmEligibilityRepository cfgCrmEligibilityRepository;

    @Autowired
    public CfgCrmEligibilityObjectMapper(CfgCrmEligibilityRepository cfgCrmEligibilityRepository) {
        this.cfgCrmEligibilityRepository = cfgCrmEligibilityRepository;
    }

    CfgCrmEligibility createRow(Row row) {

        CfgCrmEligibility cfgCrmEligibility = new CfgCrmEligibility();

        cfgCrmEligibility.setEraEntityType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCrmEligibility.setEraProductType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCrmEligibility.setRiskBucket(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        Cell cell3 = row.getCell(3);
        if (cell3 != null) {
            switch (cell3.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmEligibility.setRiskWeight(String.valueOf(cell3.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmEligibility.setRiskWeight(cell3.getStringCellValue());
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
            if (cfgCrmEligibility != null) {
                XSSFRow row = sheet.createRow(rowIndex);

                createCell(row, 0, cfgCrmEligibility.getEraEntityType());
                createCell(row, 1, cfgCrmEligibility.getEraProductType());
                createCell(row, 2, cfgCrmEligibility.getRiskBucket());
                createCell(row, 3, cfgCrmEligibility.getRiskWeight());
                createCell(row, 4, cfgCrmEligibility.getEligibility());

                rowIndex++;
            }
        }
    }
}
