package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOpsFormula;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsFormulaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsFormulaObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgOpsFormulaRepository cfgOpsFormulaRepository;

    @Autowired
    public CfgOpsFormulaObjectMapper(CfgOpsFormulaRepository cfgOpsFormulaRepository) {
        this.cfgOpsFormulaRepository = cfgOpsFormulaRepository;
    }

    @Override
    CfgOpsFormula createRow(Row row) {
        CfgOpsFormula cfgOpsFormula = new CfgOpsFormula();

        cfgOpsFormula.setBasicIndicator(getStringValue(row.getCell(0)));
        cfgOpsFormula.setFormula(getStringValue(row.getCell(1)));

        return cfgOpsFormula;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsFormula> all = cfgOpsFormulaRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsFormula cfgOpsFormula : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgOpsFormula.getBasicIndicator());
            createCell(row, 1, cfgOpsFormula.getFormula());

            rowIndex++;
        }
    }
}
