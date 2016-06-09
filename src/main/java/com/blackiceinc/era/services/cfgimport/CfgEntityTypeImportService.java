package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgEntityTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgEntityTypeImportService implements CfgImportService {

    public static final String ENTITY_TYPE = "ENTITY_TYPE";

    @Autowired
    private CfgEntityTypeObjectMapper cfgEntityTypeObjectMapper;

    @Autowired
    private CfgEntityTypeDaoCustom cfgEntityTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(ENTITY_TYPE, workbook,
                cfgEntityTypeObjectMapper, cfgEntityTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet entityTypeSheet = ImportUtil.getSheet(ENTITY_TYPE, workbook);
        cfgEntityTypeObjectMapper.importData(entityTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 5;
    }
}
