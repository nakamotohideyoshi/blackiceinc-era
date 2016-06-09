package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_COMPANY_LINKAGE")
public class CfgCompanyLinkage extends CfgObject {

    @Id
    @Column(name = "CHILD_CODE")
    private String childCode;

    @Column(name = "MOTHER_CODE")
    private String motherCode;

    @Column(name = "LINK_WEIGHT")
    private Double linkWeight;

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getMotherCode() {
        return motherCode;
    }

    public void setMotherCode(String motherCode) {
        this.motherCode = motherCode;
    }

    public Double getLinkWeight() {
        return linkWeight;
    }

    public void setLinkWeight(Double linkWeight) {
        this.linkWeight = linkWeight;
    }
}
