package com.blackiceinc.era.services.excel.mapper;


import com.blackiceinc.era.persistence.erau.model.CfgFxProdMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgFxProdMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgFxProdMappingObjectMapper extends AbstractObjectMapper<CfgFxProdMapping> {

    CfgFxProdMappingRepository cfgFxProdMappingRepository;

    @Autowired
    public CfgFxProdMappingObjectMapper(CfgFxProdMappingRepository cfgFxProdMappingRepository) {
        this.cfgFxProdMappingRepository = cfgFxProdMappingRepository;
    }

    @Override
    CfgFxProdMapping createRow(Row row) {
        CfgFxProdMapping cfgFxProdMapping = new CfgFxProdMapping();

        cfgFxProdMapping.setGlCode(getStringValue(row.getCell(0)));
        cfgFxProdMapping.setFxProdType(getStringValue(row.getCell(1)));
        cfgFxProdMapping.setGlCodeDesc(getStringValue(row.getCell(2)));

        return cfgFxProdMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgFxProdMapping> all = cfgFxProdMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgFxProdMapping cfgFxProdMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgFxProdMapping.getGlCode());
            createCell(row, 1, cfgFxProdMapping.getFxProdType());
            createCell(row, 2, cfgFxProdMapping.getGlCodeDesc());

            rowIndex++;
        }
    }
}
