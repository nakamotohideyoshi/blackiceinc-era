package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktEqtSpc;
import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtSpcRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktEqtSpcObjectMapper {

    CfgMktEqtSpcRepository cfgMktEqtSpcRepository;

    @Autowired
    public CfgMktEqtSpcObjectMapper(CfgMktEqtSpcRepository cfgMktEqtSpcRepository){
        this.cfgMktEqtSpcRepository = cfgMktEqtSpcRepository;
    }

    public List<CfgMktEqtSpc> extractData(XSSFSheet sheet) {
        List<CfgMktEqtSpc> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgMktEqtSpc createRow(Row row) {

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
        for (CfgMktEqtSpc cfgMktEqtSpc:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktEqtSpc.getMktProductType());
            row.createCell(1).setCellValue(cfgMktEqtSpc.getUnderlying());
            row.createCell(2).setCellValue(cfgMktEqtSpc.getDiversifiedEquity());
            row.createCell(3).setCellValue(cfgMktEqtSpc.getDiversifiedIndex());
            row.createCell(4).setCellValue(cfgMktEqtSpc.getLiquidEquity());
            row.createCell(5).setCellValue(cfgMktEqtSpc.getRiskWeight());

            rowIndex++;
        }
    }
}
