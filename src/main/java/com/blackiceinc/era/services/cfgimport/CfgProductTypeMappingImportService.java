package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgProductTypeMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgProductTypeMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgProductTypeMappingImportService implements CfgImportService {
    public static final String PRODUCT_TYPE_MAPPING = "PRODUCT_TYPE_MAPPING";

    @Autowired
    private CfgProductTypeMappingObjectMapper cfgProductTypeMappingObjectMapper;

    @Autowired
    private CfgProductTypeMappingDaoCustom cfgProductTypeMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(PRODUCT_TYPE_MAPPING, workbook,
                cfgProductTypeMappingObjectMapper, cfgProductTypeMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet productTypeMappingSheet = ImportUtil.getSheet(PRODUCT_TYPE_MAPPING, workbook);
        cfgProductTypeMappingObjectMapper.importData(productTypeMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 8;
    }
}
