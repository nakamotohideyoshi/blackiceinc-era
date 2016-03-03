package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsType;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgCapElementsTypeObjectMapper {

    CfgCapElementsTypeRepository cfgCapElementsTypeRepository;

    @Autowired
    public CfgCapElementsTypeObjectMapper(CfgCapElementsTypeRepository cfgCapElementsTypeRepository){
        this.cfgCapElementsTypeRepository = cfgCapElementsTypeRepository;
    }

    public List<CfgCapElementsType> extractData(XSSFSheet sheet) {
        List<CfgCapElementsType> result = new ArrayList<>();

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

    private CfgCapElementsType createRow(Row row) {

        CfgCapElementsType cfgCapElementsType = new CfgCapElementsType();

        cfgCapElementsType.setCapElementType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCapElementsType.setDescription(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgCapElementsType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsType> all = cfgCapElementsTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsType cfgCapElementsType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCapElementsType.getCapElementType());
            row.createCell(1).setCellValue(cfgCapElementsType.getDescription());

            rowIndex++;
        }
    }
}
