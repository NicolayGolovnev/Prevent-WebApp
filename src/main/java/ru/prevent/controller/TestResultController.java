package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import ru.prevent.service.UserAndQuizService;

@Controller
public class TestResultController {

    @Autowired
    UserAndQuizService userAndQuizService;

    @GetMapping("/loadQuizByUser")
    public String loadService(@ModelAttribute("userId") Long idUser, @ModelAttribute("quizId") Long idQuiz, Model model) {
        model.addAttribute("quizResult", userAndQuizService.findQuizResult(idUser, idQuiz));
        return "showResult";
    }
}
