package com.blackiceinc.era.services.stresstesting;

import com.blackiceinc.era.services.stresstesting.model.ExcelMapping;

import java.util.ArrayList;
import java.util.List;

class Configuration {

    private static final String STRESS_TESTING = "STRESS_TESTING";

    static List<ExcelMapping> getForStressTesting() {
        List<ExcelMapping> conf = new ArrayList<>();

        // Financial Statements / Balance Sheet BEGIN
        add("M", "17", StressTestingService.CURRENT_YEAR, conf);
        add("T", "17", StressTestingService.COUNTRY_CODE, conf);
        add("M", "18", StressTestingService.PERIODS_YEAR, conf);
        add("M", "19", StressTestingService.FIGURES_BASE, conf);
        add("M", "20", StressTestingService.CURRENCY, conf);
        add("M", "21", StressTestingService.CONVERSION_RATE, conf);
        add("G", "29", "select sum(Outstanding_Lcy_Amt) from MEASUREMENT_SENSITIVITY", conf);
        add("W", "29", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE='CAP_ELEMENTS' and HEADING IN ('CHAR_CAP', 'CAP_SURP')", conf);
        add("G", "30", "select sum(OUTSTANDING_LCY_AMT) from MEASUREMENT_SENSITIVITY where ASSET_CLASS_FINAL in ('CLASS_DOM_BANK', 'CLASS_FOREIGN_BANK')", conf);
        add("W", "30", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and HEADING in ('RET_EAR')", conf);
        add("G", "31", "select sum(OUTSTANDING_LCY_AMT) from MEASUREMENT_SENSITIVITY where ASSET_CLASS_FINAL in ('CLASS_DOM_BANK', 'CLASS_FOREIGN_BANK')", conf);
        add("W", "31", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and HEADING in ('RES_SUP_CHA_CAP','RES_OPE_DEV','RES_CON_PROC_FA','FIN_RES','OTH_RES_REG')", conf);
        add("G", "33", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OTH_ASSETS' and HEADING in ('GL_CASH','GL_GOLD')", conf);
        add("W", "33", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and HEADING in ('DIF_REV_FA','DIF_REV_LONG_CAP','GEN_PRO_75','HYB_INST','SUB_DEBT','GOOD_WILL','ACC_LOSS','TREA_SHARES','LOAN_CAP_CONTR_PURCH_SHARE_CI','CAP_PURCH_SHARE_CI','CAP_PURCH_SHARE_ENT_B_S_I','INVES_CAP_PURCH_SHARE_ENT_EXC_10_CHAR_CAP','INVES_CAP_PURCH_SHARE_ENT_EXC_40_CHAR_CAP')", conf);
        add("G", "35", "select sum(NOMINAL_LCY_AMT) from MARKET_RISK", conf);
        add("G", "46", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OTH_ASSETS' and HEADING not in ('GL_CASH','GL_GOLD')", conf);

        // set formulas to be executed in Financial Statements / Balance Sheet
        add("G", "27", StressTestingService.FORMULA, conf);
        add("O", "27", StressTestingService.FORMULA, conf);
        add("W", "27", StressTestingService.FORMULA, conf);
        // Financial Statements / Balance Sheet END

        // Income Statement BEGIN
        add("G", "51", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and HEADING = 'INTEREST_INCOME'", conf);
        add("O", "51", "select sum(ALLOW_LCY_AMT) from MEASUREMENT_SENSITIVITY", conf);
        add("G", "52", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and HEADING = 'INTEREST_EXPENSES'", conf);
        add("G", "54", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and HEADING = 'SERVICE_INCOME'", conf);
        add("G", "55", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and HEADING = 'SERVICE_EXPENSES'", conf);
        add("G", "57", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and SBV_SUMMARY.HEADING in ('GAIN/LOSS FROM FX' ,'GAIN/LOSS FROM SECURITIES')", conf);
        add("G", "59", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'OPS_RISK' and SBV_SUMMARY.HEADING in ( 'SERVICE_INCOME_OTH' ,'SERVICE_EXPENSES_OTH')", conf);
        add("O", "60", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and SBV_SUMMARY.HEADING in ('RET_EAR')", conf);

        // set formulas to be executed in Income Statement
        add("G", "50", StressTestingService.FORMULA, conf);
        add("O", "52", StressTestingService.FORMULA, conf);
        add("G", "53", StressTestingService.FORMULA, conf);
        add("G", "56", StressTestingService.FORMULA, conf);
        add("G", "61", StressTestingService.FORMULA, conf);
        add("G", "62", StressTestingService.FORMULA, conf);

        add("W", "50", StressTestingService.FORMULA, conf);
        add("W", "51", StressTestingService.FORMULA, conf);
        add("W", "52", StressTestingService.FORMULA, conf);
        add("W", "53", StressTestingService.FORMULA, conf);
        add("W", "54", StressTestingService.FORMULA, conf);
        add("W", "55", StressTestingService.FORMULA, conf);
        add("W", "56", StressTestingService.FORMULA, conf);
        add("W", "57", StressTestingService.FORMULA, conf);
        // Income Statement END

        // Regulatory Data BEGIN
        add("G", "74", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('TIER_1_CAP', 'DEDUCT_TIER1')", conf);
        add("O", "74", "select sum(RWA_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('SOVEREIGN', 'FINANCIAL_INSTIT', 'CORP', 'RETAIL', 'NON_PERFORM_CLAIMS', 'CCR', 'CR_REPO')", conf);
        add("O", "75", "select sum(RWA_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('FX_RISK', 'IRR_GNR', 'IRR_SPC')", conf);
        add("G", "76", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ( 'TIER_2_CAP' ,'ITEMS_50%')", conf);
        add("O", "76", "select sum(EXPOSURE_VALUE_LCY_AMT) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'TOTAL' and SBV_SUMMARY.HEADING = 'OPS_SUM_TOTAL'", conf);
        add("O", "77", "select sum(RWA_LCY_AMT)from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('OTH_ONB')", conf);

        // set formulas to be executed in Regulatory Data
        add("G", "73", StressTestingService.FORMULA, conf);
        add("O", "73", StressTestingService.FORMULA, conf);

//        add("G", "79", StressTestingService.FORMULA, conf);
//        add("G", "80", StressTestingService.FORMULA, conf);
//        add("G", "81", StressTestingService.FORMULA, conf);
//        add("G", "82", StressTestingService.FORMULA, conf);
//        add("G", "83", StressTestingService.FORMULA, conf);
        // Regulatory Data END

        // Credit Risk BEGIN
        add("T", "95", "select sum(RWA_LCY_AMT)from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING = 'SOVEREIGN'", conf);
        add("T", "97", "select sum(RWA_LCY_AMT)from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('FINANCIAL_INSTIT')", conf);
        add("T", "98", "select sum(RWA_LCY_AMT)from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('CORP')", conf);
        add("T", "100", "select sum(RWA_LCY_AMT)from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'SUMMARY' and SBV_SUMMARY.HEADING in ('RETAIL')", conf);

        // set formulas to be executed in Credit Risk
//        add("T", "105", StressTestingService.FORMULA, conf);
//        add("F", "95", StressTestingService.FORMULA, conf);
//        add("M", "95", StressTestingService.FORMULA, conf);
//        add("F", "96", StressTestingService.FORMULA, conf);
//        add("M", "96", StressTestingService.FORMULA, conf);
//        add("Z", "96", StressTestingService.FORMULA, conf);
//        add("F", "97", StressTestingService.FORMULA, conf);
//        add("M", "99", StressTestingService.FORMULA, conf);
//        add("M", "105", StressTestingService.FORMULA, conf);
//        add("Z", "105", StressTestingService.FORMULA, conf);
//        add("M", "106", StressTestingService.FORMULA, conf);
//        add("Z", "106", StressTestingService.FORMULA, conf);
//        add("M", "107", StressTestingService.FORMULA, conf);
//        add("Z", "107", StressTestingService.FORMULA, conf);
//        add("Z", "108", StressTestingService.FORMULA, conf);
//        add("Z", "109", StressTestingService.FORMULA, conf);
        // Credit Risk END

        // Market Risk BEGIN
        // set formulas to be executed in Market Risk
//        add("T", "129", StressTestingService.FORMULA, conf);
        // Market Risk END

        // Credit Risk (Portfolio Level) BEGIN
        // set formulas to be executed in Credit Risk (Portfolio Level)
//        add("G", "147", StressTestingService.FORMULA, conf);
        // Credit Risk (Portfolio Level) END

        // Credit Exposure by Basel II Asset Classes BEGIN
        // set formulas to be executed in Credit Exposure by Basel II Asset Classes
//        add("G", "159", StressTestingService.FORMULA, conf);
        // Credit Exposure by Basel II Asset Classes END

        // Total credit exposure by region BEGIN
        // set formulas to be executed in Total credit exposure by region
//        add("G", "174", StressTestingService.FORMULA, conf);
        // Total credit exposure by region ENDc

        // PD/NPL ratio BEGIN
//        add("G", "174", StressTestingService.FORMULA, conf);
        // PD/NPL ratio END

        return conf;
    }

    private static void add(String column, String row, String value, List<ExcelMapping> conf) {
        conf.add(new ExcelMapping(column, row, STRESS_TESTING, value));
    }

}
