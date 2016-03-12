package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgMktIrrSpcRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrSpcRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CfgMktIrrSpcRiskObjectMapper extends AbstractObjectMapper {

    CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;

    @Autowired
    public CfgMktIrrSpcRiskObjectMapper(CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository) {
        this.cfgMktIrrSpcRiskRepository = cfgMktIrrSpcRiskRepository;
    }

    CfgMktIrrSpcRisk createRow(Row row) {
        CfgMktIrrSpcRisk cfgMktIrrSpcRisk = new CfgMktIrrSpcRisk();

        cfgMktIrrSpcRisk.setMktAssetClass(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setIssueRiskBucket(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setIssuerRiskBucket(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setResidualMaturityStart(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setResidualMaturityEnd(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setInstrumentGroup(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : null);
        cfgMktIrrSpcRisk.setRiskWeight(row.getCell(6) != null ? row.getCell(6).getNumericCellValue() : null);

        return cfgMktIrrSpcRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrSpcRisk> all = cfgMktIrrSpcRiskRepository.findAll();
        ExcelUtils.removeAllRowsExcelFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrSpcRisk cfgMktIrrSpcRisk : all) {
            XSSFRow row = sheet.createRow(rowIndex);

            createCell(row, 0, cfgMktIrrSpcRisk.getMktAssetClass());
            createCell(row, 1, cfgMktIrrSpcRisk.getIssueRiskBucket());
            createCell(row, 2, cfgMktIrrSpcRisk.getIssuerRiskBucket());
            createCell(row, 3, cfgMktIrrSpcRisk.getResidualMaturityStart());
            createCell(row, 4, cfgMktIrrSpcRisk.getResidualMaturityEnd());
            createCell(row, 5, cfgMktIrrSpcRisk.getInstrumentGroup());
            createCell(row, 6, cfgMktIrrSpcRisk.getRiskWeight());

            rowIndex++;
        }
    }
}
