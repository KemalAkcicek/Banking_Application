package com.kemalakcicek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
