package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsLimitDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCapElementsLimitObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCapElementsLimitImportService implements CfgImportService {
    public static final String CAP_ELEMENTS_LIMIT = "CAP_ELEMENTS_LIMIT";

    @Autowired
    private CfgCapElementsLimitObjectMapper cfgCapElementsLimitObjectMapper;

    @Autowired
    private CfgCapElementsLimitDaoCustom cfgCapElementsLimitDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CAP_ELEMENTS_LIMIT, workbook,
                cfgCapElementsLimitObjectMapper, cfgCapElementsLimitDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet capElementsLimitSheet = ImportUtil.getSheet(CAP_ELEMENTS_LIMIT, workbook);
        cfgCapElementsLimitObjectMapper.importData(capElementsLimitSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 39;
    }
}
