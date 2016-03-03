package com.blackiceinc.era.services.excel.mapper;

import com.blackiceinc.era.persistence.erau.model.CfgFinancialBook;
import com.blackiceinc.era.persistence.erau.model.CfgMktIrrGnrRisk;
import com.blackiceinc.era.persistence.erau.repository.CfgMktIrrGnrRiskRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CfgMktIrrGnrRiskObjectMapper {

    CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository;

    @Autowired
    public CfgMktIrrGnrRiskObjectMapper(CfgMktIrrGnrRiskRepository cfgMktIrrGnrRiskRepository){
        this.cfgMktIrrGnrRiskRepository = cfgMktIrrGnrRiskRepository;
    }

    public List<CfgMktIrrGnrRisk> extractData(XSSFSheet sheet) {
        List<CfgMktIrrGnrRisk> result = new ArrayList<>();

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

    private CfgMktIrrGnrRisk createRow(Row row) {

        CfgMktIrrGnrRisk cfgMktIrrGnrRisk = new CfgMktIrrGnrRisk();

        cfgMktIrrGnrRisk.setZoneCode(row.getCell(0) != null ? row.getCell(0).getStringCellValue() : null);
        cfgMktIrrGnrRisk.setBandCode(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : null);
        cfgMktIrrGnrRisk.setCurrency(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null);
        cfgMktIrrGnrRisk.setCouponRateStart(row.getCell(3) != null ? Long.valueOf(row.getCell(3).getStringCellValue()) : null);
        cfgMktIrrGnrRisk.setCouponRateEnd(row.getCell(4) != null ? Long.valueOf(row.getCell(4).getStringCellValue()) : null);
        cfgMktIrrGnrRisk.setMaturityBandStart(row.getCell(5) != null ? Long.valueOf(row.getCell(5).getStringCellValue()) : null);
        cfgMktIrrGnrRisk.setMaturityBandEnd(row.getCell(6) != null ? Long.valueOf(row.getCell(6).getStringCellValue()) : null);
        cfgMktIrrGnrRisk.setRiskWeight(row.getCell(7) != null ? row.getCell(7).getNumericCellValue() : null);

        return cfgMktIrrGnrRisk;
    }

    public void importData(XSSFSheet sheet) {
        List<CfgMktIrrGnrRisk> all = cfgMktIrrGnrRiskRepository.findAll();
        ExcelUtils.removeAllRowsExceltFirstOne(sheet);
        int rowIndex = 1;
        for (CfgMktIrrGnrRisk cfgMktIrrGnrRisk:all){
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(cfgMktIrrGnrRisk.getZoneCode());
            row.createCell(1).setCellValue(cfgMktIrrGnrRisk.getBandCode());
            row.createCell(2).setCellValue(cfgMktIrrGnrRisk.getCurrency());
            row.createCell(3).setCellValue(cfgMktIrrGnrRisk.getCouponRateStart());
            row.createCell(4).setCellValue(cfgMktIrrGnrRisk.getCouponRateEnd());
            row.createCell(5).setCellValue(cfgMktIrrGnrRisk.getMaturityBandStart());
            row.createCell(6).setCellValue(cfgMktIrrGnrRisk.getMaturityBandEnd());
            row.createCell(7).setCellValue(cfgMktIrrGnrRisk.getRiskWeight());

            rowIndex++;
        }
    }
}
