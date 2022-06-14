package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.prevent.entity.UserEntity;
import ru.prevent.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SecurityController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails user, Model model) {
        if (user != null) {
            if (user.getUsername().equals("admin"))
                return "redirect:/admin/";
            else
                return "redirect:/";
        }

        model.addAttribute("userForm", new UserEntity());
        return "login";
    }

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute("user") UserEntity user) {
        userService.register(user);
        return "redirect:/login";
    }
}
