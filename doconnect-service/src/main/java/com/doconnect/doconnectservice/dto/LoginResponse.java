package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private boolean status;
    
    private Long user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
