package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCreditMeasureDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCreditMeasureObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCreditMeasureImportService implements CfgImportService {
    public static final String CREDIT_MEASURE = "CREDIT_MEASURE";

    @Autowired
    private CfgCreditMeasureObjectMapper cfgCreditMeasureObjectMapper;

    @Autowired
    private CfgCreditMeasureDaoCustom cfgCreditMeasureDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CREDIT_MEASURE, workbook,
                cfgCreditMeasureObjectMapper, cfgCreditMeasureDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet creditMeasureSheet = ImportUtil.getSheet(CREDIT_MEASURE, workbook);
        cfgCreditMeasureObjectMapper.importData(creditMeasureSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 13;
    }
}
