package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.HistoryResultsEntity;
import ru.prevent.repository.HistoryResultsRepository;

import java.util.List;

@Service
public class HistoryResultService {
    @Autowired
    private HistoryResultsRepository repository;

    @Transactional
    public void save(HistoryResultsEntity entity) {
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<HistoryResultsEntity> findAllByIdUserAndQuiz(Long id) {
        return repository.findAllByUserQuiz_Id(id);
    }
}
