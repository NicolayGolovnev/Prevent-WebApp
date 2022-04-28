package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserQuizzesEntity;
import ru.prevent.repository.UserQuizzesRepository;

@Service
public class UserQuizzesService {

    @Autowired
    UserQuizzesRepository userQuizzesRepository;

    public void save(UserQuizzesEntity userQuizzes){
        userQuizzesRepository.save(userQuizzes);
    }

    public UserQuizzesEntity findById(Long id){
        return userQuizzesRepository.findById(id).orElse(null);
    }
}
