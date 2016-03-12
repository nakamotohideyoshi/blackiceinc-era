package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsMappingRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsMappingObjectMapper extends AbstractObjectMapper {

    CfgCapElementsMappingRepository cfgCapElementsMappingRepository;

    @Autowired
    public CfgCapElementsMappingObjectMapper(CfgCapElementsMappingRepository cfgCapElementsMappingRepository) {
        this.cfgCapElementsMappingRepository = cfgCapElementsMappingRepository;
    }

    CfgCapElementsMapping createRow(Row row) {

        CfgCapElementsMapping cfgCapElementsMapping = new CfgCapElementsMapping();

        cfgCapElementsMapping.setCapElements(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);

        Cell cell1 = row.getCell(1);
        if (cell1 != null) {
            switch (cell1.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCapElementsMapping.setGlCode(cell1 != null ? String.valueOf((long) cell1.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCapElementsMapping.setGlCode(cell1 != null ? cell1.getStringCellValue() : null);
                    break;
            }
        }


        cfgCapElementsMapping.setNote(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

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
