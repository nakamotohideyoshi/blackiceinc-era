package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrIntra;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrIntraRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktIrrGnrIntraObjectMapper {

    CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository;

    @Autowired
    public CfgMktIrrGnrIntraObjectMapper(CfgMktIrrGnrIntraRepository cfgMktIrrGnrIntraRepository){
        this.cfgMktIrrGnrIntraRepository = cfgMktIrrGnrIntraRepository;
    }

    public List<CfgMktIrrGnrIntra> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrIntra> result = new ArrayList<>();

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

    private CfgMktIrrGnrIntra createRow(Row row) {

        CfgMktIrrGnrIntra cfgMktIrrGnrIntra = new CfgMktIrrGnrIntra();

        cfgMktIrrGnrIntra.setCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktIrrGnrIntra.setZoneCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktIrrGnrIntra.setRiskWeight(row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : null);

        return cfgMktIrrGnrIntra;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrIntra> all = cfgMktIrrGnrIntraRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrIntra cfgMktIrrGnrIntra:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrIntra.getCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrIntra.getZoneCode());
            row.createCell(2).setCellValue(cfgMktIrrGnrIntra.getRiskWeight());

            rowIndex++;
        }
    }
}
