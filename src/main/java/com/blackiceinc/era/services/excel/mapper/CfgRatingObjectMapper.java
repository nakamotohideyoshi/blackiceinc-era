package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRating;
import com.blackiceinc.era.persistence.erau.repository.CfgRatingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgRatingObjectMapper extends AbstractObjectMapper {

    CfgRatingRepository cfgRatingRepository;

    @Autowired
    public CfgRatingObjectMapper(CfgRatingRepository cfgRatingRepository) {
        this.cfgRatingRepository = cfgRatingRepository;
    }

    CfgRating createRow(Row row) {
        CfgRating cfgRating = new CfgRating();

        cfgRating.setAgencyCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgRating.setRating(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgRating.setQualifying(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgRating.setLongShort(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);

        cfgRating.setRiskBucket(row.getCell(4) != null ? Long.valueOf((long) row.getCell(4).getNumericCellValue()) : null);

        return cfgRating;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgRating> all = cfgRatingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgRating cfgRating : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgRating.getAgencyCode());
            createCell(row, 1, cfgRating.getRating());
            createCell(row, 2, cfgRating.getQualifying());
            createCell(row, 3, cfgRating.getLongShort());
            createCell(row, 4, cfgRating.getRiskBucket());

            rowIndex++;
        }
    }
}
