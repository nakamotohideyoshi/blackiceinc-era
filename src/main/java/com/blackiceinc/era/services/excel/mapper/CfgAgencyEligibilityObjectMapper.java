package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAgencyEligibility;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
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
public class CfgAgencyEligibilityObjectMapper {

    CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository;

    @Autowired
    public CfgAgencyEligibilityObjectMapper(CfgAgencyEligibilityRepository cfgAgencyEligibilityRepository){
        this.cfgAgencyEligibilityRepository = cfgAgencyEligibilityRepository;
    }

    public List<CfgAgencyEligibility> extractData(XSSFSheet sheet) {
        List<CfgAgencyEligibility> result = new ArrayList<>();

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

    private CfgAgencyEligibility createRow(Row row) {

        CfgAgencyEligibility cfgAgencyEligibility = new CfgAgencyEligibility();

        cfgAgencyEligibility.setAgencyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgAgencyEligibility.setAgencyDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgAgencyEligibility.setAgencyType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgAgencyEligibility;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAgencyEligibility> all = cfgAgencyEligibilityRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAgencyEligibility cfgAgencyEligibility:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgAgencyEligibility.getAgencyCode());
            row.createCell(1).setCellValue(cfgAgencyEligibility.getAgencyDesc());
            row.createCell(2).setCellValue(cfgAgencyEligibility.getAgencyType());

            rowIndex++;
        }
    }
}
