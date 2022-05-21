package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.repository.QuizRepository;
import ru.prevent.repository.UserAndQuizRepository;
import ru.prevent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAndQuizService {
    @Autowired
    UserAndQuizRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRepository quizRepository;

    public UserAndQuizzesEntity findQuizResult(Long idUser, Long idQuiz) {
        return repository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow();
    }

    public UserAndQuizzesEntity findById(Long id) {
        Optional<UserAndQuizzesEntity> optionalUserAndQuiz = repository.findById(id);
        if (optionalUserAndQuiz.isPresent())
            return optionalUserAndQuiz.get();
        else
            throw new RuntimeException("Record quiz-user[id=" + id + "] not found!");
    }

    public List<UserAndQuizzesEntity> findAllOpenAndAssignedQuizzesByUserId(Long userId) {
        List<UserAndQuizzesEntity> opened = repository.findByUser_IdAndStatus(userId, "открытый");
        List<UserAndQuizzesEntity> assigned = repository.findByUser_IdAndStatus(userId, "назначен");
        opened.addAll(assigned);

        return opened;
    }

    public void save(UserAndQuizzesEntity entity){
        repository.save(entity);}
}
