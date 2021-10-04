package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Cacheable(true)
@Table(name = "ATTRIBUTE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Attribute implements java.io.Serializable {
    // private Set<Product> products = new HashSet<Product>(0);
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ATTRIBUTE_ID", unique = true, nullable = false, columnDefinition = "NUMBER", length = 50)
    private long attributeid;

    @Column(name = "ATTRIBUTE_NAME", columnDefinition = "VARCHAR2", length = 100)
    private String attributename;

//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "PRODUCTATTRIBUTE", joinColumns = {
//            @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")}, inverseJoinColumns = {
//            @JoinColumn(name = "ATTRIBUTEID", referencedColumnName = "ATTRIBUTEID")})
//    @BatchSize(size = 20)
//    private Set<Product> products;
      @JsonIgnore
      @ManyToMany(fetch = FetchType.LAZY, mappedBy = "attributes")
      private Set<Product> products = new HashSet<Product>(0);

}

