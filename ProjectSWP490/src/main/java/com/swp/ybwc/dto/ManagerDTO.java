package com.swp.ybwc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagerDTO {
    private String managerId;
    private String username;
    private Long password;
    private Long roleId;
}
