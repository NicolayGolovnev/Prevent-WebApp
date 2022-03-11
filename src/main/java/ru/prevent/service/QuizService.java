package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.Quiz;
import ru.prevent.repository.QuizRepository;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;

    public List<Quiz> findAll() {
        return repository.findAll();
    }
}
