package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCcfMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCcfMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCcfMappingImportService implements CfgImportService {
    public static final String CCF_MAPPING = "CCF_MAPPING";

    @Autowired
    private CfgCcfMappingObjectMapper cfgCcfMappingObjectMapper;

    @Autowired
    private CfgCcfMappingDaoCustom cfgCcfMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CCF_MAPPING, workbook,
                cfgCcfMappingObjectMapper, cfgCcfMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet ccfMappingSheet = ImportUtil.getSheet(CCF_MAPPING, workbook);
        cfgCcfMappingObjectMapper.importData(ccfMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 15;
    }
}
