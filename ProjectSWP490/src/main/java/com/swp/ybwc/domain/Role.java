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

//@Caption("{%rolename}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements java.io.Serializable {

    //   @Caption("Roleid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ROLE_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long roleid;

    //   @Caption("Rolename")
    @Column(name = "ROLE_NAME", columnDefinition = "NVARCHAR2", length = 100)
    private String rolename;

    //   @Caption("Managers")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<Manager> managers = new HashSet<Manager>(0);

    //   @Caption("Customers")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<Customer> customers = new HashSet<Customer>(0);

}

