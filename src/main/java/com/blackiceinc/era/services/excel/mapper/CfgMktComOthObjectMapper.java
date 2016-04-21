package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktComOth;
import com.blackiceinc.era.persistence.erau.repository.CfgMktComOthRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktComOthObjectMapper extends AbstractObjectMapper<CfgMktComOth> {

    private CfgMktComOthRepository cfgMktComOthRepository;

    @Autowired
    public CfgMktComOthObjectMapper(CfgMktComOthRepository cfgMktComOthRepository) {
        this.cfgMktComOthRepository = cfgMktComOthRepository;
    }

    @Override
    CfgMktComOth createRow(Row row) {
        CfgMktComOth cfgMktComOth = new CfgMktComOth();

        cfgMktComOth.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktComOth.setRiskWeight(getDoubleValue(row.getCell(1)));

        return cfgMktComOth;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktComOth> all = cfgMktComOthRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktComOth cfgMktComOth : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktComOth.getMktProductType());
            createCell(row, 1, cfgMktComOth.getRiskWeight());

            rowIndex++;
        }
    }
}
