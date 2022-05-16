package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.KeyQuizEntity;
import ru.prevent.repository.KeyQuizRepository;

import java.util.List;

@Service
public class KeyQuizService {

    @Autowired
    KeyQuizRepository repository;

    public List<KeyQuizEntity> findAllByQuizId(Long quizId){
        return repository.findAllByQuiz_Id(quizId);
    }

}
