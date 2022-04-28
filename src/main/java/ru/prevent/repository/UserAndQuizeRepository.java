package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserQuizzesEntity;

import java.util.Optional;

public interface UserAndQuizeRepository extends JpaRepository<UserQuizzesEntity, Long> {

    Optional<UserQuizzesEntity> findByUser_IdAndQuiz_Id(Long idUser, Long idQuiz);
}
