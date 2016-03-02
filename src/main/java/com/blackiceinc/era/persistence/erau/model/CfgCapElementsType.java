package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_CAP_ELEMENTS_TYPE")
public class CfgCapElementsType {

    @Id
    @Column(name = "CAP_ELEMENT_TYPE")
    private String capElementType;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getCapElementType() {
        return capElementType;
    }

    public void setCapElementType(String capElementType) {
        this.capElementType = capElementType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
