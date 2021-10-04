package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderdetailonlDTO {
    private Long orderonlId;
    private Long orderdetailonlId;
    private Long orderonlQty;
    private Long productId;
    private String voucherCode;
    private Float price;
    private Float pricedct;
}
