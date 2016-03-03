package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsFormula;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsFormulaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgOpsFormulaObjectMapper {

    CfgOpsFormulaRepository cfgOpsFormulaRepository;

    @Autowired
    public CfgOpsFormulaObjectMapper(CfgOpsFormulaRepository cfgOpsFormulaRepository){
        this.cfgOpsFormulaRepository = cfgOpsFormulaRepository;
    }

    public List<CfgOpsFormula> extractData(XSSFSheet sheet) {
        List<CfgOpsFormula> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgOpsFormula createRow(Row row) {

        CfgOpsFormula cfgOpsFormula = new CfgOpsFormula();

        cfgOpsFormula.setBasicIndicator(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgOpsFormula.setFormula(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgOpsFormula;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsFormula> all = cfgOpsFormulaRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsFormula cfgOpsFormula:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgOpsFormula.getBasicIndicator());
            row.createCell(1).setCellValue(cfgOpsFormula.getFormula());

            rowIndex++;
        }
    }
}
