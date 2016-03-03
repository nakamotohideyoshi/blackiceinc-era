package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktComDrt;
import com.blackiceinc.era.persistence.erau.repository.CfgMktComDrtRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktComDrtObjectMapper {

    CfgMktComDrtRepository cfgMktComDrtRepository;

    @Autowired
    public CfgMktComDrtObjectMapper(CfgMktComDrtRepository cfgMktComDrtRepository){
        this.cfgMktComDrtRepository = cfgMktComDrtRepository;
    }

    public List<CfgMktComDrt> extractData(XSSFSheet sheet) {
        List<CfgMktComDrt> result = new ArrayList<>();

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

    private CfgMktComDrt createRow(Row row) {

        CfgMktComDrt cfgMktComDrt = new CfgMktComDrt();

        cfgMktComDrt.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktComDrt.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgMktComDrt;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktComDrt> all = cfgMktComDrtRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktComDrt cfgMktComDrt:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktComDrt.getMktProductType());
            row.createCell(1).setCellValue(cfgMktComDrt.getRiskWeight());

            rowIndex++;
        }
    }
}
