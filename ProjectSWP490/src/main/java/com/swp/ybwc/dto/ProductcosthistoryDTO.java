package com.swp.ybwc.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductcosthistoryDTO {
    private Long productId;
    private java.sql.Date dateChange;
    private Float price;
    private Long productcosthistoryId;
}
