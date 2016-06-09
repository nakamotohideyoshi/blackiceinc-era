package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgOpsFormulaDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgOpsFormulaObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgOpsFormulaImportService implements CfgImportService {
    public static final String OPS_FORMULA = "OPS_FORMULA";

    @Autowired
    private CfgOpsFormulaObjectMapper cfgOpsFormulaObjectMapper;

    @Autowired
    private CfgOpsFormulaDaoCustom cfgOpsFormulaDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(OPS_FORMULA, workbook,
                cfgOpsFormulaObjectMapper, cfgOpsFormulaDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet opsFormulaSheet = ImportUtil.getSheet(OPS_FORMULA, workbook);
        cfgOpsFormulaObjectMapper.importData(opsFormulaSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 35;
    }
}
