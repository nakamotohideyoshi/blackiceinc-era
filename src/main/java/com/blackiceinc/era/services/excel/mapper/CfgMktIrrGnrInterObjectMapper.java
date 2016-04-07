package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrInterRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrGnrInterObjectMapper extends AbstractObjectMapper<CfgMktIrrGnrInter> {

    CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Autowired
    public CfgMktIrrGnrInterObjectMapper(CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository) {
        this.cfgMktIrrGnrInterRepository = cfgMktIrrGnrInterRepository;
    }

    @Override
    CfgMktIrrGnrInter createRow(Row row) {
        CfgMktIrrGnrInter cfgMktIrrGnrInter = new CfgMktIrrGnrInter();

        cfgMktIrrGnrInter.setCode(getStringValue(row.getCell(0)));
        cfgMktIrrGnrInter.setZoneCode1(getStringValue(row.getCell(1)));
        cfgMktIrrGnrInter.setZoneCode2(getStringValue(row.getCell(2)));
        cfgMktIrrGnrInter.setRiskWeight(getDoubleValue(row.getCell(3)));

        return cfgMktIrrGnrInter;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> all = cfgMktIrrGnrInterRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrInter cfgMktIrrGnrInter : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrGnrInter.getCode());
            createCell(row, 1, cfgMktIrrGnrInter.getZoneCode1());
            createCell(row, 2, cfgMktIrrGnrInter.getZoneCode2());
            createCell(row, 3, cfgMktIrrGnrInter.getRiskWeight());

            rowIndex++;
        }
    }
}
