package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.User;
import ru.prevent.service.UserService;

@Controller
@RequestMapping("ajax")
public class RestfulAdminController {
    @Autowired
    UserService userService;

    @GetMapping("/openForm")
    public ModelAndView loadModal() {
        User user = userService.findById(1L);
        ModelAndView model = new ModelAndView("includes/openForm");
        model.addObject("user", user);
        return model;
    }
}
