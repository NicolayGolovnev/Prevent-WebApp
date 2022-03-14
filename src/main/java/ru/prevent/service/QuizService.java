package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.Quiz;
import ru.prevent.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;

    public List<Quiz> findAll() {
        return repository.findAll();
    }

    public Quiz findById(Long id) {
        Optional<Quiz> optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new RuntimeException("Quiz with id=" + id + " not found!");
    }
}
