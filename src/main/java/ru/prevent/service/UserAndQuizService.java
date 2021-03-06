package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.repository.UserAndQuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAndQuizService {
    @Autowired
    private UserAndQuizRepository repository;

    @Transactional(readOnly = true)
    public UserAndQuizzesEntity findQuizResult(Long idUser, Long idQuiz) {
        return repository.findByUser_IdAndQuiz_Id(idUser, idQuiz).orElseThrow(
                () -> new ObjectNotFoundException("Record [userId=" + idUser + ", quizId=" + idQuiz + "] not found!"));
    }

    @Transactional(readOnly = true)
    public List<UserAndQuizzesEntity> findAllByUserIdAndQuizId(Long userId, Long quizId) {
        return repository.findAllByUserIdAndQuizId(userId, quizId);
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

    public UserAndQuizzesEntity findByUserAndQuizId(Long userId, Long quizId){
        return repository.findByUser_IdAndQuiz_Id(userId, quizId).orElseThrow();
    }
}
