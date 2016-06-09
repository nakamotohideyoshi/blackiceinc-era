package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgAddOnDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgAddOnObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgAddOnImportService implements CfgImportService {
    public static final String ADD_ON = "ADD_ON";

    @Autowired
    private CfgAddOnObjectMapper cfgAddOnObjectMapper;

    @Autowired
    private CfgAddOnDaoCustom cfgAddOnDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(ADD_ON, workbook,
                cfgAddOnObjectMapper, cfgAddOnDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet addOnSheet = ImportUtil.getSheet(ADD_ON, workbook);
        cfgAddOnObjectMapper.importData(addOnSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 15;
    }
}
