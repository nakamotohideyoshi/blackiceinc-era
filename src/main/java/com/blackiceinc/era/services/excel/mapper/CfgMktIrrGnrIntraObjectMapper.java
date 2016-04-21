package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrIntraRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrIntraObjectMapper extends AbstractObjectMapper<CfgMktIrrGnrIntra> {

    private CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;

    @Autowired
    public CfgMktIrrGnrIntraObjectMapper(CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository) {
        this.cfgMktIrrGnrIntraRepository = cfgMktIrrGnrIntraRepository;
    }

    @Override
    CfgMktIrrGnrIntra createRow(Row row) {
        CfgMktIrrGnrIntra cfgMktIrrGnrIntra = new CfgMktIrrGnrIntra();

        cfgMktIrrGnrIntra.setCode(getStringValue(row.getCell(0)));
        cfgMktIrrGnrIntra.setZoneCode(getStringValue(row.getCell(1)));
        cfgMktIrrGnrIntra.setRiskWeight(getDoubleValue(row.getCell(2)));

        return cfgMktIrrGnrIntra;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrIntra> all = cfgMktIrrGnrIntraRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrIntra cfgMktIrrGnrIntra : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrIntra.getCode());
            createCell(row, 1, cfgMktIrrGnrIntra.getZoneCode());
            createCell(row, 2, cfgMktIrrGnrIntra.getRiskWeight());

            rowIndex++;
        }
    }
}
