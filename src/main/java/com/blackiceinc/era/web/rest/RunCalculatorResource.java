package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.services.ImportExportMessageProvider;
import com.blackiceinc.era.services.RunCalculatorService;
import com.blackiceinc.era.web.rest.model.DeleteResponse;
import com.blackiceinc.era.web.rest.model.CRUDResponseObj;
import com.blackiceinc.era.web.rest.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RunCalculatorResource {

    private static final String STATUS_CLOSED = "Closed";
    private static final String OPEN = "Open";
    private final Logger log = LoggerFactory.getLogger(RunCalculatorResource.class);

    @Autowired
    private RunCalculatorRepository runCalculatorRepository;

    @Autowired
    private RunCalculatorService runCalculatorService;

    @Autowired
    private MeasurementSensitivityRepository measurementSensitivityRepository;

    @Autowired
    private ImportExportMessageProvider importExportMessageProvider;

    @RequestMapping(value = "/runCalculator",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RunCalculator>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "length", required = false) Integer length,
                                                      @RequestParam(value = "snapshotDate", required = false) Date snapshotDate,
                                                      @RequestParam(value = "loadJobNbr", required = false) BigDecimal loadJobNbr,
                                                      @RequestParam(value = "scenarioId", required = false) String scenarioId) throws URISyntaxException  {

        Page<RunCalculator> runCalculators = runCalculatorService
                .findRunCalculationByParams(page, length, snapshotDate, loadJobNbr, scenarioId);

        importExportMessageProvider.addMessage("RUN CALC");

        return new ResponseEntity<>(runCalculators, HttpStatus.OK);
    }

    @RequestMapping(value = "/runCalculator",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@Valid @RequestBody RunCalculator runCalculator) throws URISyntaxException {
        log.debug("REST request to save RunCalculator : {}", runCalculator);

        Response res = new Response();
        if (runCalculator.getId() != null) {
            res.setMessage("A new Run Calculator cannot already have an ID");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        try {
            runCalculator.setStatus(OPEN);
            RunCalculator savedEntity = runCalculatorRepository.save(runCalculator);
            res.setContent(savedEntity);
            res.setTotalElements(runCalculatorRepository.count());
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            res.setMessage("Cannot Add Duplicate Entries! A Record With These Values Already Exists.");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        } catch (ConstraintViolationException ex) {
            res.setMessage(getValidationExceptionMessages(ex));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/runCalculator",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteData(@RequestParam String idListStr) throws URISyntaxException {
        DeleteResponse res = new DeleteResponse();
        String[] idList = idListStr.split("\\|");
        HttpStatus returnStatus = HttpStatus.OK;

        for (String id : idList) {
            try {
                runCalculatorService.delete(Long.parseLong(id));
                res.addRecordResponse(new CRUDResponseObj(id, true));
            } catch (NumberFormatException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Invalid number formatting"));
                returnStatus = HttpStatus.NOT_FOUND;
            } catch (EmptyResultDataAccessException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Data with id=" + id + " does not exist."));
                returnStatus = HttpStatus.NOT_FOUND;
            } catch (DataIntegrityViolationException ex) {
                res.addRecordResponse(new CRUDResponseObj(id, false, "Cannot Be Deleted Due To Foreign Key Constraint."));
                returnStatus = HttpStatus.CONFLICT;
            }
        }

        res.setTotalElements(runCalculatorRepository.count());
        return new ResponseEntity<Response>(res, returnStatus);
    }

    @RequestMapping(value = "/runCalculator/check",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public
    HttpEntity<Map<String, Object>> checkIfExists(@RequestParam LinkedHashMap<String, String> allRequestParams) throws URISyntaxException {
        int page = (allRequestParams.containsKey("page")) ? Integer.parseInt(allRequestParams.get("page")) : 0;
        int length = (allRequestParams.containsKey("length")) ? Integer.parseInt(allRequestParams.get("length")) : 1;
        String hashKey = allRequestParams.containsKey("hashKey")?allRequestParams.get("hashKey"):null;
        Date snapshotDate = (allRequestParams.containsKey("snapshotDate")) ? Date.valueOf(allRequestParams.get("snapshotDate")) : null;
        BigDecimal loadJobNbr = (allRequestParams.containsKey("loadJobNbr")) ? BigDecimal.valueOf(Long.valueOf(allRequestParams.get("loadJobNbr"))) : null;
        String scenarioId = (allRequestParams.containsKey("scenarioId")) ? allRequestParams.get("scenarioId") : null;

        Page<RunCalculator> pageable = runCalculatorService
                .findRunCalculationByParams(page, length, snapshotDate, loadJobNbr, scenarioId);

        Map<String, Object> msg = new HashMap<String, Object>();
        if (pageable.getNumberOfElements() == 0) {
            msg.put("exists", false);
        } else {
            msg.put("exists", true);
        }
        msg.put("content", pageable.getContent());
        msg.put("hashKey", hashKey);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "runCalculator/runCalculation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> runCalculation(@Valid @RequestBody RunCalculator runCalculator) throws URISyntaxException {
        Response res = new Response();

        try {
            long start = System.currentTimeMillis();
            log.info("Running procedure RUN_CALC for runCalculator : {}", runCalculator.toString());
            // execute PL/SQL procedure
            runCalculatorRepository.runCalculatorStoredProcedure(runCalculator.getScenarioId(),
                    runCalculator.getLoadJobNbr().intValue(), runCalculator.getSnapshotDate());
            log.info("Procedure RUN_CALC for runCalculator : {} finished in {} ms", runCalculator.toString(), System.currentTimeMillis() - start);
            // runCalculatorRepository.runCalculatorStoredProcedureTest(runCalculator.getScenarioId(),
            // runCalculator.getLoadJobNbr().intValue(), runCalculator.getSnapshotDate());
        } catch (Exception ex){
            log.error("Error executing PL/SQL procedure", ex);
            res.setMessage("Error running calculation!");
            return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "runCalculator/closeCalculation/{id}", method = RequestMethod.POST)
    public ResponseEntity<Response> closeCalculation(@PathVariable Long id) throws URISyntaxException {
        Response res = new Response();

        try {
            RunCalculator runCalculator = runCalculatorRepository.findOne(id);
            if (!STATUS_CLOSED.equals(runCalculator.getStatus())) {
                runCalculator.setStatus(STATUS_CLOSED);
                RunCalculator savedEntity = runCalculatorRepository.save(runCalculator);
                res.setContent(savedEntity);
            } else {
                res.setMessage("Calculation already closed!");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
        } catch (EmptyResultDataAccessException ex) {
            res.setMessage("Data does not exist.");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/runCalculator/filter-options", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List> getFilterOptions(HttpServletRequest request) throws URISyntaxException, SQLException {
        return runCalculatorService.getFilterOptions();
    }

    @RequestMapping(value = "/runCalculator/snapshotDateOptions", method = RequestMethod.GET)
    @ResponseBody
    public List<Date> getSnapshotDateOptions() throws SQLException {
        return runCalculatorService.getSnapshotDateOptions();
    }

    private String getValidationExceptionMessages(ConstraintViolationException validationException) {
        validationException.printStackTrace();
        Set<String> messages = new HashSet();
        Set<ConstraintViolation<?>> stack = validationException.getConstraintViolations();
        StringBuilder errorMessages = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : stack) {
            messages.add(constraintViolation.getMessage());
        }
        for (String message : messages) {
            errorMessages.append(message).append(",");
        }
        errorMessages.replace(errorMessages.length() - 1, errorMessages.length(), "");
        return errorMessages.toString();
    }
}
