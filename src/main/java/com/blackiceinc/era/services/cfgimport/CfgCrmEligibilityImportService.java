package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgCrmEligibilityDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgCrmEligibilityObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgCrmEligibilityImportService implements CfgImportService {
    public static final String CRM_ELIGIBILITY = "CRM_ELIGIBILITY";

    @Autowired
    private CfgCrmEligibilityObjectMapper cfgCrmEligibilityObjectMapper;

    @Autowired
    private CfgCrmEligibilityDaoCustom cfgCrmEligibilityDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(CRM_ELIGIBILITY, workbook,
                cfgCrmEligibilityObjectMapper, cfgCrmEligibilityDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet crmEligibilitySheet = ImportUtil.getSheet(CRM_ELIGIBILITY, workbook);
        cfgCrmEligibilityObjectMapper.importData(crmEligibilitySheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 16;
    }
}
