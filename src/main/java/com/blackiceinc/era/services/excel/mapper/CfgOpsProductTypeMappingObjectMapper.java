package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductTypeMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeMappingRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsProductTypeMappingObjectMapper extends AbstractObjectMapper {

    CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository;

    @Autowired
    public CfgOpsProductTypeMappingObjectMapper(CfgOpsProductTypeMappingRepository cfgOpsProductTypeMappingRepository) {
        this.cfgOpsProductTypeMappingRepository = cfgOpsProductTypeMappingRepository;
    }

    CfgOpsProductTypeMapping createRow(Row row) {
        CfgOpsProductTypeMapping cfgOpsProductTypeMapping = new CfgOpsProductTypeMapping();

        cfgOpsProductTypeMapping.setOpsProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);

        Cell cell1 = row.getCell(1);
        if (cell1 != null) {
            switch (cell1.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgOpsProductTypeMapping.setOpsGlCode(cell1 != null ? String.valueOf((long) cell1.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgOpsProductTypeMapping.setOpsGlCode(cell1 != null ? cell1.getStringCellValue() : null);
                    break;
            }
        }

        cfgOpsProductTypeMapping.setOpsVibCode(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgOpsProductTypeMapping.setDescription(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        // TODO: comment column is missin in database

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
