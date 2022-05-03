package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/create")
    public ModelAndView getAll() {
        ModelAndView model = new ModelAndView("admin/createPatient");
        model.addObject("patient", new UserEntity());
        return model;
    }

    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("patient") UserEntity user) {
        //TODO предварительно для этого пользователя сразу добавить все общедоступные опросы
        userRepository.save(user);
        return "redirect:/ajax/getPatientList";
    }

}
