package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgRiskWeightMappingRepository;
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
public class CfgRiskWeightMappingObjectMapper {

    CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Autowired
    public CfgRiskWeightMappingObjectMapper(CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository) {
        this.cfgRiskWeightMappingRepository = cfgRiskWeightMappingRepository;
    }

    public List<CfgRiskWeightMapping> extractData(XSSFSheet sheet) {
        List<CfgRiskWeightMapping> result = new ArrayList<>();

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

    private CfgRiskWeightMapping createRow(Row row) {

        CfgRiskWeightMapping cfgRiskWeightMapping = new CfgRiskWeightMapping();

        cfgRiskWeightMapping.setAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgRiskWeightMapping.setEraNplCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        Cell cell2 = row.getCell(2);
        if (cell2!=null){
            switch (cell2.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setYearOfEstablishment(cell2 != null ? String.valueOf((int)cell2.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setYearOfEstablishment(cell2 != null ? cell2.getStringCellValue() : null);
                    break;
            }
        }

        cfgRiskWeightMapping.setCreditMeasure1(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);

        Cell cell4 = row.getCell(4);
        if (cell4!=null){
            switch(cell4.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure1Beg(cell4 != null ? String.valueOf(cell4.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure1Beg(cell4 != null ? cell4.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell5 = row.getCell(5);
        if (cell5!=null){
            switch(cell5.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure1End(cell5 != null ? String.valueOf(cell5.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure1End(cell5 != null ? cell5.getStringCellValue() : null);
                    break;
            }
        }

        cfgRiskWeightMapping.setCreditMeasure2(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);


        Cell cell7 = row.getCell(7);
        if (cell7!=null){
            switch (cell7.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure2Beg(cell7 != null ? String.valueOf(cell7.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure2Beg(cell7 != null ? cell7.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell8 = row.getCell(8);
        if (cell8!=null){
            switch (cell8.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure2End(cell8 != null ? String.valueOf(cell8.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure2End(cell8 != null ? cell8.getStringCellValue() : null);
                    break;
            }
        }

        cfgRiskWeightMapping.setRiskWeight(row.getCell(9) != null ? row.getCell(9).getNumericCellValue() : null);
        cfgRiskWeightMapping.setSeq(row.getCell(10) != null ? (long)row.getCell(10).getNumericCellValue() : null);

        return cfgRiskWeightMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgRiskWeightMapping> all = cfgRiskWeightMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgRiskWeightMapping cfgRiskWeightMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgRiskWeightMapping.getAssetClass());
            row.createCell(1).setCellValue(cfgRiskWeightMapping.getEraNplCode());
            row.createCell(2).setCellValue(cfgRiskWeightMapping.getYearOfEstablishment());
            row.createCell(3).setCellValue(cfgRiskWeightMapping.getCreditMeasure1());
            row.createCell(4).setCellValue(cfgRiskWeightMapping.getCreditMeasure1Beg());
            row.createCell(5).setCellValue(cfgRiskWeightMapping.getCreditMeasure1End());
            row.createCell(6).setCellValue(cfgRiskWeightMapping.getCreditMeasure2());
            row.createCell(7).setCellValue(cfgRiskWeightMapping.getCreditMeasure2Beg());
            row.createCell(8).setCellValue(cfgRiskWeightMapping.getCreditMeasure2End());
            row.createCell(9).setCellValue(cfgRiskWeightMapping.getRiskWeight());
            row.createCell(10).setCellValue(cfgRiskWeightMapping.getSeq());

            rowIndex++;
        }
    }
}
