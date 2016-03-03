package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrSpcRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrSpcRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktIrrSpcRiskObjectMapper {

    CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository;

    @Autowired
    public CfgMktIrrSpcRiskObjectMapper(CfgMktIrrSpcRiskRepository cfgMktIrrSpcRiskRepository){
        this.cfgMktIrrSpcRiskRepository = cfgMktIrrSpcRiskRepository;
    }

    public List<CfgMktIrrSpcRisk> extractData(XSSFSheet sheet) {
        List<CfgMktIrrSpcRisk> result = new ArrayList<>();

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIndex = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // ignore first row since it's column names
            if (rowIndex > 0) {
                result.add(createRow(row));
            }

            rowIndex++;
        }

        return result;
    }

    private CfgMktIrrSpcRisk createRow(Row row) {

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
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrSpcRisk cfgMktIrrSpcRisk:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrSpcRisk.getMktAssetClass());
            row.createCell(1).setCellValue(cfgMktIrrSpcRisk.getIssueRiskBucket());
            row.createCell(2).setCellValue(cfgMktIrrSpcRisk.getIssuerRiskBucket());
            row.createCell(2).setCellValue(cfgMktIrrSpcRisk.getResidualMaturityStart());
            row.createCell(2).setCellValue(cfgMktIrrSpcRisk.getResidualMaturityEnd());
            row.createCell(2).setCellValue(cfgMktIrrSpcRisk.getInstrumentGroup());
            row.createCell(2).setCellValue(cfgMktIrrSpcRisk.getRiskWeight());

            rowIndex++;
        }
    }
}
