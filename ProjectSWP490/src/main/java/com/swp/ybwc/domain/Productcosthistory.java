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

//@Caption("{%datechange}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTCOSTHISTORY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Productcosthistory implements java.io.Serializable {

    //   @Caption("Datechange")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRODUCTCOSTHISTORY_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 22, scale = 0)
    private long productcosthistoryId;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CHANGE", unique = true, nullable = false, columnDefinition = "DATE", length = 7)
    private Date datechange;

    //   @Caption("Product")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, columnDefinition = "NUMBER")
    private Product product;

    //   @Caption("Price")
    @Column(name = "PRICE", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double price;

}

