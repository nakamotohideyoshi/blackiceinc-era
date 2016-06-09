package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrSpcRiskDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktIrrSpcRiskObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktIrrSpcRiskImportService implements CfgImportService {
    public static final String MKT_IRR_SPC_RISK = "MKT_IRR_SPC_RISK";

    @Autowired
    private CfgMktIrrSpcRiskObjectMapper cfgMktIrrSpcRiskObjectMapper;

    @Autowired
    private CfgMktIrrSpcRiskDaoCustom cfgMktIrrSpcRiskDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_IRR_SPC_RISK, workbook,
                cfgMktIrrSpcRiskObjectMapper, cfgMktIrrSpcRiskDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktIrrSpcRiskSheet = ImportUtil.getSheet(MKT_IRR_SPC_RISK, workbook);
        cfgMktIrrSpcRiskObjectMapper.importData(mktIrrSpcRiskSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 25;
    }
}
