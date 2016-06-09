package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgReclassDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgReclassObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgReclassImportService implements CfgImportService {
    public static final String RECLASS = "RECLASS";

    @Autowired
    private CfgReclassObjectMapper cfgReclassObjectMapper;

    @Autowired
    private CfgReclassDaoCustom cfgReclassDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(RECLASS, workbook,
                cfgReclassObjectMapper, cfgReclassDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet reclassSheet = ImportUtil.getSheet(RECLASS, workbook);
        cfgReclassObjectMapper.importData(reclassSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 18;
    }
}
