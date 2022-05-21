package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.entity.UserEntity;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserAndQuizService;
import ru.prevent.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @Autowired
    UserAndQuizService userAndQuizService;

    @GetMapping("/create")
    public ModelAndView getPageForNewUser() {
        ModelAndView model = new ModelAndView("admin/patient-create-page");
        model.addObject("patient", new UserEntity());
        return model;
    }

    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("patient") UserEntity user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User by id=" + id + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ModelAndView getPageForUser(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("admin/patient-page");
        model.addObject("patient", userService.findById(id));
        return model;
    }

    @PostMapping("/assignPool")
    public ResponseEntity<?> assignPoolById(@ModelAttribute("user") String user, @ModelAttribute("quiz") String quiz) {
        UserAndQuizzesEntity userAndQuizEntity = UserAndQuizzesEntity.builder()
                .status("назначен")
                .completeDate(LocalDate.now())
                .user(userService.findByFIO(user))
                .quiz(quizService.findByTitle(quiz))
                .build();
        userAndQuizService.save(userAndQuizEntity);
        return new ResponseEntity<>("Quiz (" + quiz + ") for user (" +
                user + ") created successfully", HttpStatus.OK);
    }

}
