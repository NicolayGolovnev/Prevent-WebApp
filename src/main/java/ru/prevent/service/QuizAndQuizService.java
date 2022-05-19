package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.QuizAndQuizEntity;
import ru.prevent.repository.QuizAndQuizRepository;

import java.util.List;

@Service
public class QuizAndQuizService {

    @Autowired
    QuizAndQuizRepository repository;

    public List<QuizAndQuizEntity> findAllChildTests(Long quizId){
        return repository.findAllByParentQuiz_Id(quizId);
    }

}
