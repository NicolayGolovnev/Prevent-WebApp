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
import ru.prevent.repository.UserAndQuizeRepository;
import ru.prevent.repository.UserRepository;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserAndQuizService;
import ru.prevent.service.UserService;

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
        ModelAndView model = new ModelAndView("admin/patient-page");
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

    @PostMapping("/assignPool")
    public ResponseEntity<?> assignPoolById(@ModelAttribute("uqModel") UserNQuizModel uqModel) {
        UserAndQuizzesEntity userAndQuizEntity = UserAndQuizzesEntity.builder()
                .status("assign")
                .user(userService.findById(uqModel.getUserId()))
                .quiz(quizService.findById(uqModel.getQuizId()))
                .build();
        userAndQuizService.save(userAndQuizEntity);
        return new ResponseEntity<>("Quiz (id=" + uqModel.getQuizId() + ") for user (id=" +
                uqModel.getUserId() + ") created successfully", HttpStatus.OK);
    }

}
