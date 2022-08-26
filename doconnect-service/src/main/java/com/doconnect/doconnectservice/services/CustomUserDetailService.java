package com.doconnect.doconnectservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return CustomUserDetail.build(user);
    }
    
}
