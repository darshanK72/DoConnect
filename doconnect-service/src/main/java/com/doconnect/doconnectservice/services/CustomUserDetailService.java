package com.doconnect.doconnectservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectservice.entity.User;
import com.doconnect.doconnectservice.repository.UserRepository;

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 26-8-2022
 * Description : User Detail Service class implementing UserDetailService interface
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /*
     * @Author : Darshan Khairnar
     * Created Date : 25-8-2022
     * Modified Date : 26-8-2022gg
     * Description : getting user by username
     * Params : username(string)
     * Return Type : UserDetail
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return CustomUserDetail.build(user);
    }

}
