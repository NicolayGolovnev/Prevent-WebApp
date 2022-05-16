package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.HistoryResultsEntity;
import ru.prevent.repository.HistoryResultsRepository;

@Service
public class HistoryResultService {

    @Autowired
    HistoryResultsRepository repository;

    public void save(HistoryResultsEntity entity){repository.save(entity);}
}
