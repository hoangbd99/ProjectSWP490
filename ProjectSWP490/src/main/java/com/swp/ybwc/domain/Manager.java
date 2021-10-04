package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@Caption("{%managerid}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MANAGER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Manager implements java.io.Serializable {

    //   @Caption("Managerid")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @Column(name = "MANAGER_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2", length = 20)
    private String managerid;

    //   @Caption("Role")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false, columnDefinition = "NUMBER")
    private Role role;

    //   @Caption("Username")
    @Column(name = "USERNAME", nullable = false, columnDefinition = "VARCHAR2", length = 50)
    private String username;

    //  @Caption("Password")
    @Column(name = "PASSWORD", nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long password;

}
