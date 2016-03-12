package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgCcfMapping;
import com.blackiceinc.era.persistence.erau.repository.CfgCcfMappingRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgCcfMappingObjectMapper extends AbstractObjectMapper {

    CfgCcfMappingRepository cfgCcfMappingRepository;

    @Autowired
    public CfgCcfMappingObjectMapper(CfgCcfMappingRepository cfgCcfMappingRepository) {
        this.cfgCcfMappingRepository = cfgCcfMappingRepository;
    }

    CfgCcfMapping createRow(Row row) {

        CfgCcfMapping cfgCcfMapping = new CfgCcfMapping();

        cfgCcfMapping.setEraProductType(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgCcfMapping.setCcf(row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : null);
        cfgCcfMapping.setUnconditionallyCancelable(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);

        Cell cell3 = row.getCell(3);
        if (cell3 != null) {
            switch (cell3.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCcfMapping.setMaturityStart(cell3 != null ? String.valueOf((int) cell3.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCcfMapping.setMaturityStart(cell3 != null ? cell3.getStringCellValue() : null);
                    break;
            }
        }

        Cell cell4 = row.getCell(4);
        if (cell4 != null) {
            switch (cell4.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cfgCcfMapping.setMaturityStart(cell4 != null ? String.valueOf((int) cell4.getNumericCellValue()) : null);
                    break;
                case Cell.CELL_TYPE_STRING:
                    cfgCcfMapping.setMaturityEnd(cell4 != null ? cell4.getStringCellValue() : null);
                    break;
            }
        }

        cfgCcfMapping.setSeq(row.getCell(5) != null ? (long) row.getCell(5).getNumericCellValue() : null);

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
