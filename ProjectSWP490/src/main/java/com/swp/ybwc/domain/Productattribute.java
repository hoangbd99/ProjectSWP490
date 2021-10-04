package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTATTRIBUTE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Productattribute implements Serializable {
    @Id
    @Column(name = "PRODUCT_ID")
    private Long productid;

    @Id
    @Column(name = "ATTRIBUTE_ID")
    private Long attributeid;

}
