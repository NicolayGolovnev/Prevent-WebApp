package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
