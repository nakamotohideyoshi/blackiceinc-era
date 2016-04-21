package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtSpc;
import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtSpcRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktEqtSpcObjectMapper extends AbstractObjectMapper<CfgMktEqtSpc> {

    private CfgMktEqtSpcRepository cfgMktEqtSpcRepository;

    @Autowired
    public CfgMktEqtSpcObjectMapper(CfgMktEqtSpcRepository cfgMktEqtSpcRepository) {
        this.cfgMktEqtSpcRepository = cfgMktEqtSpcRepository;
    }

    @Override
    CfgMktEqtSpc createRow(Row row) {
        CfgMktEqtSpc cfgMktEqtSpc = new CfgMktEqtSpc();

        cfgMktEqtSpc.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktEqtSpc.setUnderlying(getStringValue(row.getCell(1)));
        cfgMktEqtSpc.setDiversifiedEquity(getStringValue(row.getCell(2)));
        cfgMktEqtSpc.setDiversifiedIndex(getStringValue(row.getCell(3)));
        cfgMktEqtSpc.setLiquidEquity(getStringValue(row.getCell(4)));
        cfgMktEqtSpc.setRiskWeight(getDoubleValue(row.getCell(5)));

        return cfgMktEqtSpc;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktEqtSpc> all = cfgMktEqtSpcRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktEqtSpc cfgMktEqtSpc : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktEqtSpc.getMktProductType());
            createCell(row, 1, cfgMktEqtSpc.getUnderlying());
            createCell(row, 2, cfgMktEqtSpc.getDiversifiedEquity());
            createCell(row, 3, cfgMktEqtSpc.getDiversifiedIndex());
            createCell(row, 4, cfgMktEqtSpc.getLiquidEquity());
            createCell(row, 5, cfgMktEqtSpc.getRiskWeight());

            rowIndex++;
        }
    }
}
