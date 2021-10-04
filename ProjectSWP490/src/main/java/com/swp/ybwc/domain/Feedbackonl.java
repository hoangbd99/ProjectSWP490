package com.swp.ybwc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@Caption("{%feedbackcontent}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FEEDBACKONL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feedbackonl implements java.io.Serializable {

    //   @Caption("Orderonlid")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @Column(name = "ORDERONL_ID", unique = true, nullable = false, columnDefinition = "NUMBER", precision = 10, scale = 0)
    private long orderonlid;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Orderheaderonl orderonlidFeed;

    //   @Caption("Grade")
    @Column(name = "GRADE", columnDefinition = "NUMBER", precision = 10, scale = 0)
    private Long grade;

    //  @Caption("Feedbackcontent")
    @Column(name = "FEEDBACK_CONTENT", columnDefinition = "NVARCHAR2", length = 1000)
    private String feedbackcontent;

}

