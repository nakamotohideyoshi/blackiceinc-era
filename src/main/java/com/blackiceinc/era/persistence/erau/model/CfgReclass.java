package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.*;

@Entity
@Table(name = "CFG_RECLASS")
@NamedNativeQuery(name = "CfgReclass.insert",
        query = "INSERT INTO CFG_RECLASS (CHECK_NO, DESCRIPTION, ERA_ENTITY_TYPE_IN, ERA_PRODUCT_TYPE_IN, \"CHECK\", ERA_ENTITY_TYPE_OUT, ERA_ASSET_CLASS_OUT) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
public class CfgReclass extends CfgObject {

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

    @Column(name = "ERA_ASSET_CLASS_OUT")
    private String eraAssetClassOut;

    public CfgReclass(String checkNo, String description, String eraEntityTypeIn, String eraProductTypeIn, String check, String eraEntityTypeOut, String eraAssetClassOut) {
        this.checkNo = checkNo;
        this.description = description;
        this.eraEntityTypeIn = eraEntityTypeIn;
        this.eraProductTypeIn = eraProductTypeIn;
        this.check = check;
        this.eraEntityTypeOut = eraEntityTypeOut;
        this.eraAssetClassOut = eraAssetClassOut;
    }

    public CfgReclass() {
        // default constructor
    }

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

    public String getEraAssetClassOut() {
        return eraAssetClassOut;
    }

    public void setEraAssetClassOut(String eraAssetClassOut) {
        this.eraAssetClassOut = eraAssetClassOut;
    }
}
