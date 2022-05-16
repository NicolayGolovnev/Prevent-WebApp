package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuizAndQuizEntity;

public interface QuizAndQuizRepository extends JpaRepository<QuizAndQuizEntity, Long> {
}
