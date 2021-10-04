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

//@Caption("{%location}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TABLERESTAURANT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tablerestaurant implements java.io.Serializable {

    //   @Caption("Tableid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TABLE_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long tableid;

    //   @Caption("Tablenumber")
    @Column(name = "TABLE_NUMBER", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long tablenumber;

    //   @Caption("Maxpeople")
    @Column(name = "MAX_PEOPLE", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long maxpeople;

    //   @Caption("Status")
    @Column(name = "STATUS", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long status;

    //   @Caption("Location")
    @Column(name = "LOCATION", columnDefinition = "NVARCHAR2", length = 1000)
    private String location;

    //   @Caption("Ordertables")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tablerestaurant")
    private Set<Ordertable> ordertables = new HashSet<Ordertable>(0);

}
