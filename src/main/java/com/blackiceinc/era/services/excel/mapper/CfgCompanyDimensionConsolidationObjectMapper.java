package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDimensionConsolidationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCompanyDimensionConsolidationObjectMapper extends AbstractObjectMapper {
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;

    @Autowired
    public CfgCompanyDimensionConsolidationObjectMapper(CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository) {
        this.cfgCompanyDimensionConsolidationRepository = cfgCompanyDimensionConsolidationRepository;
    }

    CfgCompanyDimensionConsolidation createRow(Row row) {
        CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation = new CfgCompanyDimensionConsolidation();

        cfgCompanyDimensionConsolidation.setCompanyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setEntityCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setConsoMode(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setConsoPerct(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgCompanyDimensionConsolidation;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCompanyDimensionConsolidation> all = cfgCompanyDimensionConsolidationRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCompanyDimensionConsolidation cfgCompanyDimension : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCompanyDimension.getCompanyCode());
            createCell(row, 1, cfgCompanyDimension.getEntityCode());
            createCell(row, 2, cfgCompanyDimension.getConsoMode());
            createCell(row, 3, cfgCompanyDimension.getConsoPerct());

            rowIndex++;
        }
    }
}
