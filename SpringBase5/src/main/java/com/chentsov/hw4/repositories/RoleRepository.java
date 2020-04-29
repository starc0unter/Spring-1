package com.chentsov.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chentsov.hw4.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

