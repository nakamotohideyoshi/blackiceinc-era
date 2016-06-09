package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsFormulaDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCapElementsFormulaObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCapElementsFormulaImportService implements CfgImportService {
    public static final String CAP_ELEMENTS_FORMULA = "CAP_ELEMENTS_FORMULA";

    @Autowired
    private CfgCapElementsFormulaObjectMapper cfgCapElementsFormulaObjectMapper;

    @Autowired
    private CfgCapElementsFormulaDaoCustom cfgCapElementsFormulaDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CAP_ELEMENTS_FORMULA, workbook,
                cfgCapElementsFormulaObjectMapper, cfgCapElementsFormulaDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet capElementsFormulaSheet = ImportUtil.getSheet(CAP_ELEMENTS_FORMULA, workbook);
        cfgCapElementsFormulaObjectMapper.importData(capElementsFormulaSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 41;
    }
}
