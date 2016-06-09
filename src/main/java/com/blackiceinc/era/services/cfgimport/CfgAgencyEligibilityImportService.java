package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgAgencyEligibilityDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgAgencyEligibilityObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgAgencyEligibilityImportService implements CfgImportService {
    public static final String AGENCY_ELIGIBILITY = "AGENCY_ELIGIBILITY";

    @Autowired
    private CfgAgencyEligibilityObjectMapper cfgAgencyEligibilityObjectMapper;

    @Autowired
    private CfgAgencyEligibilityDaoCustom cfgAgencyEligibilityDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(AGENCY_ELIGIBILITY, workbook,
                cfgAgencyEligibilityObjectMapper, cfgAgencyEligibilityDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet agencyEligibilitySheet = ImportUtil.getSheet(AGENCY_ELIGIBILITY, workbook);
        cfgAgencyEligibilityObjectMapper.importData(agencyEligibilitySheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 11;
    }
}
