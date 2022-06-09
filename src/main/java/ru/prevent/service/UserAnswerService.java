package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.UserAndAnswersEntity;
import ru.prevent.repository.UserAndAnswerRepository;

import java.util.List;

@Service
public class UserAnswerService {

    @Autowired
    private UserAndAnswerRepository repository;

    @Transactional
    public void save(UserAndAnswersEntity userAnswer) {
        repository.save(userAnswer);
    }

    @Transactional(readOnly = true)
    public List<UserAndAnswersEntity> findAllByQuizId(Long quizId) {
        return repository.findAllByUserQuizzes_Id(quizId);
    }
}
