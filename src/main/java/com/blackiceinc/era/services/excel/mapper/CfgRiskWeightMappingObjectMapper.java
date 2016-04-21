package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgRiskWeightMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgRiskWeightMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgRiskWeightMappingObjectMapper extends AbstractObjectMapper<CfgRiskWeightMapping> {

    private CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository;

    @Autowired
    public CfgRiskWeightMappingObjectMapper(CfgRiskWeightMappingRepository cfgRiskWeightMappingRepository) {
        this.cfgRiskWeightMappingRepository = cfgRiskWeightMappingRepository;
    }

    @Override
    CfgRiskWeightMapping createRow(Row row) {

        CfgRiskWeightMapping cfgRiskWeightMapping = new CfgRiskWeightMapping();

        cfgRiskWeightMapping.setAssetClass(getStringValue(row.getCell(0)));
        cfgRiskWeightMapping.setEraNplCode(getStringValue(row.getCell(1)));
        cfgRiskWeightMapping.setYearOfEstablishment(getStringValue(row.getCell(2)));
        cfgRiskWeightMapping.setCreditMeasure1(getStringValue(row.getCell(3)));
        cfgRiskWeightMapping.setCreditMeasure1Beg(getStringValue(row.getCell(4)));
        cfgRiskWeightMapping.setCreditMeasure1End(getStringValue(row.getCell(5)));
        cfgRiskWeightMapping.setCreditMeasure2(getStringValue(row.getCell(6)));
        cfgRiskWeightMapping.setCreditMeasure2Beg(getStringValue(row.getCell(7)));
        cfgRiskWeightMapping.setCreditMeasure2End(getStringValue(row.getCell(8)));
        cfgRiskWeightMapping.setRiskWeight(getDoubleValue(row.getCell(9)));
        cfgRiskWeightMapping.setSeq(getLongValue(row.getCell(10)));

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
