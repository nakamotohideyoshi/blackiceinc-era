package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgCcfMappingRepository;
import org.apache.poi.ss.usermodel.Cell;
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

        Cell cell3 = row.getCell(3);
        if (cell3!=null){
            switch(cell3.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCcfMapping.setMaturityStart(cell3 != null ? String.valueOf((int)cell3.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCcfMapping.setMaturityStart(cell3 != null ? cell3.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell4 = row.getCell(4);
        if (cell4!=null){
            switch (cell4.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCcfMapping.setMaturityStart(cell4 != null ? String.valueOf((int)cell4.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCcfMapping.setMaturityEnd(cell4 != null ? cell4.getStringCellValue() : null);
                    break;
            }
        }

        cfgCcfMapping.setSeq(row.getCell(5) != null ? (long)row.getCell(5).getNumericCellValue() : null);

        return cfgCcfMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCcfMapping> all = cfgCcfMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
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
