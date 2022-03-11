package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
