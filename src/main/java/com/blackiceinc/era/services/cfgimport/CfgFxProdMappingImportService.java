package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgFxProdMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgFxProdMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgFxProdMappingImportService implements CfgImportService {
    public static final String FX_PROD_MAPPING = "FX_PROD_MAPPING";

    @Autowired
    private CfgFxProdMappingObjectMapper cfgFxProdMappingObjectMapper;

    @Autowired
    private CfgFxProdMappingDaoCustom cfgFxProdMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(FX_PROD_MAPPING, workbook,
                cfgFxProdMappingObjectMapper, cfgFxProdMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet fxProdMappingSheet = ImportUtil.getSheet(FX_PROD_MAPPING, workbook);
        cfgFxProdMappingObjectMapper.importData(fxProdMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 23;
    }
}
