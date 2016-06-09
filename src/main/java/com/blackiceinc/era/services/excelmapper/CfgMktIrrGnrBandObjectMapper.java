package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrBand;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrBandRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrBandObjectMapper extends AbstractObjectMapper<CfgObject> {

    CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository;

    @Autowired
    public CfgMktIrrGnrBandObjectMapper(CfgMktIrrGnrBandRepository cfgMktIrrGnrBandRepository) {
        this.cfgMktIrrGnrBandRepository = cfgMktIrrGnrBandRepository;
    }

    @Override
    CfgMktIrrGnrBand createRow(Row row) {
        CfgMktIrrGnrBand cfgMktIrrGnrBand = new CfgMktIrrGnrBand();

        cfgMktIrrGnrBand.setCode(getStringValue(row.getCell(0)));
        cfgMktIrrGnrBand.setRiskWeight(getDoubleValue(row.getCell(1)));

        return cfgMktIrrGnrBand;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrBand> all = cfgMktIrrGnrBandRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrBand cfgMktIrrGnrBand : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrBand.getCode());
            createCell(row, 1, cfgMktIrrGnrBand.getRiskWeight());

            rowIndex++;
        }
    }
}
