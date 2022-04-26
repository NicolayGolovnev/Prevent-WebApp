package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAnswers;

public interface UserAnswerRepository extends JpaRepository<UserAnswers, Long> {
}
