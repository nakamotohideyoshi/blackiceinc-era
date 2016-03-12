package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmHaircutRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCrmHaircutObjectMapper extends AbstractObjectMapper {

    CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Autowired
    public CfgCrmHaircutObjectMapper(CfgCrmHaircutRepository cfgCrmHaircutRepository) {
        this.cfgCrmHaircutRepository = cfgCrmHaircutRepository;
    }

    CfgCrmHaircut createRow(Row row) {

        CfgCrmHaircut cfgCrmHaircut = new CfgCrmHaircut();

        cfgCrmHaircut.setEraColType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCrmHaircut.setEraEntityType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        Cell cell2 = row.getCell(2);
        if (cell2 != null) {
            switch (cell2.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setRiskBucket(String.valueOf((int) cell2.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setRiskBucket(cell2.getStringCellValue());
                    break;
            }
        }


        Cell cell3 = row.getCell(3);
        if (cell3 != null) {
            switch (cell3.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setMinResidualMaturity(String.valueOf((int) cell3.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setMinResidualMaturity(cell3.getStringCellValue());
                    break;
            }
        }


        Cell cell4 = row.getCell(4);
        if (cell4 != null) {
            switch (cell4.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setMaxResidualMaturity(String.valueOf((int) cell4.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setMaxResidualMaturity(cell4.getStringCellValue());
                    break;
            }
        }

        cfgCrmHaircut.setHaircut(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : null);

        return cfgCrmHaircut;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCrmHaircut> all = cfgCrmHaircutRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCrmHaircut cfgCrmHaircut : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCrmHaircut.getEraColType());
            createCell(row, 1, cfgCrmHaircut.getEraEntityType());
            createCell(row, 2, cfgCrmHaircut.getRiskBucket());
            createCell(row, 3, cfgCrmHaircut.getMinResidualMaturity());
            createCell(row, 4, cfgCrmHaircut.getMaxResidualMaturity());
            createCell(row, 5, cfgCrmHaircut.getHaircut());

            rowIndex++;
        }
    }
}
