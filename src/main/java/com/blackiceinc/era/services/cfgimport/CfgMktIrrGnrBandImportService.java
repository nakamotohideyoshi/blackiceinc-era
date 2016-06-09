package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrBandDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktIrrGnrBandObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktIrrGnrBandImportService implements CfgImportService {
    public static final String MKT_IRR_GNR_BAND = "MKT_IRR_GNR_BAND";

    @Autowired
    private CfgMktIrrGnrBandObjectMapper cfgMktIrrGnrBandObjectMapper;

    @Autowired
    private CfgMktIrrGnrBandDaoCustom cfgMktIrrGnrBandDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_IRR_GNR_BAND, workbook,
                cfgMktIrrGnrBandObjectMapper, cfgMktIrrGnrBandDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktIrrGnrBandSheet = ImportUtil.getSheet(MKT_IRR_GNR_BAND, workbook);
        cfgMktIrrGnrBandObjectMapper.importData(mktIrrGnrBandSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 27;
    }
}
