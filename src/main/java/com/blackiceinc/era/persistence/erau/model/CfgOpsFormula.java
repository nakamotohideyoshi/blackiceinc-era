package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_OPS_FORMULA")
public class CfgOpsFormula  {

    @Id
    @Column(name = "BASIC_INDICATOR")
    private String basicIndicator;

    @Column(name = "FORMULA")
    private String formula;

    public CfgOpsFormula() {
        // default constructor
    }

    public CfgOpsFormula(String basicIndicator, String formula) {
        this.basicIndicator = basicIndicator;
        this.formula = formula;
    }

    public String getBasicIndicator() {
        return basicIndicator;
    }

    public void setBasicIndicator(String basicIndicator) {
        this.basicIndicator = basicIndicator;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
