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
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("JpaAttributeTypeInspection")
//@Caption("{%vouchercode}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VOUCHER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Voucher implements java.io.Serializable {

    //   @Caption("Vouchercode")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id

    @Column(name = "VOUCHER_CODE", unique = true, nullable = false, columnDefinition = "VARCHAR2", length = 50)
    private String vouchercode;

    //   @Caption("Description")
    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2", length = 500)
    private String description;

    //   @Caption("Discountpct")
    @Column(name = "DISCOUNT_PCT", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long discountpct;

    //   @Caption("Type")
    @Column(name = "TYPE", columnDefinition = "VARCHAR2", length = 50)
    private String type;

    //   @Caption("Startdate")
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", columnDefinition = "DATE", length = 7)
    private Date startdate;

    //   @Caption("Enddate")
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", columnDefinition = "DATE", length = 7)
    private Date enddate;

    //   @Caption("Minqty")
    @Column(name = "MIN_QTY", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long minqty;

    //   @Caption("Maxqty")
    @Column(name = "MAX_QTY", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long maxqty;

    //   @Caption("Status")
    @Column(name = "STATUS", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long status;

    //    @Caption("Discountproducts")
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voucher")
    private Set<Discountproduct> discountproducts = new HashSet<Discountproduct>(0);

}
