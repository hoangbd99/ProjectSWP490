package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Caption("{%name}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERHEADERONL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orderheaderonl implements java.io.Serializable {

    //   @Caption("Orderonlid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDERONL_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long orderonlid;

    //   @Caption("Customer")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COSTUMER_ID", columnDefinition = "NUMBER")
    private Customer customer;

    //   @Caption("Orderdate")

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE", columnDefinition = "DATE", length = 7)
    private Date orderdate;

    //   @Caption("Status")
    @Column(name = "STATUS", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long status;

    //   @Caption("Name")
    @Column(name = "NAME", columnDefinition = "NVARCHAR2", length = 100)
    private String name;

    //   @Caption("Phone")
    @Column(name = "PHONE", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long phone;

    //   @Caption("Shiptoaddress")
    @Column(name = "SHIPTO_ADDRESS", columnDefinition = "NVARCHAR2", length = 1000)
    private String shiptoaddress;

    //   @Caption("Totaldue")
    @Column(name = "TOTAL_DUE", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double totaldue;

    //  @Caption("Orderdetailonls")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderheaderonl")
    private Set<Orderdetailonl> orderdetailonls = new HashSet<Orderdetailonl>(0);

}
