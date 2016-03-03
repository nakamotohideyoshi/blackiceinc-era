package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmHaircutRepository;
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
public class CfgCrmHaircutObjectMapper {

    CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Autowired
    public CfgCrmHaircutObjectMapper(CfgCrmHaircutRepository cfgCrmHaircutRepository){
        this.cfgCrmHaircutRepository = cfgCrmHaircutRepository;
    }

    public List<CfgCrmHaircut> extractData(XSSFSheet sheet) {
        List<CfgCrmHaircut> result = new ArrayList<>();

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

    private CfgCrmHaircut createRow(Row row) {

        CfgCrmHaircut cfgCrmHaircut = new CfgCrmHaircut();

        cfgCrmHaircut.setEraColType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCrmHaircut.setEraEntityType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        Cell cell2 = row.getCell(2);
        if (cell2!=null){
            switch (cell2.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setRiskBucket(cell2 != null ? String.valueOf((int)cell2.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setRiskBucket(cell2 != null ? cell2.getStringCellValue() : null);
                    break;
            }
        }


        Cell cell3 = row.getCell(3);
        if (cell3!=null){
            switch (cell3.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setMinResidualMaturity(cell3 != null ? String.valueOf((int)cell3.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setMinResidualMaturity(cell3 != null ? cell3.getStringCellValue() : null);
                    break;
            }
        }


        Cell cell4 = row.getCell(4);
        if (cell4!=null){
            switch (cell4.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCrmHaircut.setMaxResidualMaturity(cell4 != null ? String.valueOf((int)cell4.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCrmHaircut.setMaxResidualMaturity(cell4 != null ? cell4.getStringCellValue() : null);
                    break;
            }
        }


        cfgCrmHaircut.setHaircut(row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : null);

        return cfgCrmHaircut;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCrmHaircut> all = cfgCrmHaircutRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCrmHaircut cfgCrmHaircut:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgCrmHaircut.getEraColType());
            row.createCell(1).setCellValue(cfgCrmHaircut.getEraEntityType());
            row.createCell(2).setCellValue(cfgCrmHaircut.getRiskBucket());
            row.createCell(3).setCellValue(cfgCrmHaircut.getMinResidualMaturity());
            row.createCell(4).setCellValue(cfgCrmHaircut.getMaxResidualMaturity());
            row.createCell(5).setCellValue(cfgCrmHaircut.getHaircut());

            rowIndex++;
        }
    }
}
