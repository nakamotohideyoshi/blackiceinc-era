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
public class CfgMktEqtSpcObjectMapper extends AbstractObjectMapper {

    CfgMktEqtSpcRepository cfgMktEqtSpcRepository;

    @Autowired
    public CfgMktEqtSpcObjectMapper(CfgMktEqtSpcRepository cfgMktEqtSpcRepository) {
        this.cfgMktEqtSpcRepository = cfgMktEqtSpcRepository;
    }

    CfgMktEqtSpc createRow(Row row) {
        CfgMktEqtSpc cfgMktEqtSpc = new CfgMktEqtSpc();

        cfgMktEqtSpc.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktEqtSpc.setUnderlying(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktEqtSpc.setDiversifiedEquity(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktEqtSpc.setDiversifiedIndex(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgMktEqtSpc.setLiquidEquity(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgMktEqtSpc.setRiskWeight(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : null);

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
