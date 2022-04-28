package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAnswersEntity;
import ru.prevent.repository.UserAnswerRepository;

@Service
public class UserAnswerService {

    @Autowired
    UserAnswerRepository usersAnswerRepository;

    public void save(UserAnswersEntity userAnswer){
        usersAnswerRepository.save(userAnswer);
    }
}
