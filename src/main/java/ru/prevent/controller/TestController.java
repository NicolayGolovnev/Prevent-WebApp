package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prevent.entity.*;
import ru.prevent.model.QAModelCreation;
import ru.prevent.model.QuestionAnswersModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.*;

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

    @Autowired
    UserAnswerService userAnswerService;

    @GetMapping("/{userId}")
    public String loadTestIndex(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));

        //TODO собирать не все тесты, а тесты доступные пользователю
        model.addAttribute("quizzes", quizService.findAll());

        UserNQuizModel userNQuizModel = new UserNQuizModel();
        userNQuizModel.setUserId(userId);

        model.addAttribute("uqModel", userNQuizModel);
        return "/test/index";
    }

    @GetMapping("/loadQuizByUser")
    public String loadQuiz(@RequestParam("userId") Long uid, @RequestParam("quizId") Long qid, Model model) {
        UserEntity user = userService.findById(uid);
        QuizEntity quiz = quizService.findById(qid);

        //TODO проверка имеет ли опросник опросников детей

        List<QuestionEntity> entityQuestionEntities = questionService.findQuestionsByQuizId(quiz.getId());

        QAModelCreation questions = new QAModelCreation();
        for (var question : entityQuestionEntities) {
            QuestionAnswersModel buf = QuestionAnswersModel.builder()
                    .id(question.getId())
                    .question(question)
                    .answers(question.getAnswers())
                    .userAnswer(new AnswerEntity())
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
        UserEntity user = userService.findById(resultForm.getUserId());
        QuizEntity quiz = quizService.findById(resultForm.getQuizId());

        int resultOfTest = 0;
        for (QuestionAnswersModel question: answeredQuestions) {
            resultOfTest += question.getUserAnswer().getWeight();
        }

        UserAndQuizzesEntity newUserQuiz = UserAndQuizzesEntity.builder()
                .status("true")
                .completeDate(LocalDate.now())
                .result(Integer.toString(resultOfTest))
                .user(user)
                .quiz(quiz)
                .build();
        userQuizzesService.save(newUserQuiz);

        QuestionEntity questionField;
        for (QuestionAnswersModel question: answeredQuestions) {
            questionField = questionService.findById(question.getId());
            UserAndAnswersEntity newUserAnswer = UserAndAnswersEntity.builder()
                    .contentAnswer("")
                    .userQuizzes(newUserQuiz)
                    .answer(question.getUserAnswer())
                    .question(questionField)
                    .user(user)
                    .build();
            userAnswerService.save(newUserAnswer);
        }

        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);

        return "redirect:/showResult/" + newUserQuiz.getId();
    }

    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model){
        model.addAttribute("result", userQuizzesService.findById(id));
        return "test/showResult";
    }
}
