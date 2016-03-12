package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktProductMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgMktProductMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktProductMappingObjectMapper extends AbstractObjectMapper {

    CfgMktProductMappingRepository cfgMktProductMappingRepository;

    @Autowired
    public CfgMktProductMappingObjectMapper(CfgMktProductMappingRepository cfgMktProductMappingRepository) {
        this.cfgMktProductMappingRepository = cfgMktProductMappingRepository;
    }

    CfgMktProductMapping createRow(Row row) {
        CfgMktProductMapping cfgMktProductMapping = new CfgMktProductMapping();

        cfgMktProductMapping.setMktProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktProductMapping.setContractType(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktProductMapping.setExchangedTraded(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktProductMapping.setInstrumentType(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgMktProductMapping.setTableName(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgMktProductMapping.setUnderlyingType(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);

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
