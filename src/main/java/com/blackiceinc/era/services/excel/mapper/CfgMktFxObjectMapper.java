package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktFx;
import com.blackiceinc.era.persistence.erau.repository.CfgMktFxRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktFxObjectMapper extends AbstractObjectMapper {

    CfgMktFxRepository cfgMktFxRepository;

    @Autowired
    public CfgMktFxObjectMapper(CfgMktFxRepository cfgMktFxRepository) {
        this.cfgMktFxRepository = cfgMktFxRepository;
    }

    CfgMktFx createRow(Row row) {
        CfgMktFx cfgMktFx = new CfgMktFx();

        cfgMktFx.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktFx.setRiskWeight(getDoubleValue(row.getCell(1)));

        return cfgMktFx;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktFx> all = cfgMktFxRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktFx cfgMktFx : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktFx.getMktProductType());
            createCell(row, 1, cfgMktFx.getRiskWeight());

            rowIndex++;
        }
    }
}
