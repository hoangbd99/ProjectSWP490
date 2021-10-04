package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderdetailoffDTO {
    private Long orderoffId;
    private Long orderdetailoffId;
    private Long orderqty;
    private Long productId;
    private String voucherCode;
    private Float price;
    private Float pricedct;
}
