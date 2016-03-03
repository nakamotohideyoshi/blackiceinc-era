package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CFG_OPS_PRODUCT_TYPE")
public class CfgOpsProductType {

    @Id
    @Column(name = "OPS_PRODUCT_TYPE")
    private String opsProductType;

    @Column(name = "OPS_PRODUCT_DESC")
    private String opsProductDesc;

    @Column(name = "OPS_BUS_INDICATOR")
    private String opsBusIndicator;

    public CfgOpsProductType() {
    }

    public CfgOpsProductType(String opsProductType, String opsProductDesc, String opsBusIndicator) {
        this.opsProductType = opsProductType;
        this.opsProductDesc = opsProductDesc;
        this.opsBusIndicator = opsBusIndicator;
    }

    public String getOpsProductType() {
        return opsProductType;
    }

    public void setOpsProductType(String opsProductType) {
        this.opsProductType = opsProductType;
    }

    public String getOpsProductDesc() {
        return opsProductDesc;
    }

    public void setOpsProductDesc(String opsProductDesc) {
        this.opsProductDesc = opsProductDesc;
    }

    public String getOpsBusIndicator() {
        return opsBusIndicator;
    }

    public void setOpsBusIndicator(String opsBusIndicator) {
        this.opsBusIndicator = opsBusIndicator;
    }
}
