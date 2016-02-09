package com.blackiceinc.era.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	
	@RequestMapping(value = "/filter-options", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getFilterOptions(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		
		measurementSensitivityRepository.findAll();
		
		return result;
	}
	
}
