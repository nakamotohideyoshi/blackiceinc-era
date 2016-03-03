package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClassMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktAssetClassMappingObjectMapper {

    CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;

    @Autowired
    public CfgMktAssetClassMappingObjectMapper(CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository){
        this.cfgMktAssetClassMappingRepository = cfgMktAssetClassMappingRepository;
    }

    public List<CfgMktAssetClassMapping> extractData(XSSFSheet sheet) {
        List<CfgMktAssetClassMapping> result = new ArrayList<>();

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

    private CfgMktAssetClassMapping createRow(Row row) {

        CfgMktAssetClassMapping cfgMktAssetClassMapping = new CfgMktAssetClassMapping();

        cfgMktAssetClassMapping.setMktAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktAssetClassMapping.setEraEntityType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktAssetClassMapping.setMktProductType(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        return cfgMktAssetClassMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktAssetClassMapping> all = cfgMktAssetClassMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktAssetClassMapping cfgMktAssetClassMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktAssetClassMapping.getMktAssetClass());
            row.createCell(1).setCellValue(cfgMktAssetClassMapping.getEraEntityType());
            row.createCell(2).setCellValue(cfgMktAssetClassMapping.getMktProductType());

            rowIndex++;
        }
    }
}
