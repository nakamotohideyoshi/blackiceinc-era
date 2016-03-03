package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktComOth;
import com.blackiceinc.era.persistence.erau.repository.CfgMktComOthRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktComOthObjectMapper {

    CfgMktComOthRepository cfgMktComOthRepository;

    @Autowired
    public CfgMktComOthObjectMapper(CfgMktComOthRepository cfgMktComOthRepository){
        this.cfgMktComOthRepository = cfgMktComOthRepository;
    }

    public List<CfgMktComOth> extractData(XSSFSheet sheet) {
        List<CfgMktComOth> result = new ArrayList<>();

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

    private CfgMktComOth createRow(Row row) {

        CfgMktComOth cfgMktComOth = new CfgMktComOth();

        cfgMktComOth.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktComOth.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgMktComOth;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktComOth> all = cfgMktComOthRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktComOth cfgMktComOth:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktComOth.getMktProductType());
            row.createCell(1).setCellValue(cfgMktComOth.getRiskWeight());

            rowIndex++;
        }
    }
}
