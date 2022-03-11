package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
