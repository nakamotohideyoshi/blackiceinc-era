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

        cfgCompanyDimensionConsolidation.setCompanyCode(getStringValue(row.getCell(0)));
        cfgCompanyDimensionConsolidation.setEntityCode(getStringValue(row.getCell(1)));
        cfgCompanyDimensionConsolidation.setConsoMode(getStringValue(row.getCell(2)));
        cfgCompanyDimensionConsolidation.setConsoPerct(getDoubleValue(row.getCell(3)));

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
