package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtGnr;
import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtGnrRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktEqtGnrObjectMapper extends AbstractObjectMapper {

    CfgMktEqtGnrRepository cfgMktEqtGnrRepository;

    @Autowired
    public CfgMktEqtGnrObjectMapper(CfgMktEqtGnrRepository cfgMktEqtGnrRepository) {
        this.cfgMktEqtGnrRepository = cfgMktEqtGnrRepository;
    }

    CfgMktEqtGnr createRow(Row row) {
        CfgMktEqtGnr cfgMktEqtGnr = new CfgMktEqtGnr();

        cfgMktEqtGnr.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktEqtGnr.setUnderlying(getStringValue(row.getCell(1)));
        cfgMktEqtGnr.setRiskWeight(getDoubleValue(row.getCell(2)));

        return cfgMktEqtGnr;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktEqtGnr> all = cfgMktEqtGnrRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktEqtGnr cfgMktEqtGnr : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktEqtGnr.getMktProductType());
            createCell(row, 1, cfgMktEqtGnr.getUnderlying());
            createCell(row, 2, cfgMktEqtGnr.getRiskWeight());

            rowIndex++;
        }
    }
}
