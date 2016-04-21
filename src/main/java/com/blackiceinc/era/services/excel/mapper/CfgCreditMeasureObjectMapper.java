package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCreditMeasure;
import com.blackiceinc.era.persistence.erau.repository.CfgCreditMeasureRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCreditMeasureObjectMapper extends AbstractObjectMapper<CfgCreditMeasure> {

    private CfgCreditMeasureRepository cfgCreditMeasureRepository;

    @Autowired
    public CfgCreditMeasureObjectMapper(CfgCreditMeasureRepository cfgCreditMeasureRepository) {
        this.cfgCreditMeasureRepository = cfgCreditMeasureRepository;
    }

    @Override
    CfgCreditMeasure createRow(Row row) {

        CfgCreditMeasure cfgCreditMeasure = new CfgCreditMeasure();

        cfgCreditMeasure.setCreditMeasure(getStringValue(row.getCell(0)));
        cfgCreditMeasure.setCreditMeasureDesc(getStringValue(row.getCell(1)));

        return cfgCreditMeasure;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCreditMeasure> all = cfgCreditMeasureRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCreditMeasure cfgCreditMeasure : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCreditMeasure.getCreditMeasure());
            createCell(row, 1, cfgCreditMeasure.getCreditMeasureDesc());

            rowIndex++;
        }
    }
}
