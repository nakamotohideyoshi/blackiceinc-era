package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsRiskObjectMapper extends AbstractObjectMapper<CfgOpsRisk> {

    private CfgOpsRiskRepository cfgOpsRiskRepository;

    @Autowired
    public CfgOpsRiskObjectMapper(CfgOpsRiskRepository cfgOpsRiskRepository) {
        this.cfgOpsRiskRepository = cfgOpsRiskRepository;
    }

    @Override
    CfgOpsRisk createRow(Row row) {
        CfgOpsRisk cfgOpsRisk = new CfgOpsRisk();

        cfgOpsRisk.setCode(getStringValue(row.getCell(0)));
        cfgOpsRisk.setRiskWeight(getDoubleValue(row.getCell(1)));

        return cfgOpsRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsRisk> all = cfgOpsRiskRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsRisk cfgOpsRisk : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgOpsRisk.getCode());
            createCell(row, 1, cfgOpsRisk.getRiskWeight());

            rowIndex++;
        }
    }
}
