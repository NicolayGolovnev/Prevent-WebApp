package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserService;

@Controller
@RequestMapping("ajax")
@Api(tags = "Контроллер подгрузки страниц администратора")
public class AdminAjaxController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @ApiOperation(value = "Загрузка страницы со списком всех существующих пользователей")
    @GetMapping("/getPatientList")
    public ModelAndView getPatientList() {
        ModelAndView model = new ModelAndView("includes/patients");
        model.addObject("patients", userService.findAll());
        return model;
    }

    @ApiOperation(value = "Загрузка страницы со списком всех существующих опросов")
    @GetMapping("/getQuizList")
    public ModelAndView getQuizList() {
        ModelAndView model = new ModelAndView("includes/quizes");
        model.addObject("quizes", quizService.findAll());
        return model;
    }

    @ApiOperation(value = "Загрузка страницы Назначение опроса")
    @GetMapping("/getAssignPool")
    public ModelAndView getAssignPool() {
        ModelAndView model = new ModelAndView("includes/assignment");
        model.addObject("uqModel", new UserNQuizModel());
        model.addObject("patients", userService.findAll());
        model.addObject("quizes", quizService.findAllClosed());
        return model;
    }

    @ApiOperation(value = "Загрузка страницы модального окна для удаления пользователя")
    @GetMapping("/getPatientDeleteModal")
    public String getPatientDeleteModal() {
        return "includes/patient-delete-modal";
    }

    @ApiOperation(value = "Загрузка страницы модального окна для удаления опроса")
    @GetMapping("/getQuizDeleteModal")
    public String getQuizDeleteModal() {
        return "includes/quiz-delete-modal";
    }

    @ApiOperation(value = "Загрузка страницы со списком всех опросов для выбора дочернего опроса")
    @GetMapping("/getListQuizzesForSelect")
    public ModelAndView getListQuizzesForSelect() {
        ModelAndView model = new ModelAndView("includes/list-quizzes-for-childrens");
        model.addObject("quizzes", quizService.findAll());
        return model;
    }
}
