package com.doconnect.doconnectservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectservice.dto.UserDTO;

import com.doconnect.doconnectservice.services.UserServiceImpl;

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 30-8-2022
 * Description : User Controller
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doconnect/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        return ResponseEntity.ok(this.userService.getUser(user_id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.registerUser(userDTO));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long user_id) {
        return ResponseEntity.ok(this.userService.updateUser(userDTO, user_id));

    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long user_id) {
        return ResponseEntity.ok(this.userService.deleteUser(user_id));
    }

}
