package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.QuizAndQuizEntity;
import ru.prevent.entity.QuizEntity;
import ru.prevent.repository.QuizAndQuizRepository;

import java.util.List;

@Service
public class QuizAndQuizService {

    @Autowired
    QuizAndQuizRepository repository;

    @Autowired
    QuizService quizService;

    public List<QuizAndQuizEntity> findAllChildTests(Long quizId) {
        List<QuizAndQuizEntity> childQuizzes = repository.findAllByParentQuiz_Id(quizId);
        if (childQuizzes.isEmpty()) {
            QuizEntity quiz = quizService.findById(quizId);
            childQuizzes.add(QuizAndQuizEntity.builder()
                    .parentQuiz(quiz)
                    .childQuiz(quiz)
                    .build());
        }
        return childQuizzes;
    }

}
