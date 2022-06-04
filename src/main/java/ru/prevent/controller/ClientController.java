package ru.prevent.controller;

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

    @GetMapping("/lk/{userId}")
    public String loadUserPage(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));

        List<UserAndQuizzesEntity> openQuizzes = userAndQuizService.findAllAppointedQuizzesByUserId(userId);
        openQuizzes.addAll(userAndQuizService.findAllOpenQuizzesByUserId(userId));
        List<QuizEntity> quizzes = new ArrayList<>();
        for (UserAndQuizzesEntity test: openQuizzes) {
            quizzes.add(test.getQuiz());
        }
        model.addAttribute("quizzes", quizzes);

        UserNQuizModel userNQuizModel = new UserNQuizModel();
        userNQuizModel.setUserId(userId);

        model.addAttribute("uqModel", userNQuizModel);
        model.addAttribute("completedQuizzes", userAndQuizService.findCompletedQuizzesByUserId(userId));
        return "user/userPage";
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

        int countQuiz = childQuizzes.size();
        int[] countQuestionInBlocks = new int[countQuiz];
        for (int i = 0; i < countQuiz; i++)
            countQuestionInBlocks[i] = quizModel.getChildQuizzes().get(i).getQuestions().size();
        model.addAttribute("countBlocks", countQuiz);
        model.addAttribute("countQuestionsInBlocks", countQuestionInBlocks);
        model.addAttribute("user", user);
        model.addAttribute("quiz", quiz);
        model.addAttribute("test", quizModel);

        return "user/quiz";
    }

    @GetMapping("/showCompleteTest")
    public String showCompleteTest(@RequestParam("userId") Long userId, @RequestParam("quizId") Long quizId, Model model){
        UserAndQuizzesEntity userAndQuiz = userAndQuizService.findById(quizId);
        model.addAttribute("results", historyResultService.findAllByIdUserAndQuiz(quizId));
        model.addAttribute("quizInfo", userAndQuiz);

        return "user/showResult";
    }

    private HistoryResultsEntity saveBlock(UserEntity user, UserAndQuizzesEntity userAndQuiz, ChildQuizModel childQuiz)
    {
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
                .build();
        historyResultService.save(newResult);
        return newResult;
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

        List<HistoryResultsEntity> resultsToModel = new ArrayList<>();
        if (childQuizzes.size() == 1 && (childQuizzes.get(0).getId().equals(resultForm.getQuizId()))){
            HistoryResultsEntity result = saveBlock(user, userAndQuiz, childQuizzes.get(0));
            historyResultService.save(result);
            resultsToModel.add(result);
        }
        else {
            for (ChildQuizModel childQuiz: childQuizzes) {
                /*int resultQuiz = 0;
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
                resultsToModel.add(newResult);*/
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

    @GetMapping("/showResult/{id}")
    public String showResult(@PathVariable Long id, Model model){
        model.addAttribute("result", userAndQuizService.findById(id));
        return "user/showResult";
    }
}
