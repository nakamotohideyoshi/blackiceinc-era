package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCrmHaircut;
import com.blackiceinc.era.persistence.erau.repository.CfgCrmHaircutRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCrmHaircutObjectMapper extends AbstractObjectMapper<CfgCrmHaircut> {

    private CfgCrmHaircutRepository cfgCrmHaircutRepository;

    @Autowired
    public CfgCrmHaircutObjectMapper(CfgCrmHaircutRepository cfgCrmHaircutRepository) {
        this.cfgCrmHaircutRepository = cfgCrmHaircutRepository;
    }

    @Override
    CfgCrmHaircut createRow(Row row) {
        CfgCrmHaircut cfgCrmHaircut = new CfgCrmHaircut();

        cfgCrmHaircut.setEraColType(getStringValue(row.getCell(0)));
        cfgCrmHaircut.setEraEntityType(getStringValue(row.getCell(1)));
        cfgCrmHaircut.setRiskBucket(getStringValue(row.getCell(2)));
        cfgCrmHaircut.setMinResidualMaturity(getStringValue(row.getCell(3)));
        cfgCrmHaircut.setMaxResidualMaturity(getStringValue(row.getCell(4)));
        cfgCrmHaircut.setHaircut(getDoubleValue(row.getCell(5)));
        cfgCrmHaircut.setSeq(getLongValue(row.getCell(6)));

        return cfgCrmHaircut;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCrmHaircut> all = cfgCrmHaircutRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCrmHaircut cfgCrmHaircut : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCrmHaircut.getEraColType());
            createCell(row, 1, cfgCrmHaircut.getEraEntityType());
            createCell(row, 2, cfgCrmHaircut.getRiskBucket());
            createCell(row, 3, cfgCrmHaircut.getMinResidualMaturity());
            createCell(row, 4, cfgCrmHaircut.getMaxResidualMaturity());
            createCell(row, 5, cfgCrmHaircut.getHaircut());
            createCell(row, 6, cfgCrmHaircut.getSeq());

            rowIndex++;
        }
    }
}
