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
@Table(name = "ERA_RUN_CALCULATOR")
public class RunCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="era_run_calculator_seq_gen")
    @SequenceGenerator(name="era_run_calculator_seq_gen", sequenceName="ERA_RUN_CALCULATOR_SEQ")
    private Long id;

    @NotNull
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
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
