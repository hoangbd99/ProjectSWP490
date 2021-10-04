package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Caption("{%note}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERTABLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ordertable implements java.io.Serializable {

    //   @Caption("Id")
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "tableid", column = @Column(name = "TABLE_ID", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)),
            @AttributeOverride(name = "orderoffid", column = @Column(name = "ORDEROFF_ID", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0))})
    private OrdertableId id;

    //   @Caption("Orderheaderoff")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDEROFF_ID", nullable = false, insertable = false, updatable = false, columnDefinition = "NUMBER")
    private Orderheaderoff orderheaderoff;

    //   @Caption("Tablerestaurant")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TABLE_ID", nullable = false, insertable = false, updatable = false, columnDefinition = "NUMBER")
    private Tablerestaurant tablerestaurant;

    //   @Caption("Note")
    @Column(name = "NOTE", columnDefinition = "NVARCHAR2", length = 1000)
    private String note;

}

