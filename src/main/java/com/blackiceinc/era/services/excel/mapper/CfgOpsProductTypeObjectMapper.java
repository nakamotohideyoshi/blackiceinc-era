package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgOpsProductType;
import com.blackiceinc.era.persistence.erau.repository.CfgOpsProductTypeRepository;
import com.blackiceinc.era.services.excel.mapper.exception.PrimaryColumnMappingException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgOpsProductTypeObjectMapper extends AbstractObjectMapper {

    CfgOpsProductTypeRepository cfgOpsProductTypeRepository;

    @Autowired
    public CfgOpsProductTypeObjectMapper(CfgOpsProductTypeRepository cfgOpsProductTypeRepository) {
        this.cfgOpsProductTypeRepository = cfgOpsProductTypeRepository;
    }

    CfgOpsProductType createRow(Row row) throws PrimaryColumnMappingException {
        CfgOpsProductType cfgOpsProductType = new CfgOpsProductType();

        cfgOpsProductType.setOpsProductType(getStringValue(row.getCell(0)));
        cfgOpsProductType.setOpsProductDesc(getStringValue(row.getCell(1)));
        cfgOpsProductType.setOpsBusIndicator(getStringValue(row.getCell(2)));

        return cfgOpsProductType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgOpsProductType> all = cfgOpsProductTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgOpsProductType cfgOpsProductType : all) {
            if (cfgOpsProductType!=null){
                XSSFRow row = sheet.createRow(rowIndex);

                createCell(row, 0, cfgOpsProductType.getOpsProductType());
                createCell(row, 1, cfgOpsProductType.getOpsProductDesc());
                createCell(row, 2, cfgOpsProductType.getOpsBusIndicator());

                rowIndex++;
            }
        }
    }
}
