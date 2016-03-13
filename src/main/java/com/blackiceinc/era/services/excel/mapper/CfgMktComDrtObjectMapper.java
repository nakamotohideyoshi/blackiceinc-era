package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktComDrt;
import com.blackiceinc.era.persistence.erau.repository.CfgMktComDrtRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktComDrtObjectMapper extends AbstractObjectMapper {

    CfgMktComDrtRepository cfgMktComDrtRepository;

    @Autowired
    public CfgMktComDrtObjectMapper(CfgMktComDrtRepository cfgMktComDrtRepository) {
        this.cfgMktComDrtRepository = cfgMktComDrtRepository;
    }

    CfgMktComDrt createRow(Row row) {
        CfgMktComDrt cfgMktComDrt = new CfgMktComDrt();

        cfgMktComDrt.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktComDrt.setRiskWeight(getDoubleValue(row.getCell(1)));

        return cfgMktComDrt;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktComDrt> all = cfgMktComDrtRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktComDrt cfgMktComDrt : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktComDrt.getMktProductType());
            createCell(row, 1, cfgMktComDrt.getRiskWeight());

            rowIndex++;
        }
    }
}
