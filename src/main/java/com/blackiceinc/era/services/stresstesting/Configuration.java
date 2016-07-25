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
        add("G", "29", "select sum(balance_lcy_amt) from gl_balance\n" +
                "where SNAPSHOT_DATE = '31-jan-16'\n" +
                "and (substr(sbv_code,1,3) in ('201','202','203','204','205') OR\n" +
                "substr(sbv_code,1,2) in ('21','22','23','24','25','26','27','28','29')\n" +
                "OR substr(sbv_code,1,2) in ('2191','2291','2391','2491','2591','2691','2791','2891','2991')\n" +
                "OR substr(sbv_code,1,2) in ('2192','2292','2392','2492','2592','2692','2792','2892','2992')\n" +
                "or substr(sbv_code,1,3) in ('139','209'))\n" +
                "and BALANCE_LCY_AMT > 0", conf);
        add("O", "29", " SELECT ABS(SUM(BALANCE_LCY_AMT)) FROM GL_BALANCE\n" +
                "WHERE (SBV_CODE IN ('4111', '4112','4121','4122','4131','4132','4141','4142')\n" +
                "OR  SUBSTR(SBV_CODE,1,2) IN ('40','42'))\n" +
                "AND SNAPSHOT_DATE = '31-JAN-16'", conf);
        add("W", "29", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SBV_SUMMARY_TYPE = 'CAP_ELEMENTS'\n" +
                "AND HEADING IN ('CHAR_CAP','CAP_SURP')\n" +
                "AND SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'", conf);
        add("G", "30", "select sum(balance_lcy_amt) from gl_balance\n" +
                "where SNAPSHOT_DATE = '31-jan-16'\n" +
                "and substr(sbv_code,1,2) in ('21','22','23','24','25','26','27','28','29')\n" +
                "and BALANCE_LCY_AMT > 0", conf);
        add("W", "30", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SBV_SUMMARY_TYPE = 'CAP_ELEMENTS'\n" +
                "AND HEADING IN ('RET_EAR')\n" +
                "AND SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'", conf);
        add("G", "31", "select sum(balance_lcy_amt) from gl_balance\n" +
                "where SNAPSHOT_DATE = '31-jan-16'\n" +
                "and substr(sbv_code,1,3) in ('201','202','203','204','205')\n" +
                "and BALANCE_LCY_AMT > 0", conf);
        add("W", "31", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and HEADING in ('RES_SUP_CHA_CAP','RES_OPE_DEV','RES_CON_PROC_FA','FIN_RES','OTH_RES_REG')", conf);
        add("G", "33", "select sum(balance_lcy_amt) from gl_balance\n" +
                "where SNAPSHOT_DATE = '31-jan-16'\n" +
                "and substr(sbv_code,1,3) in ('101','103','104','105')", conf);
        add("W", "33", "select abs(sum(EXPOSURE_VALUE_LCY_AMT)) from SBV_SUMMARY where SBV_SUMMARY_TYPE = 'CAP_ELEMENTS' and HEADING in ('DIF_REV_FA','DIF_REV_LONG_CAP','GEN_PRO_75','HYB_INST','SUB_DEBT','GOOD_WILL','ACC_LOSS','TREA_SHARES','LOAN_CAP_CONTR_PURCH_SHARE_CI','CAP_PURCH_SHARE_CI','CAP_PURCH_SHARE_ENT_B_S_I','INVES_CAP_PURCH_SHARE_ENT_EXC_10_CHAR_CAP','INVES_CAP_PURCH_SHARE_ENT_EXC_40_CHAR_CAP')", conf);
        add("G", "34", "select sum(BALANCE_LCY_AMT) from gl_balance\n" +
                "where snapshot_Date = '31-jan-16'\n" +
                "and substr(sbv_code,1,4) in ('1510')", conf);
        add("G", "35", "select sum(NOMINAL_LCY_AMT) from MARKET_RISK\n" +
                "where snapshot_Date = '31-jan-2016'\n" +
                "and SCENARIO_ID = '3'\n" +
                "and LOAD_JOB_NBR = '3'\n" +
                "and MKT_ASSET_CLASS = 'SOV_BOND'", conf);
        add("G", "37", "select sum(BALANCE_LCY_AMT) from gl_balance\n" +
                "where snapshot_Date = '31-jan-16'\n" +
                "and (sbv_code in ('1550','1560','1570') or substr(sbv_code,1,2) in ('34'))\n" +
                "and balance_lcy_amt > 0", conf);
        add("G", "46", "SELECT SUM(EXPOSURE_VALUE_LCY_AMT) FROM SBV_SUMMARY\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OTH_ASSETS'\n" +
                "AND HEADING NOT IN ('CASH_AND_CASH_EQUIV')", conf);

        // set formulas to be executed in Financial Statements / Balance Sheet
        add("G", "27", StressTestingService.FORMULA, conf);
        add("O", "27", StressTestingService.FORMULA, conf);
        add("W", "27", StressTestingService.FORMULA, conf);
        // Financial Statements / Balance Sheet END

        // Income Statement BEGIN
        add("G", "51", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING = 'INTEREST_INCOME'\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("O", "51", "select SUM(DCN-DCC) from sta.tblbalance_sheet_month_cir_10  \n" +
                "where ngaysl = '31-JAN-16'\n" +
                "and donviid = '01314000'\n" +
                "AND TK IN ('8822','8827')", conf);
        add("G", "52", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING = 'INTEREST_EXPENSES'\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("G", "54", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING IN ('SERVICE_INCOME')\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("G", "55", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING IN ('SERVICE_EXPENSES')\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("G", "57", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING IN ('GAIN/LOSS FROM FX', 'GAIN/LOSS FROM SECURITIES')\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("G", "59", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND SBV_SUMMARY_TYPE = 'OPS_RISK'\n" +
                "AND HEADING IN ('SERVICE_INCOME_OTH', 'SERVICE_EXPENSES_OTH')\n" +
                "AND EXTRACT (YEAR FROM SNAPSHOT_DATE) = '2015'", conf);
        add("O", "60", "SELECT ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) FROM SBV_SUMMARY\n" +
                "WHERE SBV_SUMMARY_TYPE = 'CAP_ELEMENTS'\n" +
                "AND HEADING IN ('RET_EAR')\n" +
                "AND SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'", conf);

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
        add("G", "74", "select ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) from sbv_summary\n" +
                "where snapshot_date = '31-jan-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND HEADING = 'TIER_1_CAP_AFTER_DEDUCT'", conf);
        add("O", "74", "SELECT SUM(RWA_LCY_AMT) FROM SBV_SUMMARY\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND (HEADING IN ('SOVEREIGN','FINANCIAL_INSTIT','CORP','RETAIL','MORTGAGES','NON-PERFORM_CLAIMS',\n" +
                "'LOANS_SECURED_BY_REAL_ESTATE')\n" +
                "OR SBV_SUMMARY_TYPE = 'OTH_ASSETS')", conf);
        add("O", "75", "SELECT SUM(RWA_LCY_AMT) FROM SBV_SUMMARY\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND HEADING IN ('FX_RISK','IRR_GNR','IRR_SPC')\n" +
                "AND SBV_SUMMARY_TYPE = 'SUMMARY'", conf);
        add("G", "76", "select ABS(SUM(EXPOSURE_VALUE_LCY_AMT)) from sbv_summary\n" +
                "where snapshot_date = '31-jan-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND HEADING = 'TIER_2_CAP'", conf);
        add("O", "76", "SELECT SUM(RWA_LCY_AMT) FROM SBV_SUMMARY\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND HEADING IN ('OPS_SUM_TOTAL')", conf);
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
        add("T", "95", "select sum(RWA_LCY_AMT) from sbv_summary\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "and heading = 'SOVEREIGN'", conf);
        add("T", "97", "select sum(RWA_LCY_AMT) from sbv_summary\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "and heading in ('FINANCIAL_INSTIT')", conf);
        add("T", "98", "select sum(ms.RWA_AMT) FROM MEASUREMENT_SENSITIVITY ms\n" +
                "              WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "              AND SCENARIO_ID = '3'\n" +
                "              AND LOAD_JOB_NBR = '3'\n" +
                "              AND ms.ASSET_CLASS_FINAL in\n" +
                "              ('CLASS_CORP','CLASS_CORP_PF','CLASS_CORP_OF','CLASS_CORP_CF','CLASS_CORP_IPRE','CLASS_FINANCIAL_LEASE_CORP')\n" +
                "              AND ms.ERA_NPL_CODE = 'P'", conf);
        add("T", "99", "select sum(ms.RWA_AMT) FROM MEASUREMENT_SENSITIVITY ms\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "AND ms.ASSET_CLASS_FINAL in ('CLASS_SME')\n" +
                "AND ms.ERA_NPL_CODE = 'P'", conf);
        add("T", "100", "select sum(RWA_LCY_AMT) from sbv_summary\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "and heading = 'RETAIL'", conf);
        add("T", "101", "select sum(RWA_LCY_AMT) from sbv_summary\n" +
                "WHERE SNAPSHOT_DATE = '31-JAN-16'\n" +
                "AND SCENARIO_ID = '3'\n" +
                "AND LOAD_JOB_NBR = '3'\n" +
                "and heading = 'MORTGAGES'", conf);

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
