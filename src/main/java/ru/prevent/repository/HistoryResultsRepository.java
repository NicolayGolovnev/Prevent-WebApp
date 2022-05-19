package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.HistoryResultsEntity;

import java.util.List;

public interface HistoryResultsRepository extends JpaRepository<HistoryResultsEntity, Long> {

    public List<HistoryResultsEntity> findAllByUserQuiz_Id(Long id);
}
