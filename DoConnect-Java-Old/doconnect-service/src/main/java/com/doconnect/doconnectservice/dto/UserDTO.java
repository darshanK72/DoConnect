package com.doconnect.doconnectservice.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 26-8-2022
 * Description : User Dto class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    private Long user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> roles;
}
