package com.blackiceinc.era.services.cfgimport;

import com.blackiceinc.era.persistence.erau.repository.CfgRatingDaoCustom;
import com.blackiceinc.era.services.ImportUtil;
import com.blackiceinc.era.services.excelmapper.CfgRatingObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class CfgRatingImportService implements CfgImportService {
    public static final String RATING = "RATING";

    @Autowired
    private CfgRatingObjectMapper cfgRatingObjectMapper;

    @Autowired
    private CfgRatingDaoCustom cfgRatingDaoCustom;

    @Override
    public void makeImportIntoDb(XSSFWorkbook workbook) {
        ImportUtil.makeImportIntoDb(RATING, workbook,
                cfgRatingObjectMapper, cfgRatingDaoCustom);
    }

    @Override
    public void makeImportIntoExcelFromDb(XSSFWorkbook workbook) {
        XSSFSheet ratingSheet = ImportUtil.getSheet(RATING, workbook);
        cfgRatingObjectMapper.importData(ratingSheet);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 12;
    }
}
