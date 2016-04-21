package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgReclassCheckType;
import com.blackiceinc.era.persistence.erau.repository.CfgReclassCheckTypeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgReclassCheckTypeObjectMapper extends AbstractObjectMapper<CfgReclassCheckType> {

    private CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository;

    @Autowired
    public CfgReclassCheckTypeObjectMapper(CfgReclassCheckTypeRepository cfgReclassCheckTypeRepository) {
        this.cfgReclassCheckTypeRepository = cfgReclassCheckTypeRepository;
    }

    @Override
    CfgReclassCheckType createRow(Row row) {
        CfgReclassCheckType cfgReclassCheckType = new CfgReclassCheckType();

        cfgReclassCheckType.setCheckType(getStringValue(row.getCell(0)));
        cfgReclassCheckType.setCheckDescription(getStringValue(row.getCell(1)));
        cfgReclassCheckType.setWhereClause(getStringValue(row.getCell(2)));
        cfgReclassCheckType.setConsoField(getStringValue(row.getCell(3)));
        cfgReclassCheckType.setAmtField(getStringValue(row.getCell(4)));

        return cfgReclassCheckType;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgReclassCheckType> all = cfgReclassCheckTypeRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgReclassCheckType cfgReclassCheckType : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgReclassCheckType.getCheckType());
            createCell(row, 1, cfgReclassCheckType.getCheckDescription());
            createCell(row, 2, cfgReclassCheckType.getWhereClause());
            createCell(row, 3, cfgReclassCheckType.getConsoField());
            createCell(row, 4, cfgReclassCheckType.getAmtField());

            rowIndex++;
        }
    }
}
