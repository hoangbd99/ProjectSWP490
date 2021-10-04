package com.swp.ybwc.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TablerestaurantDTO {
    private Long tableId;
    private Long tableNumber;
    private Long maxPeople;
    private Long status;
    private String location;

}
