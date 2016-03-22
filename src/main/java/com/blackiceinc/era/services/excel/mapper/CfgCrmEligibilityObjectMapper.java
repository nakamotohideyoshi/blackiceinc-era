package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmEligibility;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmEligibilityRepository;
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

        cfgCrmEligibility.setEraEntityType(getStringValue(row.getCell(0)));
        cfgCrmEligibility.setEraProductType(getStringValue(row.getCell(1)));
        cfgCrmEligibility.setRiskBucket(getStringValue(row.getCell(2)));
        cfgCrmEligibility.setRiskWeight(getStringValue(row.getCell(3)));
        cfgCrmEligibility.setEligibility(getStringValue(row.getCell(4)));
        cfgCrmEligibility.setSeq(getLongValue(row.getCell(5)));

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
                createCell(row, 5, cfgCrmEligibility.getSeq());

                rowIndex++;
            }
        }
    }
}
