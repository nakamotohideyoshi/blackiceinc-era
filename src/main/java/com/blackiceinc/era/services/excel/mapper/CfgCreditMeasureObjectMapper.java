package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCreditMeasure;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCreditMeasureRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCreditMeasureObjectMapper {

    CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Autowired
    public CfgCreditMeasureObjectMapper(CfgCreditMeasureRepository cfgCreditMeasureRepository){
        this.cfgCreditMeasureRepository = cfgCreditMeasureRepository;
    }

    public List<CfgCreditMeasure> extractData(XSSFSheet sheet) {
        List<CfgCreditMeasure> result = new ArrayList<>();

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

    private CfgCreditMeasure createRow(Row row) {

        CfgCreditMeasure cfgCreditMeasure = new CfgCreditMeasure();

        cfgCreditMeasure.setCreditMeasure(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCreditMeasure.setCreditMeasureDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgCreditMeasure;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCreditMeasure> all = cfgCreditMeasureRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCreditMeasure cfgCreditMeasure:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCreditMeasure.getCreditMeasure());
            row.createCell(1).setCellValue(cfgCreditMeasure.getCreditMeasureDesc());

            rowIndex++;
        }
    }
}
