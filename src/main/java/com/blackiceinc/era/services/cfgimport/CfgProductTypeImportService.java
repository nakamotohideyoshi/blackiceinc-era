package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgProductTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgProductTypeImportService implements CfgImportService {

    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";

    @Autowired
    private CfgProductTypeObjectMapper cfgProductTypeObjectMapper;

    @Autowired
    private CfgProductTypeDaoCustom cfgProductTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(PRODUCT_TYPE, workbook,
                cfgProductTypeObjectMapper, cfgProductTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet productTypeSheet = ImportUtil.getSheet(PRODUCT_TYPE, workbook);
        cfgProductTypeObjectMapper.importData(productTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 7;
    }
}
