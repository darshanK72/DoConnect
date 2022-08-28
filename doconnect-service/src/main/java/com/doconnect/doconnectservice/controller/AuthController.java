package com.doconnect.doconnectservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectservice.dto.LoginRequest;
import com.doconnect.doconnectservice.dto.LoginResponse;
import com.doconnect.doconnectservice.dto.UserDTO;
import com.doconnect.doconnectservice.services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/doconnect/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {
        UserDTO userDTO = this.userService.getUserByUsername(loginRequest.getUsername());
        if(passwordEncoder.matches(loginRequest.getPassword(), userDTO.getPassword()))
        {
            LoginResponse loginResponse = new LoginResponse();

            loginResponse.setStatus(true);
            loginResponse.setUser_id(userDTO.getUser_id());
            loginResponse.setUsername(userDTO.getUsername());
            loginResponse.setFirstName(userDTO.getFirstName());
            loginResponse.setLastName(userDTO.getLastName());
            loginResponse.setEmail(userDTO.getEmail());
            loginResponse.setPhone(userDTO.getPhone());
            loginResponse.setPassword(userDTO.getPassword());
            loginResponse.setRoles(userDTO.getRoles());

            return ResponseEntity.ok(loginResponse);
        }
        else{
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setStatus(false);
            return ResponseEntity.ok(loginResponse);
        }
        
    }
    
}
