package com.doconnect.doconnectservice.services;

import java.util.List;

import javax.validation.Valid;

import com.doconnect.doconnectservice.dto.UserDTO;


/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 28-8-2022
 */
public interface UserService {
    
    public List<UserDTO> getAllUsers();
    public String registerUser(@Valid UserDTO userDTO);
    public UserDTO getUser(Long user_id);
    public String updateUser(@Valid UserDTO userDTO, @Valid Long user_id);
    public String deleteUser(Long user_id);
    public UserDTO getUserByUsername(String username);
}
