package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserEntity;
import ru.prevent.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userService;

    @GetMapping("/create")
    public ModelAndView getPageForNewUser() {
        ModelAndView model = new ModelAndView("admin/createPatient");
        model.addObject("patient", new UserEntity());
        return model;
    }

    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("patient") UserEntity user) {
        //TODO предварительно для этого пользователя сразу добавить все общедоступные опросы
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User by id=" + id + " deleted successfully", HttpStatus.OK);
    }

}
