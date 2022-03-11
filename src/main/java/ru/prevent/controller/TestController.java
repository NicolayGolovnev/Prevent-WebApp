package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserService;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @GetMapping("/")
    public String loadTestIndex(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("quizzes", quizService.findAll());
        model.addAttribute("uqModel", new UserNQuizModel());
        return "/test/index";
    }
}
