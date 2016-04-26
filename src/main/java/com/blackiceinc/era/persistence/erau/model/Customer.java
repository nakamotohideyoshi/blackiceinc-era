package com.blackiceinc.era.persistence.erau.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @EmbeddedId
    private CustomerKey customerKey;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "INDUSTRY_CODE")
    private String industryCode;

    public Customer() {
        this.customerKey = new CustomerKey();
    }

    public String getCustomerId() {
        return customerKey.getCustomerId();
    }

    public void setCustomerId(String customerId) {
        this.customerKey.setCustomerId(customerId);
    }

    public Date getSnapshotDate() {
        return customerKey.getSnapshotDate();
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.customerKey.setSnapshotDate(snapshotDate);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
}
