package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@Caption("{%vouchercode}")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERDETAILONL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orderdetailonl implements java.io.Serializable {

    //   @Caption("Productid")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "PRODUCT_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long productid;

    //   @Caption("productidDiscount")
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Discountproduct productidDiscount;


    //   @Caption("Orderheaderonl")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDERONL_ID", nullable = false, columnDefinition = "NUMBER")
    private Orderheaderonl orderheaderonl;


    //   @Caption("Orderonldetailid")

    @Column(name = "ORDERDETAILONL_ID", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long orderdetailonlId;

    //   @Caption("Orderonlqty")

    @Column(name = "ORDERONL_QTY", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long orderonlqty;

    //   @Caption("Vouchercode")
    @Column(name = "VOUCHER_CODE", columnDefinition = "VARCHAR2", length = 50)
    private String vouchercode;

    //   @Caption("Price")
    @Column(name = "PRICE", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double price;

    //  @Caption("Pricedct")
    @Column(name = "PRICEDCT", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double pricedct;

}
