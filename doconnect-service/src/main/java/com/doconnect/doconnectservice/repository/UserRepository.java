package com.doconnect.doconnectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnect.doconnectservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
