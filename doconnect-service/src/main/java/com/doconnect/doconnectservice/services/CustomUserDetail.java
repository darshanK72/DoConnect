package com.doconnect.doconnectservice.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.doconnect.doconnectservice.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class CustomUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetail(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetail build(User user)
    {
        List<GrantedAuthority> authorities  = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

            return new CustomUserDetail(user.getUser_id(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        CustomUserDetail user = (CustomUserDetail)o;
        return Objects.equals(id,user.id);
    }
    
}
