package com.swp.ybwc.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductstorageDTO {
    private Long productId;
    private Long quantity;
    private java.sql.Date inputDate;
    private Long productstorageId;

}
