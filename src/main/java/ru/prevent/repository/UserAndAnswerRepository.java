package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndAnswersEntity;

import java.util.List;

public interface UserAndAnswerRepository extends JpaRepository<UserAndAnswersEntity, Long> {
    List<UserAndAnswersEntity> findAllByUserQuizzes_Id(Long quizId);
}
