package com.blackiceinc.era.services.excelmapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktProductMapping;
import com.blackiceinc.era.persistence.erau.model.CfgObject;
import com.blackiceinc.era.persistence.erau.repository.CfgMktProductMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktProductMappingObjectMapper extends AbstractObjectMapper<CfgObject> {

    private CfgMktProductMappingRepository cfgMktProductMappingRepository;

    @Autowired
    public CfgMktProductMappingObjectMapper(CfgMktProductMappingRepository cfgMktProductMappingRepository) {
        this.cfgMktProductMappingRepository = cfgMktProductMappingRepository;
    }

    @Override
    CfgMktProductMapping createRow(Row row) {
        CfgMktProductMapping cfgMktProductMapping = new CfgMktProductMapping();

        cfgMktProductMapping.setMktProductType(getStringValue(row.getCell(0)));
        cfgMktProductMapping.setContractType(getStringValue(row.getCell(1)));
        cfgMktProductMapping.setExchangedTraded(getStringValue(row.getCell(2)));
        cfgMktProductMapping.setInstrumentType(getStringValue(row.getCell(3)));
        cfgMktProductMapping.setTableName(getStringValue(row.getCell(4)));
        cfgMktProductMapping.setUnderlyingType(getStringValue(row.getCell(5)));

        return cfgMktProductMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktProductMapping> all = cfgMktProductMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktProductMapping cfgMktProductMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktProductMapping.getMktProductType());
            createCell(row, 1, cfgMktProductMapping.getContractType());
            createCell(row, 2, cfgMktProductMapping.getExchangedTraded());
            createCell(row, 3, cfgMktProductMapping.getInstrumentType());
            createCell(row, 4, cfgMktProductMapping.getTableName());
            createCell(row, 5, cfgMktProductMapping.getUnderlyingType());

            rowIndex++;
        }
    }
}
