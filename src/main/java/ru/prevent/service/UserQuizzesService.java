package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.repository.UserQuizzesRepository;

@Service
public class UserQuizzesService {

    @Autowired
    UserQuizzesRepository userQuizzesRepository;

    public void save(UserAndQuizzesEntity userQuizzes){
        userQuizzesRepository.save(userQuizzes);
    }

    public UserAndQuizzesEntity findById(Long id){
        return userQuizzesRepository.findById(id).orElse(null);
    }
}
