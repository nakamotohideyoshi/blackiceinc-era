package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgFxProdTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgFxProdTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgFxProdTypeImportService implements CfgImportService {
    public static final String FX_PROD_TYPE = "FX_PROD_TYPE";

    @Autowired
    private CfgFxProdTypeObjectMapper cfgFxProdTypeObjectMapper;

    @Autowired
    private CfgFxProdTypeDaoCustom cfgFxProdTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(FX_PROD_TYPE, workbook,
                cfgFxProdTypeObjectMapper, cfgFxProdTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet fxProdTypeSheet = ImportUtil.getSheet(FX_PROD_TYPE, workbook);
        cfgFxProdTypeObjectMapper.importData(fxProdTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 22;
    }
}
