package com.blackiceinc.era.services;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class CreditRiskService {


    public HashMap<String, List> getFilterOptions() {
        HashMap<String, List> filterOptions = new HashMap<>();

        List<Date> snapshotDateList = getSnapshotDates();
        List<Integer> loadJobNbrList = getLoadJobNbrs();
        List<String> scenarioIdList = getScenarioIds();
        List<String> industryList = getIndustries();
        List<String> profitCentreList = getProfitCentres();
        List<String> assetClassList = getAssets();
        List<String> exposureTypeList = getExposureTypes();
        List<String> entityTypeList = getEntityTypes();
        List<String> productTypeList = getProductTypes();

        filterOptions.put("snapshotDate", snapshotDateList);
        filterOptions.put("loadJobNbr", loadJobNbrList);
        filterOptions.put("scenarioId", scenarioIdList);
        filterOptions.put("industry", industryList);
        filterOptions.put("profitCentre", profitCentreList);
        filterOptions.put("assetClass", assetClassList);
        filterOptions.put("exposureType", exposureTypeList);
        filterOptions.put("entityType", entityTypeList);
        filterOptions.put("productType", productTypeList);

        return filterOptions;
    }

    private List<Date> getSnapshotDates() {
        List<Date> snapshotDateList = new ArrayList<>();
        snapshotDateList.add(new Date(2016, 2, 1));
        snapshotDateList.add(new Date(2016, 3, 1));
        return snapshotDateList;
    }

    public List<Integer> getLoadJobNbrs() {
        return Arrays.asList(1, 2, 3);
    }

    public List<String> getScenarioIds() {
        return Arrays.asList("1", "2", "3");
    }

    public List<String> getIndustries() {
        return Arrays.asList("industry 1", "industry 2");
    }

    public List<String> getAssets() {
        return Arrays.asList("Asset 1", "Asset 2");
    }

    public List<String> getProfitCentres() {
        return Arrays.asList("profit centre 1", "profit centre 2", "profit centre 3");
    }

    public List<String> getExposureTypes() {
        return Arrays.asList("exposure type 1", "exposure type 2");
    }

    public List<String> getEntityTypes() {
        return Arrays.asList("entity type 1", "entity type 2");
    }

    public List<String> getProductTypes() {
        return Arrays.asList("product type 1", "product type 2");
    }
}
