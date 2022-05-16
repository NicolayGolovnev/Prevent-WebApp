package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.HistoryResultsEntity;

public interface HistoryResultsRepository extends JpaRepository<HistoryResultsEntity, Long> {
}
