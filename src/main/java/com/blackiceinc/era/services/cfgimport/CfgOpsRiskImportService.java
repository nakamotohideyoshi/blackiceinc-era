package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgOpsRiskDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgOpsRiskObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgOpsRiskImportService implements CfgImportService {
    public static final String OPS_RISK = "OPS_RISK";

    @Autowired
    private CfgOpsRiskObjectMapper cfgOpsRiskObjectMapper;

    @Autowired
    private CfgOpsRiskDaoCustom cfgOpsRiskDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(OPS_RISK, workbook,
                cfgOpsRiskObjectMapper, cfgOpsRiskDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet opsRiskSheet = ImportUtil.getSheet(OPS_RISK, workbook);
        cfgOpsRiskObjectMapper.importData(opsRiskSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 36;
    }
}
