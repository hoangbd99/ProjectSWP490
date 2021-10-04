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

//@Caption("{%username}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements java.io.Serializable {

    // @Caption("Customerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long customerid;

    //  @Caption("Role")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false, columnDefinition = "NUMBER")
    private Role role;

    //  @Caption("Username")
    @Column(name = "USERNAME", columnDefinition = "VARCHAR2", length = 20)
    private String username;

    //  @Caption("Password")
    @Column(name = "PASSWORD", columnDefinition = "VARCHAR2", length = 20)
    private String password;

    //   @Caption("Name")
    @Column(name = "NAME", columnDefinition = "NVARCHAR2", length = 100)
    private String name;

    //  @Caption("Gender")
    @Column(name = "GENDER", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long gender;

    //  @Caption("Age")
    @Column(name = "AGE", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long age;

    //  @Caption("Email")
    @Column(name = "EMAIL", columnDefinition = "VARCHAR2", length = 50)
    private String email;

    //   @Caption("Phone")
    @Column(name = "PHONE", columnDefinition = "VARCHAR2", length = 10)
    private String phone;

    //  @Caption("Address1")
    @Column(name = "ADDRESS1", columnDefinition = "NVARCHAR2", length = 300)
    private String address1;

    //   @Caption("Address2")
    @Column(name = "ADDRESS2", columnDefinition = "VARCHAR2", length = 150)
    private String address2;

    //  @Caption("Orderheaderonls")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Orderheaderonl> orderheaderonls = new HashSet<Orderheaderonl>(0);

}
