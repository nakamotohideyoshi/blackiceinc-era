package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAssetClassMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgAssetClassMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgAssetClassMappingObjectMapper extends AbstractObjectMapper {

    CfgAssetClassMappingRepository cfgAssetClassMappingRepository;

    @Autowired
    public CfgAssetClassMappingObjectMapper(CfgAssetClassMappingRepository cfgAssetClassMappingRepository) {
        this.cfgAssetClassMappingRepository = cfgAssetClassMappingRepository;
    }

    CfgAssetClassMapping createRow(Row row) {

        CfgAssetClassMapping cfgAssetClassMapping = new CfgAssetClassMapping();

        cfgAssetClassMapping.setAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgAssetClassMapping.setEntityType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgAssetClassMapping.setProductType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgAssetClassMapping;
    }

    public void importCfgAssetClassMapping(XSSFSheet sheet) {
        List<CfgAssetClassMapping> all = cfgAssetClassMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAssetClassMapping cfgAssetClassMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgAssetClassMapping.getAssetClass());
            createCell(row, 1, cfgAssetClassMapping.getEntityType());
            createCell(row, 2, cfgAssetClassMapping.getProductType());

            rowIndex++;
        }
    }
}
