package com.doconnect.doconnectservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.User;

/*
 * @Author : Darshan Khairnar
 * Created Date : 25-8-2022
 * Modified Date : 29-8-2022
 * Description : User Repository Class
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long user_id);

    Optional<User> findByUsername(String username);

}
