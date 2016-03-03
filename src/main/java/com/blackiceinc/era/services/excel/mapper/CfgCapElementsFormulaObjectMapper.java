package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsFormula;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsFormulaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsFormulaObjectMapper {

    CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;

    @Autowired
    public CfgCapElementsFormulaObjectMapper(CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository){
        this.cfgCapElementsFormulaRepository = cfgCapElementsFormulaRepository;
    }

    public List<CfgCapElementsFormula> extractData(XSSFSheet sheet) {
        List<CfgCapElementsFormula> result = new ArrayList<>();

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

    private CfgCapElementsFormula createRow(Row row) {

        CfgCapElementsFormula cfgCapElementsFormula = new CfgCapElementsFormula();

        cfgCapElementsFormula.setCapElements(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCapElementsFormula.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgCapElementsFormula.setFormula(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgCapElementsFormula;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsFormula> all = cfgCapElementsFormulaRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsFormula cfgCapElementsFormula:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElementsFormula.getCapElements());
            row.createCell(1).setCellValue(cfgCapElementsFormula.getDescription());
            row.createCell(2).setCellValue(cfgCapElementsFormula.getFormula());

            rowIndex++;
        }
    }
}
