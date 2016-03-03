package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimension;
import com.blackiceinc.era.persistence.erau.model.CfgCompanyDimensionConsolidation;
import com.blackiceinc.era.persistence.erau.repository.CfgCompanyDimensionConsolidationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCompanyDimensionConsolidationObjectMapper {
    private CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository;

    @Autowired
    public CfgCompanyDimensionConsolidationObjectMapper(CfgCompanyDimensionConsolidationRepository cfgCompanyDimensionConsolidationRepository){
        this.cfgCompanyDimensionConsolidationRepository = cfgCompanyDimensionConsolidationRepository;
    }

    public List<CfgCompanyDimensionConsolidation> extractCfgCompanyDimensionsConsolidation(XSSFSheet sheet) {
        List<CfgCompanyDimensionConsolidation> result = new ArrayList<>();

        // Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgCompanyDimensionConsolidationObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgCompanyDimensionConsolidation createCfgCompanyDimensionConsolidationObj(Row row) {
        CfgCompanyDimensionConsolidation cfgCompanyDimensionConsolidation = new CfgCompanyDimensionConsolidation();

        cfgCompanyDimensionConsolidation.setCompanyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setEntityCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setConsoMode(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgCompanyDimensionConsolidation.setConsoPerct(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgCompanyDimensionConsolidation;
    }

    public void importCfgCompanyDimensionConsolidation(XSSFSheet sheet) {
        List<CfgCompanyDimensionConsolidation> all = cfgCompanyDimensionConsolidationRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCompanyDimensionConsolidation cfgCompanyDimension : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCompanyDimension.getCompanyCode());
            row.createCell(1).setCellValue(cfgCompanyDimension.getEntityCode());
            row.createCell(2).setCellValue(cfgCompanyDimension.getConsoMode());
            row.createCell(3).setCellValue(cfgCompanyDimension.getConsoPerct());

            rowIndex++;
        }
    }
}
