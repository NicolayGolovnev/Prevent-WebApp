package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prevent.entity.*;
import ru.prevent.model.QAModelCreation;
import ru.prevent.model.QuestionAnswersModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.QuestionService;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserQuizzesService;
import ru.prevent.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserQuizzesService userQuizzesService;

    @GetMapping("/")
    public String loadIndex() {
        return "admin/index";
    }
}
