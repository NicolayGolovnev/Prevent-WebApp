package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserService;

@Controller
@RequestMapping("ajax")
public class RestfulAdminController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @GetMapping("/getPatientList")
    public ModelAndView getPatientList() {
        ModelAndView model = new ModelAndView("admin/includes/patients");
        model.addObject("patients", userService.findAll());
        return model;
    }

    @GetMapping("/getQuizList")
    public ModelAndView getQuizList() {
        ModelAndView model = new ModelAndView("admin/includes/quizes");
        model.addObject("quizes", quizService.findAll());
        return model;
    }

    @GetMapping("/getAssignPool")
    public ModelAndView getAssignPool() {
        ModelAndView model = new ModelAndView("admin/includes/assignPool");
        model.addObject("patients", userService.findAll());
        model.addObject("quizes", quizService.findAll());
        return model;
    }
}
