package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsTypeDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCapElementsTypeObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCapElementsTypeImportService implements CfgImportService {
    public static final String CAP_ELEMENTS_TYPE = "CAP_ELEMENTS_TYPE";

    @Autowired
    private CfgCapElementsTypeObjectMapper cfgCapElementsTypeObjectMapper;

    @Autowired
    private CfgCapElementsTypeDaoCustom cfgCapElementsTypeDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CAP_ELEMENTS_TYPE, workbook,
                cfgCapElementsTypeObjectMapper, cfgCapElementsTypeDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet capElementsTypeSheet = ImportUtil.getSheet(CAP_ELEMENTS_TYPE, workbook);
        cfgCapElementsTypeObjectMapper.importData(capElementsTypeSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 38;
    }
}
