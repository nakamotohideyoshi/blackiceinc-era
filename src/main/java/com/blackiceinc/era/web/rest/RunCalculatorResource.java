package com.blackiceinc.era.web.rest;

import com.blackiceinc.era.persistence.erau.model.MeasurementSensitivity;
import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import com.blackiceinc.era.persistence.erau.repository.MeasurementSensitivityRepository;
import com.blackiceinc.era.persistence.erau.repository.RunCalculatorRepository;
import com.blackiceinc.era.persistence.erau.specifications.RunCalculatorSpecificationsBuilder;
import com.blackiceinc.era.web.rest.model.DeleteResponse;
import com.blackiceinc.era.web.rest.model.FailedCRUDResponseObj;
import com.blackiceinc.era.web.rest.model.Response;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RunCalculatorResource {

    public static final String STATUS_CLOSED = "Closed";
    private final Logger log = LoggerFactory.getLogger(RunCalculatorResource.class);

    @Autowired
    private RunCalculatorRepository runCalculatorRepository;

    @Autowired
    private MeasurementSensitivityRepository measurementSensitivityRepository;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/runCalculator",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RunCalculator>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "length", required = false) Integer length,
                                                      @RequestParam(value = "snapshotDate", required = false) Date snapshotDate,
                                                      @RequestParam(value = "loadJobNbr", required = false) BigDecimal loadJobNbr,
                                                      @RequestParam(value = "scenarioId", required = false) String scenarioId) {

        /** Check to see if the queryParam contains "page" & "length" if not set default values **/
        int pageNumber = (page != null) ? page : 0;
        int pageSize = (length != null) ? length : 25;

        Page<RunCalculator> findAll = null;
        try {

            RunCalculatorSpecificationsBuilder builder = new RunCalculatorSpecificationsBuilder();
            if (snapshotDate!=null){
                builder.with("snapshotDate", ":", snapshotDate, "", "");
            }

            if (loadJobNbr!=null){
                builder.with("loadJobNbr", ":", loadJobNbr, "", "");
            }

            if (scenarioId!=null){
                builder.with("scenarioId", ":", scenarioId, "", "");
            }

            Specification<RunCalculator> specRunCalculator = builder.build();

            findAll = runCalculatorRepository.findAll(specRunCalculator, new PageRequest(pageNumber, pageSize));
        } catch (Exception ex) {
            log.error("DB Exception", ex);
        }

        return new ResponseEntity<>(findAll, HttpStatus.OK);
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

        try{
            RunCalculator savedEntity = runCalculatorRepository.save(runCalculator);
            res.setContent(savedEntity);
            res.setTotalElements(runCalculatorRepository.count());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(DataIntegrityViolationException ex){
            res.setMessage("Cannot Add Duplicate Entries : A Record With These Values Already Exists.");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        } catch(ConstraintViolationException ex){
            res.setMessage(getValidationExceptionMessages(ex));
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
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
        errorMessages.replace(errorMessages.length()-1, errorMessages.length(), "");
        return errorMessages.toString();
    }

    @RequestMapping(value = "/runCalculator",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteData(@RequestParam String idListStr) {
        DeleteResponse res = new DeleteResponse();
        String[] idList = idListStr.split("\\|");
        HttpStatus returnStatus = HttpStatus.OK;

        for (String id : idList) {
            try {
                runCalculatorRepository.delete(Long.parseLong(id));
                res.modifyDeleteSuccessResultMap(id, true);
            }catch (NumberFormatException ex) {
                res.addFailedDeleteRecord(new FailedCRUDResponseObj(id, "Invalid number formatting"));
                res.modifyDeleteSuccessResultMap(id, false);
                returnStatus = HttpStatus.NOT_FOUND;
            }catch (EmptyResultDataAccessException ex) {
                res.addFailedDeleteRecord(new FailedCRUDResponseObj(id, "Data with id=" + id + " does not exist."));
                res.modifyDeleteSuccessResultMap(id, false);
                returnStatus = HttpStatus.NOT_FOUND;
            }catch (DataIntegrityViolationException ex) {
                res.addFailedDeleteRecord(new FailedCRUDResponseObj(id, "Cannot Be Deleted Due To Foreign Key Constraint."));
                res.modifyDeleteSuccessResultMap(id, false);
                returnStatus = HttpStatus.CONFLICT;
            }
        }

        res.setTotalElements(runCalculatorRepository.count());
        return new ResponseEntity<Response>(res, returnStatus);
    }

    @RequestMapping(value = "/runCalculator/check", method = RequestMethod.GET)
    public  @ResponseBody
    HttpEntity<Map<String, Object>> checkIfExists(@RequestParam LinkedHashMap<String, String> allRequestParams) {
        Page<RunCalculator> pageable;
        int pageNumber = (allRequestParams.containsKey("page")) ? Integer.parseInt(allRequestParams.get("page")) : 0;
        int pageSize = (allRequestParams.containsKey("length")) ? Integer.parseInt(allRequestParams.get("length")) : 1;
        allRequestParams.remove("page");
        allRequestParams.remove("length");
        String hashKey = null;
        if (allRequestParams.containsKey("hashKey")) {
            hashKey = allRequestParams.get("hashKey");
            allRequestParams.remove("hashKey");
        }

        Date snapshotDate = (allRequestParams.containsKey("snapshotDate")) ? Date.valueOf(allRequestParams.get("snapshotDate")) : null;
        BigDecimal loadJobNbr = (allRequestParams.containsKey("loadJobNbr")) ? BigDecimal.valueOf(Long.valueOf(allRequestParams.get("loadJobNbr"))) : null;
        String scenarioId = (allRequestParams.containsKey("scenarioId")) ? allRequestParams.get("scenarioId") : null;

        RunCalculatorSpecificationsBuilder builder = new RunCalculatorSpecificationsBuilder();
        if (snapshotDate!=null){
            builder.with("snapshotDate", ":", snapshotDate, "", "");
        }

        if (loadJobNbr!=null){
            builder.with("loadJobNbr", ":", loadJobNbr, "", "");
        }

        if (scenarioId!=null){
            builder.with("scenarioId", ":", scenarioId, "", "");
        }

        Specification<RunCalculator> specRunCalculator = builder.build();

        pageable = runCalculatorRepository.findAll(specRunCalculator, new PageRequest(pageNumber, pageSize));

        Map<String, Object> msg = new HashMap<String, Object>();
        if (pageable.getNumberOfElements() == 0) {
            msg.put("exists", false);
        } else {
            msg.put("exists", true);
        }
        msg.put("content", pageable.getContent());
        msg.put("hashKey", hashKey);

        return new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "runCalculator/runCalculation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> runCalculation(@Valid @RequestBody RunCalculator runCalculator){
        Response res = new Response();

        // execute PL/SQL procedure

        try{
            runCalculatorRepository.runCalculatorStoredProcedure(runCalculator.getScenarioId(),
                    runCalculator.getLoadJobNbr().intValue(), runCalculator.getSnapshotDate());
        }catch(InvalidDataAccessResourceUsageException ex){
            log.error("Error executing PL/SQL procedure", ex);
            res.setMessage("Error executing PL/SQL procedure.");
            return new ResponseEntity<>(res, HttpStatus.EXPECTATION_FAILED);
        }

//        runCalculatorRepository.runCalculatorStoredProcedureTest(runCalculator.getScenarioId(),
//                runCalculator.getLoadJobNbr().intValue(), runCalculator.getSnapshotDate());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "runCalculator/closeCalculation/{id}", method = RequestMethod.POST)
    public ResponseEntity<Response> closeCalculation(@PathVariable Long id){
        Response res = new Response();

        try{
            RunCalculator runCalculator = runCalculatorRepository.findOne(id);
            if (!STATUS_CLOSED.equals(runCalculator.getStatus())){
                runCalculator.setStatus(STATUS_CLOSED);
                RunCalculator savedEntity = runCalculatorRepository.save(runCalculator);
                res.setContent(savedEntity);
            } else {
                res.setMessage("Calculation already closed!");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
        }catch (EmptyResultDataAccessException ex) {
            res.setMessage("Data does not exist.");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/runCalculator/filter-options", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getFilterOptions(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        List<Date> snapshotDateList = new ArrayList<>();
        List<BigDecimal> loadJobNbrList = new ArrayList<>();
        List<String> scenarioIdList = new ArrayList<>();

        try{

//            fillFilterFromMeasurementSensetivityDB(snapshotDateList, loadJobNbrList, scenarioIdList);
            fillFilterWithDummyData(snapshotDateList, loadJobNbrList, scenarioIdList);

        }catch (Exception ex){
            log.error("Error while pulling measurement sensitivity data from database", ex);
        }

        result.put("snapshotDate", snapshotDateList);
        result.put("loadJobNbr", loadJobNbrList);
        result.put("scenarioId", scenarioIdList);

        return result;
    }

    private void fillFilterWithDummyData(List<Date> snapshotDateList, List<BigDecimal> loadJobNbrList, List<String> scenarioIdList) {
        for (MeasurementSensitivity ms : generateDummyMeasurementData()) {
            if (!snapshotDateList.contains(ms.getSnapshotDate())) {
                snapshotDateList.add(ms.getSnapshotDate());
            }

            if (!loadJobNbrList.contains(ms.getLoadJobNbr())) {
                loadJobNbrList.add(ms.getLoadJobNbr());
            }

            if (!scenarioIdList.contains(ms.getScenarioId())) {
                scenarioIdList.add(ms.getScenarioId());
            }
        }
    }

    private void fillFilterFromMeasurementSensetivityDB(List<Date> snapshotDateList, List<BigDecimal> loadJobNbrList, List<String> scenarioIdList) throws SQLException {
        Connection conn = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.user"),
                env.getProperty("jdbc.pass"));
        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();

        long start = System.currentTimeMillis();
        log.info("Starting taking MEASUREMENT_SENSITIVITY data");
        ResultSet resultSet = stmt.executeQuery("select DISTINCT SNAPSHOT_DATE, LOAD_JOB_NBR, SCENARIO_ID from MEASUREMENT_SENSITIVITY");
        log.info("MEASUREMENT_SENSITIVITY data took {} ms", System.currentTimeMillis() - start);
        while (resultSet.next()) {
            Date snapshotDate = resultSet.getDate("SNAPSHOT_DATE");
            BigDecimal loadJobNbr = resultSet.getBigDecimal("LOAD_JOB_NBR");
            String scenarioId = resultSet.getString("SCENARIO_ID");

            if (!snapshotDateList.contains(snapshotDate)) {
                snapshotDateList.add(snapshotDate);
            }

            if (!loadJobNbrList.contains(loadJobNbr)) {
                loadJobNbrList.add(loadJobNbr);
            }

            if (!scenarioIdList.contains(scenarioId)) {
                scenarioIdList.add(scenarioId);
            }
        }
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

    private MeasurementSensitivity createMeasurementSensitivity(Date date, BigDecimal jobNbr, String scenarioId) {
        MeasurementSensitivity ms1 = new MeasurementSensitivity();
        ms1.setSnapshotDate(date);
        ms1.setLoadJobNbr(jobNbr);
        ms1.setScenarioId(scenarioId);

        return ms1;
    }

}
