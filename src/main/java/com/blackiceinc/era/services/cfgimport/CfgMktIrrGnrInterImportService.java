package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrInterDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktIrrGnrInterObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktIrrGnrInterImportService implements CfgImportService {
    public static final String MKT_IRR_GNR_INTER = "MKT_IRR_GNR_INTER";

    @Autowired
    private CfgMktIrrGnrInterObjectMapper cfgMktIrrGnrInterObjectMapper;

    @Autowired
    private CfgMktIrrGnrInterDaoCustom cfgMktIrrGnrInterDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_IRR_GNR_INTER, workbook,
                cfgMktIrrGnrInterObjectMapper, cfgMktIrrGnrInterDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktIrrGnrInterSheet = ImportUtil.getSheet(MKT_IRR_GNR_INTER, workbook);
        cfgMktIrrGnrInterObjectMapper.importData(mktIrrGnrInterSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 29;
    }
}
