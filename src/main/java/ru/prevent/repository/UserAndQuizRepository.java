package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndQuizzesEntity;

import java.util.List;
import java.util.Optional;

public interface UserAndQuizRepository extends JpaRepository<UserAndQuizzesEntity, Long> {
    Optional<UserAndQuizzesEntity> findByUser_IdAndQuiz_Id(Long idUser, Long idQuiz);

    List<UserAndQuizzesEntity> findByUser_IdAndStatus(Long idUser, String status);

    List<UserAndQuizzesEntity> findAllByUserIdAndQuizId(Long userId, Long quizId);
}
