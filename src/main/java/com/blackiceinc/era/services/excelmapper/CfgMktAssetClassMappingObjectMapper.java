package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktAssetClassMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgMktAssetClassMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktAssetClassMappingObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository;

    @Autowired
    public CfgMktAssetClassMappingObjectMapper(CfgMktAssetClassMappingRepository cfgMktAssetClassMappingRepository) {
        this.cfgMktAssetClassMappingRepository = cfgMktAssetClassMappingRepository;
    }

    @Override
    CfgMktAssetClassMapping createRow(Row row) {
        CfgMktAssetClassMapping cfgMktAssetClassMapping = new CfgMktAssetClassMapping();

        cfgMktAssetClassMapping.setMktAssetClass(getStringValue(row.getCell(0)));
        cfgMktAssetClassMapping.setEraEntityType(getStringValue(row.getCell(1)));
        cfgMktAssetClassMapping.setMktProductType(getStringValue(row.getCell(2)));

        return cfgMktAssetClassMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktAssetClassMapping> all = cfgMktAssetClassMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktAssetClassMapping cfgMktAssetClassMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktAssetClassMapping.getMktAssetClass());
            createCell(row, 1, cfgMktAssetClassMapping.getEraEntityType());
            createCell(row, 2, cfgMktAssetClassMapping.getMktProductType());

            rowIndex++;
        }
    }
}
