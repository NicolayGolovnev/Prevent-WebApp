package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.prevent.entity.QuizEntity;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.entity.UserEntity;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserAndQuizService;
import ru.prevent.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("user")
@Api(tags = "Контроллер пользователя")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserAndQuizService userAndQuizService;

    @ApiOperation(value = "Загрузка страницы регистрации пользователя с пустой формой")
    @GetMapping("/create")
    public ModelAndView getPageForNewUser() {
        ModelAndView model = new ModelAndView("admin/patient-create-page");
        model.addObject("patient", new UserEntity());
        return model;
    }

    @ApiOperation(value = "Операция создания/редактирования полученного пользователя")
    @PostMapping("/create")
    public String saveUser(@ModelAttribute("patient") UserEntity user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @ApiOperation(value = "Операция удаления пользователя по уникальному идентификатору")
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User by id=" + id + " deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Загрузка страницы с информацией пользователя по уникальному идентификатору")
    @GetMapping("/{id}")
    public ModelAndView getPageForUser(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("admin/patient-page");
        model.addObject("patient", userService.findById(id));
        return model;
    }

    @ApiOperation(value = "Операция назначения опроса")
    @PostMapping("/assignPool")
    public ResponseEntity<?> assignPoolById(@ModelAttribute("user") String user, @ModelAttribute("quiz") String quiz) {
        UserEntity userEntity = userService.findByFIO(user);
        QuizEntity quizEntity = quizService.findByTitle(quiz);

        if (userAndQuizService.findAllByUserIdAndQuizId(userEntity.getId(), quizEntity.getId()).isEmpty()) {
            UserAndQuizzesEntity userAndQuizEntity = UserAndQuizzesEntity.builder()
                    .status("назначен")
                    .completeDate(LocalDate.now())
                    .user(userEntity)
                    .quiz(quizEntity)
                    .build();
            userAndQuizService.save(userAndQuizEntity);

            return new ResponseEntity<>("Quiz (" + quiz + ") for user (" +
                    user + ") created successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Данный опрос уже назначен этому пациенту!",
                    HttpStatus.BAD_REQUEST);
        }


    }
}
