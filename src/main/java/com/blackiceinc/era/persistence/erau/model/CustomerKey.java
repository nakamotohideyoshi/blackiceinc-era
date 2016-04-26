package com.blackiceinc.era.persistence.erau.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class CustomerKey implements Serializable {

    private static final long serialVersionUID = -6491807383944846069L;

    @Column(name="CUSTOMER_ID")
    private String customerId;

    @Column(name = "SNAPSHOT_DATE")
    private Date snapshotDate;

    public CustomerKey() {
        // default constructor
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CustomerKey that = (CustomerKey) o;

        return new EqualsBuilder()
                .append(customerId, that.customerId)
                .append(snapshotDate, that.snapshotDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customerId)
                .append(snapshotDate)
                .toHashCode();
    }
}
