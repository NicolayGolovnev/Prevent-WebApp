package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.AnswerEntity;
import ru.prevent.repository.AnswerRepository;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository repository;

    public void save(AnswerEntity answer) {
        repository.save(answer);
    }

    public void saveAll(List<AnswerEntity> answers) {
        repository.saveAll(answers);
    }
}
