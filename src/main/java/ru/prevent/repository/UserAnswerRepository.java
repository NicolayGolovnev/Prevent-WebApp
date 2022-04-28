package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndAnswersEntity;

public interface UserAnswerRepository extends JpaRepository<UserAndAnswersEntity, Long> {
}
