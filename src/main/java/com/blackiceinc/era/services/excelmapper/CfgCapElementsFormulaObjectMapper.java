package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsFormula;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsFormulaRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsFormulaObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository;

    @Autowired
    public CfgCapElementsFormulaObjectMapper(CfgCapElementsFormulaRepository cfgCapElementsFormulaRepository) {
        this.cfgCapElementsFormulaRepository = cfgCapElementsFormulaRepository;
    }

    @Override
    CfgCapElementsFormula createRow(Row row) {
        CfgCapElementsFormula cfgCapElementsFormula = new CfgCapElementsFormula();

        cfgCapElementsFormula.setCapElements(getStringValue(row.getCell(0)));
        cfgCapElementsFormula.setDescription(getStringValue(row.getCell(1)));
        cfgCapElementsFormula.setFormula(getStringValue(row.getCell(2)));

        return cfgCapElementsFormula;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsFormula> all = cfgCapElementsFormulaRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsFormula cfgCapElementsFormula : all) {
            if (cfgCapElementsFormula!=null){
                XSSFRow row = sheet.createRow(rowIndex);

                createCell(row, 0, cfgCapElementsFormula.getCapElements());
                createCell(row, 1, cfgCapElementsFormula.getDescription());
                createCell(row, 2, cfgCapElementsFormula.getFormula());

                rowIndex++;
            }
        }
    }
}
