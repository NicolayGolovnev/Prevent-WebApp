package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.QuizEntity;
import ru.prevent.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("/create")
    public ModelAndView getPageForNewQuiz() {
        ModelAndView model = new ModelAndView("admin/assignPool");
        model.addObject("quiz", new QuizEntity());
        return model;
    }
}
