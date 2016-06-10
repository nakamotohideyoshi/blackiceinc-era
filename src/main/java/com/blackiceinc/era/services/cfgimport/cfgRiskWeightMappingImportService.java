package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgRiskWeightMappingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgRiskWeightMappingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgRiskWeightMappingImportService implements CfgImportService {
    public static final String RISK_WEIGHT_MAPPING = "RISK_WEIGHT_MAPPING";

    @Autowired
    private CfgRiskWeightMappingObjectMapper cfgRiskWeightMappingObjectMapper;

    @Autowired
    private CfgRiskWeightMappingDaoCustom cfgRiskWeightMappingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(RISK_WEIGHT_MAPPING, workbook,
                cfgRiskWeightMappingObjectMapper, cfgRiskWeightMappingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet riskWeightMappingSheet = ImportUtil.getSheet(RISK_WEIGHT_MAPPING, workbook);
        cfgRiskWeightMappingObjectMapper.importData(riskWeightMappingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 14;
    }
}
