package ru.prevent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prevent.entity.*;
import ru.prevent.model.ChildQuizModel;
import ru.prevent.model.QuestionAnswersModel;
import ru.prevent.model.QuizModel;
import ru.prevent.model.UserNQuizModel;
import ru.prevent.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(tags = "Контроллер конечного пользователя (клиента)")
@Slf4j
public class ClientController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserAnswerService userAnswerService;

    @Autowired
    private UserAndQuizService userAndQuizService;

    @Autowired
    private QuizAndQuizService quizAndQuizService;

    @Autowired
    private KeyQuizService keyQuizService;

    @Autowired
    private HistoryResultService historyResultService;

    @ApiOperation(value = "Загрузка главной страницы пользователя по уникальному идентификатору")
    @GetMapping("/")
    public String loadUserPage(@AuthenticationPrincipal UserDetails user, Model model) {
        Long userId = userService.findByUsername(user.getUsername()).getId();
        model.addAttribute("user", userService.findById(userId));

        List<UserAndQuizzesEntity> openQuizzes = userAndQuizService.findAllAppointedQuizzesByUserId(userId);
        openQuizzes.addAll(userAndQuizService.findAllOpenQuizzesByUserId(userId));
        /*List<QuizEntity> quizzes = new ArrayList<>();
        for (UserAndQuizzesEntity test: openQuizzes) {
            quizzes.add(test.getQuiz());
        }
        model.addAttribute("quizzes", quizzes);*/
        model.addAttribute("quizzes", openQuizzes);

        UserNQuizModel userNQuizModel = new UserNQuizModel();
        userNQuizModel.setUserId(userId);

        model.addAttribute("uqModel", userNQuizModel);
        model.addAttribute("completedQuizzes", userAndQuizService.findCompletedQuizzesByUserId(userId));
        return "user/userPage";
    }

    @ApiOperation(value = "Загрузка страницы прохождения опроса для пользователя")
    @GetMapping("/loadQuizByUser")
    public String loadQuiz(@RequestParam("id") Long id, Model model) {
        UserAndQuizzesEntity record = userAndQuizService.findById(id);
        UserEntity user = record.getUser();
        QuizEntity quiz = record.getQuiz();

        List<QuizAndQuizEntity> childQuizzes = quizAndQuizService.findAllChildTests(quiz.getId());
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
                        .weight(question.getWeight())
                        .build();
                childQuizModel.add(buf);
            }

            childQuizModel.setTittle(childQuiz.getChildQuiz().getTitle());
            childQuizModel.setId(childQuiz.getChildQuiz().getId());
            childQuizModel.setWeight(childQuiz.getChildQuiz().getWeight());
            quizModel.add(childQuizModel);
        }

        if(childQuizzes.size() == 1 &&
                (childQuizzes.get(0).getParentQuiz().getId().equals(childQuizzes.get(0).getChildQuiz().getId()))){
            quizModel.getChildQuizzes().get(0).setTittle("");
        }

        quizModel.setRecordId(record.getId());

        int countQuiz = childQuizzes.size();
        int[] countQuestionInBlocks = new int[countQuiz];
        for (int i = 0; i < countQuiz; i++)
            countQuestionInBlocks[i] = quizModel.getChildQuizzes().get(i).getQuestions().size();
        model.addAttribute("countBlocks", countQuiz);
        model.addAttribute("countQuestionsInBlocks", countQuestionInBlocks);
        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        model.addAttribute("userNQuiz", record);
        model.addAttribute("test", quizModel);

        return "user/quiz";
    }

    @ApiOperation(value = "Загрузка страницы просмотра результата опроса для пользователя")
    @GetMapping("/showCompleteTest")
    public String showCompleteTest(@RequestParam("userId") Long userId, @RequestParam("quizId") Long quizId, Model model){
        UserAndQuizzesEntity userAndQuiz = userAndQuizService.findById(quizId);
        model.addAttribute("results", historyResultService.findAllByIdUserAndQuiz(quizId));
        model.addAttribute("quizInfo", userAndQuiz);

        return "user/showResult";
    }

    private HistoryResultsEntity saveBlock(UserEntity user, UserAndQuizzesEntity userAndQuiz, ChildQuizModel childQuiz) {
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
                    .userQuiz(userAndQuiz)
                    .build();
            userAnswerService.save(newUserAnswer);
            resultQuiz += question.getUserAnswer().getWeight() * question.getWeight();
        }
        resultQuiz *= childQuiz.getWeight();
        List<KeyQuizEntity> keysQuiz = keyQuizService.findAllByQuizId(childQuiz.getId());
        String resultTest = "";
        for (KeyQuizEntity keyQuiz : keysQuiz) {
            if (resultQuiz >= keyQuiz.getMinArg() && resultQuiz <= keyQuiz.getMaxArg()) {
                resultTest = keyQuiz.getResultArg();
                break;
            }
        }
        HistoryResultsEntity newResult = HistoryResultsEntity.builder()
                .result(resultTest)
                .user(user)
                .userQuiz(userAndQuiz)
                .build();
        historyResultService.save(newResult);
        return newResult;
    }

    @ApiOperation(value = "Операция сохранение и подсчет баллов результатов опроса")
    @PostMapping("/saveResults")
    public String saveResults(@ModelAttribute("questions") QuizModel resultForm, Model model) {
        UserEntity user = userService.findById(resultForm.getUserId());
        QuizEntity quiz = quizService.findById(resultForm.getQuizId());
        List<ChildQuizModel> childQuizzes = resultForm.getChildQuizzes();

        UserAndQuizzesEntity userAndQuiz = userAndQuizService.findByUserAndQuizId(user.getId(), quiz.getId());
        if (userAndQuiz.getStatus().equals("назначен")){
            userAndQuiz.setStatus("завершен");
            userAndQuiz.setCompleteDate(LocalDate.now());
        }
        else{
            userAndQuiz = UserAndQuizzesEntity.builder()
                    .user(user)
                    .quiz(quiz)
                    .completeDate(LocalDate.now())
                    .status("завершен")
                    .build();
        }
        userAndQuizService.save(userAndQuiz);

        List<HistoryResultsEntity> resultsToModel = new ArrayList<>();
        if (childQuizzes.size() == 1 && (childQuizzes.get(0).getId().equals(resultForm.getQuizId()))){
            HistoryResultsEntity result = saveBlock(user, userAndQuiz, childQuizzes.get(0));
            historyResultService.save(result);
            resultsToModel.add(result);
        }
        else {
            for (ChildQuizModel childQuiz: childQuizzes) {
                HistoryResultsEntity resultBlock = saveBlock(user, userAndQuiz, childQuiz);
                resultBlock.setChildrenQuiz(quizService.findById(childQuiz.getId()));
                historyResultService.save(resultBlock);
                resultsToModel.add(resultBlock);
            }
        }
        model.addAttribute("results", resultsToModel);
        model.addAttribute("quizInfo", userAndQuiz);
        return "user/showResult";
    }


    @ApiOperation(value = "", hidden = true)
    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model) {
        model.addAttribute("result", userAndQuizService.findById(id));
        return "user/showResult";
    }
}
