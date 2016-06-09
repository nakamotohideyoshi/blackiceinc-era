package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtSpcDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktEqtSpcObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktEqtSpcImportService implements CfgImportService {
    public static final String MKT_EQT_SPC = "MKT_EQT_SPC";

    @Autowired
    private CfgMktEqtSpcObjectMapper cfgMktEqtSpcObjectMapper;

    @Autowired
    private CfgMktEqtSpcDaoCustom cfgMktEqtSpcDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_EQT_SPC, workbook,
                cfgMktEqtSpcObjectMapper, cfgMktEqtSpcDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktEqtSpcSheet = ImportUtil.getSheet(MKT_EQT_SPC, workbook);
        cfgMktEqtSpcObjectMapper.importData(mktEqtSpcSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 30;
    }
}
