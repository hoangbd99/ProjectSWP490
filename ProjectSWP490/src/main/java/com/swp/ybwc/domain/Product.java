package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Caption("{%name}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements java.io.Serializable {

    //   @Caption("Productid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long productid;

    //   @Caption("Category")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, columnDefinition = "NUMBER")
    private Category category;

    //   @Caption("Name")
    @Column(name = "NAME", columnDefinition = "NVARCHAR2", length = 40)
    private String name;

    //   @Caption("Price")
    @Column(name = "PRICE", columnDefinition = "FLOAT", precision = 126, scale = 0)
    private Double price;

    //   @Caption("Description")
    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2", length = 40)
    private String description;

    //   @Caption("Status")
    @Column(name = "STATUS", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long status;

    //   @Caption("Productcosthistories")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Productcosthistory> productcosthistories = new HashSet<Productcosthistory>(0);

    //   @Caption("Attributes")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTATTRIBUTE", joinColumns = {
            @JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false, columnDefinition = "NUMBER")}, inverseJoinColumns = {
            @JoinColumn(name = "ATTRIBUTE_ID", nullable = false, updatable = false, columnDefinition = "NUMBER")})
    private Set<Attribute> attributes = new HashSet<Attribute>(0);

    //   @Caption("Productstorages")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Productstorage> productstorages = new HashSet<Productstorage>(0);

}

