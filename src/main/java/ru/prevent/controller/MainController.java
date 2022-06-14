package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.EmailSenderService;
import ru.prevent.service.UserService;

@Controller
@Api(tags = "Контроллер стартовой страницы")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailService;

    @ApiOperation(value = "Загрузка страницы выбранного пользователя")
    @PostMapping("/loadUser")
    public String loadUser(@ModelAttribute("chooseUser") UserNQuizModel m) {
        return "redirect:/lk/" + m.getUserId().toString();
    }
}
