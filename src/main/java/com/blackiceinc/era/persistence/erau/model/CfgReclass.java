package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_RECLASS")
public class CfgReclass {

    @Id
    @Column(name = "CHECK_NO")
    private String checkNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ERA_ENTITY_TYPE_IN")
    private String eraEntityTypeIn;

    @Column(name = "ERA_PRODUCT_TYPE_IN")
    private String eraProductTypeIn;

    @Column(name = "\"CHECK\"")
    private String check;

    @Column(name = "ERA_ENTITY_TYPE_OUT")
    private String eraEntityTypeOut;

    @Column(name = "ERA_PRODUCT_TYPE_OUT")
    private String eraProductTypeOut;

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEraEntityTypeIn() {
        return eraEntityTypeIn;
    }

    public void setEraEntityTypeIn(String eraEntityTypeIn) {
        this.eraEntityTypeIn = eraEntityTypeIn;
    }

    public String getEraProductTypeIn() {
        return eraProductTypeIn;
    }

    public void setEraProductTypeIn(String eraProductTypeIn) {
        this.eraProductTypeIn = eraProductTypeIn;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getEraEntityTypeOut() {
        return eraEntityTypeOut;
    }

    public void setEraEntityTypeOut(String eraEntityTypeOut) {
        this.eraEntityTypeOut = eraEntityTypeOut;
    }

    public String getEraProductTypeOut() {
        return eraProductTypeOut;
    }

    public void setEraProductTypeOut(String eraProductTypeOut) {
        this.eraProductTypeOut = eraProductTypeOut;
    }
}
