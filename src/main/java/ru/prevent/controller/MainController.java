package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation(value = "Загрузка начальной страницы сервиса")
    @GetMapping("/")
    public String loadIndex(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("chooseUser", new UserNQuizModel());
        return "index";
    }

    @ApiOperation(value = "Тестовый запрос на отправку сообщения по e-mail")
    @GetMapping("/sendEmail")
    public String sendEmail() {
        String to = "kolya.golovnev@mail.ru";
        String header = "Test send email by Spring";
        String message = "This is simple test for sending on email!\n\n" +
                "Welcome by Spring!";
        emailService.sendSimpleEmail(to, header, message);
        return "redirect:/";
    }
}
