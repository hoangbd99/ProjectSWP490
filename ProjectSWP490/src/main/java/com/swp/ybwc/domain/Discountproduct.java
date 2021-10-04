package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DISCOUNTPRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Discountproduct implements java.io.Serializable {

    //   @Caption("Productid")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "PRODUCT_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long productid;

    //   @Caption("ProductidPro")
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Product productidPro;

    //    @Caption("Voucher")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VOUCHER_CODE", nullable = false, columnDefinition = "NUMBER")
    private Voucher voucher;

}