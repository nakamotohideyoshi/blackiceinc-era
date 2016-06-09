package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckDefDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgReclassCheckDefObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgReclassCheckDefImportService implements CfgImportService {
    public static final String RECLASS_CHECK_DEF = "RECLASS_CHECK_DEF";

    @Autowired
    private CfgReclassCheckDefObjectMapper cfgReclassCheckDefObjectMapper;

    @Autowired
    private CfgReclassCheckDefDaoCustom cfgReclassCheckDefDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(RECLASS_CHECK_DEF, workbook,
                cfgReclassCheckDefObjectMapper, cfgReclassCheckDefDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet reclassCheckDefSheet = ImportUtil.getSheet(RECLASS_CHECK_DEF, workbook);
        cfgReclassCheckDefObjectMapper.importData(reclassCheckDefSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
