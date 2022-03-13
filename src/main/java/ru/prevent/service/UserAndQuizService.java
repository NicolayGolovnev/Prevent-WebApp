package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.Quiz;
import ru.prevent.entity.User;
import ru.prevent.entity.UserQuizzes;
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

    public UserQuizzes findQuizResult(Long idUser, Long idQuiz){
        return userAndQuizeRepository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow();
    }
}
