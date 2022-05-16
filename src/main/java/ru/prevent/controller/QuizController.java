package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        ModelAndView model = new ModelAndView("admin/quiz-create-page");
        model.addObject("quiz", new QuizEntity());
        return model;
    }


    @GetMapping("/{id}")
    public ModelAndView getPageForQuiz(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("admin/quiz-page");
        model.addObject("quiz", quizService.findById(id));
        model.addObject("quizzes", quizService.findAll());
        return model;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateQuiz(@ModelAttribute("quiz") QuizEntity quiz) {
        System.out.println(quiz.toString());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuizById(@PathVariable Long id) {
        quizService.deleteById(id);
        return new ResponseEntity<>("Quiz by id=" + id + " deleted successfully", HttpStatus.OK);
    }
}
