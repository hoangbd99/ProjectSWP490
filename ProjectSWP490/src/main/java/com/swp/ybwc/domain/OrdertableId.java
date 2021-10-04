package com.swp.ybwc.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrdertableId implements java.io.Serializable {

    private long tableid;
    private long orderoffid;

    public OrdertableId() {
    }

    @Column(name = "TABLE_ID", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    public long getTableid() {
        return this.tableid;
    }

    public void setTableid(long tableid) {
        this.tableid = tableid;
    }

    @Column(name = "ORDEROFF_ID", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    public long getOrderoffid() {
        return this.orderoffid;
    }

    public void setOrderoffid(long orderoffid) {
        this.orderoffid = orderoffid;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof OrdertableId))
            return false;
        OrdertableId castOther = (OrdertableId) other;

        return (this.getTableid() == castOther.getTableid()) && (this.getOrderoffid() == castOther.getOrderoffid());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (int) this.getTableid();
        result = 37 * result + (int) this.getOrderoffid();
        return result;
    }

}

