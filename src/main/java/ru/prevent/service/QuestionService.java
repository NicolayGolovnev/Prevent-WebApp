package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.QuestionEntity;
import ru.prevent.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public List<QuestionEntity> findQuestionsByQuizId(Long id) { return repository.findAllByQuiz_Id(id);}

    public void save(QuestionEntity question) {
        repository.save(question);
    }

    public void saveAll(List<QuestionEntity> questions) {
        repository.saveAll(questions);
    }
}
