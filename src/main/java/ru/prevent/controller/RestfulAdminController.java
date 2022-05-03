package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.service.UserService;

@Controller
@RequestMapping("ajax")
public class RestfulAdminController {
    @Autowired
    UserService userService;

    @GetMapping("/openForm")
    public ModelAndView loadModal() {
        UserEntity userEntity = userService.findById(1L);
        ModelAndView model = new ModelAndView("admin/includes/openForm");
        model.addObject("user", userEntity);
        return model;
    }

    @GetMapping("/getPatientList")
    public ModelAndView getPatientList() {
        ModelAndView model = new ModelAndView("admin/includes/patients");
        model.addObject("patients", userService.findAll());
        return model;
    }

    @GetMapping("/search-plugin")
    public String getSearchPluginPage() {
        return "/admin/includes/search-plugin";
    }
}
