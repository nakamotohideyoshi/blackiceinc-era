package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClass;
import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktAssetClassObjectMapper extends AbstractObjectMapper<CfgMktAssetClass> {

    CfgMktAssetClassRepository cfgMktAssetClassRepository;

    @Autowired
    public CfgMktAssetClassObjectMapper(CfgMktAssetClassRepository cfgMktAssetClassRepository) {
        this.cfgMktAssetClassRepository = cfgMktAssetClassRepository;
    }

    @Override
    CfgMktAssetClass createRow(Row row) {
        CfgMktAssetClass cfgMktAssetClass = new CfgMktAssetClass();

        cfgMktAssetClass.setMktAssetClass(getStringValue(row.getCell(0)));
        cfgMktAssetClass.setMktAssetClassDesc(getStringValue(row.getCell(1)));

        return cfgMktAssetClass;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktAssetClass> all = cfgMktAssetClassRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktAssetClass cfgMktAssetClass : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktAssetClass.getMktAssetClass());
            createCell(row, 1, cfgMktAssetClass.getMktAssetClassDesc());

            rowIndex++;
        }
    }
}
