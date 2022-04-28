package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndQuizzesEntity;

import java.util.Optional;

public interface UserAndQuizeRepository extends JpaRepository<UserAndQuizzesEntity, Long> {

    Optional<UserAndQuizzesEntity> findByUser_IdAndQuiz_Id(Long idUser, Long idQuiz);
}
