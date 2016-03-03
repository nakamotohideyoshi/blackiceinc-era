package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktProductMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgMktProductMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktProductMappingObjectMapper {

    CfgMktProductMappingRepository cfgMktProductMappingRepository;

    @Autowired
    public CfgMktProductMappingObjectMapper(CfgMktProductMappingRepository cfgMktProductMappingRepository){
        this.cfgMktProductMappingRepository = cfgMktProductMappingRepository;
    }

    public List<CfgMktProductMapping> extractData(XSSFSheet sheet) {
        List<CfgMktProductMapping> result = new ArrayList<>();

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

    private CfgMktProductMapping createRow(Row row) {

        CfgMktProductMapping cfgMktProductMapping = new CfgMktProductMapping();

        cfgMktProductMapping.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktProductMapping.setContractType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktProductMapping.setExchangedTraded(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktProductMapping.setInstrumentType(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgMktProductMapping.setTableName(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgMktProductMapping.setUnderlyingType(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

        return cfgMktProductMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktProductMapping> all = cfgMktProductMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktProductMapping cfgMktProductMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktProductMapping.getMktProductType());
            row.createCell(1).setCellValue(cfgMktProductMapping.getContractType());
            row.createCell(2).setCellValue(cfgMktProductMapping.getExchangedTraded());
            row.createCell(3).setCellValue(cfgMktProductMapping.getInstrumentType());
            row.createCell(4).setCellValue(cfgMktProductMapping.getTableName());
            row.createCell(5).setCellValue(cfgMktProductMapping.getUnderlyingType());

            rowIndex++;
        }
    }
}
