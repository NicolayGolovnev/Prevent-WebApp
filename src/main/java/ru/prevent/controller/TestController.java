package ru.prevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.prevent.entity.Answer;
import ru.prevent.entity.Question;
import ru.prevent.entity.Quiz;
import ru.prevent.entity.User;
import ru.prevent.model.QuestionAnswersModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.repository.AnswerRepository;
import ru.prevent.service.QuestionService;
import ru.prevent.service.QuizService;
import ru.prevent.service.UserService;

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

        List<QuestionAnswersModel> questions = new ArrayList<>();
        for (var question : entityQuestions) {
            List<Answer> answers = new ArrayList<>();
            question.getAnswers().forEach(collection -> answers.add(collection.getAnswer()));
            QuestionAnswersModel buf = QuestionAnswersModel.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .numQuestion(question.getNumQuestion())
                    .weight(question.getWeight())
                    .answers(answers)
                    .build();
            questions.add(buf);
        }

        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        return "/test/quiz";
    }
}
