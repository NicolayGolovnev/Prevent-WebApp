package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAndAnswersEntity;
import ru.prevent.repository.UserAndAnswerRepository;

import java.util.List;

@Service
public class UserAnswerService {

    @Autowired
    UserAndAnswerRepository repository;

    public void save(UserAndAnswersEntity userAnswer){
        repository.save(userAnswer);
    }
    public List<UserAndAnswersEntity> findAllByQuizId(Long quizId){return repository.findAllByUserQuizzes_Id(quizId);}
}
