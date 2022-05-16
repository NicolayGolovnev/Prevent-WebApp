package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuizEntity;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findAllByAccessIsTrue();
}
