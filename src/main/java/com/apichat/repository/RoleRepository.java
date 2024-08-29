package com.apichat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apichat.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
