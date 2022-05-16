package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
}
