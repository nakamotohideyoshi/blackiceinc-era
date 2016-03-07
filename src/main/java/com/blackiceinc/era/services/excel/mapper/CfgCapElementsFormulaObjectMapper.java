package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsFormula;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsFormulaRepository;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsFormulaObjectMapper extends AbstractObjectMapper {

    CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;

    @Autowired
    public CfgCapElementsFormulaObjectMapper(CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository) {
        this.cfgCapElementsFormulaRepository = cfgCapElementsFormulaRepository;
    }

    CfgCapElementsFormula createRow(Row row) throws PrimaryColumnMappingException {

        CfgCapElementsFormula cfgCapElementsFormula = new CfgCapElementsFormula();

        Cell cell0 = row.getCell(0);
        if (cell0 != null) {
            cfgCapElementsFormula.setCapElements(cell0.getStringCellValue());
        } else {
            log.warn("Null value for a primary column");
            throw new PrimaryColumnMappingException();
        }
        cfgCapElementsFormula.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCapElementsFormula.setFormula(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgCapElementsFormula;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsFormula> all = cfgCapElementsFormulaRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsFormula cfgCapElementsFormula : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElementsFormula.getCapElements());
            row.createCell(1).setCellValue(cfgCapElementsFormula.getDescription());
            row.createCell(2).setCellValue(cfgCapElementsFormula.getFormula());

            rowIndex++;
        }
    }
}
