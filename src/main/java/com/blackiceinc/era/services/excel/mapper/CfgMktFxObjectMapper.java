package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktFx;
import com.blackiceinc.era.persistence.erau.repository.CfgMktFxRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktFxObjectMapper {

    CfgMktFxRepository cfgMktFxRepository;

    @Autowired
    public CfgMktFxObjectMapper(CfgMktFxRepository cfgMktFxRepository){
        this.cfgMktFxRepository = cfgMktFxRepository;
    }

    public List<CfgMktFx> extractData(XSSFSheet sheet) {
        List<CfgMktFx> result = new ArrayList<>();

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

    private CfgMktFx createRow(Row row) {

        CfgMktFx cfgMktFx = new CfgMktFx();

        cfgMktFx.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktFx.setRiskWeight(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);

        return cfgMktFx;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktFx> all = cfgMktFxRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktFx cfgMktFx:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktFx.getMktProductType());
            row.createCell(1).setCellValue(cfgMktFx.getRiskWeight());

            rowIndex++;
        }
    }
}
