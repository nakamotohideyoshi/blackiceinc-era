package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgAddOn;
import com.blackiceinc.era.persistence.erau.repository.CfgAddOnRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgAddOnObjectMapper extends AbstractObjectMapper<CfgAddOn> {

    private CfgAddOnRepository cfgAddOnRepository;

    @Autowired
    public CfgAddOnObjectMapper(CfgAddOnRepository cfgAddOnRepository) {
        this.cfgAddOnRepository = cfgAddOnRepository;
    }

    @Override
    CfgAddOn createRow(Row row) {
        CfgAddOn cfgAddOn = new CfgAddOn();

        cfgAddOn.setEraProductType(getStringValue(row.getCell(0)));
        cfgAddOn.setMaturityStart(getStringValue(row.getCell(1)));
        cfgAddOn.setMaturityEnd(getStringValue(row.getCell(2)));
        cfgAddOn.setRiskWeight(getDoubleValue(row.getCell(3)));

        return cfgAddOn;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgAddOn> all = cfgAddOnRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgAddOn cfgAddOn : all) {
            XSSFRow row = sheet.createRow(rowIndex);
            createCell(row, 0, cfgAddOn.getEraProductType());
            createCell(row, 1, cfgAddOn.getMaturityStart());
            createCell(row, 2, cfgAddOn.getMaturityEnd());
            createCell(row, 3, cfgAddOn.getRiskWeight());

            rowIndex++;
        }
    }


}
