package com.blackiceinc.era.web.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;

@RestController
@RequestMapping("/api")
public class RunCalculatorResource {

	private final Logger log = LoggerFactory.getLogger(RunCalculatorResource.class);
	
	@Autowired
    private RunCalculatorRepository runCalculatorRepository;
	
	@Autowired
	private MeasurementSensitivityRepository measurementSensitivityRepository;
	
	@RequestMapping(value = "/runCalculator",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<RunCalculator>> getAll(@RequestParam(value = "page", required = false) Integer page,
	        @RequestParam(value = "length", required = false) Integer length){
		
        /** Check to see if the queryParam contains "page" & "length" if not set default values **/
        int pageNumber = (page!=null) ? page : 0;
        int pageSize = (length!=null) ? length : 25;
        
		Page<RunCalculator> findAll = null;
		try{
			
			findAll = runCalculatorRepository.findAll(new PageRequest(pageNumber, pageSize));	
		}catch(Exception ex){
			log.error("DB Exception", ex);
		}
		
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/runCalculator",
	        method = RequestMethod.POST,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RunCalculator> create(@Valid @RequestBody RunCalculator runCalculator) throws URISyntaxException {
	        log.debug("REST request to save RunCalculator : {}", runCalculator);
	        if (runCalculator.getId() != null) {
//	            return ResponseEntity.badRequest().header("Failure", "A new workEntry cannot already have an ID").body(null);
	        }

	        RunCalculator result = runCalculatorRepository.save(runCalculator);
	        return ResponseEntity.created(new URI("/api/workEntrys/" + result.getId()))
	            .body(result);
	    }
	
	@RequestMapping(value = "/runCalculator/{id}",
	        method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        log.debug("REST request to delete RunCalculator : {}", id);
	        runCalculatorRepository.delete(id);
	        return ResponseEntity.ok().build();
	    }
	
	@RequestMapping(value = "/runCalculator/filter-options", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getFilterOptions(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		List<Date> snapshotDateList = new ArrayList<>();
		List<BigDecimal>  loadJobNbrList = new ArrayList<>();
		List<String>  scenarioIdList = new ArrayList<>();
		for (MeasurementSensitivity ms:generateDummyMeasurementData()){
			if (!snapshotDateList.contains(ms.getSnapshotDate())){
				snapshotDateList.add(ms.getSnapshotDate());
			}

			if (!loadJobNbrList.contains(ms.getLoadJobNbr())){
				loadJobNbrList.add(ms.getLoadJobNbr());
			}

			if (!scenarioIdList.contains(ms.getScenarioId())){
				scenarioIdList.add(ms.getScenarioId());
			}
		}

		result.put("snapshotDate", snapshotDateList);
		result.put("loadJobNbr", loadJobNbrList);
		result.put("scenarioId", scenarioIdList);

//		measurementSensitivityRepository.findAll();
		
		return result;
	}

	private List<MeasurementSensitivity> generateDummyMeasurementData() {
		List<MeasurementSensitivity> result = new ArrayList<MeasurementSensitivity>();

		result.add(createMeasurementSensitivity(new Date(116, 0, 1), new BigDecimal(1), "scen1"));
		result.add(createMeasurementSensitivity(new Date(116, 1, 1), new BigDecimal(2), "scen2"));
		result.add(createMeasurementSensitivity(new Date(116, 1, 1), new BigDecimal(3), "scen2"));
		result.add(createMeasurementSensitivity(new Date(116, 2, 1), new BigDecimal(3), "scen3"));
		result.add(createMeasurementSensitivity(new Date(116, 3, 1), new BigDecimal(4), "scen4"));
		result.add(createMeasurementSensitivity(new Date(116, 4, 1), new BigDecimal(4), "scen4"));
		result.add(createMeasurementSensitivity(new Date(116, 5, 1), new BigDecimal(10), "scen5"));
		result.add(createMeasurementSensitivity(new Date(116, 6, 1), new BigDecimal(10), "scen6"));
		result.add(createMeasurementSensitivity(new Date(116, 7, 1), new BigDecimal(11), "scen7"));
		result.add(createMeasurementSensitivity(new Date(116, 8, 1), new BigDecimal(13), "scen7"));

		return result;
	}

	private MeasurementSensitivity createMeasurementSensitivity(Date date, BigDecimal jobNbr, String scenarioId){
		MeasurementSensitivity ms1 = new MeasurementSensitivity();
		ms1.setSnapshotDate(date);
		ms1.setLoadJobNbr(jobNbr);
		ms1.setScenarioId(scenarioId);

		return ms1;
	}
	
}
