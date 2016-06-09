package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtGnrDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktEqtGnrObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktEqtGnrImportService implements CfgImportService {
    public static final String MKT_EQT_GNR = "MKT_EQT_GNR";

    @Autowired
    private CfgMktEqtGnrObjectMapper cfgMktEqtGnrObjectMapper;

    @Autowired
    private CfgMktEqtGnrDaoCustom cfgMktEqtGnrDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_EQT_GNR, workbook,
                cfgMktEqtGnrObjectMapper, cfgMktEqtGnrDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktEqtGnrSheet = ImportUtil.getSheet(MKT_EQT_GNR, workbook);
        cfgMktEqtGnrObjectMapper.importData(mktEqtGnrSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 31;
    }
}
