package com.chentsov.hw4.services;

import com.chentsov.hw4.entities.SystemUser;
import com.chentsov.hw4.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    List<User> getAllUsers();
    boolean save(SystemUser systemUser);
}
