package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CAP_ELEMENTS_FORMULA")
public class CfgCapElementsFormula {

    @Id
    @Column(name = "CAP_ELEMENTS")
    private String capElements;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FORMULA")
    private String formula;

    public String getCapElements() {
        return capElements;
    }

    public void setCapElements(String capElements) {
        this.capElements = capElements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
