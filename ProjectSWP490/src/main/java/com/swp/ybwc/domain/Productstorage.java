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

//@Caption("{%inputdate}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTSTORAGE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Productstorage implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRODUCTSTORAGE_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 22, scale = 0)
    private long productstorageId;

    @Temporal(TemporalType.DATE)
    @Column(name = "INPUT_DATE", unique = true, nullable = false, columnDefinition = "DATE", length = 7)
    private Date inputdate;

    //   @Caption("Product")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, columnDefinition = "NUMBER")
    private Product product;

    //   @Caption("Quantity")
    @Column(name = "QUANTITY", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long quantity;
}