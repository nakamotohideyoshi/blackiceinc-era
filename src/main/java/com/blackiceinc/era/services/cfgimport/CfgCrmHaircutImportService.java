package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCrmHaircutDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCrmHaircutObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCrmHaircutImportService implements CfgImportService {
    public static final String CRM_HAIRCUT = "CRM_HAIRCUT";

    @Autowired
    private CfgCrmHaircutObjectMapper cfgCrmHaircutObjectMapper;

    @Autowired
    private CfgCrmHaircutDaoCustom cfgCrmHaircutDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CRM_HAIRCUT, workbook,
                cfgCrmHaircutObjectMapper, cfgCrmHaircutDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet crmHaircutSheet = ImportUtil.getSheet(CRM_HAIRCUT, workbook);
        cfgCrmHaircutObjectMapper.importData(crmHaircutSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 17;
    }
}
