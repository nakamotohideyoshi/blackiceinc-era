package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgNonPerformingMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgNonPerformingMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgNonPerformingMappingObjectMapper extends AbstractObjectMapper<CfgNonPerformingMapping> {

    private CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository;

    @Autowired
    public CfgNonPerformingMappingObjectMapper(CfgNonPerformingMappingRepository cfgNonPerformingMappingRepository) {
        this.cfgNonPerformingMappingRepository = cfgNonPerformingMappingRepository;
    }

    @Override
    CfgNonPerformingMapping createRow(Row row) {
        CfgNonPerformingMapping cfgNonPerformingMapping = new CfgNonPerformingMapping();

        cfgNonPerformingMapping.setEraNplCode(getStringValue(row.getCell(0)));
        cfgNonPerformingMapping.setPerformingStatus(getStringValue(row.getCell(1)));

        return cfgNonPerformingMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgNonPerformingMapping> all = cfgNonPerformingMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgNonPerformingMapping cfgNonPerformingMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgNonPerformingMapping.getEraNplCode());
            createCell(row, 1, cfgNonPerformingMapping.getPerformingStatus());

            rowIndex++;
        }
    }
}
