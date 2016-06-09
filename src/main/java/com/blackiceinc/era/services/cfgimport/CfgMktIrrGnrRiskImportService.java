package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrRiskDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktIrrGnrRiskObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktIrrGnrRiskImportService implements CfgImportService {
    public static final String MKT_IRR_GNR_RISK = "MKT_IRR_GNR_RISK";

    @Autowired
    private CfgMktIrrGnrRiskObjectMapper cfgMktIrrGnrRiskObjectMapper;

    @Autowired
    private CfgMktIrrGnrRiskDaoCustom cfgMktIrrGnrRiskDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_IRR_GNR_RISK, workbook,
                cfgMktIrrGnrRiskObjectMapper, cfgMktIrrGnrRiskDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktIrrGnrRiskSheet = ImportUtil.getSheet(MKT_IRR_GNR_RISK, workbook);
        cfgMktIrrGnrRiskObjectMapper.importData(mktIrrGnrRiskSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 26;
    }
}
