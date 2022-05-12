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
    UserQuizzesService userQuizzesService;

    @Autowired
    UserAnswerService userAnswerService;

    @Autowired
    UserAndQuizService userAndQuizService;

    @Autowired
    QuizAndQuizService quizAndQuizService;

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


        //  пытаюсь сделать свое
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
            quizModel.add(childQuizModel);
        }

        //TODO проверка имеет ли опросник опросников детей
        /*List<QuizAndQuizEntity> childQuizzes = quizAndQuizService.findAllChildTests(quizId);
        QAModelCreation questions = new QAModelCreation();
        for (QuizAndQuizEntity childQuiz: childQuizzes) {
            List<QuestionEntity> entityQuestions = questionService.findQuestionsByQuizId(childQuiz.getChildQuiz().getId());
            for (var question : entityQuestions) {
                QuestionAnswersModel buf = QuestionAnswersModel.builder()
                        .id(question.getId())
                        .question(question)
                        .answers(question.getAnswers())
                        .userAnswer(new AnswerEntity())
                        .build();
                questions.add(buf);
            }
        }*/

        //model.addAttribute("questions", questions);
        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        model.addAttribute("test", quizModel);

        //model.addAttribute("quizzesTitles", quizzesTitles);
        return "/test/quiz";
    }

    @GetMapping("/showCompleteTest")
    public String showCompleteTest(@RequestParam("userId") Long userId, @RequestParam("quizId") Long quizId, Model model){
        List<UserAndAnswersEntity> userAnswers = userAnswerService.findAllByQuizId(quizId);

        Map<String, String> questions = new HashMap<>();
        String tmpAnswer;
        for (UserAndAnswersEntity answer: userAnswers) {
            if(answer.getContentAnswer().length() == 0)
                tmpAnswer = answer.getAnswer().getContent();
            else
                tmpAnswer = answer.getContentAnswer();
            questions.put(answer.getQuestion().getContent(), tmpAnswer);
        }
        UserAndQuizzesEntity quiz = userAndQuizService.findById(quizId);
        ShowCompleteTestModel test = ShowCompleteTestModel.builder()
                .questionsAndAnswers(questions)
                .result(quiz.getResult())
                .dateOfFinish(quiz.getCompleteDate().toString())
                .title(quiz.getQuiz().getTitle())
                .build();

        model.addAttribute("test", test);
        return "showTest";
    }

    @PostMapping("/saveResults")
    public String saveResults(@ModelAttribute("questions") QuizModel resultForm, Model model){
        System.out.println();
        /*List<QuestionAnswersModel> answeredQuestions = resultForm.getQuestions();
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

        return "redirect:/showResult/" + newUserQuiz.getId();*/
        return "";
    }

    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model){
        model.addAttribute("result", userQuizzesService.findById(id));
        return "showResult";
    }
}
