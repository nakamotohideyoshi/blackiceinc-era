package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgRiskWeightMappingRepository;
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
        cfgRiskWeightMapping.setYearOfEstablishment(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure1(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure1Beg(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure1End(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure2(row.getCell(6) != null ? row.getCell(6).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure2Beg(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : null);
        cfgRiskWeightMapping.setCreditMeasure2End(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : null);
        cfgRiskWeightMapping.setRiskWeight(row.getCell(9) != null ? row.getCell(9).getNumericCellValue() : null);
        cfgRiskWeightMapping.setSeq(row.getCell(10) != null ? Long.valueOf(row.getCell(10).getStringCellValue()) : null);

        return cfgRiskWeightMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgRiskWeightMapping> all = cfgRiskWeightMappingRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
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
