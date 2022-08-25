package com.doconnect.doconnectservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long user_id);

}
