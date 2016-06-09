package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCapElementsMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCapElementsMappingImportService implements CfgImportService {
    public static final String CAP_ELEMENTS_MAPPING = "CAP_ELEMENTS_MAPPING";

    @Autowired
    private CfgCapElementsMappingObjectMapper cfgCapElementsMappingObjectMapper;

    @Autowired
    private CfgCapElementsMappingDaoCustom cfgCapElementsMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CAP_ELEMENTS_MAPPING, workbook,
                cfgCapElementsMappingObjectMapper, cfgCapElementsMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet capElementsMappingSheet = ImportUtil.getSheet(CAP_ELEMENTS_MAPPING, workbook);
        cfgCapElementsMappingObjectMapper.importData(capElementsMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 40;
    }
}
