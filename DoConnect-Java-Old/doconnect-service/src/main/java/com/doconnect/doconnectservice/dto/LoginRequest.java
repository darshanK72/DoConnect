package com.doconnect.doconnectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author : Diwakar Sriram Peddinti
 * Created Date : 25-8-2022
 * Modified Date : 29-8-2022
 * Description : Login Request DTO class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
    
}
