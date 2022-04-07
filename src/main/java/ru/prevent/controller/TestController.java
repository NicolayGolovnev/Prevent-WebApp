package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prevent.entity.*;
import ru.prevent.model.QAModelCreation;
import ru.prevent.model.QuestionAnswersModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.QuestionService;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserQuizzesService;
import ru.prevent.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserQuizzesService userQuizzesService;

    @GetMapping("/")
    public String loadTestIndex(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("quizzes", quizService.findAll());
        model.addAttribute("uqModel", new UserNQuizModel());
        return "/test/index";
    }

    @GetMapping("/loadQuizByUser")
    public String loadQuiz(@RequestParam("userId") Long uid, @RequestParam("quizId") Long qid, Model model) {
        User user = userService.findById(uid);
        Quiz quiz = quizService.findById(qid);

        //TODO проверка имеет ли опросник опросников детей

        List<Question> entityQuestions = questionService.findQuestionsByQuizId(quiz.getId());

        QAModelCreation questions = new QAModelCreation();
        for (var question : entityQuestions) {
            QuestionAnswersModel buf = QuestionAnswersModel.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .numQuestion(question.getNumQuestion())
                    .weight(question.getWeight())
                    .answers(question.getAnswers())
                    .userAnswer(new Answer())
                    .build();
            questions.add(buf);
        }

        model.addAttribute("questions", questions);
        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        return "/test/quiz";
    }

    @PostMapping("/saveResults")
    public String saveResults(@ModelAttribute("questions") QAModelCreation resultForm, Model model){
        List<QuestionAnswersModel> answeredQuestions = resultForm.getQuestions();
        User user = userService.findById(resultForm.getUserId());
        Quiz quiz = quizService.findById(resultForm.getQuizId());
        int resultOfTest = 0;
        for (QuestionAnswersModel question: answeredQuestions) {
            resultOfTest += question.getUserAnswer().getWeight();
        }
        UserQuizzes newRecord = UserQuizzes.builder()
                .status("true")
                .completeDate(LocalDate.now())
                .result(Integer.toString(resultOfTest))
                .user(user)
                .quiz(quiz)
                .build();
        userQuizzesService.save(newRecord);

        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);

        return "redirect:/showResult/" + newRecord.getId();
    }

    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model){
        model.addAttribute("result", userQuizzesService.findById(id));
        return "test/showResult";
    }
}
