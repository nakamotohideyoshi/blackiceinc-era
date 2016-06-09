package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CAP_ELEMENTS_MAPPING")
public class CfgCapElementsMapping extends CfgObject {

    @Id
    @Column(name = "CAP_ELEMENTS")
    private String capElements;

    @Column(name = "GL_CODE")
    private String glCode;

    @Column(name = "NOTE")
    private String note;

    public CfgCapElementsMapping() {
        // default constructor
    }

    public CfgCapElementsMapping(String capElements, String glCode, String note) {
        this.capElements = capElements;
        this.glCode = glCode;
        this.note = note;
    }

    public String getCapElements() {
        return capElements;
    }

    public void setCapElements(String capElements) {
        this.capElements = capElements;
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
