package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAnswersEntity;

public interface UserAnswerRepository extends JpaRepository<UserAnswersEntity, Long> {
}
