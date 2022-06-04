package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.repository.QuizRepository;
import ru.prevent.repository.UserAndQuizRepository;
import ru.prevent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAndQuizService {
    @Autowired
    private UserAndQuizRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public UserAndQuizzesEntity findQuizResult(Long idUser, Long idQuiz) {
        return repository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<UserAndQuizzesEntity> findCompletedQuizzesByUserId(Long idUser) {
        return repository.findByUser_IdAndStatus(idUser, "завершен");
    }

    @Transactional(readOnly = true)
    public UserAndQuizzesEntity findById(Long id) {
        Optional<UserAndQuizzesEntity> optionalUserAndQuiz = repository.findById(id);
        if (optionalUserAndQuiz.isPresent())
            return optionalUserAndQuiz.get();
        else
            throw new ObjectNotFoundException("Record [id=" + id + "] not found!");
    }

    @Transactional(readOnly = true)
    public List<UserAndQuizzesEntity> findAllOpenQuizzesByUserId(Long userId) {
        return repository.findByUser_IdAndStatus(userId, "открытый");
    }

    @Transactional(readOnly = true)
    public List<UserAndQuizzesEntity> findAllAppointedQuizzesByUserId(Long userId) {
        return repository.findByUser_IdAndStatus(userId, "назначен");
    }

    @Transactional
    public void save(UserAndQuizzesEntity entity) {
        repository.save(entity);
    }
}
