package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClass;
import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktAssetClassObjectMapper {

    CfgMktAssetClassRepository cfgMktAssetClassRepository;

    @Autowired
    public CfgMktAssetClassObjectMapper(CfgMktAssetClassRepository cfgMktAssetClassRepository){
        this.cfgMktAssetClassRepository = cfgMktAssetClassRepository;
    }

    public List<CfgMktAssetClass> extractData(XSSFSheet sheet) {
        List<CfgMktAssetClass> result = new ArrayList<>();

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

    private CfgMktAssetClass createRow(Row row) {

        CfgMktAssetClass cfgMktAssetClass = new CfgMktAssetClass();

        cfgMktAssetClass.setMktAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktAssetClass.setMktAssetClassDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgMktAssetClass;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktAssetClass> all = cfgMktAssetClassRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktAssetClass cfgMktAssetClass:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktAssetClass.getMktAssetClass());
            row.createCell(1).setCellValue(cfgMktAssetClass.getMktAssetClassDesc());

            rowIndex++;
        }
    }
}
