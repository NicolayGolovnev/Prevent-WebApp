package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.Question;
import ru.prevent.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public List<Question> findQuestionsByQuizId(Long id) { return repository.findAllByQuiz_Id(id);}
}
