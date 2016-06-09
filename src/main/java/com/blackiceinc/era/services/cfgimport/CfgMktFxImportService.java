package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktFxDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktFxObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktFxImportService implements CfgImportService {
    public static final String MKT_FX = "MKT_FX";

    @Autowired
    private CfgMktFxObjectMapper cfgMktFxObjectMapper;

    @Autowired
    private CfgMktFxDaoCustom cfgMktFxDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_FX, workbook,
                cfgMktFxObjectMapper, cfgMktFxDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktFxSheet = ImportUtil.getSheet(MKT_FX, workbook);
        cfgMktFxObjectMapper.importData(mktFxSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 34;
    }
}
