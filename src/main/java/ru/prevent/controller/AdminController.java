package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.service.UserService;

import java.util.List;

@Controller
@RequestMapping("admin")
@Api(tags = "Контроллер администратора")
public class AdminController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Загрузка начальной страницы АРМ администратора")
    @GetMapping("/")
    public ModelAndView loadIndex() {
        ModelAndView model = new ModelAndView("admin/index");
        List<UserEntity> users = userService.findAll();
        model.addObject("patients", users);
        return model;
    }
}
