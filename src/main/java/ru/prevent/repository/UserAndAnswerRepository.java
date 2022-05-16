package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndAnswersEntity;

public interface UserAndAnswerRepository extends JpaRepository<UserAndAnswersEntity, Long> {
}
