package com.doconnect.doconnectservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doconnect.doconnectservice.entity.Role;
import com.doconnect.doconnectservice.enums.ERoles;

@Repository
public interface RoleRespository extends JpaRepository<Role,Long>{

    Optional<Role> findByRole(ERoles roles);
}
