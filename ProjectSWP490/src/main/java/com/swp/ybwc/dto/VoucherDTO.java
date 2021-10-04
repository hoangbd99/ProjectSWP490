package com.swp.ybwc.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoucherDTO {
    private String voucherCode;
    private String description;
    private Long discountPct;
    private String type;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private Long minQty;
    private Long maxQty;
    private Long status;
}
