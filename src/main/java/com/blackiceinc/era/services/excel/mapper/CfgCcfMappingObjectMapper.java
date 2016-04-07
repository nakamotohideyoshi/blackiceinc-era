package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgCcfMappingRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCcfMappingObjectMapper extends AbstractObjectMapper<CfgCcfMapping> {

    CfgCcfMappingRepository cfgCcfMappingRepository;

    @Autowired
    public CfgCcfMappingObjectMapper(CfgCcfMappingRepository cfgCcfMappingRepository) {
        this.cfgCcfMappingRepository = cfgCcfMappingRepository;
    }

    @Override
    CfgCcfMapping createRow(Row row) {
        CfgCcfMapping cfgCcfMapping = new CfgCcfMapping();

        cfgCcfMapping.setEraProductType(getStringValue(row.getCell(0)));
        cfgCcfMapping.setCcf(getDoubleValue(row.getCell(1)));
        cfgCcfMapping.setUnconditionallyCancelable(getStringValue(row.getCell(2)));
        cfgCcfMapping.setMaturityStart(getStringValue(row.getCell(3)));
        cfgCcfMapping.setMaturityStart(getStringValue(row.getCell(4)));
        cfgCcfMapping.setSeq(getLongValue(row.getCell(5)));

        return cfgCcfMapping;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgCcfMapping> all = cfgCcfMappingRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgCcfMapping cfgCcfMapping : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgCcfMapping.getEraProductType());
            createCell(row, 1, cfgCcfMapping.getCcf());
            createCell(row, 2, cfgCcfMapping.getUnconditionallyCancelable());
            createCell(row, 3, cfgCcfMapping.getMaturityStart());
            createCell(row, 4, cfgCcfMapping.getMaturityEnd());
            createCell(row, 5, cfgCcfMapping.getSeq());

            rowIndex++;
        }
    }
}
