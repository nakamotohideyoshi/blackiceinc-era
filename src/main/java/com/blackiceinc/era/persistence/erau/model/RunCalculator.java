package com.blackiceinc.era.persistence.erau.model;

import com.blackiceinc.era.persistence.erau.model.util.CustomDateDeserializer;
import com.blackiceinc.era.persistence.erau.model.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "RunCalculator.runCalculation", procedureName = "CALC_RUN", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCENARIO_ID", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_LOAD_JOB_NBR", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SNAPSHOT_DATE", type = Date.class),

        }),
        @NamedStoredProcedureQuery(name = "RunCalculator.runCalculationTest", procedureName = "CALC_RUN_TEST", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCENARIO_ID", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_LOAD_JOB_NBR", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SNAPSHOT_DATE", type = Date.class),

        })
})
@Table(name = "RUN_CALCULATOR")
public class RunCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "SNAPSHOT_DATE")
    private Date snapshotDate;

    @NotNull
    @Column(name = "LOAD_JOB_NBR")
    private Long loadJobNbr;

    @NotNull
    @Size(min=1)
    @Column(name = "SCENARIO_ID")
    private String scenarioId;

    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public Long getLoadJobNbr() {
        return loadJobNbr;
    }

    public void setLoadJobNbr(Long loadJobNbr) {
        this.loadJobNbr = loadJobNbr;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RunCalculator{" +
                "id=" + id +
                ", snapshotDate=" + snapshotDate +
                ", loadJobNbr=" + loadJobNbr +
                ", scenarioId='" + scenarioId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
