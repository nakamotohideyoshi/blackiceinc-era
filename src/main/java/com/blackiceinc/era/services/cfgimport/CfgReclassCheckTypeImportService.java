package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgReclassCheckTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgReclassCheckTypeImportService implements CfgImportService {
    public static final String RECLASS_CHECK_TYPE = "RECLASS_CHECK_TYPE";

    @Autowired
    private CfgReclassCheckTypeObjectMapper cfgReclassCheckTypeObjectMapper;

    @Autowired
    private CfgReclassCheckTypeDaoCustom cfgReclassCheckTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(RECLASS_CHECK_TYPE, workbook,
                cfgReclassCheckTypeObjectMapper, cfgReclassCheckTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet reclassCheckTypeSheet = ImportUtil.getSheet(RECLASS_CHECK_TYPE, workbook);
        cfgReclassCheckTypeObjectMapper.importData(reclassCheckTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 20;
    }
}
