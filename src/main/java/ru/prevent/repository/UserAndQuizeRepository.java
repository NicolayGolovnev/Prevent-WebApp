package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.Quiz;
import ru.prevent.entity.User;
import ru.prevent.entity.UserQuizzes;

import java.util.Optional;

public interface UserAndQuizeRepository extends JpaRepository<UserQuizzes, Long> {

    Optional<UserQuizzes> findByUser_IdAndQuiz_Id(Long idUser, Long idQuiz);
}
