package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserQuizzesEntity;
import ru.prevent.repository.QuizRepository;
import ru.prevent.repository.UserAndQuizeRepository;
import ru.prevent.repository.UserRepository;

@Service
public class UserAndQuizService {
    @Autowired
    UserAndQuizeRepository userAndQuizeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRepository quizRepository;

    public UserQuizzesEntity findQuizResult(Long idUser, Long idQuiz){
        return userAndQuizeRepository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow();
    }
}
