package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.prevent.entity.QuizEntity;
import ru.prevent.service.QuizService;

import java.util.List;

@RestController
public class RESTfulController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/rest/{id}")
    public List<QuizEntity> load(@PathVariable Long id) {
        List<QuizEntity> quizes = quizService.findAll();
        return quizes;
    }
}
