package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.prevent.service.UserService;

@Controller
public class UserController {

    public Long ID_USER = 1L;
    @Autowired
    UserService userService;

    @GetMapping("/userPage/{id}")
    public String showUserPage(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "userPage";
    }
}