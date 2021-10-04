package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderheaderoffDTO {
    private Long orderoffId;
    private java.sql.Date orderDate;
    private Long status;
    private Float totalDue;
    private String note;
    private String feedback;
    private Long tableNumber;

}
