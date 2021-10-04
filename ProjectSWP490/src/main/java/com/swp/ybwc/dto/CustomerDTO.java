package com.swp.ybwc.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private Long customerId;
    private String username;
    private String password;
    private Long roleId;
    private String name;
    private Long gender;
    private Long age;
    private String email;
    private String phone;
    private String address1;
    private String address2;

}
