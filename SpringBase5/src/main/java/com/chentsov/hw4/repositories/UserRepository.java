package com.chentsov.hw4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.chentsov.hw4.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String userName);
}
