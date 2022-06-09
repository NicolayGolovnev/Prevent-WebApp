package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndAnswersEntity;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAndAnswersEntity, Long> {
    List<UserAndAnswersEntity> findAllByUserQuizzes_Id(Long quizId);
}
