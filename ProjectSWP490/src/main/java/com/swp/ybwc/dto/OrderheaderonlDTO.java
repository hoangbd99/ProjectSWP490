package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderheaderonlDTO {
    private Long orderonlId;
    private java.sql.Date orderDate;
    private Long status;
    private String name;
    private Long phone;
    private Long costumerId;
    private String shiptoAddress;
    private Float totalDue;

}
