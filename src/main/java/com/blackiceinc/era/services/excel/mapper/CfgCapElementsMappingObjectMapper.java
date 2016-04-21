package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsMappingObjectMapper extends AbstractObjectMapper<CfgCapElementsMapping> {

    private CfgCapElementsMappingRepository cfgCapElementsMappingRepository;

    @Autowired
    public CfgCapElementsMappingObjectMapper(CfgCapElementsMappingRepository cfgCapElementsMappingRepository) {
        this.cfgCapElementsMappingRepository = cfgCapElementsMappingRepository;
    }

    @Override
    CfgCapElementsMapping createRow(Row row) {
        CfgCapElementsMapping cfgCapElementsMapping = new CfgCapElementsMapping();

        cfgCapElementsMapping.setCapElements(getStringValue(row.getCell(0)));
        cfgCapElementsMapping.setGlCode(getStringValue(row.getCell(1)));
        cfgCapElementsMapping.setNote(getStringValue(row.getCell(2)));

        return cfgCapElementsMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsMapping> all = cfgCapElementsMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsMapping cfgCapElementsMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCapElementsMapping.getCapElements());
            createCell(row, 1, cfgCapElementsMapping.getGlCode());
            createCell(row, 2, cfgCapElementsMapping.getNote());

            rowIndex++;
        }
    }
}
