package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgRiskWeightMappingRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgRiskWeightMappingObjectMapper extends AbstractObjectMapper {

    CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Autowired
    public CfgRiskWeightMappingObjectMapper(CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository) {
        this.cfgRiskWeightMappingRepository = cfgRiskWeightMappingRepository;
    }

    CfgRiskWeightMapping createRow(Row row) {

        CfgRiskWeightMapping cfgRiskWeightMapping = new CfgRiskWeightMapping();

        cfgRiskWeightMapping.setAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgRiskWeightMapping.setEraNplCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);

        Cell cell2 = row.getCell(2);
        if (cell2 != null) {
            switch (cell2.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setYearOfEstablishment(String.valueOf((int) cell2.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setYearOfEstablishment(cell2.getStringCellValue());
                    break;
            }
        }

        cfgRiskWeightMapping.setCreditMeasure1(row.getCell(3).getStringCellValue());

        Cell cell4 = row.getCell(4);
        if (cell4 != null) {
            switch (cell4.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure1Beg(String.valueOf(cell4.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure1Beg(cell4.getStringCellValue());
                    break;
            }
        }

        Cell cell5 = row.getCell(5);
        if (cell5 != null) {
            switch (cell5.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure1End(String.valueOf(cell5.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure1End(cell5.getStringCellValue());
                    break;
            }
        }

        cfgRiskWeightMapping.setCreditMeasure2(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);


        Cell cell7 = row.getCell(7);
        if (cell7 != null) {
            switch (cell7.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure2Beg(String.valueOf(cell7.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure2Beg(cell7.getStringCellValue());
                    break;
            }
        }

        Cell cell8 = row.getCell(8);
        if (cell8 != null) {
            switch (cell8.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgRiskWeightMapping.setCreditMeasure2End(String.valueOf(cell8.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgRiskWeightMapping.setCreditMeasure2End(cell8.getStringCellValue());
                    break;
            }
        }

        cfgRiskWeightMapping.setRiskWeight(row.getCell(9) != null ? row.getCell(9).getNumericCellValue() : null);
        cfgRiskWeightMapping.setSeq(row.getCell(10) != null ? (long) row.getCell(10).getNumericCellValue() : null);

        return cfgRiskWeightMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgRiskWeightMapping> all = cfgRiskWeightMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgRiskWeightMapping cfgRiskWeightMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgRiskWeightMapping.getAssetClass());
            createCell(row, 1, cfgRiskWeightMapping.getEraNplCode());
            createCell(row, 2, cfgRiskWeightMapping.getYearOfEstablishment());
            createCell(row, 3, cfgRiskWeightMapping.getCreditMeasure1());
            createCell(row, 4, cfgRiskWeightMapping.getCreditMeasure1Beg());
            createCell(row, 5, cfgRiskWeightMapping.getCreditMeasure1End());
            createCell(row, 6, cfgRiskWeightMapping.getCreditMeasure2());
            createCell(row, 7, cfgRiskWeightMapping.getCreditMeasure2Beg());
            createCell(row, 8, cfgRiskWeightMapping.getCreditMeasure2End());
            createCell(row, 9, cfgRiskWeightMapping.getRiskWeight());
            createCell(row, 10, cfgRiskWeightMapping.getSeq());

            rowIndex++;
        }
    }
}
