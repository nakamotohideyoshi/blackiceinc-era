package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgCcfMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCcfMappingObjectMapper {

    CfgCcfMappingRepository cfgCcfMappingRepository;

    @Autowired
    public CfgCcfMappingObjectMapper(CfgCcfMappingRepository cfgCcfMappingRepository){
        this.cfgCcfMappingRepository = cfgCcfMappingRepository;
    }

    public List<CfgCcfMapping> extractData(XSSFSheet sheet) {
        List<CfgCcfMapping> result = new ArrayList<>();

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

    private CfgCcfMapping createRow(Row row) {

        CfgCcfMapping cfgCcfMapping = new CfgCcfMapping();

        cfgCcfMapping.setEraProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCcfMapping.setCcf(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);
        cfgCcfMapping.setUnconditionallyCancelable(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgCcfMapping.setMaturityStart(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgCcfMapping.setMaturityEnd(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgCcfMapping.setSeq(row.getCell(5) != null ? Long.valueOf(row.getCell(5).getStringCellValue()) : null);

        return cfgCcfMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCcfMapping> all = cfgCcfMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCcfMapping cfgCcfMapping:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCcfMapping.getEraProductType());
            row.createCell(1).setCellValue(cfgCcfMapping.getCcf());
            row.createCell(2).setCellValue(cfgCcfMapping.getUnconditionallyCancelable());
            row.createCell(3).setCellValue(cfgCcfMapping.getMaturityStart());
            row.createCell(4).setCellValue(cfgCcfMapping.getMaturityEnd());
            row.createCell(5).setCellValue(cfgCcfMapping.getSeq());

            rowIndex++;
        }
    }
}
