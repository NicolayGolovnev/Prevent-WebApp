package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @PostMapping("/loadUser")
    public String loadUser(@ModelAttribute("chooseUser") UserNQuizModel m) {
        return "redirect:/" + m.getUserId().toString();
    }

    @GetMapping("/")
    public String loadIndex(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("chooseUser", new UserNQuizModel());
        return "index";
    }
}
