package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsRiskRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsRiskObjectMapper extends AbstractObjectMapper {

    CfgOpsRiskRepository cfgOpsRiskRepository;

    @Autowired
    public CfgOpsRiskObjectMapper(CfgOpsRiskRepository cfgOpsRiskRepository) {
        this.cfgOpsRiskRepository = cfgOpsRiskRepository;
    }

    CfgOpsRisk createRow(Row row) {
        CfgOpsRisk cfgOpsRisk = new CfgOpsRisk();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            switch (cell0.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgOpsRisk.setCode(cell0 != null ? String.valueOf((long) cell0.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgOpsRisk.setCode(cell0 != null ? cell0.getStringCellValue() : null);
                    break;
            }
        }

        cfgOpsRisk.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgOpsRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsRisk> all = cfgOpsRiskRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsRisk cfgOpsRisk : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgOpsRisk.getCode());
            createCell(row, 1, cfgOpsRisk.getRiskWeight());

            rowIndex++;
        }
    }
}
