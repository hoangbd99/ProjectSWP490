package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Caption("{%note}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERHEADEROFF")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orderheaderoff implements java.io.Serializable {

    //   @Caption("Orderoffid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDEROFF_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long orderoffid;

    //   @Caption("Orderdate")
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE", columnDefinition = "DATE", length = 7)
    private Date orderdate;

    //   @Caption("Status")
    @Column(name = "STATUS", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long status;

    //   @Caption("Totaldue")
    @Column(name = "TOTAL_DUE", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double totaldue;

    //   @Caption("Note")
    @Column(name = "NOTE", columnDefinition = "NVARCHAR2", length = 1000)
    private String note;

    //   @Caption("Feedback")
    @Column(name = "FEEDBACK", columnDefinition = "VARCHAR2", length = 500)
    private String feedback;

    //   @Caption("Tablenumber")
    @NotNull
    @Column(name = "TABLE_ID", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long tablenumber;

    //  @Caption("Orderdetailoffs")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderheaderoff")
    private Set<Orderdetailoff> orderdetailoffs = new HashSet<Orderdetailoff>(0);

    // @Caption("Ordertables")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderheaderoff")
    private Set<Ordertable> ordertables = new HashSet<Ordertable>(0);

}
