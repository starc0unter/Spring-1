package com.chentsov.hw4.controllers;

import com.chentsov.hw4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/admin/users"})
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

}
