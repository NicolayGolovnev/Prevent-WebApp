package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAndAnswersEntity;
import ru.prevent.repository.UserAnswerRepository;

import java.util.List;

@Service
public class UserAnswerService {

    @Autowired
    UserAnswerRepository userAnswersRepository;

    public void save(UserAndAnswersEntity userAnswer){
        userAnswersRepository.save(userAnswer);
    }
    public List<UserAndAnswersEntity> findAllByQuizId(Long quizId){return userAnswersRepository.findAllByUserQuizzes_Id(quizId);}
}
