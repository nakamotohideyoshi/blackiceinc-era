package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktComDrtDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktComDrtObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktComDrtImportService implements CfgImportService {
    public static final String MKT_COM_DRT = "MKT_COM_DRT";

    @Autowired
    private CfgMktComDrtObjectMapper cfgMktComDrtObjectMapper;

    @Autowired
    private CfgMktComDrtDaoCustom cfgMktComDrtDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_COM_DRT, workbook,
                cfgMktComDrtObjectMapper, cfgMktComDrtDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktComDrtSheet = ImportUtil.getSheet(MKT_COM_DRT, workbook);
        cfgMktComDrtObjectMapper.importData(mktComDrtSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 32;
    }
}
