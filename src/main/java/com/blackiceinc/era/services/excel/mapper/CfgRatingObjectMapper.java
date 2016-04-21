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
public class CfgRatingObjectMapper extends AbstractObjectMapper<CfgRating> {

    private CfgRatingRepository cfgRatingRepository;

    @Autowired
    public CfgRatingObjectMapper(CfgRatingRepository cfgRatingRepository) {
        this.cfgRatingRepository = cfgRatingRepository;
    }

    @Override
    CfgRating createRow(Row row) {
        CfgRating cfgRating = new CfgRating();

        cfgRating.setAgencyCode(getStringValue(row.getCell(0)));
        cfgRating.setRating(getStringValue(row.getCell(1)));
        cfgRating.setQualifying(getStringValue(row.getCell(2)));
        cfgRating.setRiskBucket(getLongValue(row.getCell(3)));

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
            createCell(row, 3, cfgRating.getRiskBucket());

            rowIndex++;
        }
    }
}
