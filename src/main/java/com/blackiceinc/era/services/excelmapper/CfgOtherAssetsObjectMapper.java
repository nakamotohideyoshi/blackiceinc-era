package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.model.CfgOtherAssets;
import com.blackiceinc.era.persistence.erau.repository.CfgOtherAssetsRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOtherAssetsObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgOtherAssetsRepository cfgOtherAssetsRepository;

    @Autowired
    public CfgOtherAssetsObjectMapper(CfgOtherAssetsRepository cfgOtherAssetsRepository) {
        this.cfgOtherAssetsRepository = cfgOtherAssetsRepository;
    }

    @Override
    CfgOtherAssets createRow(Row row) {
        CfgOtherAssets cfgOtherAssets = new CfgOtherAssets();

        cfgOtherAssets.setGlCode(getStringValue(row.getCell(0)));
        cfgOtherAssets.setGroupCheck(getStringValue(row.getCell(1)));
        cfgOtherAssets.setHeading(getStringValue(row.getCell(2)));
        cfgOtherAssets.setGlDesc(getStringValue(row.getCell(3)));
        cfgOtherAssets.setEraContractType(getStringValue(row.getCell(4)));
        cfgOtherAssets.setCheckCriteria(getStringValue(row.getCell(5)));
        cfgOtherAssets.setRiskWeight(getDoubleValue(row.getCell(6)));

        return cfgOtherAssets;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOtherAssets> all = cfgOtherAssetsRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOtherAssets cfgOtherAssets : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgOtherAssets.getGlCode());
            createCell(row, 1, cfgOtherAssets.getGroupCheck());
            createCell(row, 2, cfgOtherAssets.getHeading());
            createCell(row, 3, cfgOtherAssets.getGlDesc());
            createCell(row, 4, cfgOtherAssets.getEraContractType());
            createCell(row, 5, cfgOtherAssets.getCheckCriteria());
            createCell(row, 6, cfgOtherAssets.getRiskWeight());

            rowIndex++;
        }
    }
}
