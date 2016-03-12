package com.blackiceinc.era.persistence.erau.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the measurement_sensitivity database table.
 * 
 */
@Entity
@Table(name="MEASUREMENT_SENSITIVITY")
public class MeasurementSensitivity {
	
	@Id
	@Column(name="MEASUREMENT_NBR")
	private String measurementNbr;

	@Column(name="SNAPSHOT_DATE")
	private Date snapshotDate;

	@Column(name="LOAD_JOB_NBR")
	private Long loadJobNbr;

	@Column(name="SCENARIO_ID")
	private String scenarioId;

	public MeasurementSensitivity() {
	}

	public String getMeasurementNbr() {
		return this.measurementNbr;
	}

	public void setMeasurementNbr(String measurementNbr) {
		this.measurementNbr = measurementNbr;
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
}