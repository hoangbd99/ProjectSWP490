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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Cacheable(true)
@Table(name = "BLACKLIST")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Blacklist implements java.io.Serializable {

    // @Caption("Customerid")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long customerId;

    // @Caption("CustomerId")
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Customer customerIdCus;

    //@Caption("Note")
    @Column(name = "NOTE", columnDefinition = "NVARCHAR2", length = 1000)
    private String note;

}

