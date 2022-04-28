package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.QuestionEntity;
import ru.prevent.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public List<QuestionEntity> findQuestionsByQuizId(Long id) { return repository.findAllByQuiz_Id(id);}

    @Transactional(readOnly = true)
    public QuestionEntity findById(Long id){
        Optional<QuestionEntity> optional = repository.findQuestionById(id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new RuntimeException("Question with id=" + id + " not found!");
    }


}
