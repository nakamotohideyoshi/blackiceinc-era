package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsProductTypeMappingObjectMapper extends AbstractObjectMapper<CfgOpsProductTypeMapping> {

    private CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;

    @Autowired
    public CfgOpsProductTypeMappingObjectMapper(CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository) {
        this.cfgOpsProductTypeMappingRepository = cfgOpsProductTypeMappingRepository;
    }

    @Override
    CfgOpsProductTypeMapping createRow(Row row) {
        CfgOpsProductTypeMapping cfgOpsProductTypeMapping = new CfgOpsProductTypeMapping();

        cfgOpsProductTypeMapping.setOpsProductType(getStringValue(row.getCell(0)));
        cfgOpsProductTypeMapping.setOpsGlCode(getStringValue(row.getCell(1)));
        cfgOpsProductTypeMapping.setOpsVibCode(getStringValue(row.getCell(2)));
        cfgOpsProductTypeMapping.setDescription(getStringValue(row.getCell(3)));
        // TODO: comment column is missing in database

        return cfgOpsProductTypeMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsProductTypeMapping> all = cfgOpsProductTypeMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsProductTypeMapping cfgOpsProductTypeMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgOpsProductTypeMapping.getOpsProductType());
            createCell(row, 1, cfgOpsProductTypeMapping.getOpsGlCode());
            createCell(row, 2, cfgOpsProductTypeMapping.getOpsVibCode());
            createCell(row, 3, cfgOpsProductTypeMapping.getDescription());

            rowIndex++;
        }
    }
}
