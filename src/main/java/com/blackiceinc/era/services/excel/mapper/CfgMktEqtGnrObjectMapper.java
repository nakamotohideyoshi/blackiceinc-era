package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktEqtGnr;
import com.blackiceinc.era.persistence.erau.repository.CfgMktEqtGnrRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktEqtGnrObjectMapper {

    CfgMktEqtGnrRepository cfgMktEqtGnrRepository;

    @Autowired
    public CfgMktEqtGnrObjectMapper(CfgMktEqtGnrRepository cfgMktEqtGnrRepository){
        this.cfgMktEqtGnrRepository = cfgMktEqtGnrRepository;
    }

    public List<CfgMktEqtGnr> extractData(XSSFSheet sheet) {
        List<CfgMktEqtGnr> result = new ArrayList<>();

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

    private CfgMktEqtGnr createRow(Row row) {

        CfgMktEqtGnr cfgMktEqtGnr = new CfgMktEqtGnr();

        cfgMktEqtGnr.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktEqtGnr.setUnderlying(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktEqtGnr.setRiskWeight(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);

        return cfgMktEqtGnr;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktEqtGnr> all = cfgMktEqtGnrRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktEqtGnr cfgMktEqtGnr:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktEqtGnr.getMktProductType());
            row.createCell(1).setCellValue(cfgMktEqtGnr.getUnderlying());
            row.createCell(2).setCellValue(cfgMktEqtGnr.getRiskWeight());

            rowIndex++;
        }
    }
}
