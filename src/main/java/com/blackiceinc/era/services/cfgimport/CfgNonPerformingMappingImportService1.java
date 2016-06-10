package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgNonPerformingMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgNonPerformingMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgNonPerformingMappingImportService1 implements CfgImportService {
    public static final String NON_PERFORMING_MAPPING = "NON_PERFORMING_MAPPING";

    @Autowired
    private CfgNonPerformingMappingObjectMapper cfgNonPerformingMappingObjectMapper;

    @Autowired
    private CfgNonPerformingMappingDaoCustom cfgNonPerformingMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(NON_PERFORMING_MAPPING, workbook,
                cfgNonPerformingMappingObjectMapper, cfgNonPerformingMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet nonPerformingMappingSheet = ImportUtil.getSheet(NON_PERFORMING_MAPPING, workbook);
        cfgNonPerformingMappingObjectMapper.importData(nonPerformingMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 10;
    }
}
