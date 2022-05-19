package ru.prevent.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prevent.entity.*;
import ru.prevent.model.*;
import ru.prevent.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserAnswerService userAnswerService;

    @Autowired
    UserAndQuizService userAndQuizService;

    @Autowired
    QuizAndQuizService quizAndQuizService;

    @Autowired
    KeyQuizService keyQuizService;

    @Autowired
    HistoryResultService historyResultService;

    @GetMapping("/{userId}")
    public String loadTestIndex(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));

        List<UserAndQuizzesEntity> openQuizzes = userAndQuizService.findAllOpenQuizzesByUserId(userId);
        List<QuizEntity> quizzes = new ArrayList<>();
        for (UserAndQuizzesEntity test: openQuizzes) {
            quizzes.add(quizService.findById(test.getQuiz().getId()));
        }
        model.addAttribute("quizzes", quizzes);

        UserNQuizModel userNQuizModel = new UserNQuizModel();
        userNQuizModel.setUserId(userId);

        model.addAttribute("uqModel", userNQuizModel);
        model.addAttribute("completedQuizzes", userAndQuizService.findCompletedQuizzesByUserId(userId));
        return "userPage1";
    }

    @GetMapping("/loadQuizByUser")
    public String loadQuiz(@RequestParam("userId") Long userId, @RequestParam("quizId") Long quizId, Model model) {
        UserEntity user = userService.findById(userId);
        QuizEntity quiz = quizService.findById(quizId);

        List<QuizAndQuizEntity> childQuizzes = quizAndQuizService.findAllChildTests(quizId);
        QuizModel quizModel = new QuizModel();

        for (QuizAndQuizEntity childQuiz: childQuizzes) {
            ChildQuizModel childQuizModel = new ChildQuizModel();
            List<QuestionEntity> entityQuestions = questionService.findQuestionsByQuizId(childQuiz.getChildQuiz().getId());
            for (var question : entityQuestions) {
                QuestionAnswersModel buf = QuestionAnswersModel.builder()
                        .id(question.getId())
                        .question(question)
                        .answers(question.getAnswers())
                        .userAnswer(new AnswerEntity())
                        .build();
                childQuizModel.add(buf);
            }

            childQuizModel.setTittle(childQuiz.getChildQuiz().getTitle());
            childQuizModel.setId(childQuiz.getChildQuiz().getId());
            quizModel.add(childQuizModel);
        }

        int countQuiz = childQuizzes.size();
        int[] countQuestionInBlocks = new int[countQuiz];
        for (int i = 0; i < countQuiz; i++)
            countQuestionInBlocks[i] = quizModel.getChildQuizzes().get(i).getQuestions().size();
        model.addAttribute("countBlocks", countQuiz);
        model.addAttribute("countQuestionsInBlocks", countQuestionInBlocks);
        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        model.addAttribute("test", quizModel);

        return "/test/quiz";
    }

    @GetMapping("/showCompleteTest")
    public String showCompleteTest(@RequestParam("userId") Long userId, @RequestParam("quizId") Long quizId, Model model){
        UserAndQuizzesEntity userAndQuiz = userAndQuizService.findById(quizId);
        model.addAttribute("results", historyResultService.findAllByIdUserAndQuiz(quizId));
        model.addAttribute("quizInfo", userAndQuiz);

        return "showResult";
    }

    @PostMapping("/saveResults")
    public String saveResults(@ModelAttribute("questions") QuizModel resultForm, Model model){
        UserEntity user = userService.findById(resultForm.getUserId());
        QuizEntity quiz = quizService.findById(resultForm.getQuizId());
        List<ChildQuizModel> childQuizzes = resultForm.getChildQuizzes();

        UserAndQuizzesEntity userAndQuiz = UserAndQuizzesEntity.builder()
                .user(user)
                .quiz(quiz)
                .completeDate(LocalDate.now())
                .status("завершен")
                .build();
        userAndQuizService.save(userAndQuiz);

        if (childQuizzes.size() == 1){
            //TODO сделать для опросов, не состоящих из опросов
        }
        else {
            List<HistoryResultsEntity> resultsToModel = new ArrayList<>();
            for (ChildQuizModel childQuiz: childQuizzes) {
                int resultQuiz = 0;
                QuestionEntity questionField;
                for(QuestionAnswersModel question: childQuiz.getQuestions())
                {
                    questionField = questionService.findById(question.getId());
                    UserAndAnswersEntity newUserAnswer = UserAndAnswersEntity.builder()
                            .contentAnswer("")
                            .answer(question.getUserAnswer())
                            .question(questionField)
                            .user(user)
                            .userQuizzes(userAndQuiz)
                            .build();
                    userAnswerService.save(newUserAnswer);
                    resultQuiz += question.getUserAnswer().getWeight();
                }
                List<KeyQuizEntity> keysQuiz = keyQuizService.findAllByQuizId(childQuiz.getId());
                String resultTest = "";
                for(KeyQuizEntity keyQuiz: keysQuiz){
                    if(resultQuiz >= keyQuiz.getMinArg() && resultQuiz <= keyQuiz.getMaxArg()){
                        resultTest = keyQuiz.getResultArg();
                        break;
                    }
                }
                HistoryResultsEntity newResult = HistoryResultsEntity.builder()
                        .result(resultTest)
                        .user(user)
                        .userQuiz(userAndQuiz)
                        .childrenQuiz(quizService.findById(childQuiz.getId()))
                        .build();
                historyResultService.save(newResult);
                resultsToModel.add(newResult);
            }
            model.addAttribute("results", resultsToModel);
        }
        model.addAttribute("quizInfo", userAndQuiz);
        return "showResult";
    }

    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model){
        model.addAttribute("result", userAndQuizService.findById(id));
        return "showResult";
    }
}
