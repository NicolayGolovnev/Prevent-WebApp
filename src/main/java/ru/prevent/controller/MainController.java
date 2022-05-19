package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.model.QuizModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @PostMapping("/loadUser")
    public String loadUser(@ModelAttribute("chooseUser") UserNQuizModel m, Model model) {
        String s = "redirect:/" + Long.toString(m.getUserId());
        return "redirect:/" + Long.toString(m.getUserId());
    }

    @GetMapping("/")
    public String loadIndex(Model model) {
        UserNQuizModel m = new UserNQuizModel();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("chooseUser", m);
        return "index";
    }
}
