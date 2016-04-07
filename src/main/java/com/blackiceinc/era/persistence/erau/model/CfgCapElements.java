package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CAP_ELEMENTS")
public class CfgCapElements {

    @Id
    @Column(name = "CAP_ELEMENTS")
    private String capElements;

    @Column(name = "CAP_ELEMENTS_DESC")
    private String capElementsDesc;

    @Column(name = "TYPE")
    private String type;

    public CfgCapElements() {
        // default constructor
    }

    public CfgCapElements(String capElements, String capElementsDesc, String type) {
        this.capElements = capElements;
        this.capElementsDesc = capElementsDesc;
        this.type = type;
    }

    public String getCapElements() {
        return capElements;
    }

    public void setCapElements(String capElements) {
        this.capElements = capElements;
    }

    public String getCapElementsDesc() {
        return capElementsDesc;
    }

    public void setCapElementsDesc(String capElementsDesc) {
        this.capElementsDesc = capElementsDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
