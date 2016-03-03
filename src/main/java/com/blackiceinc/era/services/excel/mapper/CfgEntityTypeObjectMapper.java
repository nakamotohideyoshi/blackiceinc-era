package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgEntityType;
import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.repository.CfgEntityTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgEntityTypeObjectMapper {

    CfgEntityTypeRepository cfgEntityTypeRepository;

    @Autowired
    public CfgEntityTypeObjectMapper(CfgEntityTypeRepository cfgEntityTypeRepository){
        this.cfgEntityTypeRepository = cfgEntityTypeRepository;
    }

    public List<CfgEntityType> extractCfgEntityTypes(XSSFSheet sheet) {
        List<CfgEntityType> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createCfgEntityTypeObj(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgEntityType createCfgEntityTypeObj(Row row) {

        CfgEntityType cfgEntityType = new CfgEntityType();

        cfgEntityType.setEraEntityType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgEntityType.setEntityDesc(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        return cfgEntityType;
    }

    public void importCfgCfgEntityTypes(XSSFSheet sheet) {
        List<CfgEntityType> all = cfgEntityTypeRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgEntityType cfgEntityType:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgEntityType.getEraEntityType());
            row.createCell(1).setCellValue(cfgEntityType.getEntityDesc());

            rowIndex++;
        }
    }
}
