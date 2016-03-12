package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAgencyEligibility;
import com.blackiceinc.era.persistence.erau.repository.CfgAgencyEligibilityRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgAgencyEligibilityObjectMapper extends AbstractObjectMapper {

    CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;

    @Autowired
    public CfgAgencyEligibilityObjectMapper(CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository){
        this.cfgAgencyEligibilityRepository = cfgAgencyEligibilityRepository;
    }

    CfgAgencyEligibility createRow(Row row) {

        CfgAgencyEligibility cfgAgencyEligibility = new CfgAgencyEligibility();

        cfgAgencyEligibility.setAgencyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgAgencyEligibility.setAgencyDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgAgencyEligibility.setAgencyType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgAgencyEligibility;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAgencyEligibility> all = cfgAgencyEligibilityRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAgencyEligibility cfgAgencyEligibility:all){
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgAgencyEligibility.getAgencyCode());
            createCell(row, 1, cfgAgencyEligibility.getAgencyDesc());
            createCell(row, 2, cfgAgencyEligibility.getAgencyType());

            rowIndex++;
        }
    }
}
