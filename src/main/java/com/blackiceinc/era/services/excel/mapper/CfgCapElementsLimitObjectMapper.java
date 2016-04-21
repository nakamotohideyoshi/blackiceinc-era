package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCapElementsLimit;
import com.blackiceinc.era.persistence.erau.repository.CfgCapElementsLimitRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCapElementsLimitObjectMapper extends AbstractObjectMapper<CfgCapElementsLimit> {

    private CfgCapElementsLimitRepository cfgCapElementsLimitRepository;

    @Autowired
    public CfgCapElementsLimitObjectMapper(CfgCapElementsLimitRepository cfgCapElementsLimitRepository) {
        this.cfgCapElementsLimitRepository = cfgCapElementsLimitRepository;
    }

    @Override
    CfgCapElementsLimit createRow(Row row) {
        CfgCapElementsLimit cfgCapElementsLimit = new CfgCapElementsLimit();

        cfgCapElementsLimit.setLimitType(getStringValue(row.getCell(0)));
        cfgCapElementsLimit.setOperator(getStringValue(row.getCell(1)));
        cfgCapElementsLimit.setThreshold(getDoubleValue(row.getCell(2)));
        cfgCapElementsLimit.setConsoTable(getStringValue(row.getCell(3)));
        cfgCapElementsLimit.setConsoField(getStringValue(row.getCell(4)));
        cfgCapElementsLimit.setConsoFieldValue(getStringValue(row.getCell(5)));
        cfgCapElementsLimit.setConsoAmt(getStringValue(row.getCell(6)));

        return cfgCapElementsLimit;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCapElementsLimit> all = cfgCapElementsLimitRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCapElementsLimit cfgCapElementsLimit : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCapElementsLimit.getLimitType());
            createCell(row, 1, cfgCapElementsLimit.getOperator());
            createCell(row, 2, cfgCapElementsLimit.getThreshold());
            createCell(row, 3, cfgCapElementsLimit.getConsoTable());
            createCell(row, 4, cfgCapElementsLimit.getConsoField());
            createCell(row, 5, cfgCapElementsLimit.getConsoFieldValue());
            createCell(row, 6, cfgCapElementsLimit.getConsoAmt());

            rowIndex++;
        }
    }
}
