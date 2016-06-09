package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCapElementsObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCapElementsImportService implements CfgImportService {

    public static final String CAP_ELEMENTS = "CAP_ELEMENTS";

    @Autowired
    private CfgCapElementsObjectMapper cfgCapElementsObjectMapper;

    @Autowired
    private CfgCapElementsDaoCustom cfgCapElementsDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CAP_ELEMENTS, workbook,
                cfgCapElementsObjectMapper, cfgCapElementsDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet capElementsSheet = ImportUtil.getSheet(CAP_ELEMENTS, workbook);
        cfgCapElementsObjectMapper.importData(capElementsSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 37;
    }
}
