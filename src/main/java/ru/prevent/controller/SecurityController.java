package ru.prevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.prevent.entity.UserEntity;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Controller
@ApiIgnore
public class SecurityController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request) {
        return "login";
    }
}
