package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    UserQuizzesService userQuizzesSerrvice;

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
        List<Question> entityQuestions = questionService.findQuestionsByQuizId(quiz.getId());

//        List<QuestionAnswersModel> questions = new ArrayList<>();
        QAModelCreation questions = new QAModelCreation();
        for (var question : entityQuestions) {
            List<Answer> answers = new ArrayList<>();
            question.getAnswers().forEach(collection -> answers.add(collection.getAnswer()));
            QuestionAnswersModel buf = QuestionAnswersModel.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .numQuestion(question.getNumQuestion())
                    .weight(question.getWeight())
                    .answers(answers)
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
    public void saveResults(@ModelAttribute("questions") QAModelCreation resultForm, Model model){
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
        userQuizzesSerrvice.save(newRecord);

        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);

        System.out.println();
    }
}
