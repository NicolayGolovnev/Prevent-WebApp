package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAndAnswersEntity;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.repository.QuizRepository;
import ru.prevent.repository.UserAndQuizeRepository;
import ru.prevent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAndQuizService {
    @Autowired
    UserAndQuizeRepository userAndQuizeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRepository quizRepository;

    public UserAndQuizzesEntity findQuizResult(Long idUser, Long idQuiz){
        return userAndQuizeRepository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow();
    }

    public List<UserAndQuizzesEntity> findQuizzesByUserId(Long idUser){
        return userAndQuizeRepository.findAllByUserId(idUser);
    }

    public String findQuizResult(Long id){
        UserAndQuizzesEntity quiz = userAndQuizeRepository.findById(id).get();
        return quiz.getResult();
    }

    public UserAndQuizzesEntity findById(Long id){
        Optional<UserAndQuizzesEntity> quiz = userAndQuizeRepository.findById(id);
        if(quiz.isPresent())
            return quiz.get();
        else
            throw new RuntimeException("Record with id=" + id + " not found!");
    }
}
