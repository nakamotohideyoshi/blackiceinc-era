package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktComOthDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktComOthObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktComOthImportService implements CfgImportService {
    public static final String MKT_COM_OTH = "MKT_COM_OTH";

    @Autowired
    private CfgMktComOthObjectMapper cfgMktComOthObjectMapper;

    @Autowired
    private CfgMktComOthDaoCustom cfgMktComOthDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_COM_OTH, workbook,
                cfgMktComOthObjectMapper, cfgMktComOthDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktComOthSheet = ImportUtil.getSheet(MKT_COM_OTH, workbook);
        cfgMktComOthObjectMapper.importData(mktComOthSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 33;
    }
}
