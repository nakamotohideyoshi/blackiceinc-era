package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrIntraDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgMktIrrGnrIntraObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgMktIrrGnrIntraImportService implements CfgImportService {
    public static final String MKT_IRR_GNR_INTRA = "MKT_IRR_GNR_INTRA";

    @Autowired
    private CfgMktIrrGnrIntraObjectMapper cfgMktIrrGnrIntraObjectMapper;

    @Autowired
    private CfgMktIrrGnrIntraDaoCustom cfgMktIrrGnrIntraDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(MKT_IRR_GNR_INTRA, workbook,
                cfgMktIrrGnrIntraObjectMapper, cfgMktIrrGnrIntraDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet mktIrrGnrIntraSheet = ImportUtil.getSheet(MKT_IRR_GNR_INTRA, workbook);
        cfgMktIrrGnrIntraObjectMapper.importData(mktIrrGnrIntraSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 28;
    }
}
