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
    private QuizService quizService;

    @GetMapping("/create")
    public ModelAndView getPageForNewQuiz() {
        ModelAndView model = new ModelAndView("admin/quiz-create-page");
        model.addObject("quiz", new QuizEntity());
        return model;
    }

    @PostMapping("/create")
    public String createQuiz(@ModelAttribute("quiz") QuizEntity quiz) {
        quizService.save(quiz);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}")
    public ModelAndView getPageForQuiz(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("admin/quiz-page");
        model.addObject("quiz", quizService.findById(id));
        model.addObject("quizzes", quizService.findAll());
        return model;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuizById(@PathVariable Long id) {
        quizService.deleteById(id);
        return new ResponseEntity<>("Quiz[id=" + id + "] deleted successfully", HttpStatus.OK);
    }
}
