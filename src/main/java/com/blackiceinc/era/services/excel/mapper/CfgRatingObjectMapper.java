package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRating;
import com.blackiceinc.era.persistence.erau.repository.CfgRatingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgRatingObjectMapper {

    CfgRatingRepository cfgRatingRepository;

    @Autowired
    public CfgRatingObjectMapper(CfgRatingRepository cfgRatingRepository){
        this.cfgRatingRepository = cfgRatingRepository;
    }

    public List<CfgRating> extractData(XSSFSheet sheet) {
        List<CfgRating> result = new ArrayList<>();

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

    private CfgRating createRow(Row row) {

        CfgRating cfgRating = new CfgRating();

        cfgRating.setAgencyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgRating.setRating(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgRating.setQualifying(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgRating.setLongShort(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);


        cfgRating.setRiskBucket(row.getCell(4) != null ? Long.valueOf((long)row.getCell(4).getNumericCellValue()) : null);

        return cfgRating;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgRating> all = cfgRatingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgRating cfgRating:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgRating.getAgencyCode());
            row.createCell(1).setCellValue(cfgRating.getRating());
            row.createCell(2).setCellValue(cfgRating.getQualifying());
            row.createCell(3).setCellValue(cfgRating.getLongShort());

            if (cfgRating.getRiskBucket()!=null){
                row.createCell(4).setCellValue(cfgRating.getRiskBucket());
            }

            rowIndex++;
        }
    }
}
