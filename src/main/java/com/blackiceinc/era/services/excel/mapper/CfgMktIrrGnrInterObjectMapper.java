package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrInter;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrInterRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktIrrGnrInterObjectMapper {

    CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository;

    @Autowired
    public CfgMktIrrGnrInterObjectMapper(CfgMktIrrGnrInterRepository cfgMktIrrGnrInterRepository){
        this.cfgMktIrrGnrInterRepository = cfgMktIrrGnrInterRepository;
    }

    public List<CfgMktIrrGnrInter> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> result = new ArrayList<>();

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

    private CfgMktIrrGnrInter createRow(Row row) {

        CfgMktIrrGnrInter cfgMktIrrGnrInter = new CfgMktIrrGnrInter();

        cfgMktIrrGnrInter.setCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktIrrGnrInter.setZoneCode1(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktIrrGnrInter.setZoneCode2(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktIrrGnrInter.setRiskWeight(row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : null);

        return cfgMktIrrGnrInter;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrInter> all = cfgMktIrrGnrInterRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrInter cfgMktIrrGnrInter:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrInter.getCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrInter.getZoneCode1());
            row.createCell(2).setCellValue(cfgMktIrrGnrInter.getZoneCode2());
            row.createCell(3).setCellValue(cfgMktIrrGnrInter.getRiskWeight());

            rowIndex++;
        }
    }
}
