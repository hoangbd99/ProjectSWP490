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

//@Caption("{%categoryname}")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Cacheable(true)
@Table(name = "CATEGORY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category implements java.io.Serializable {


    //   @Caption("Categoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CATEGORY_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long categoryId;

    //  @Caption("Categoryname")
    @Column(name = "CATEGORY_NAME", columnDefinition = "VARCHAR2", length = 50)
    private String categoryName;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<Product>(0);


    @Override
    public String toString() {

        return "Category [id =" + categoryId + "name =" + categoryName + "]";
    }
}

