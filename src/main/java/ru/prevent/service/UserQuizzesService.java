package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserQuizzes;
import ru.prevent.repository.UserQuizzesRepository;

@Service
public class UserQuizzesService {

    @Autowired
    UserQuizzesRepository userQuizzesRepository;

    public void save(UserQuizzes userQuizzes){
        userQuizzesRepository.save(userQuizzes);
    }
}
